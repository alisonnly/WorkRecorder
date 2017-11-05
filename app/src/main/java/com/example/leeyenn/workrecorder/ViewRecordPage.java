package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeyenn on 11-10-17.
 *
 * View all record page
 */

public class ViewRecordPage extends AppCompatActivity {

    private RecordDBHandler recordDBHandler;
    private Record record;
    private List<Record> recordList;
    private ListView listView;
    private ArrayAdapter<Record> recordArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_page);

        //Retrieve passed over date from Calendar Page
        record = (Record) getIntent().getSerializableExtra("RecordDate");

        //Initializer dbhandler
        recordDBHandler = new RecordDBHandler(this);

        //Initialize array
        recordList = new ArrayList<Record>();
        recordList = recordDBHandler.getRecordsByDate(record.getdTripDate());

        //Get UI elements
        listView = (ListView)findViewById(R.id.recordListView);

        //Set adapter for listview
        recordArrayAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, recordList);
        listView.setAdapter(recordArrayAdapter);

        //Set listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                //Pass the id over to viewindivrecord page
                Intent viewIndivRecordIntent = new Intent(ViewRecordPage.this, ViewIndivRecordPage.class);
                viewIndivRecordIntent.putExtra("RecordID", id);
                startActivity(viewIndivRecordIntent);
            }
        });


    }
}
