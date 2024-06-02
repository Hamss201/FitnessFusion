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

public class adminlogIn extends AppCompatActivity {
    EditText adminUser06, adminPassword06;
    Button adminLogin06, adminReset06;
    TextView userLogin06;

    private final String admin_email = "admin@gmail.com";
    private final String admin_pass = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminlog_in);

        adminUser06 = findViewById(R.id.al_user206);
        adminPassword06 = findViewById(R.id.al_pass206);
        adminLogin06 = findViewById(R.id.al_Done206);
        adminReset06 = findViewById(R.id.al_reset206);
        userLogin06 = findViewById(R.id.tv4_206);

        userLogin06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userLoginIntent = new Intent(adminlogIn.this, logIn.class);
                startActivity(userLoginIntent);
                finish();
            }
        });

        adminReset06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminUser06.setText("");
                adminPassword06.setText("");
            }
        });

        adminLogin06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdmin = adminUser06.getText().toString().trim();
                String passwordAdmin = adminPassword06.getText().toString().trim();

                if (emailAdmin.isEmpty() || passwordAdmin.isEmpty()) {
                    Toast.makeText(adminlogIn.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                fetchEmailAndLoginAdmin(emailAdmin, passwordAdmin);
            }
        });
    }

    private void fetchEmailAndLoginAdmin(final String emailAdmin, final String passwordAdmin) {
                if (emailAdmin != null && emailAdmin.equals(admin_email)) {
                    if (passwordAdmin != null && passwordAdmin.equals(admin_pass)) {
                        Toast.makeText(adminlogIn.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                        Intent intentAdmin = new Intent(adminlogIn.this, adminActivity.class);
                        startActivity(intentAdmin);
                        finish();
                    } else {
                        Toast.makeText(adminlogIn.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(adminlogIn.this, "Email Not Found", Toast.LENGTH_SHORT).show();
                }
            }

}
