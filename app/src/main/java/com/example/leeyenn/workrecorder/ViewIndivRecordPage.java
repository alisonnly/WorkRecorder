package com.example.leeyenn.workrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewIndivRecordPage extends AppCompatActivity {

    private Record record;
    private RecordDBHandler recordDBHandler;
    private TextView displayDateTextView, displayTimeTextView, displayNameTextView, displayLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_indiv_record_page);

        //Retrieve passed over date from Calendar Page
        //A long value is always declared with a L suffix.
//        long recordID = getIntent().getLongExtra("RecordID", -1L);
//        int intRecordID = (int) recordID;

        //Initialize RecordDBHandler
        recordDBHandler = new RecordDBHandler(this);
        record = recordDBHandler.getRecordById(6);

        //Get UI elements
        displayDateTextView = (TextView)findViewById(R.id.tDateDisplaytextView);
        displayTimeTextView = (TextView)findViewById(R.id.tTimeDisplaytextView);
        displayNameTextView = (TextView)findViewById(R.id.cNameDisplaytextView);
        displayLocationTextView = (TextView)findViewById(R.id.cLocationDisplaytextView);

        //Set Text
        displayDateTextView.setText(record.getdTripDate());
        displayTimeTextView.setText(record.getdTripTime());
        displayNameTextView.setText(record.getsCompanyName());
        displayLocationTextView.setText(record.getsCompanyLocation());
    }
}
