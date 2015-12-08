package nl.windesheim.fighttheepidemics;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
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
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysettings);

        //SharedPreferences anonSwitchStatus;
        //SharedPreferences wifiSwitchStatus;
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
                MainActivity mActivity = new MainActivity();

                if (anonSwitch.isChecked()) {
                    //function anonymous on
                    Toast.makeText(getApplicationContext(), "You are going to send data anonymously.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", true);
                    editor.commit();

                    mActivity.startLocationUpdates();

                } else {
                    //function anonymous off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", false);
                    editor.commit();

                    mActivity.stopLocationUpdates();
                }
            }
        });

        wifiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

                if (wifiSwitch.isChecked()) {
                    //wifi on
                    Toast.makeText(getApplicationContext(), "You are going to use Wi-fi for sending location data.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("WifiSwitchStatus", true);
                    editor.commit();

                    //08 12 2015 added - wifi enabled
                    wifiManager.setWifiEnabled(true);
                } else {
                    //wifi off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("WifiSwitchStatus", false);
                    editor.commit();

                    //08 12 2015 added - wifi disabled
                    wifiManager.setWifiEnabled(false);
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


                    /*
                    private void setMobileDataEnabled(Context context, boolean enabled) {
                        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        final Class conmanClass = Class.forName(conman.getClass().getName());
                        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
                        iConnectivityManagerField.setAccessible(true);
                        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
                        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
                        final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                        setMobileDataEnabledMethod.setAccessible(true);

                        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
                    }
                     */

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
