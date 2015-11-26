package nl.windesheim.fighttheepidemics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


//drop-down menu (spinner p.356)
//submit button method
//when it is clicked -> toast "your servey has been sent"
//p.71


public class HealthReportActivity extends AppCompatActivity
        //implements AdapterView.OnItemSelectedListener
{

//    String[] items = {"1 day", "2 days","3 days","over 3 days","over 5 days","over a week","over two weeks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_report_high_chance);
    }
}

/*
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){

    }
*/


/*
    public void onHealthClicked(View v){
        setContentView(R.layout.activity_main);

        Button health = (Button) findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code -- go to health page
            }
        });
    }

    public void onMySettingsClicked(View v){
        setContentView(R.layout.activity_main);

        Button mySettings = (Button) findViewById(R.id.mySettings);
        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code -- go to mySetting page
            }
        });
    }
*/