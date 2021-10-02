package com.example.infinitybloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    String phnkey, nmkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        phnkey = i.getStringExtra("phone");
        nmkey = i.getStringExtra("name");
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
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

    public void InfoButtonClick(View v){
        Intent i = new Intent(dashboard.this , Info.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_about:
                Intent intent = new Intent(dashboard.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.nav_contact:
                Intent intent1 = new Intent(dashboard.this, Instructions.class);
                startActivity(intent1);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}