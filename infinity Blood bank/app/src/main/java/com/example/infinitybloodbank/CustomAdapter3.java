package com.example.infinitybloodbank;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CustomAdapter3 extends ArrayAdapter<Request>{
    private List<Request> requestlist;
    private Activity context;
    public int flag, pr = 0;

    public CustomAdapter3(Activity context, List<Request> requestlist) {
        super(context, R.layout.sample_layout3, requestlist);
        this.context = context;
        this.requestlist = requestlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout3, null, true);

        flag = position;
        Request request = requestlist.get(position);
        TextView t1 = view.findViewById(R.id.mrblood);
        TextView t2 = view.findViewById(R.id.mrloc);
        TextView t3 = view.findViewById(R.id.mrreas);
        TextView t4 = view.findViewById(R.id.mrnumber);
        TextView t5 = view.findViewById(R.id.mrdetails);
        Button bt = view.findViewById(R.id.del);
        Button bt2 = view.findViewById(R.id.up);

        t1.setText("Blood Group: " + request.bg + "            Clicked: " + String.valueOf(request.clicked));
        t2.setText("Location: "+request.location);
        t3.setText("Reason: "+request.reason);
        t4.setText("Number: +880"+request.phone + " (" + request.name + ")");
        t5.setText("District: "+request.district  + "       Age: "+request.age + "      Gender: "+request.gender);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseDatabase.getInstance().getReference("allRequest").child(requestlist.get(position).key).child(requestlist.get(position).status).setValue("No");

                FirebaseDatabase.getInstance().getReference("allRequest").child(requestlist.get(position).key).removeValue();
                Toast.makeText(context, "Request Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseDatabase.getInstance().getReference("allRequest").child(requestlist.get(position).key).child(requestlist.get(position).status).setValue("No");
                /*FirebaseDatabase.getInstance().getReference("allRequest").child(requestlist.get(position).key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.child("")
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(context, "Request Deleted", Toast.LENGTH_SHORT).show();*/
                String uid = requestlist.get(position).key;
                Intent i = new Intent(context, EditRequest.class);
                i.putExtra("uid", uid);
                context.finish();
                context.startActivity(i);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //parent.getChildAt(pr).setBackgroundColor(Color.WHITE);
                //parent.getChildAt(position).setBackgroundColor(0xFFEFBBCD);
                String s = "Blood Group: " + requestlist.get(position).bg;
                String s1 = "Location: " + requestlist.get(position).location + ", " + requestlist.get(position).district;
                String s2 = "Reason: " + requestlist.get(position).reason;
                String s3 = "Age: " + requestlist.get(position).age  + " (" + requestlist.get(position).gender+ ")";
                String s4 = "Number: +880" + requestlist.get(position).phone + " (" + requestlist.get(position).name+ ")";
                String s5 = "#infinty_Blood_bank";

                String ss = s + System.lineSeparator() + s1 + System.lineSeparator() + s2 + System.lineSeparator() + s3 + System.lineSeparator() + s4 + System.lineSeparator() + System.lineSeparator() + s5;

                ClipboardManager cm = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(ss);

                Toast.makeText(context, "Information copied to clipboard", Toast.LENGTH_SHORT).show();
                pr = position;
                return false;
            }
        });
        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return requestlist.size();
    }

    @Override
    public Request getItem(int position) {
        return requestlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*private View.OnClickListener mMyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+"+880"+requestlist.get(flag).phone));
            context.startActivity(intent);
        } };*/
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