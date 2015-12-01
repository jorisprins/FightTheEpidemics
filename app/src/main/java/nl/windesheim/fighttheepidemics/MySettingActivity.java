package nl.windesheim.fighttheepidemics;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MySettingActivity extends AppCompatActivity {

    public boolean anonSwitchStatus = false;
    public boolean wifiSwitchStatus = false;
    public boolean mobileSwitchStatus = false;

    private Switch anonSwitch;

    private Switch wifiSwitch;
    private Switch mobileSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysettings);

        //SharedPreferences anonSwitchStatus;
       // SharedPreferences wifiSwitchStatus;
        //SharedPreferences mobileSwitchStatus;


        anonSwitch = (Switch) findViewById(R.id.anonSwitch);
        wifiSwitch = (Switch) findViewById(R.id.wifiSwitch);
        mobileSwitch = (Switch) findViewById(R.id.mobileSwitch);


        //TO LOAD
        SharedPreferences anonPrefs = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE);
        anonSwitch.setChecked(anonPrefs.getBoolean("AnonSwitchStatus", anonSwitchStatus));

        SharedPreferences wifiPrefs = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE);
        wifiSwitch.setChecked(wifiPrefs.getBoolean("WifiSwitchStatus", wifiSwitchStatus));

        SharedPreferences mobilePrefs = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE);
        mobileSwitch.setChecked(mobilePrefs.getBoolean("MobileSwitchStatus", mobileSwitchStatus));



        //TO SAVE
        anonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (anonSwitch.isChecked()) {
                    //function anonymous on

                    Toast.makeText(getApplicationContext(), "You are going to send data anonymously.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", true);
                    editor.commit();
                } else {
                    //function anonymous off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", false);
                    editor.commit();
                }
            }
        });

        wifiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wifiSwitch.isChecked()) {
                    //wifi on
                    Toast.makeText(getApplicationContext(), "You are going to use Wi-fi for sending location data.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("WifiSwitchStatus", true);
                    editor.commit();
                } else {
                    //wifi off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("WifiSwitchStatus", false);
                    editor.commit();
                }
            }
        });

        mobileSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobileSwitch.isChecked()) {
                    //mobile on

                    Toast.makeText(getApplicationContext(), "You are going to use mobile data for sending location data.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("MobileSwitchStatus", true);
                    editor.commit();
                } else {
                    //mobile off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("MobileSwitchStatus", false);
                    editor.commit();
                }
            }
        });


    }





}
