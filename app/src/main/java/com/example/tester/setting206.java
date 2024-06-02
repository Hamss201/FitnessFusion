package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class setting206 extends AppCompatActivity {
    Button setbtn;
    TextView setcha206, setlog206, setexit206;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting206);

        setbtn=findViewById(R.id.setBack);
        setcha206=findViewById(R.id.settingChangepass);
        setlog206=findViewById(R.id.settingLogout);
        setexit206=findViewById(R.id.closeApp);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsback = new Intent(setting206.this, MainActivity.class);
                startActivity(intentsback);
                finish();
            }
        });

        setcha206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentschan = new Intent(setting206.this, changePass.class);
                startActivity(intentschan);
                finish();
            }
        });

        setlog206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentslog = new Intent(setting206.this, logIn.class);
                startActivity(intentslog);
                finish();
            }
        });

        setexit206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}