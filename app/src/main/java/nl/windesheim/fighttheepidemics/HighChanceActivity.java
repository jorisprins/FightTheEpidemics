package nl.windesheim.fighttheepidemics;

import android.content.Intent;
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
    //int s = ((MyApplication) this.getApplication()).getChance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_report_high_chance);
    }

    public void onhUnderstandClicked(View v){
        Intent HealthActivityIntent = new Intent(getApplicationContext(), HealthActivity.class);
        startActivity(HealthActivityIntent);
        //use intent flag
        //((MyApplication) this.getApplication()).setChance(2);
    }

    public void onhReportClicked(View v) {
        Intent highReportIntent = new Intent(getApplicationContext(), HealthReportActivity.class);
        startActivity(highReportIntent);


    }


}