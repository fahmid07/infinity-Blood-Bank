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

public class AllRequest extends AppCompatActivity implements SearchView.OnQueryTextListener {

    AutoCompleteTextView dst, bld;
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
                searchView2.setQuery(two + " " + one, false);
                searchView2.clearFocus();
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
                searchView2.setQuery(two + " " + one, false);
                searchView2.clearFocus();
            }
        });

        //listview.setFilterText(bld.getText().toString());
        //searchView2.setQuery(bld.getText().toString(), false);
        //searchView2.clearFocus();

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
            dst.setText("District");
            bld.setText("Bg");
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