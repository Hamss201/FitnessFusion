package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tester.BMI;

public class tdeeCalc extends AppCompatActivity {

    private int age;
    private int height;
    private double bodyfatPercent = -1;
    private double mass;
    private int activityLevel = 0;
    private boolean male = false;
    private boolean female = false;

    private RadioGroup radioGroup206;
    private EditText ageInput206;
    private EditText heightInput206;
    private EditText bodyfatPercentage206;
    private EditText weightInput206;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdee_calc);

        // Initialize UI components
        ageInput206 = findViewById(R.id.age_input);
        heightInput206 = findViewById(R.id.height_input);
        bodyfatPercentage206 = findViewById(R.id.bodyfat_input);
        weightInput206 = findViewById(R.id.wt_input);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        radioGroup206 = findViewById(R.id.radioGroup);

        rbMale.setOnClickListener(radioButtonClickListener);
        rbFemale.setOnClickListener(radioButtonClickListener);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        final Spinner mySpinner = findViewById(R.id.activity_input);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(tdeeCalc.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.activity));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setSelection(0);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activityLevel = mySpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.rb_male) {
                male = true;
                female = false;
            } else if (id == R.id.rb_female) {
                female = true;
                male = false;
            } else {
                throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };
    public void onCalculate(View view) {
        try {
            if (!isEmpty(ageInput206) && !isEmpty(heightInput206) && !isEmpty(weightInput206) &&
                    !weightInput206.getText().toString().equals(".") && !bodyfatPercentage206.getText().toString().equals(".")) {
                age = Integer.parseInt(ageInput206.getText().toString());
                height = Integer.parseInt(heightInput206.getText().toString());

                Intent passdata_intent = new Intent(this, BMI.class);

                if (!isEmpty(bodyfatPercentage206)) {
                    bodyfatPercent = Double.parseDouble(bodyfatPercentage206.getText().toString());
                }
                passdata_intent.putExtra("bodyfatPercent", bodyfatPercent);

                mass = Double.parseDouble(weightInput206.getText().toString());
                passdata_intent.putExtra("mass", mass);
                passdata_intent.putExtra("activityLevel", activityLevel);
                passdata_intent.putExtra("height", height);
                passdata_intent.putExtra("age", age);
                passdata_intent.putExtra("ifMale", male);

                startActivity(passdata_intent);

            } else {
                Toast.makeText(getApplicationContext(), "Please Complete The Necessary Sections Correctly", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please Complete The Necessary Sections Correctly", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public void onReturn(View view) {
        Intent tbackIntent = new Intent(this, MainActivity.class);
        startActivity(tbackIntent);
    }

}
