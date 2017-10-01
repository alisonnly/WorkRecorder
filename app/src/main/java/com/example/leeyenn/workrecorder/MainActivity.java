package com.example.leeyenn.workrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Variable declaration
    Button Record, Calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the UI elements
        Record = (Button)findViewById(R.id.btnRecord);
        Calculate = (Button)findViewById(R.id.btnCalculate);

        //Set listener for all buttons
        Record.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Create intent to redirect to another page

                //Redirect to Record Page
                Intent recordIntent = new Intent(MainActivity.this, RecordPage.class);
                startActivity(recordIntent);
            }
        });

        Calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent calculateIntent = new Intent(MainActivity.this, CalculatePage.class);
                startActivity(calculateIntent);
            }
            //Redirect to Calculate Page
        });
    }
}
