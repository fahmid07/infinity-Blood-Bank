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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllRequest extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView listview;
    private SearchView searchView2;
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
        searchView2 = findViewById(R.id.search_bar2);
        setupSearchView();
        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AllRequest.this.customAdapter1.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AllRequest.this.customAdapter1.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setupSearchView()
    {
        searchView2.setIconifiedByDefault(false);
        searchView2.setOnQueryTextListener(this);
        searchView2.setSubmitButtonEnabled(true);
        searchView2.setQueryHint("Search by District or Blood Group");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            listview.clearTextFilter();
        } else {
            listview.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    protected void onStart() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                reqlist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    Request req = dns.getValue(Request.class);

                    if(req.status.equals("Yes")) reqlist.add(req);
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