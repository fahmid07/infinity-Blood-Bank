package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRequest extends AppCompatActivity{

    private ListView listview;
    DatabaseReference ref;
    private List<Request> reqlist;
    private CustomAdapter3 customAdapter3;
    FirebaseUser currentUser;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);

        Intent i = getIntent();
        phone = i.getStringExtra("phone");

        ref = FirebaseDatabase.getInstance().getReference("allRequest");
        reqlist = new ArrayList<>();
        customAdapter3 = new CustomAdapter3(MyRequest.this, reqlist);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        String nm = currentUser.getEmail();
        phone = nm.substring(0, 10);


        listview = findViewById(R.id.llistviewId1);

    }

    @Override
    protected void onStart() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                reqlist.clear();
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String nm = currentUser.getEmail();
                phone = nm.substring(0, 10);
                for(DataSnapshot dns:snapshot.getChildren()){
                    Request req = dns.getValue(Request.class);

                    if(req.status.equals("Yes") && req.from.equals(phone)) reqlist.add(req);
                }
                listview.setAdapter(customAdapter3);

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }



}