package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button in, up, skp, frgt;
    com.google.android.material.textfield.TextInputEditText phone, pass;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        in = findViewById(R.id.signin);
        up = findViewById(R.id.createac);
        skp = findViewById(R.id.skip);
        frgt = findViewById(R.id.forgot);

        phone = findViewById(R.id.usernametxt);
        pass = findViewById(R.id.passwordtxt);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, dashboard.class);
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
    }

    public void SignButtonClick(View v){
        Intent i = new Intent(this, dashboard.class);
        startActivity(i);
    }

    public void ForgotButtonClick(View v){
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }
}