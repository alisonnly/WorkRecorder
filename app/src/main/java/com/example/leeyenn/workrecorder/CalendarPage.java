package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.util.GregorianCalendar;


public class CalendarPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        //Find UI elements of calendar
        CalendarView calendarView = (CalendarView)findViewById(R.id.calendar);

        //Set listener for calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView cView, int year, int month, int dayOfMonth){
                //Combine the dates together
                GregorianCalendar selectedDate = (new GregorianCalendar( year, month, dayOfMonth ));
                //Initializing commonfunction class
                CommonFunction commonFunction = new CommonFunction();
                //Convert date to string
                String storeDate = commonFunction.convertDateToString(selectedDate);

                //Go into DB to check for existing data
                Log.i("Check DB", "Checking for records");
                RecordDBHandler recordDBHandler = new RecordDBHandler(CalendarPage.this);
                int checkRecordCount = recordDBHandler.checkForExistingRecord(storeDate);

                if (checkRecordCount <= 0){
                    //If there is no record, let user create new record
                    Log.i("No Record", "No Record Found");
                    //Pass value over with intent
                    Intent calendarIntent = new Intent(CalendarPage.this, RecordPage.class);
                    calendarIntent.putExtra("chosenDate", storeDate);
                    startActivity(calendarIntent);
                }
                else {
                    Log.i("Record Found", "No of records found: " + checkRecordCount);
                }
            }
        });
    }

}
