package com.example.infinitybloodbank;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class phoneotp extends AppCompatActivity {

    //three objects needed
    //this is the verification id that will be sent to the user
    private String mVerificationId;

    String name, phone, pass, district, blood;

    private com.google.android.material.textfield.TextInputEditText otpcode;

    private FirebaseAuth auth;
    Button submitBtn;
    private String OTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneotp);

        //initializing objects
        auth = FirebaseAuth.getInstance();
        otpcode = findViewById(R.id.otptxt);
        submitBtn = findViewById(R.id.submitotp);

        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent i = getIntent();
        String mobile = i.getStringExtra("phone");
        phone = mobile;
        name = i.getStringExtra("name");
        pass = i.getStringExtra("pass");
        blood = i.getStringExtra("blood");
        district = i.getStringExtra("district");


    }
    public void SubmitButtonClick(View v){
        String verification_code = otpcode.getText().toString();
        if(!verification_code.isEmpty()){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code, verification_code);
            signIn(credential);
            System.out.println("hi  4");
        }else{
            Toast.makeText(phoneotp.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
            System.out.println("hi  5");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user !=null){
            Intent mainIntent = new Intent(phoneotp.this , dashboard.class);
            startActivity(mainIntent);
            finish();
        }
    }
    private void signIn(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    System.out.println("hi 2");
                    String nn = "Yes";
                    User user = new User(phone, name, pass, district, blood, nn);
                    FirebaseDatabase.getInstance().getReference("Users").child(phone).setValue(user);
                    Intent mainIntent = new Intent(phoneotp.this , dashboard.class);
                    startActivity(mainIntent);
                    finish();
                }else{
                    System.out.println("hi  1");
                }
            }
        });
    }

}