package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Variable declaration
    Button Record, Calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
