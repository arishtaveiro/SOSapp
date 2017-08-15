package com.example.arisht.sos;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Button send,fin;


    EditText phn1, phn2, phn3;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        phn1 = (EditText) findViewById(R.id.phn1);
        phn2 = (EditText) findViewById(R.id.phn2);
        phn3 = (EditText) findViewById(R.id.phn3);
        fin = (Button) findViewById(R.id.fin);

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //send = (Button) findViewById(R.id.send);


        //databse and tables
        db = openOrCreateDatabase("Emergency", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NUMBER1 (PHONE1 VARCHAR(10), PHONE2 VARCHAR(10));");
        db.execSQL("CREATE TABLE IF NOT EXISTS NUMBER2 (PHONE1 VARCHAR(10), PHONE2 VARCHAR(10));");
        db.execSQL("CREATE TABLE IF NOT EXISTS NUMBER3 (PHONE1 VARCHAR(10), PHONE2 VARCHAR(10));");


        //populating edittext with stored number
        //for 1st number
        Cursor c1;
        int temp1a, temp2a;
        String data1a, data2a;
        data1a = "";
        data2a = "";
        c1 = db.rawQuery("SELECT * FROM NUMBER1", null);
        c1.moveToFirst();


        try {

            temp1a = c1.getInt(0);
            data1a += Integer.toString(temp1a);
            temp2a = c1.getInt(1);
            data2a += Integer.toString(temp2a);

        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "PLEASE STORE A EMERGENCY NUMBER", Toast.LENGTH_SHORT).show();
        }


        if (data1a != null && data2a != null) {
            phn1.setText(data1a + data2a);
        } else {
            Toast.makeText(Main2Activity.this, "NOTHING IN DATABASE", Toast.LENGTH_SHORT).show();
        }

        //for 2nd number
        Cursor c2;
        int temp1b, temp2b;
        String data1b, data2b;
        data1b = "";
        data2b = "";
        c2 = db.rawQuery("SELECT * FROM NUMBER2", null);
        c2.moveToFirst();

        try {

            temp1b = c2.getInt(0);
            data1b += Integer.toString(temp1b);
            temp2b = c2.getInt(1);
            data2b += Integer.toString(temp2b);

        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "NOTHING IN DATABASE", Toast.LENGTH_SHORT).show();
        }


        if (data1b != null && data2b != null) {
            phn2.setText(data1b + data2b);
        } else {
            Toast.makeText(Main2Activity.this, "PLEASE STORE A EMERGENCY NUMBER", Toast.LENGTH_SHORT).show();
        }

        //for 3rd number
        Cursor c3;
        int temp1c, temp2c;
        String data1c, data2c;
        data1c = "";
        data2c = "";
        c3 = db.rawQuery("SELECT * FROM NUMBER3", null);
        c3.moveToFirst();


        try {

            temp1c = c3.getInt(0);
            data1c += Integer.toString(temp1c);
            temp2c = c3.getInt(1);
            data2c += Integer.toString(temp2c);

        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "PLEASE STORE A EMERGENCY NUMBER", Toast.LENGTH_SHORT).show();
        }


        if (data1c != null && data2c != null) {
            phn3.setText(data1c + data2c);
        } else {
            Toast.makeText(Main2Activity.this, "NOTHING IN DATABASE", Toast.LENGTH_SHORT).show();
        }
    }


        //database
        //inserting 1st emergency contact in database

    public void insert1(View v) {

        // phn1 = (EditText) findViewById(R.id.phn1);
        try {
            //overwrites any pre existing number
            db.execSQL("DELETE FROM NUMBER1;");
            String number1 = String.valueOf(phn1.getText()).substring(0, 5);
            String number2 = String.valueOf(phn1.getText()).substring(5);
            if (number1 != null && number2 != null) {
                db.execSQL("INSERT INTO NUMBER1(PHONE1, PHONE2) VALUES(" + number1 + "," + number2 + ");");
                Toast.makeText(Main2Activity.this, "1st NUMBER STORED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Main2Activity.this, "NOTHING IN NUMBER FIELD 1", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "PLEASE ENTER A VALID NUMBER", Toast.LENGTH_SHORT).show();

        }

    }

    //inserting 2nd emergency contact in database
    public void insert2(View v) {

        //  phn2 = (EditText) findViewById(R.id.phn1);
        try {


            //overwrites any pre existing number
            db.execSQL("DELETE FROM NUMBER2;");
            String number1 = String.valueOf(phn2.getText()).substring(0, 5);
            String number2 = String.valueOf(phn2.getText()).substring(5);
            if (number1 != null && number2 != null) {
                db.execSQL("INSERT INTO NUMBER2(PHONE1, PHONE2) VALUES(" + number1 + "," + number2 + ");");
                Toast.makeText(Main2Activity.this, "2nd NUMBER STORED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Main2Activity.this, "NOTHING IN NUMBER FIELD 2", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "PLEASE ENTER A VALID NUMBER", Toast.LENGTH_SHORT).show();

        }

    }

    //inserting 3rd emergency contact in database
    public void insert3(View v) {

        //   phn3 = (EditText) findViewById(R.id.phn3);
        try {
            //overwrites any pre existing number
            db.execSQL("DELETE FROM NUMBER3;");
            String number1 = String.valueOf(phn3.getText()).substring(0, 5);
            String number2 = String.valueOf(phn3.getText()).substring(5);
            Toast.makeText(Main2Activity.this, number1 + "   " + number2, Toast.LENGTH_SHORT).show();

            if (number1 != null && number2 != null) {
                db.execSQL("INSERT INTO NUMBER3(PHONE1, PHONE2) VALUES(" + number1 + "," + number2 + ");");
                Toast.makeText(Main2Activity.this, "3rd NUMBER STORED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Main2Activity.this, "NOTHING IN NUMBER FIELD 3", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(Main2Activity.this, "PLEASE ENTER A VALID NUMBER", Toast.LENGTH_SHORT).show();

        }

    }


    //for deleting records.
    public void delete1(View v) {
        db.execSQL("DELETE FROM NUMBER1;");
        phn1.setText("");
        Toast.makeText(Main2Activity.this, "1st NUMBER DELETED", Toast.LENGTH_SHORT).show();
    }

    //for deleting records.
    public void delete2(View v) {
        db.execSQL("DELETE FROM NUMBER2;");
        phn2.setText("");
        Toast.makeText(Main2Activity.this, "2nd NUMBER DELETED", Toast.LENGTH_SHORT).show();
    }

    //for deleting records.
    public void delete3(View v) {
        db.execSQL("DELETE FROM NUMBER3;");
        phn3.setText("");
        Toast.makeText(Main2Activity.this, "3rd NUMBER DELETED", Toast.LENGTH_SHORT).show();
    }
}






