package com.example.leeyenn.workrecorder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.getDefault());
        simpleDateFormat.setCalendar(gDate);
        String newStringDate = simpleDateFormat.format(gDate.getTime());
        return newStringDate;
    }

    //Convert string to date
    public GregorianCalendar convertStringToDate(String sDate) {
        //Specify the date format. Sqlite has to be in yyyy-MM-dd format
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
