package nl.windesheim.fighttheepidemics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


//drop-down menu (spinner p.356)
//submit button method
//when it is clicked -> toast "your servey has been sent"
//p.71


public class HealthReportActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_report);
    }

}
