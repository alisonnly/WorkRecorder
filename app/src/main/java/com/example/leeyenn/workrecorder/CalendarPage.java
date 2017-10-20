package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by leeyenn on 11-10-17.
 *
 * View calendar page
 */

public class CalendarPage extends AppCompatActivity {

    private CommonFunction commonFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        //Find UI elements of calendar
        CalendarView calendarView = (CalendarView)findViewById(R.id.calendar);

        //Initializing commonfunction class
        commonFunction = new CommonFunction(this);

        //Set listener for calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView cView, int year, int month, int dayOfMonth){
                //Combine the dates together
                GregorianCalendar selectedDate = (new GregorianCalendar( year, month, dayOfMonth ));

                //Convert date to string
                String storeDate = commonFunction.convertDateToString(selectedDate);
                Record record = new Record();
                record.setdTripDate(storeDate);

                //Go into DB to check for existing data
                Log.i("Check DB", "Checking for records");
                RecordDBHandler recordDBHandler = new RecordDBHandler(CalendarPage.this);
                int checkRecordCount = recordDBHandler.checkForExistingRecord(record.getdTripDate());

                if (checkRecordCount <= 0){
                    //If there is no record, let user create new record
                    Log.i("No Record", "No Record Found");
                    //Pass value over with intent
                    Intent calendarIntent = new Intent(CalendarPage.this, RecordPage.class);
                    calendarIntent.putExtra("Record", record);
                    startActivity(calendarIntent);
                }
                else {
                    Log.i("Record Found", "No of records found: " + checkRecordCount);
                    Intent viewRecordIntent = new Intent(CalendarPage.this, ViewRecordPage.class);
                    startActivity(viewRecordIntent);
                }
            }
        });
    }

}
