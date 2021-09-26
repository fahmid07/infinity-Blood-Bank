package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    AutoCompleteTextView dst, bld, avyn;
    com.google.android.material.textfield.TextInputEditText phone, pass, name;
    private FirebaseAuth mAuth;
    private ProgressBar progressBB;
    String phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);
        avyn = findViewById(R.id.avlbld);

        pass = findViewById(R.id.passwordtxt);
        name = findViewById(R.id.nametxt);
        progressBB = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();

        String [] districts = {"Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati", "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur", "Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira", "Jamalpur", "Mymensingh",     "Netrakona","Sherpur", "Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj", "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon", "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};
        ArrayAdapter dstr = new ArrayAdapter(this, R.layout.district_item, districts);
        //dst.setText(dstr.getItem(0).toString(), false);
        dst.setAdapter(dstr);

        String [] bloods = {"B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        //bld.setText(bldg.getItem(0).toString(), false);
        bld.setAdapter(bldg);

        String [] yn = {"Yes", "No"};
        ArrayAdapter yna = new ArrayAdapter(this, R.layout.district_item, yn);
        //bld.setText(bldg.getItem(0).toString(), false);
        avyn.setAdapter(yna);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        String nm = currentUser.getEmail();
        String phn = nm.substring(0, 10);
        phone_no = phn;


        FirebaseDatabase.getInstance().getReference("Users").child(phn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
                dst.setText(snapshot.child("district").getValue().toString(), false);
                bld.setText(snapshot.child("blood").getValue().toString(), false);
                avyn.setText(snapshot.child("active").getValue().toString(),false);
                pass.setText(snapshot.child("pass").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void SubmitButtonClick(View v){
        progressBB.setVisibility(View.VISIBLE);
        String password = pass.getText().toString().trim();
        String full_name = name.getText().toString().trim();
        String blood = bld.getText().toString().trim();
        String district = dst.getText().toString().trim();
        String available = avyn.getText().toString().trim();


        if(password.isEmpty()) {
            pass.setError("Enter a password");
            pass.requestFocus();
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

        if(!(available.equals("Yes") || available.equals("No"))) {
            avyn.setError("Yes or No");
            avyn.requestFocus();
            return;
        }

        User user = new User(phone_no, full_name, password, district, blood, available);
        FirebaseDatabase.getInstance().getReference("Users").child(phone_no).setValue(user);


        progressBB.setVisibility(View.GONE);

        Toast.makeText(EditProfile.this,"Account updated.",Toast.LENGTH_LONG).show();


        Intent i = new Intent(EditProfile.this , Account.class);
        /*i.putExtra("pass", password);
        i.putExtra("name", full_name);
        i.putExtra("blood", blood);
        i.putExtra("district", district);*/
        this.finish();
        startActivity(i);
    }
}