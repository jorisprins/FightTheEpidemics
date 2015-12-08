package nl.windesheim.fighttheepidemics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.lang.Override;

public class HealthCheckActivity extends AppCompatActivity
{
    private CheckBox cbFever;
    private CheckBox cbCough;
    private CheckBox cbSorethroat;
    private CheckBox cbRunnynose;
    private CheckBox cbBodyaches;
    private CheckBox cbHeadache;
    private CheckBox cbFatigue;
    private CheckBox cbDbreathing;
    private CheckBox cbBlurredvision;

    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);

        cbFever = (CheckBox) findViewById(R.id.cbFever);
        cbCough = (CheckBox) findViewById(R.id.cbCough);
        cbSorethroat = (CheckBox) findViewById(R.id.cbSorethroat);
        cbRunnynose = (CheckBox) findViewById(R.id.cbRunnynose);
        cbBodyaches = (CheckBox) findViewById(R.id.cbBodyaches);
        cbHeadache = (CheckBox) findViewById(R.id.cbHeadache);
        cbFatigue = (CheckBox) findViewById(R.id.cbFatigue);
        cbDbreathing = (CheckBox) findViewById(R.id.cbDbreathing);
        cbBlurredvision = (CheckBox) findViewById(R.id.cbBlurredvision);

        cbFever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbCough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbSorethroat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbRunnynose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbBodyaches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbHeadache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbFatigue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbDbreathing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });

        cbBlurredvision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    count++;
                }
            }
        });


    }


    public void onCheckClicked(View v){
        /*
        Intent checkIntent = new Intent(getApplicationContext(), CheckResultActivity.class);
        startActivity(checkIntent);
        */
        Intent lowChanceIntent = new Intent(getApplicationContext(), LowChanceActivity.class);
        Intent mediumChanceIntent = new Intent(getApplicationContext(), MediumChanceActivity.class);
        Intent highChanceIntent = new Intent(getApplicationContext(), HighChanceActivity.class);

        if (count == 0){
            Toast.makeText(getApplicationContext(), "Please check your symptoms first.", Toast.LENGTH_SHORT).show();
            finish(); //i have to change this to remain on activity
        } else if (count > 0 && count <= 2){
            startActivity(lowChanceIntent);
        } else if (count > 2 && count <= 5) {
            startActivity(mediumChanceIntent);
        } else if (count > 5 && count <= 9) {
            startActivity(highChanceIntent);
        }
    }
}