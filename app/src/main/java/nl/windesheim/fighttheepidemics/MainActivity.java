package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onHealthClicked(View v){
        Intent healthIntent = new Intent(getApplicationContext(), HealthActivity.class);
        startActivity(healthIntent);

        /*
        setContentView(R.layout.activity_main);

        Button health = (Button) findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code -- go to health page
            }
        });
        */
    }

    public void onMySettingsClicked(View v){
        Intent mySettingIntent = new Intent(getApplicationContext(), MySettingActivity.class);
        startActivity(mySettingIntent);

        /*
        setContentView(R.layout.activity_main);

        Button mySettings = (Button) findViewById(R.id.mySettings);
        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code -- go to mySetting page
            }
        });
        */
    }

    public void onSignInClicked(View v){
        Intent mainSignInIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(mainSignInIntent);
    }

}
