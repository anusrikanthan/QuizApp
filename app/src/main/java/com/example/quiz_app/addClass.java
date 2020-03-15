package com.example.quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addClass extends AppCompatActivity implements View.OnClickListener {
Button b1;
String CoursePattern;
EditText t1;
EditText t2;
EditText t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        getSupportActionBar().hide();
      b1=findViewById(R.id.Update);
      b1.setOnClickListener(this);
      t1=findViewById(R.id.CourseCode);
      t2=findViewById(R.id.CourseName);
      t3=findViewById(R.id.CourseMentor);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==b1.getId())
        {
             Pattern p = Pattern.compile("[1-9]{2}[A-Z]{3}[0-9]{3}");

             Matcher m=p.matcher(t1.getText().toString());
         String st=t2.getText().toString();
         String st1=t3.getText().toString();
             if(m.find() && st.matches("^[a-zA-Z]*$") && st1.matches("^[a-zA-Z]*$") )
             {
                 Toast.makeText(this,"Course Added",Toast.LENGTH_SHORT).show();
                 Intent i=new Intent(this,admin_dashboard.class);
                 startActivity(i);

             }
             else
             {
                 AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                 // Setting Dialog Title
                 alertDialog.setTitle("Invalid Course Details");
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
