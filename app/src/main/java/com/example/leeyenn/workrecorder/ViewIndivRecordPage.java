package com.example.leeyenn.workrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewIndivRecordPage extends AppCompatActivity {

    private Record record;
    private RecordDBHandler recordDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_indiv_record_page);

        //Retrieve passed over date from Calendar Page
        //A long value is always declared with a L suffix.
        long recordID = getIntent().getLongExtra("RecordID", -1L);
        int intRecordID = (int) recordID;

        //Initialize RecordDBHandler
        recordDBHandler = new RecordDBHandler(this);
        record = recordDBHandler.getRecordById(intRecordID);
    }
}
