package com.example.leeyenn.workrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class RecordPage extends AppCompatActivity {

    TextView displayDate;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);

        //Retrieve passed over date from Calendar Page
        selectedDate = getIntent().getStringExtra("chosenDate");

        //Get UI elements
        displayDate = (TextView)findViewById(R.id.display);

        //Set text
        displayDate.setText(selectedDate);

        //Set Listeners
        displayDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });


    }
}
