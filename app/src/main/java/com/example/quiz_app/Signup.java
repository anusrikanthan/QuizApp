package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity implements View.OnClickListener {
TextView t1,t2,t3,t4,t5;
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        t1=findViewById(R.id.namedisp);
        t2=findViewById(R.id.emaildisp);
        t3=findViewById(R.id.degdisp);
        t4=findViewById(R.id.branchdisp);
        t5=findViewById(R.id.phdisp);
        dbHelper dh=new dbHelper(this);
        SharedPreferences sp = getSharedPreferences("mycredentials",
                Context.MODE_PRIVATE);
        String EMAIL = sp.getString("Email","NA");
         Log.d("Bl",EMAIL);
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
            t5.setText(phone);
        }
        b1=findViewById(R.id.editprof);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.logout);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

       if(v.getId()==b1.getId()) {
           Intent i=new Intent(this,EditProfile.class);
           startActivity(i);
       }
        if(v.getId()==b2.getId()) {
            Intent i=new Intent(this,MainActivity  .class);
            startActivity(i);
        }


        }
}
