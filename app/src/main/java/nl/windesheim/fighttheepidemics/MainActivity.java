package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener  {

    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FILE_NAME = "GPSLogData";

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;
    private SharedPreferences sharedPreferences;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    //private static int UPDATE_INTERVAL = 1000 * 60 * 5; // 5 minutes
    private static int UPDATE_INTERVAL = 1000 * 5;
    //private static int FASTEST_INTERVAL = 1000 * 60 * 5; // 5 minutes
    private static int FASTEST_INTERVAL = 1000 * 5; // 5 seconds
    private static int DISPLACEMENT = 1; // 10 meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First we need to check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();

            mRequestingLocationUpdates = true;
        }
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        //Create location request
        createLocationRequest();
    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

        if (result.hasResolution()) {
            // Google Play services can fix the issue
            // e.g. the user needs to enable it, updates to latest version
            // or the user needs to grant permissions to it
            Log.d(TAG, "Google Play services can fix the issue");
            try {
                result.startResolutionForResult(this, 0);
            } catch (IntentSender.SendIntentException e) {
                // it happens if the resolution intent has been canceled,
                // or is no longer able to execute the request
            }
        } else {
            // Google Play services has no idea how to fix the issue
            Log.d(TAG, "Google Play services has no idea how to fix the issue");
        }
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        if (mRequestingLocationUpdates) {
            startLocationUpdates();

            // Once connected with google api, get the location
            LocationRequest locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setFastestInterval(FASTEST_INTERVAL)
                    .setInterval(UPDATE_INTERVAL)
                    .setSmallestDisplacement(DISPLACEMENT);


            PendingIntent pendingIntent = PendingIntent.getService(this, 0,
                    new Intent(this, MyLocationHandler.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);

            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, locationRequest, pendingIntent);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        // Assign the new location
        mLastLocation = location;

        Log.d(TAG, "loc:" + location);
        //writeToInternalStorage(location);
        writeToSDCard(location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if it exists, if not connect
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();

        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            //We dont close the connection because we want it to run
            //in the the background too.
            //mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        Log.d(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {
        sharedPreferences = getSharedPreferences("nl.windesheim.fighttheepidemics", Context.MODE_PRIVATE);
        boolean switchState = sharedPreferences.getBoolean("AnonSwitchStatus", false);

        if(switchState) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }
    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        sharedPreferences = getSharedPreferences("nl.windesheim.fighttheepidemics", Context.MODE_PRIVATE);
        boolean switchState = sharedPreferences.getBoolean("AnonSwitchStatus", false);

        if(!switchState) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
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

    public void writeToInternalStorage(Location location){
        String data = getCurrentTimeStamp()+ " - " + location.getLatitude() + ", " + location.getLongitude();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME,
                    Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            fos.flush();

            File file = new File(getFilesDir() + " " );
            Log.d(TAG, "dir:" + file.getParent());

        } catch (FileNotFoundException e) { e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToSDCard(Location location) {
        FileOutputStream fos;
        String data = getCurrentTimeStamp() + " - " + location.getLatitude() + ", " + location.getLongitude() + "\n";

        try {
            File file = new File("/sdcard/" + FILE_NAME);
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileOutputStream fOut = new FileOutputStream(file, true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return yyyy-MM-dd HH:mm:ss formate date as string
     */
    public static String getCurrentTimeStamp(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date());

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
