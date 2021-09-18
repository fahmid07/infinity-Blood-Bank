package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button in, up, skp, frgt;
    com.google.android.material.textfield.TextInputEditText phone, pass;
    FirebaseUser currentUser;
    private DatabaseReference ref, ref2;
    private FirebaseAuth auth;
    private ProgressBar progressB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        in = findViewById(R.id.signin);
        up = findViewById(R.id.createac);
        skp = findViewById(R.id.skip);
        frgt = findViewById(R.id.forgot);

        progressB = findViewById(R.id.progressBar);
        phone = findViewById(R.id.usernametxt);
        pass = findViewById(R.id.passwordtxt);
        ref = FirebaseDatabase.getInstance().getReference("Users");
        ref2 = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, dashboard.class);
            /*String ph = currentUser.getDisplayName();
            String phn = "";
            for(int i=0; i<ph.length(); i++){
                phn += ph[i];
            }
            ref2.child(phone_no).addListenerForSingleValueEvent(listener2);*/
            startActivity(intent);
            finish();
        }
    }

    public void CreateButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

    public void SkipButtonClick(View v){
        Intent i = new Intent(this, dashboard.class);
        startActivity(i);

        /*AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(MyActivity.this, SignInActivity.class));
                        finish();
                    }
                }); */
    }

    public void SignButtonClick(View v){
        progressB.setVisibility(View.VISIBLE);
        String phone_no = phone.getText().toString();
        String passW = pass.getText().toString();

        if(phone_no.isEmpty() || phone_no.length()!=10){
            phone.setError("Enter a phone number");
            phone.requestFocus();
            return;
        }

        if(passW.equals("")) {
            pass.setError("Enter a password");
            pass.requestFocus();
            return;
        }
        System.out.println("here 3");
        ref.child(phone_no).addListenerForSingleValueEvent(listener);
        System.out.println("here 2");
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            System.out.println("hay 1");
            if(snapshot.exists()){
                String password = snapshot.child("pass").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                System.out.println("here 11");
                String passW = pass.getText().toString();
                String phone_no = phone.getText().toString();
                if(password.equals(passW)){
                    System.out.println("here 12");
                    auth.signInWithEmailAndPassword(phone_no+"@gmail.com", password)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Toast.makeText(Login.this, e.toString(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressB.setVisibility(View.GONE);
                                    finish();
                                    Intent i = new Intent(Login.this, dashboard.class);
                                    i.putExtra("phone", phone_no);
                                    i.putExtra("name", name);
                                    startActivity(i);
                                }
                            });

                }
            }
            else System.out.println("here 1");
        }

        @Override
        public void onCancelled(@NonNull  DatabaseError error) {
            Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
            System.out.println("hehere 1");
        }
    };

    /*ValueEventListener listener2 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            System.out.println("hay 1");
            if(snapshot.exists()){
                String password = snapshot.child("pass").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                System.out.println("here 11");
                String passW = pass.getText().toString();
                String phone_no = phone.getText().toString();
                if(password.equals(passW)){
                    System.out.println("here 12");
                    auth.signInWithEmailAndPassword(phone_no+"@gmail.com", password)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Toast.makeText(Login.this, e.toString(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressB.setVisibility(View.GONE);
                                    finish();
                                    Intent i = new Intent(Login.this, dashboard.class);
                                    i.putExtra("phone", phone_no);
                                    i.putExtra("name", name);
                                    startActivity(i);
                                }
                            });

                }
            }
            else System.out.println("here 1");
        }

        @Override
        public void onCancelled(@NonNull  DatabaseError error) {
            Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
            System.out.println("hehere 1");
        }
    };*/

    public void ForgotButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }
}