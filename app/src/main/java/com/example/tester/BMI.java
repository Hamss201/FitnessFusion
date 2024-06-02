package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tester.tdeeCalc;

public class BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        boolean ifMale = getIntent().getExtras().getBoolean("ifMale");
        int age = getIntent().getExtras().getInt("age");
        int height = getIntent().getExtras().getInt("height");
        double mass = getIntent().getExtras().getDouble("mass");
        double bodyfat = getIntent().getExtras().getDouble("bodyfatPercent");
        double heightInMetres = (double) (height * 1.0) / 100;
        int activityLevel = getIntent().getExtras().getInt("activityLevel");
        double activityMultiplier = 1;
        double bmi = (mass / (heightInMetres * heightInMetres));
        switch (activityLevel) {
            case 0:
                activityMultiplier = 1.2;
                break;
            case 1:
                activityMultiplier = 1.375;
                break;
            case 2:
                activityMultiplier = 1.55;
                break;
            case 3:
                activityMultiplier = 1.725;
                break;
            default:
                activityMultiplier = 1.2;
                break;
        }

        TextView bmiResultText = findViewById(R.id.bmiResult);
        TextView bmrResultText = findViewById(R.id.bmrResult);
        TextView tddeResultText = findViewById(R.id.tddeResult);

        if (bodyfat == 0) {
            // Mifflin-St Jeor Equation
            int formulaNumber = ifMale ? 5 : -161;
            double bmrDouble = (10 * mass) + (6.25 * height) - (5 * age) + formulaNumber;
            double tddeDouble = bmrDouble * activityMultiplier;
            int bmr = (int) bmrDouble;
            bmiResultText.setText("" + round(bmi, 2));
            tddeResultText.setText("" + tddeDouble);
            bmrResultText.setText("" + bmr);
        } else {
            // Katch-McArdle Equation
            double lbm = mass - ((bodyfat * mass) / 100);
            double bmrDouble = 370 + (21.6 * lbm);
            double tddeDouble = bmrDouble * activityMultiplier;
            int bmr = (int) bmrDouble;
            bmiResultText.setText("" + round(bmi, 2));
            tddeResultText.setText("" + tddeDouble);
            bmrResultText.setText("" + bmr);
        }
    }

    public void onReturn(View view) {
        Intent swapIntent = new Intent(this, tdeeCalc.class);
        startActivity(swapIntent);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
