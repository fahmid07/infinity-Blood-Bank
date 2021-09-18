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

public class all_user extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView listview1;
    private SearchView searchView1;
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
        searchView1 = findViewById(R.id.search_bar);
        setupSearchView();

        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                all_user.this.customAdapter2.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                all_user.this.customAdapter2.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setupSearchView()
    {
        searchView1.setIconifiedByDefault(false);
        searchView1.setOnQueryTextListener(this);
        searchView1.setSubmitButtonEnabled(true);
        searchView1.setQueryHint("Search by District or Blood Group");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            listview1.clearTextFilter();
        } else {
            listview1.setFilterText(newText);
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
                uslist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    User req = dns.getValue(User.class);

                    if(req.active.equals("Yes")) uslist.add(req);
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