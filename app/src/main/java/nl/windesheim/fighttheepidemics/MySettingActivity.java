package nl.windesheim.fighttheepidemics;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MySettingActivity extends AppCompatActivity {

    public boolean anonSwitchStatus = false;
    public boolean wifiSwitchStatus = false;
    public boolean mobileSwitchStatus = false;
    //public boolean enabled = true;

    private Switch anonSwitch;
    private Switch wifiSwitch;
    private Switch mobileSwitch;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysettings);

        // Enabling Up / Back navigation
        /*
        getSupportActionBar().setDisplayUseLogoEnabled(false); //not working
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //also not working
        */

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
                    Toast.makeText(getApplicationContext(), "Enabling Sending Data.", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", true);
                    editor.apply();

                    mActivity.startLocationUpdates();

                } else {
                    //function anonymous off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("AnonSwitchStatus", false);
                    editor.apply();

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
                    editor.apply();

                    wifiManager.setWifiEnabled(true);
                } else {
                    //wifi off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("WifiSwitchStatus", false);
                    editor.apply();

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
                    editor.apply();

                    //////ERROR :: APP HAS BEEN STOPPED WHEN BUTTON TOGGLE.
                    //Due to security concerns you are not allowed to turn on mobile network programmatically.
                    //The only thing you can do is to prompt the user to turn on the mobile network by displaying the settings.
                    /*
                    Intent dataNetworkSetting = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    ComponentName cn = new ComponentName("com.android.phone","com.android.phone.Settings");
                    dataNetworkSetting.setComponent(cn);
                    startActivity(dataNetworkSetting)
                    */
                } else {
                    //mobile off
                    SharedPreferences.Editor editor = getSharedPreferences("nl.windesheim.fighttheepidemics", MODE_PRIVATE).edit();
                    editor.putBoolean("MobileSwitchStatus", false);
                    editor.apply();

                    Intent dataNetworkSetting = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    ComponentName cn = new ComponentName("com.android.phone","com.android.phone.Settings");
                    dataNetworkSetting.setComponent(cn);
                    startActivity(dataNetworkSetting);
                }
            }
        });
    }

    /*
    public void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }
    */
}
