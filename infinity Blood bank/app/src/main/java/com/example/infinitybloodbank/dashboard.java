package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class dashboard extends AppCompatActivity {

    String phnkey, nmkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent i = getIntent();
        phnkey = i.getStringExtra("phone");
        nmkey = i.getStringExtra("name");
    }

    public void RequestButtonClick(View v){

        Intent i = new Intent(dashboard.this , MakeRequest.class);
        i.putExtra("phone", phnkey);
        /*i.putExtra("pass", password);
        i.putExtra("name", full_name);
        i.putExtra("blood", blood);
        i.putExtra("district", district);*/
        startActivity(i);
    }

    public void AllRequestButtonClick(View v){
        Intent i = new Intent(dashboard.this , AllRequest.class);
        i.putExtra("phone", phnkey);
        startActivity(i);
    }

    public void AllUsersButtonClick(View v){
        Intent i = new Intent(dashboard.this , all_user.class);
        i.putExtra("phone", phnkey);
        startActivity(i);
    }

    public void AccountButtonClick(View v){

        Intent i = new Intent(dashboard.this , Account.class);
        i.putExtra("phone", phnkey);
        i.putExtra("name", nmkey);
        startActivity(i);
    }
}