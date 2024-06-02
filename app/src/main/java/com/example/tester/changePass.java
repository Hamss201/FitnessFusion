package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changePass extends AppCompatActivity {
    EditText etOldPassword, etNewPassword;
    Button btnChangePassword, btnChangeBack;
    DatabaseReference setdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_pass);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangeBack = findViewById(R.id.cpassBack);

        setdb = FirebaseDatabase.getInstance().getReference().child("Users");

        btnChangeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback = new Intent(changePass.this, setting206.class);
                startActivity(intentback);
                finish();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String oldPassword = etOldPassword.getText().toString();
                final String newPassword = etNewPassword.getText().toString();

                setdb.orderByChild("pas").equalTo(oldPassword).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            snapshot.getRef().child("pas").setValue(newPassword);
                            Toast.makeText(changePass.this,"Password has been updated.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(changePass.this, changePass.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("DBError","loadPost:onCancelled",error.toException());
                    }
                });
            }
        });

    }

}