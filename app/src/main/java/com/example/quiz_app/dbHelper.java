package com.example.quiz_app;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Struct;
import java.util.HashMap;

public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quiz.db";
    public static final String CONTACTS_TABLE_NAME = "student";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_BRANCH = "branch";
    public static final String CONTACTS_COLUMN_DEGREE = "degree";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    public static final String CONTACTS_COLUMN_isFaculty = "isFaculty";
    private HashMap hp;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table student " +
                        "(email text primary key, name text,phone text,degree text,branch text,password text,isFaculty text)"
        );

    }
    public void insertvalues()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", "anupamasrikanthan@gmail.com");
        contentValues.put("name", "Anupama");
        contentValues.put("phone", "8300246038");
        contentValues.put("degree","B-Tech");
        contentValues.put("branch","CSE");


        contentValues.put("password","anu");
        contentValues.put("isFaculty","0");


        db.insert("student", null, contentValues);
        contentValues.put("email", "balaji@ymail.com");
        contentValues.put("name", "Balaji Sundar");
        contentValues.put("phone", "8300246038");
        contentValues.put("degree","B-Tech");
        contentValues.put("branch","CSE");

        contentValues.put("password","balaji2k.");
        contentValues.put("isFaculty","0");

        db.insert("student", null, contentValues);

        contentValues.put("email", "mani@gmail.com");
        contentValues.put("name", "Manigandan");
        contentValues.put("phone", "8300246038");
        contentValues.put("degree","B-Tech");
        contentValues.put("branch","CSE");

        contentValues.put("password","manika");
        contentValues.put("isFaculty","0");

        db.insert("student", null, contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertDetails (String name, String phone, String email, String degree,String branch,String password,String isFaculty)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", degree);
        contentValues.put("place", branch);
        contentValues.put("place", password);
        db.insert("student", null, contentValues);
        return true;
    }
    public Cursor getData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        return c;
    }

    public boolean updatePhone(String email, String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        if (c.moveToFirst()) {
            // Modifying record if found

            db.execSQL("UPDATE student SET phone='" + phone +
                    "' WHERE email='" + email + "'");
            return true;
        }
        else {
            return false;
        }

    }
    public boolean getifValidEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        if (c.moveToFirst()) {
            // Modifying record if found


            return true;
        }
        else {
            return false;
        }

    }

    public boolean updatePassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        if (c.moveToFirst()) {
            // Modifying record if found

            db.execSQL("UPDATE student SET password='" + password +
                    "' WHERE email='" + email + "'");
            return true;
        }
        else {
            return false;
        }

    }

    public boolean login(String email, String pwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        String pass = "";
        if (c.moveToFirst()) {
            pass = c.getString(5);
            Log.d("Editable", "append: " + pwd);

        }

        if (pwd.equals(pass)) {
            Log.d("Editable", "append: " + pass);

            return true;

        }
        else
        {
            return false;
        }

    }

    public String faculty_check(String email,String pwd)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String fac="";
        Cursor c = db.rawQuery("SELECT * FROM student WHERE email='" + email + "'", null);
        Log.d("Editable", "Cursor Length: " + c.moveToFirst());
        if (c.moveToFirst()) {
             fac= c.getString(6);
            Log.d("Editable", "append: " + fac);

        }
        c.close();
        return fac;

    }
}