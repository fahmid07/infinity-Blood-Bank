package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllRequest extends AppCompatActivity {

    private ListView listview;
    DatabaseReference ref;
    private List<Request> reqlist;
    private CustomAdapter1 customAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_request);

        ref = FirebaseDatabase.getInstance().getReference("allRequest");
        reqlist = new ArrayList<>();
        customAdapter1 = new CustomAdapter1(AllRequest.this, reqlist);


        listview = findViewById(R.id.llistviewId);
    }

    @Override
    protected void onStart() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                reqlist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    Request req = dns.getValue(Request.class);

                    reqlist.add(req);
                }
                listview.setAdapter(customAdapter1);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }
}