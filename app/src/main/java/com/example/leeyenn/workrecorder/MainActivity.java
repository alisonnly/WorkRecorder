package com.example.leeyenn.workrecorder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Variable declaration
    Button Record, Calendar;
    final private int REQUEST_FINE_LOCATION_ASK_PERMISSIONS = 1;
    final private int REQUEST_NETWORK_ASK_PERMISSIONS = 2;
    final private int REQUEST_COARSE_ASK_PERMISSIONS = 3;
    final private int REQUEST_INTERNET_ASK_PERMISSIONS = 4;
    private int PERMISSION_ALL = 1;
    private String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        //Find the UI elements
        Record = (Button)findViewById(R.id.btnRecord);
        Calendar = (Button)findViewById(R.id.btnCalendar);

        //Set listener for all buttons
        Record.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Create intent to redirect to another page

                //Redirect to Record Page
                Intent recordIntent = new Intent(MainActivity.this, RecordPage.class);
                startActivity(recordIntent);
            }
        });

        Calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent calendarIntent = new Intent(MainActivity.this, CalendarPage.class);
                startActivity(calendarIntent);
            }
            //Redirect to Calculate Page
        });


    }

    //Check for permission
    public static boolean hasPermissions(Context context, String[] permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //Get user permission
    private void requestPermission() {
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

//    }
}
