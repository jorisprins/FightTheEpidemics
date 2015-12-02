package nl.windesheim.fighttheepidemics;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Joris on 1-12-2015.
 */
public class MyLocationHandler extends IntentService {

    private static final String TAG = MainActivity.class.getSimpleName();

    public MyLocationHandler() {
        super("locationHandler");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final Location location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);

        //TODO background shizzle
        if(location !=null) {
            Log.d(TAG, "hoi" + location);
        }


    }
}
