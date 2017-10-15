package com.example.leeyenn.workrecorder;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecordPage extends AppCompatActivity {

    private String currentDate, currentTimeString;
    private EditText tDateEditText, tTimeEditText, cNameEditText, cLocationEditText, lDoorEditText, tRubbishEditText, numOfTripEditText, tPriceEditText;
    private CommonFunction commonFunction = new CommonFunction();
    private GregorianCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);

        //Retrieve passed over date from Calendar Page
        final Record record = (Record) getIntent().getSerializableExtra("Record");

        //Initialize calendar
        calendar = new GregorianCalendar();

        //Get UI elements
        tDateEditText = (EditText)findViewById(R.id.tripDateEditText);
        tTimeEditText = (EditText)findViewById(R.id.tripTimeEditText);

        //Set text
        currentDate = commonFunction.formatDate(record.getdTripDate());
        tDateEditText.setText(currentDate);
        currentTimeString = commonFunction.getTime();
        tTimeEditText.setText(currentTimeString);

        //Set Listeners
        final DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //Convert to string
                String newDate = commonFunction.convertDateToString(calendar);
                tDateEditText.setText(commonFunction.formatDate(newDate));
            }
        };
        tDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RecordPage.this, datePickerDialog, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


    }
}
