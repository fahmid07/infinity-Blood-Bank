package com.example.infinitybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    TextView t1, t2, t3;
    AutoCompleteTextView bld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        bld = findViewById(R.id.bloodtxt);
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);

        String [] bloods = {"B+", "B-", "A+", "A-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter bldg = new ArrayAdapter(this, R.layout.district_item, bloods);
        bld.setAdapter(bldg);

        bld.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String one = bld.getText().toString();

                if(one.equals("B+")){
                    t1.setText("B+, AB+");
                    t2.setText("B+, B-, O+, O-");
                    t3.setText("Platelets, Plasma");
                }
                if(one.equals("B-")){
                    t1.setText("B-, B+, AB-, AB+");
                    t2.setText("B-, O-");
                    t3.setText("Double Red Cells, Whole Blood");
                }

                if(one.equals("A-")){
                    t1.setText("A-, A+, AB-, AB+");
                    t2.setText("A-, O-");
                    t3.setText("Double Red Cells, Whole Blood");
                }

                if(one.equals("A+")){
                    t1.setText("A+, AB+");
                    t2.setText("A+, A-, O+, O-");
                    t3.setText("Platelets, Plasma");
                }

                if(one.equals("O+")){
                    t1.setText("O+, A+, B+, AB+");
                    t2.setText("O+, O-");
                    t3.setText("Double Red Cells, Whole Blood");
                }

                if(one.equals("O-")){
                    t1.setText("All Blood Types");
                    t2.setText("O-");
                    t3.setText("Double Red Cells, Whole Blood");
                }

                if(one.equals("AB+")){
                    t1.setText("AB+");
                    t2.setText("All Blood Types");
                    t3.setText("Platelets, Plasma");
                }

                if(one.equals("AB-")){
                    t1.setText("AB-, AB+");
                    t2.setText("AB-, A-, B-, O-");
                    t3.setText("Platelets, Plasma");
                }
            }
        });
    }
}