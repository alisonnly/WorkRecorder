package com.example.leeyenn.workrecorder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by leeyenn on 11-10-17.
 *
 * Create new record page
 */

public class RecordPage extends AppCompatActivity {
    //Declare variables
    private String currentDate, currentTimeString;
    private Button btnGetAddress, btnCreate, btnCancel;
    private EditText tDateEditText, tTimeEditText, cNameEditText, cLocationEditText, lDoorEditText, tRubbishEditText, numOfTripEditText, tPriceEditText;
    private CommonFunction commonFunction;
    private GregorianCalendar calendar;
    private Calendar currentTime;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private TimePickerDialog  timePickerDialog;
    private LocationManager locationManager;
    private String provider;
    private LocationListener locationListener;
    private String lat, lon;
    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);

        //Retrieve passed over date from Calendar Page
        record = (Record) getIntent().getSerializableExtra("RecordDate");

        //Initialization
        calendar = new GregorianCalendar(); //For date picker
        currentTime = Calendar.getInstance(); //For time picker
        commonFunction = new CommonFunction(this);

        //Get UI elements
        tDateEditText = (EditText)findViewById(R.id.tripDateEditText);
        tTimeEditText = (EditText)findViewById(R.id.tripTimeEditText);
        cNameEditText = (EditText)findViewById(R.id.companyNameEditText);
        btnGetAddress = (Button)findViewById(R.id.getAddressButton);
        cLocationEditText = (EditText)findViewById(R.id.companyLocationEditText);
        lDoorEditText = (EditText)findViewById(R.id.doorEditText);
        tRubbishEditText = (EditText)findViewById(R.id.typeOfRubbishEditText);
        numOfTripEditText = (EditText)findViewById(R.id.noOfTripEditText);
        tPriceEditText = (EditText)findViewById(R.id.tripPriceditText);
        btnCreate = (Button)findViewById(R.id.createButton);
        btnCancel = (Button)findViewById(R.id.cancelButton);

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
        btnGetAddress.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                // Creating an empty criteria object
                Criteria criteria = new Criteria();

                // Getting the name of the provider that meets the criteria
                provider = locationManager.getBestProvider(criteria, false);

                //Check if GPS is ON
                boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (statusOfGPS == false){
                    Toast.makeText(RecordPage.this, "Please turn on GPS and try again!", Toast.LENGTH_SHORT).show();
                }
                // Get the location from the given provider
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                //Detect location change
                locationListener = new LocationListener() {

                    @Override
                    public void onLocationChanged(Location location) {
                        lat = Double.toString(location.getLatitude());
                        lon = Double.toString(location.getLongitude());
                        cLocationEditText.setText(commonFunction.getAddress(lat,lon));
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }

                };
                if (ActivityCompat.checkSelfPermission(RecordPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RecordPage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, locationListener);

                if (location != null) {
                    locationListener.onLocationChanged(location);
                }
                else {
                    location = locationManager.getLastKnownLocation(provider);
                }
                if (location != null) {
                    locationListener.onLocationChanged(location);
                }
                else {
                    Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Create button pressed
        btnCreate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Boolean emptyNumOfTrip = commonFunction.validateNumOfTrip(numOfTripEditText.getText().toString());
                Boolean emptyTripPrice = commonFunction.validateTripPrice(tPriceEditText.getText().toString());
                if (emptyNumOfTrip == false && emptyTripPrice == false){
                    record.setdTripDate(tDateEditText.getText().toString());
                    record.setdTripTime(tTimeEditText.getText().toString());
                    record.setsCompanyName(cNameEditText.getText().toString());
                    record.setsCompanyLocation(cLocationEditText.getText().toString());
                    record.setsCompanyDoor(cLocationEditText.getText().toString());
                    record.setsTypeOfRubbish(tRubbishEditText.getText().toString());
                    record.setIntNumOfTrip(Integer.parseInt(numOfTripEditText.getText().toString()));
                    record.setfTripPrice(Float.parseFloat(tPriceEditText.getText().toString()));

                    //Get DBHandler
                    RecordDBHandler recordDBHandler = new RecordDBHandler(RecordPage.this);
                    long rowInserted = recordDBHandler.createRecord(record);
                    if(rowInserted != -1){
                        Toast.makeText(getBaseContext(), "Record created successfully!", Toast.LENGTH_SHORT).show();
                        //Direct to view record page
                        Intent viewRecordIntent = new Intent(RecordPage.this, ViewIndivRecordPage.class);
                        startActivity(viewRecordIntent);

                    }
                    else {
                        Toast.makeText(getBaseContext(), "Record not created successfully. Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
