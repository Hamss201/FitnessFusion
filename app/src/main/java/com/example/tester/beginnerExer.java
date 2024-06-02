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

public class beginnerExer extends AppCompatActivity {
    Button beginnerBack06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beginner_exer);

        beginnerBack06 = findViewById(R.id.begBack);

        beginnerBack06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentbegback206 = new Intent(beginnerExer.this, MainActivity.class);
                startActivity(intentbegback206);
                finish();
            }
        });
    }
}