package com.example.leeyenn.workrecorder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RecordPage extends AppCompatActivity {
    //Declare variables
    private String currentDate, currentTimeString;
    private Button btnGetAddress;
    private EditText tDateEditText, tTimeEditText, cNameEditText, cLocationEditText, lDoorEditText, tRubbishEditText, numOfTripEditText, tPriceEditText;
    private CommonFunction commonFunction;
    private GregorianCalendar calendar;
    private Calendar currentTime;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private TimePickerDialog  timePickerDialog;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);

        //Retrieve passed over date from Calendar Page
        final Record record = (Record) getIntent().getSerializableExtra("Record");

        //Initialization
        calendar = new GregorianCalendar(); //For date picker
        currentTime = Calendar.getInstance(); //For time picker
        commonFunction = new CommonFunction(this);

        //Get UI elements
        tDateEditText = (EditText)findViewById(R.id.tripDateEditText);
        tTimeEditText = (EditText)findViewById(R.id.tripTimeEditText);
        btnGetAddress = (Button)findViewById(R.id.getAddressButton);

        //Set text
        currentDate = commonFunction.formatDate(record.getdTripDate());
        tDateEditText.setText(currentDate);
        currentTimeString = commonFunction.getTime();
        tTimeEditText.setText(currentTimeString);

        //Set Listeners
        //Date picker
        datePickerDialog = new DatePickerDialog.OnDateSetListener(){

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

        //Time picker
        tTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;
                hour = currentTime.get(Calendar.HOUR_OF_DAY);
                minute = currentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(RecordPage.this, new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tTimeEditText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false); //set 24hr timing
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        //Get Address Button
//        btnGetAddress.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//                //Check if GPS is ON
//                boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                if (statusOfGPS == false){
//                    Toast.makeText(RecordPage.this, "Please turn on GPS and try again!", Toast.LENGTH_SHORT).show();
//                }
//                locationListener = new LocationListener() {
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        lat = Double.toString(location.getLatitude());
//                        lon = Double.toString(location.getLongitude());
//                        tTimeEditText.setText(commonFunction.getAddress(lat,lon));
//                    }
//
//                    @Override
//                    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String s) {
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String s) {
//
//                    }
//
//                };
//                if (ActivityCompat.checkSelfPermission(RecordPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RecordPage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
//                tTimeEditText.setText(commonFunction.getAddress(lat,lon));
//            }
//        });


    }
}
