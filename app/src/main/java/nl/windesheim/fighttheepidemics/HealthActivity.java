package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;


public class HealthActivity extends AppCompatActivity

{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
    }

    public void onHReportClicked(View v){
        Intent hReportIntent = new Intent(getApplicationContext(), HealthReportActivity.class);
        startActivity(hReportIntent);
    }

    public void onHCheckClicked(View v){
        Intent hCheckIntent = new Intent(getApplicationContext(), HealthCheckActivity.class);
        startActivity(hCheckIntent);
    }

    public void onHMoreInfoClicked(View v){
        Intent hMoreInfoIntent = new Intent(getApplicationContext(), HealthMoreinfoActivity.class);
        startActivity(hMoreInfoIntent);
    }
}