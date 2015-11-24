package nl.windesheim.fighttheepidemics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignInActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void onSignInClicked(View v){
        Intent signInIntent = new Intent(getApplicationContext(), MySettingActivity.class);
        startActivity(signInIntent);
    }
}