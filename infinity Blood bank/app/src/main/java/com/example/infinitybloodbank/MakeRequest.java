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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeRequest extends AppCompatActivity {

    AutoCompleteTextView dst, bld, gndr;
    com.google.android.material.textfield.TextInputEditText phone, name, location, age, reason;
    private FirebaseAuth mAuth;
    DatabaseReference ref;
    String userphn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);
        gndr = findViewById(R.id.gendertxt);

        phone = findViewById(R.id.phonetxt);
        name = findViewById(R.id.nametxt);
        location = findViewById(R.id.locationtxt);
        age = findViewById(R.id.agetxt);
        reason = findViewById(R.id.reasontxt);
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("allRequest");

        Intent i = getIntent();
        userphn = i.getStringExtra("phone");

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
        String key = ref.push().getKey();
        String var = "Yes";
        String poster = userphn;
        System.out.println(poster);
        Request req = new Request(full_name, phone_no, blood, loc, district, gen, Age, reas, var, poster, key);
        //ref.child(key).setValue(req);
        FirebaseDatabase.getInstance().getReference("allRequest").child(key).setValue(req);
        Toast.makeText(MakeRequest.this,"Request submitted.",Toast.LENGTH_LONG).show();


        Intent i = new Intent(MakeRequest.this , dashboard.class);
        i.putExtra("phone", userphn);
        /*i.putExtra("pass", password);
        i.putExtra("name", full_name);
        i.putExtra("blood", blood);
        i.putExtra("district", district);*/
        startActivity(i);
    }
}