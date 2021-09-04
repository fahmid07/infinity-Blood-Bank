package com.example.infinitybloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter1 extends ArrayAdapter<Request> {
    private List<Request> requestlist;
    private Activity context;
    public int flag;

    public CustomAdapter1(Activity context, List<Request> requestlist) {
        super(context, R.layout.sample_layout, requestlist);
        this.context = context;
        this.requestlist = requestlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        flag = position;
        Request request = requestlist.get(position);
        TextView t1 = view.findViewById(R.id.arblood);
        TextView t2 = view.findViewById(R.id.arloc);
        TextView t3 = view.findViewById(R.id.arreas);
        TextView t4 = view.findViewById(R.id.arnumber);
        TextView t5 = view.findViewById(R.id.ardetails);

        t1.setText("Blood Group: " + request.bg);
        t2.setText("Location: "+request.location);
        t3.setText("Reason: "+request.reason);
        t4.setText("Number: +880"+request.phone);
        t5.setText("District: "+request.district  + "       Age: "+request.age + "      Gender: "+request.gender);


        view.setOnClickListener(mMyButtonClickListener);
        return view;
    }

    private View.OnClickListener mMyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+"+880"+requestlist.get(flag).phone));
            context.startActivity(intent);
        } };
}

/*

private Activity context;
    private List<Request> requestlist;

    public CustomAdapter1(Activity context, List<Request> requestlist) {
        super(context, R.layout.sample_layout, requestlist);
        this.context = context;
        this.requestlist = requestlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        Request request = requestlist.get(position);
        TextView t1 = view.findViewById(R.id.arblood);
        TextView t2 = view.findViewById(R.id.arloc);
        TextView t3 = view.findViewById(R.id.arreas);
        TextView t4 = view.findViewById(R.id.arnumber);
        TextView t5 = view.findViewById(R.id.ardetails);

        t1.setText("Blood Group: " + request.bg);
        t2.setText("Location: "+request.location);
        t3.setText("Reason: "+request.reason);
        t4.setText("Number: +880"+request.phone);
        t5.setText("District: "+request.district  + "       Age: "+request.age + "      Gender: "+request.gender);


        return view;
    }

    private void startActivity(Intent intent) {
    }
 */