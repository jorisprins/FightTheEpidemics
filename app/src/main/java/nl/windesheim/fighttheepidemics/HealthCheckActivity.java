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

public class HealthCheckActivity extends AppCompatActivity {
    private CheckBox cbFever;
    private CheckBox cbCough;
    private CheckBox cbSorethroat;
    private CheckBox cbRunnynose;
    private CheckBox cbBodyaches;
    private CheckBox cbHeadache;
    private CheckBox cbFatigue;
    private CheckBox cbDbreathing;
    private CheckBox cbBlurredvision;
    private CheckBox cbSuddenly;

    public int both_symptoms = 0;
    public int flu_symptoms = 0;
    public int not_flu_symptoms = 0;

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
        cbSuddenly = (CheckBox) findViewById(R.id.cbSuddenly);

        cbFever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                } else { flu_symptoms--;}
            }
        });

        cbCough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                }else { flu_symptoms--;}
            }
        });

        cbSorethroat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    both_symptoms++;
                }else { both_symptoms--;}
            }
        });

        cbRunnynose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    both_symptoms++;
                }else {both_symptoms--;}
            }
        });

        cbBodyaches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                }else { flu_symptoms--;}
            }
        });

        cbHeadache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                }else { flu_symptoms--;}
            }
        });

        cbFatigue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                }else { flu_symptoms--;}
            }
        });

        cbDbreathing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    not_flu_symptoms++;
                } else { not_flu_symptoms--;}
            }
        });

        cbBlurredvision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    not_flu_symptoms++;
                }else { not_flu_symptoms--;}
            }
        });

        cbSuddenly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flu_symptoms++;
                } else { flu_symptoms--;}
            }
        });


    }


    public void onCheckClicked(View v) {
        /*
        Intent checkIntent = new Intent(getApplicationContext(), CheckResultActivity.class);
        startActivity(checkIntent);
        */
        Intent lowChanceIntent = new Intent(getApplicationContext(), LowChanceActivity.class);
        Intent mediumChanceIntent = new Intent(getApplicationContext(), MediumChanceActivity.class);
        Intent highChanceIntent = new Intent(getApplicationContext(), HighChanceActivity.class);
        Intent otherChanceIntent = new Intent(getApplicationContext(), OtherChanceActivity.class);
        Intent coldIntent = new Intent(getApplicationContext(), ColdActivity.class);


        if (both_symptoms == 0 && flu_symptoms == 0 && not_flu_symptoms == 0) {
            //Nothing is checked warning message
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

            Toast.makeText(getApplicationContext(), "You didn't check any symptoms.", Toast.LENGTH_SHORT).show();

        }

        else if (flu_symptoms == 1 && both_symptoms == 0 && not_flu_symptoms == 0){
            Toast.makeText(getApplicationContext(), "You should check more then one symptom.", Toast.LENGTH_SHORT).show();

        }
        else if (flu_symptoms == 0 && both_symptoms == 1 && not_flu_symptoms == 0){
            Toast.makeText(getApplicationContext(), "You should check more then one symptom.", Toast.LENGTH_SHORT).show();

        }
        else if (flu_symptoms == 0 && both_symptoms == 0 && not_flu_symptoms == 1){
            Toast.makeText(getApplicationContext(), "You should check more then one symptom.", Toast.LENGTH_SHORT).show();


        else if (flu_symptoms == 1 && both_symptoms == 1 && not_flu_symptoms == 0){
            Toast.makeText(getApplicationContext(), "You should check more then two symptoms.", Toast.LENGTH_SHORT).show();

        }
        else if (not_flu_symptoms >= 1){
            startActivity(otherChanceIntent);

        }

        else if (flu_symptoms == 2 && both_symptoms == 0){
            startActivity(mediumChanceIntent);

        }
        else if (flu_symptoms == 3 && both_symptoms == 0){
            startActivity(mediumChanceIntent);

        }
        else if (flu_symptoms >= 4 && both_symptoms == 0 && not_flu_symptoms == 0){
            startActivity(highChanceIntent);

        }


        else if (flu_symptoms < 2 && both_symptoms == 2){
            startActivity(otherChanceIntent);

        }
        else if (flu_symptoms > 2 && both_symptoms >= 0 && not_flu_symptoms == 0 ){
            startActivity(highChanceIntent);

        }
        else if (flu_symptoms > 1 && both_symptoms >= 0 && not_flu_symptoms == 0 ){
            startActivity(mediumChanceIntent);

        }





        }
    }


