package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


//drop-down menu (spinner p.356)
//submit button method
//when it is clicked -> toast "your servey has been sent"
//p.71


public class CheckResultActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check_result);
    }

    public void onHighChanceClicked(View v){
        Intent highChanceIntent = new Intent(getApplicationContext(), HighChanceActivity.class);
        startActivity(highChanceIntent);
    }

    public void onMediumChanceClicked(View v){
        Intent mediumChanceIntent = new Intent(getApplicationContext(), MediumChanceActivity.class);
        startActivity(mediumChanceIntent);
    }

    public void onLowChanceClicked(View v){
        Intent lowChanceIntent = new Intent(getApplicationContext(), LowChanceActivity.class);
        startActivity(lowChanceIntent);
    }
}