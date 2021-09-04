package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;
//import java.util.logging.Handler;


public class CreateAccount extends AppCompatActivity {

    AutoCompleteTextView dst, bld;
    com.google.android.material.textfield.TextInputEditText phone, pass, name;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    String otpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dst = findViewById(R.id.districttxt);
        bld = findViewById(R.id.bloodtxt);

        phone = findViewById(R.id.phonetxt);
        pass = findViewById(R.id.passwordtxt);
        name = findViewById(R.id.nametxt);
        mAuth = FirebaseAuth.getInstance();

        String [] districts = {"Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati", "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur", "Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira", "Jamalpur", "Mymensingh",     "Netrakona","Sherpur", "Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj", "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon", "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};
        ArrayAdapter dstr = new ArrayAdapter(this, R.layout.district_item, districts);
        //dst.setText(dstr.getItem(0).toString(), false);
        dst.setAdapter(dstr);

        String [] bloods = {"B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        //bld.setText(bldg.getItem(0).toString(), false);
        bld.setAdapter(bldg);
    }

    public void SubmitButtonClick(View v){
        String phone_no = phone.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String full_name = name.getText().toString().trim();
        String blood = bld.getText().toString().trim();
        String district = dst.getText().toString().trim();

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

        User user = new User(phone_no, full_name, password, district, blood);
        FirebaseDatabase.getInstance().getReference("Users").child(phone_no).setValue(user);

        mAuth.createUserWithEmailAndPassword(phone_no+"@gmail.com", password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccount.this,"Registration Complete",Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(CreateAccount.this, dashboard.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(CreateAccount.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        //Intent i = new Intent(CreateAccount.this , Login.class);
        /*i.putExtra("phone", phone_no);
        i.putExtra("pass", password);
        i.putExtra("name", full_name);
        i.putExtra("blood", blood);
        i.putExtra("district", district);*/
        //startActivity(i);
    }



}