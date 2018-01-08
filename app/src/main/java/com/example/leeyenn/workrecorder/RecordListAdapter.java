package com.example.leeyenn.workrecorder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
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

    //Initialize variables
    private Activity activity;
    private ArrayList<Record> recordArrayList;
    private static LayoutInflater layoutInflater = null;


    public RecordListAdapter(Activity activity, int textViewResourceId, ArrayList<Record> rArrayList) {
        super(activity, textViewResourceId, rArrayList);
        try {
            this.activity = activity;
            this.recordArrayList = rArrayList;

            layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }  catch (Exception e){

        }
    }

    //Get record array list size
    public int getRecordCount(){
        return recordArrayList.size();
    }

    //https://stackoverflow.com/questions/15297840/populate-listview-from-arraylist-of-objects
}
