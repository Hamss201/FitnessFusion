package com.example.tester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class adapter06 extends BaseAdapter {
    private Context context;
    private List<UserModel06> uslist206;

    public adapter06(Context context, List<UserModel06> uslist206) {
        this.context = context;
        this.uslist206 = uslist206;
    }

    @Override
    public int getCount() {
        return uslist206.size();
    }

    @Override
    public Object getItem(int position) {
        return uslist206.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView delUser06 = convertView.findViewById(R.id.deleteButton);

        TextView emailTextView = convertView.findViewById(R.id.listemail);
        TextView nameTextView = convertView.findViewById(R.id.listfullname);
        TextView phoneTextView = convertView.findViewById(R.id.listphone);

        UserModel06 user = uslist206.get(position);

        emailTextView.setText(user.getEmail());
        nameTextView.setText(user.getName());
        phoneTextView.setText(user.getPhone());

        delUser06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDelete206(user.getEmail());
            }
        });

        return convertView;
    }

    private void userDelete206(String ema){
        String emailKey = ema.replace(".", ",");
        DatabaseReference deldtd206 = FirebaseDatabase.getInstance().getReference("Users").child(emailKey);
        deldtd206.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(context,"User Has Been Removed",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context,"Deletion Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}