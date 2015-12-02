package nl.windesheim.fighttheepidemics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HealthCheckActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
    }

    public void onCheckClicked(View v){
        Intent checkIntent = new Intent(getApplicationContext(), CheckResultActivity.class);
        startActivity(checkIntent);
    }
}