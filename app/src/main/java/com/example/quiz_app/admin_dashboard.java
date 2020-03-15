package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class admin_dashboard extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Spinner s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportActionBar().hide();
        b1=findViewById(R.id.addclass);
        b1.setOnClickListener(this);


        s1=(Spinner)findViewById(R.id.assignclass);

    }

    @Override
    public void onClick(View v)
    {
         if(v.getId()==b1.getId())
         {
             Intent i=new Intent(this,addClass.class);
             startActivity(i);
         }
    }
}
