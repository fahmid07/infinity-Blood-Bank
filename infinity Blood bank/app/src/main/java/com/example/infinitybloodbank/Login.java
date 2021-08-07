package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button in, up, skp, frgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        in = findViewById(R.id.signin);
        up = findViewById(R.id.createac);
        skp = findViewById(R.id.skip);
        frgt = findViewById(R.id.forgot);
    }

    public void CreateButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

    public void SkipButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

    public void SignButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

    public void ForgotButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }
}