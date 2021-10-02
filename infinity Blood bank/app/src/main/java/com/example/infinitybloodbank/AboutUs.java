package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void call1(View v){
        Intent intent= new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+"+8801521533054"));
        startActivity(intent);
    }
    public void call2(View v){
        Intent intent= new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+"+8801790662626"));
        startActivity(intent);
    }
}