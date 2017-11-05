package com.example.leeyenn.workrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by leeyenn on 11-10-17.
 *
 * Generate listview for records
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class RecordListAdapter extends ArrayAdapter<Record> {

    private TextView tripDateTextView, tripTimeTextView, companyNameTextView, companyLocationTextView, locationDoorTextView,
                     typeOfRubbishTextView, numOfTripTextView, tripPriceTextView;

    public RecordListAdapter(Context context, ArrayList<Record> recordArrayList) {
        super(context, 0, recordArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        Record record = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_record_page, parent);
        }
        //Get UI elements
        tripDateTextView = (TextView)convertView.findViewById(R.id.tripDateListTextView);
        //continue textview


        return convertView;
    }
}
