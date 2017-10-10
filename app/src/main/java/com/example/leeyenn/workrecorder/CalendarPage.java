package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                GregorianCalendar selectedDate = (new GregorianCalendar( year, month, dayOfMonth ));
                Intent calendarIntent = new Intent(CalendarPage.this, Record.class);
                calendarIntent.putExtra("chosenDate", selectedDate);
                startActivity(calendarIntent);
            }
        });
    }

}
