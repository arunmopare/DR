package com.example.dr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SystemClock.sleep(2500);
        Intent i = new Intent(SplashScreen.this,LogIn.class);
        startActivity(i);
        finish();
    }
}
