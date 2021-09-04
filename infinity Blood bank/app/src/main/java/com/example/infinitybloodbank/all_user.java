package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class all_user extends AppCompatActivity {

    private ListView listview1;
    DatabaseReference ref;
    private List<User> uslist;
    private CustomAdapter2 customAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        ref = FirebaseDatabase.getInstance().getReference("Users");
        uslist = new ArrayList<>();
        customAdapter2 = new CustomAdapter2(all_user.this, uslist);

        listview1 = findViewById(R.id.llistviewId1);
    }

    @Override
    protected void onStart() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                uslist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    User req = dns.getValue(User.class);

                    uslist.add(req);
                }
                listview1.setAdapter(customAdapter2);

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }



}