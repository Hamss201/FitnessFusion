package com.example.tester;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Handler;


public class splashScreen06 extends AppCompatActivity {
    private static final int splashlen = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen06);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash206=new Intent(splashScreen06.this, logIn.class);
                startActivity(splash206);
                finish();
            }
        }, splashlen);
    }
}