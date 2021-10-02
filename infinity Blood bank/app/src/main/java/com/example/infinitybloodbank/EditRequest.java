package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditRequest extends AppCompatActivity {

    AutoCompleteTextView dst, bld, gndr;
    com.google.android.material.textfield.TextInputEditText phone, name, location, age, reason;
    private FirebaseAuth mAuth;
    DatabaseReference ref;
    public Long clicked;
    String userphn, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_request);

        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);
        gndr = findViewById(R.id.gendertxt);

        phone = findViewById(R.id.phonetxt);
        name = findViewById(R.id.nametxt);
        location = findViewById(R.id.locationtxt);
        age = findViewById(R.id.agetxt);
        reason = findViewById(R.id.reasontxt);

        Intent i = getIntent();
        uid = i.getStringExtra("uid");

        FirebaseDatabase.getInstance().getReference("allRequest").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phone.setText(snapshot.child("phone").getValue().toString());
                name.setText(snapshot.child("name").getValue().toString());
                location.setText(snapshot.child("location").getValue().toString());
                age.setText(snapshot.child("age").getValue().toString());
                reason.setText(snapshot.child("reason").getValue().toString());
                dst.setText(snapshot.child("district").getValue().toString(), false);
                bld.setText(snapshot.child("bg").getValue().toString(), false);
                gndr.setText(snapshot.child("gender").getValue().toString(), false);
                clicked = (Long) snapshot.child("clicked").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("allRequest");


        String [] districts = {"Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati", "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur", "Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira", "Jamalpur", "Mymensingh",     "Netrakona","Sherpur", "Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj", "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon", "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};
        ArrayAdapter dstr = new ArrayAdapter(this, R.layout.district_item, districts);
        //dst.setText(dstr.getItem(0).toString(), false);
        dst.setAdapter(dstr);

        String [] bloods = {"B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        //bld.setText(bldg.getItem(0).toString(), false);
        bld.setAdapter(bldg);

        String [] ages = { "Male", "Female", "Others"};
        ArrayAdapter ag = new ArrayAdapter(this, R.layout.district_item, ages);
        //bld.setText(bldg.getItem(0).toString(), false);
        gndr.setAdapter(ag);
    }

    public void SubmitButtonClick(View v){

        String phone_no = phone.getText().toString().trim();
        String full_name = name.getText().toString().trim();
        String blood = bld.getText().toString().trim();
        String district = dst.getText().toString().trim();
        String gen = gndr.getText().toString().trim();
        String Age = age.getText().toString().trim();
        String loc = location.getText().toString().trim();
        String reas = reason.getText().toString().trim();

        if(phone_no.isEmpty() || phone_no.length()!=10){
            phone.setError("Enter a phone number");
            phone.requestFocus();
            return;
        }

        if(!Patterns.PHONE.matcher(phone_no).matches()){
            phone.setError("Enter a valid phone number");
            phone.requestFocus();
            return;
        }

        if(full_name.isEmpty()) {
            name.setError("Enter your name");
            name.requestFocus();
            return;
        }

        if(blood.equals("Select Blood Group")) {
            bld.setError("Select your Blood Group");
            bld.requestFocus();
            return;
        }

        if(district.equals("Select District")) {
            dst.setError("Select your District");
            dst.requestFocus();
            return;
        }
        if(gen.equals("Select Gender")) {
            gndr.setError("Select Gender");
            gndr.requestFocus();
            return;
        }

        if(reas.isEmpty()) {
            reason.setError("What Reason?");
            reason.requestFocus();
            return;
        }

        if(loc.isEmpty()) {
            location.setError("Enter your location");
            location.requestFocus();
            return;
        }

        if(Age.isEmpty()) {
            age.setError("Enter patients age");
            age.requestFocus();
            return;
        }
        System.out.println("here 1");
        String key = uid;
        String var = "Yes";
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String nm = currentUser.getEmail();
        String userphn = nm.substring(0, 10);
        String poster = userphn;
        System.out.println(poster);
        Request req = new Request(full_name, phone_no, blood, loc, district, gen, Age, reas, var, poster, key, clicked);
        //ref.child(key).setValue(req);
        FirebaseDatabase.getInstance().getReference("allRequest").child(key).setValue(req);
        Toast.makeText(EditRequest.this,"Request updated.",Toast.LENGTH_LONG).show();


        Intent i = new Intent(EditRequest.this , MyRequest.class);
        /*i.putExtra("pass", password);
        i.putExtra("name", full_name);
        i.putExtra("blood", blood);
        i.putExtra("district", district);*/
        this.finish();
        startActivity(i);
    }
}