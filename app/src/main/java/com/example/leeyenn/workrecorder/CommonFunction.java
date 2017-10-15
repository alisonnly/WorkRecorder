package com.example.leeyenn.workrecorder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by leeyenn on 11-10-17.
 *
 * Common functions used across the whole application
 */

public class CommonFunction {

    public CommonFunction(){

    }

    //Convert Date to string
    public String convertDateToString(GregorianCalendar gDate){
        //Specify the date format. Sqlite has to be in yyyy-MM-dd format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
                java.util.Locale.getDefault());
        simpleDateFormat.setCalendar(gDate);
        String newStringDate = simpleDateFormat.format(gDate.getTime());
        return newStringDate;
    }

//    //Convert string to date
//    public GregorianCalendar convertStringToDate(String sDate) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
//                java.util.Locale.getDefault());
//        Date date = null;
//        try {
//            date = dateFormat.parse(sDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        GregorianCalendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        return calendar;
//    }

    //Get Time
    public String getTime(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String localTime = dateFormat.format(currentLocalTime);

        return localTime;
    }

    //Format Date
    public String formatDate(String date) {
        Date newDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        String newStringDate = simpleDateFormat.format(newDate);
        return newStringDate;
    }
}
