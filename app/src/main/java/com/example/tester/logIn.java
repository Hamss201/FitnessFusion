package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logIn extends AppCompatActivity {
    EditText logus06, logpw06;
    Button logdone06, logres06;
    TextView reg06, readmin06;
    DatabaseReference dbref06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        logus06 = findViewById(R.id.li_user206);
        logpw06 = findViewById(R.id.li_pass206);
        logdone06 = findViewById(R.id.li_Done206);
        logres06 = findViewById(R.id.li_reset206);
        reg06 = findViewById(R.id.tv3_206);
        readmin06 = findViewById(R.id.tvadmin206);

        dbref06 = FirebaseDatabase.getInstance().getReference("Users");

        readmin06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adred06=new Intent(logIn.this, adminlogIn.class);
                startActivity(adred06);
                finish();
            }
        });

        logres06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logus06.setText("");
                logpw06.setText("");
            }
        });
        reg06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rgp06 = new Intent(logIn.this, signUp.class);
                startActivity(rgp06);
                finish();
            }
        });
        logdone06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email06 = logus06.getText().toString().trim();
                String password06 = logpw06.getText().toString().trim();

                if (email06.isEmpty() || password06.isEmpty()) {
                    Toast.makeText(logIn.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                fetchEmailAndLogin(email06, password06);
            }
        });
    }

    private void fetchEmailAndLogin(final String email06, final String password06) {
        String sanitizedEmail = email06.replace(".", ",");
        dbref06.child(sanitizedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.child("pas").getValue(String.class);
                    if (storedPassword != null && storedPassword.equals(password06)) {
                        Toast.makeText(logIn.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                        Intent intent206 = new Intent(logIn.this, MainActivity.class);
                        startActivity(intent206);
                        finish();
                    } else {
                        Toast.makeText(logIn.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(logIn.this, "Email not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(logIn.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
