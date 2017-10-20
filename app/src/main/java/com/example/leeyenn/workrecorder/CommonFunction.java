package com.example.leeyenn.workrecorder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by leeyenn on 11-10-17.
 *
 * Common functions used across the whole application
 */

public class CommonFunction {

    private Context context;

    public CommonFunction(Context context) {
        this.context = context;
    }

    //Convert Date to string
    public String convertDateToString(GregorianCalendar gDate) {
        //Specify the date format. Sqlite has to be in yyyy-MM-dd format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        simpleDateFormat.setCalendar(gDate);
        String newStringDate = simpleDateFormat.format(gDate.getTime());
        return newStringDate;
    }

    //Formate String date
    public String formatStringDate(String oldDate){
        Date parseDate;
        String newDate = "";

        SimpleDateFormat inputDate = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            parseDate = inputDate.parse(oldDate);
            newDate = outputDate.format(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
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
    public String getTime() {
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

    //Get address
    public String getAddress(String lat, String lon){
        Geocoder geoCoder = new Geocoder(context, Locale.ENGLISH);
        String currentAddress = "";
        try{
            //Get address with lat, long, max 1 result
            List<Address> addresses = geoCoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
            if (addresses != null){
                Address returnedAddress = addresses.get(0);
                StringBuilder returnedStringAddress = new StringBuilder();
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++){
                    returnedStringAddress.append(returnedAddress.getAddressLine(i));
                }
                currentAddress = returnedStringAddress.toString();
            }
            else {
                currentAddress = "No Address Found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            currentAddress = "Can't get Address";
        }
        return currentAddress;
    }

    //Validate Number of trip
    public Boolean validateNumOfTrip(String numOfTrip){
        Boolean emptyInput;
        if (!TextUtils.isEmpty(numOfTrip)){
            emptyInput = false;
        }
        else {
            emptyInput = true;
            Toast.makeText(context, "Please enter number of trip", Toast.LENGTH_SHORT).show();
        }
        return emptyInput;
    }

    //Validate Trip Time
    public Boolean validateTripPrice(String price){
        Boolean emptyInput;
        if (!TextUtils.isEmpty(price)){
            emptyInput = false;
        }
        else {
            emptyInput = true;
            Toast.makeText(context, "Please enter a trip price", Toast.LENGTH_SHORT).show();
        }
        return emptyInput;
    }



}
