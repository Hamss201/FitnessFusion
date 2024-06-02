package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProgressBar extends AppCompatActivity {

    Button progBar06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_bar);

        progBar06 = findViewById(R.id.progBack);

        progBar06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentpg = new Intent(ProgressBar.this, MainActivity.class);
                startActivity(intentpg);
                finish();

            }
        });

    }
}