package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


//drop-down menu (spinner p.356)
//submit button method
//when it is clicked -> toast "your servey has been sent"
//p.71


public class HighChanceActivity extends AppCompatActivity

{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_report_high_chance);
    }

    public void onhUnderstandClicked(View v){
        Intent HealthActivityIntent = new Intent(getApplicationContext(), HealthActivity.class);
        startActivity(HealthActivityIntent);
        //use intent flag

    }

    public void onhReportClicked(View v) {
        Intent highReportIntent = new Intent(getApplicationContext(), HealthReportActivity.class);
        startActivity(highReportIntent);


    }


}