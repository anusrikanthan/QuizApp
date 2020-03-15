package com.example.quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView t1;
    EditText et1,et2;
    Button b1,b2;
    dbHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
       t1=findViewById(R.id.fpw);
      t1.setText(R.string.your_string);
      et1=findViewById(R.id.email);
      et2=findViewById(R.id.password);
      t1.setOnClickListener(this);
      b1=findViewById(R.id.login);

      b1.setOnClickListener(this);

     dh=new dbHelper(this);

      dh.insertvalues();


    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==t1.getId())
        {
            if(!(et1.getText().toString().equals("")))
            {
                if(dh.getifValidEmail(et1.getText().toString()))
                {
                    Intent i=new Intent(this,forpw.class);
                    startActivity(i);
                }
                else
                {
                       AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    // Setting Dialog Title
                    alertDialog.setTitle("No Such Account  Exist");
                    // Setting Dialog Message

                    alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog.show();
                }
            }
            else
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                // Setting Dialog Title
                alertDialog.setTitle("No Such Account Exist");
                // Setting Dialog Message

                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }

        }
        else if(b1.getId()==v.getId())
        {
            boolean t1=dh.login(et1.getText().toString(),et2.getText().toString());
            String a;
            if(t1)
            {
                a="T";
            }
            else {
                a = "F";
            }

            if( !(dh.login(et1.getText().toString(),et2.getText().toString())) ||(et1.getText().toString().equals(""))|| (et2.getText().toString().equals("")))
            {
                  if(et1.getText().toString().equals("admin@gmail.com")&&et2.getText().toString().equals("password"))
                  {
                      Intent i=new Intent(this,admin_dashboard.class);
                      startActivity(i);
                  }
                  else
                  {
                      AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                      // Setting Dialog Title
                      alertDialog.setTitle("Invalid Credentials");
                      // Setting Dialog Message

                      alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              et2.setText("");
                          }
                      });

                      alertDialog.show();

                  }


            }
            else
            {  Toast.makeText(getApplicationContext(),"Logging in..",Toast.LENGTH_SHORT).show();

                SharedPreferences sp = getSharedPreferences("mycredentials",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("Email",et1.getText().toString());
                edit.commit();

                Intent i= new Intent(this,Signup.class);
            startActivity(i);
            }
        }

    }
}
;