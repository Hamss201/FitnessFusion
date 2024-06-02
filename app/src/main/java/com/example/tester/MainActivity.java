package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button beg206, adv206, progbar206, tdee206;
    ImageView imgset06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        beg206=findViewById(R.id.dashB1);
        adv206=findViewById(R.id.dashB3);
        progbar206=findViewById(R.id.dashB2);
        imgset06=findViewById(R.id.geardash);
        tdee206=findViewById(R.id.dashB4);

        tdee206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tdeeintent = new Intent(MainActivity.this, tdeeCalc.class);
                startActivity(tdeeintent);
                finish();

            }
        });


        beg206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBeg206 = new Intent(MainActivity.this, beginnerExer.class);
                startActivity(intentBeg206);
                finish();
            }
        });


        adv206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentadv206 = new Intent(MainActivity.this, advExer.class);
                startActivity(intentadv206);
                finish();
            }
        });

        progbar206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentprog06 = new Intent(MainActivity.this, ProgressBar.class);
                startActivity(intentprog06);
                finish();
            }
        });

        imgset06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentset06 = new Intent(MainActivity.this, setting206.class);
                startActivity(intentset06);
                finish();
            }
        });

    }
}