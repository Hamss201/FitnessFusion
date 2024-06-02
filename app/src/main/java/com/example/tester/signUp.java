package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {
    EditText fn206, em206, ph206, pw206;
    Button done206, re206;
    TextView txt206;
    DatabaseReference dbref206;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        dbref206 = FirebaseDatabase.getInstance().getReference("Users");

        fn206 = findViewById(R.id.name206);
        em206 = findViewById(R.id.email206);
        ph206 = findViewById(R.id.phoneNo206);
        pw206 = findViewById(R.id.pass206);
        done206 = findViewById(R.id.button206);
        re206 = findViewById(R.id.reset206);
        txt206 = findViewById(R.id.txv206);

        re206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fn206.setText("");
                em206.setText("");
                ph206.setText("");
                pw206.setText("");
            }
        });

        txt206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sipg206 = new Intent(signUp.this, logIn.class);
                startActivity(sipg206);
                finish();
            }
        });

        done206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full = fn206.getText().toString().trim();
                String ema = em206.getText().toString().trim();
                String pho = ph206.getText().toString().trim();
                String pas = pw206.getText().toString().trim();

                if (TextUtils.isEmpty(full)) {
                    fn206.setError("Name Field is Empty");
                    return;
                }
                if (TextUtils.isEmpty(ema)) {
                    em206.setError("Email Field is Empty");
                    return;
                }
                if (TextUtils.isEmpty(pho)) {
                    ph206.setError("Phone Number Field is Empty");
                    return;
                }
                if (TextUtils.isEmpty(pas)) {
                    pw206.setError("Password Field is Empty");
                    return;
                }
                regUser206(full, ema, pho, pas);
            }
        });
    }

    private void regUser206(final String full, final String ema, final String pho, final String pas) {
        // Sanitize the email address for use as a Firebase key
        String emailKey = ema.replace(".", ",");
        UserModel06 user = new UserModel06(full, pho, pas, ema);
        dbref206.child(emailKey).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signUp.this, logIn.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(signUp.this, "Registration Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
