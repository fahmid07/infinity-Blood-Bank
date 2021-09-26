package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

    AutoCompleteTextView dst, bld;
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
        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);

        String [] districts = {"All", "Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati", "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur", "Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira", "Jamalpur", "Mymensingh",     "Netrakona","Sherpur", "Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj", "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon", "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};
        ArrayAdapter dstr = new ArrayAdapter(this, R.layout.district_item, districts);
        //dst.setText(dstr.getItem(0).toString(), false);
        dst.setAdapter(dstr);

        String [] bloods = {"All", "B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        //bld.setText(bldg.getItem(0).toString(), false);
        bld.setAdapter(bldg);

        bld.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String one = bld.getText().toString();
                String two = dst.getText().toString();
                if(one.equals("All")) one = "";
                if(two.equals("All")) two = "";

                if(one.equals("Blood")) one = "";
                if(two.equals("District")) two = "";
                searchView1.setQuery(two + " " + one,  false);
                searchView1.clearFocus();
            }
        });

        dst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String one = bld.getText().toString();
                String two = dst.getText().toString();
                if(one.equals("All")) one = "";
                if(two.equals("All")) two = "";

                if(one.equals("Bg")) one = "";
                if(two.equals("District")) two = "";
                searchView1.setQuery(two + " " + one, false);
                searchView1.clearFocus();
            }
        });

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