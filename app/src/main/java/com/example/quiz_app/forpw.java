package com.example.quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class forpw extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    EditText t1,t2;
    dbHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forpw);
      t1=findViewById(R.id.newpass);
      t2=findViewById(R.id.repass);
        getSupportActionBar().hide();
      b1=findViewById(R.id.change);
      b1.setOnClickListener(this);
      dh=new dbHelper(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==b1.getId())
        {
            if(t1.getText().toString().equals(t2.getText().toString()) &&(!(t1.getText().toString().equals("") && t2.getText().toString().equals(""))))
            {
                SharedPreferences sp = getSharedPreferences("mycredentials",
                        Context.MODE_PRIVATE);
                String EMAIL = sp.getString("Email","NA");
                dh.updatePassword(EMAIL,t2.getText().toString());
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
            }
            else
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                // Setting Dialog Title
                alertDialog.setTitle("Enter Proper Password");
                // Setting Dialog Message

                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }
        }

    }
}
