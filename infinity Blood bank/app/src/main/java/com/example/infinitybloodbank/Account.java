package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Account extends AppCompatActivity {

    TextView t1, t2;
    String name, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        t1 = findViewById(R.id.logo_text3);
        t2 = findViewById(R.id.logo_text4);
        Intent i = getIntent();
        phone = i.getStringExtra("phone");
        name = i.getStringExtra("name");

        t1.setText(name);

        t2.setText("+880" + phone);
    }

    public void EditButtonClick(View v){
        Intent i = new Intent(this, all_user.class);
        startActivity(i);
    }


    public void YreqButtonClick(View v){
        Intent i = new Intent(this, AllRequest.class);
        startActivity(i);
    }

    public void SignOutButtonClick(View v){
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}