package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class CreateAccount extends AppCompatActivity {

    AutoCompleteTextView dst, bld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);

        String [] districts = {"Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati", "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur", "Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira", "Jamalpur", "Mymensingh",     "Netrakona","Sherpur", "Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj", "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon", "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};
        ArrayAdapter dstr = new ArrayAdapter(this, R.layout.district_item, districts);
        //dst.setText(dstr.getItem(0).toString(), false);
        dst.setAdapter(dstr);

        String [] bloods = {"B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        //bld.setText(bldg.getItem(0).toString(), false);
        bld.setAdapter(bldg);
    }
}