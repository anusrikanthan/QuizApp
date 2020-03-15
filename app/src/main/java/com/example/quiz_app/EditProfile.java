package com.example.quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    TextView t1,t2,t3,t4;
    EditText t5;
    Button b1;
    dbHelper dh;
    Pattern p = Pattern.compile("[7-9][0-9]{9}");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        t1=findViewById(R.id.namedisp1);
        t2=findViewById(R.id.emaildisp1);
        t3=findViewById(R.id.degreedisp1);
        getSupportActionBar().hide();
        t4=findViewById(R.id.branchdisp1);
        t5=findViewById(R.id.phonedisp1);
        SharedPreferences sp = getSharedPreferences("mycredentials",
                Context.MODE_PRIVATE);
        String EMAIL = sp.getString("Email","NA");
        Log.d("Bl",EMAIL);
         dh=new dbHelper(this);
        Cursor c=dh.getData(EMAIL);
        if(c.moveToFirst())
        {
            String email,name,phone,branch,degree;
            email=c.getString(0);
            name=c.getString(1);
            phone=c.getString(2);
            branch=c.getString(4);
            degree=c.getString(3);
            t1.setText(name);
            t2.setText(email);
            t3.setText(degree);
            t4.setText(branch);

        }
        b1=findViewById(R.id.update);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          if(v.getId()==b1.getId())
          {

                  Matcher m = p.matcher(t5.getText().toString());
                  if(m.find() && m.group().equals(t5.getText().toString()))
                  {
                      dh.updatePhone(t2.getText().toString(),t5.getText().toString());
                      Intent i=new Intent(this,Signup.class);
                      startActivity(i);
                  }
                  else
                  {
                      AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                      // Setting Dialog Title
                      alertDialog.setTitle("Invalid PhoneNumber");
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
