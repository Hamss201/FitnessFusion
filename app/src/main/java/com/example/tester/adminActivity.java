package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminActivity extends AppCompatActivity {
    private ListView lv206;
    private adapter06 adp206;
    private List<UserModel06> uslist206;
    private DatabaseReference usdtbred206;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        lv206 = findViewById(R.id.adminLView);
        uslist206 = new ArrayList<>();
        adp206 = new adapter06(this, uslist206);
        lv206.setAdapter(adp206);

        usdtbred206 = FirebaseDatabase.getInstance().getReference("Users");

        usdtbred206.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uslist206.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserModel06 user = snapshot.getValue(UserModel06.class);
                    uslist206.add(user);
                }
                adp206.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(adminActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onReturn(View view) {
        Intent logIntent = new Intent(this, adminlogIn.class);
        startActivity(logIntent);
    }

}


