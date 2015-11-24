package nl.windesheim.fighttheepidemics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysettings);
    }

    public void onCancelClicked(View v) {
        finish();
    }


    //make save button to save settings

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
}
