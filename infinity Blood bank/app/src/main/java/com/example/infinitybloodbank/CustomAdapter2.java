package com.example.infinitybloodbank;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<User> implements Filterable {
    private List<User> userlist;
    private List<User> orig;
    private Activity context;
    public int flag, pr  = 0;

    public CustomAdapter2(Activity context, List<User> userlist) {
        super(context, R.layout.sample_layout2, userlist);
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout2, null, true);

        flag = position;
        User user = userlist.get(position);
        TextView t1 = view.findViewById(R.id.apname);
        TextView t2 = view.findViewById(R.id.apblood);
        TextView t3 = view.findViewById(R.id.apdistrict);
        TextView t4 = view.findViewById(R.id.apnumber);

        t1.setText("Name: " + user.name);
        t2.setText("Blood Group: "+user.blood);
        t3.setText("Distrct: "+user.district);
        t4.setText("Number: +880"+user.phone);


        //view.setOnClickListener(mMyButtonClickListener);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "Name: " + userlist.get(position).name;
                String s1 = "Blood Group: " + userlist.get(position).blood;
                String s2 = "District: " + userlist.get(position).district;
                String s3 = "Number: +880" + userlist.get(position).phone;
                String s4 = "#infinty_Blood_bank";

                String ss = s + System.lineSeparator() + s1 + System.lineSeparator() + s2 + System.lineSeparator() + s3 + System.lineSeparator() + System.lineSeparator() + s4;

                ClipboardManager cm = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(ss);

                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"+880"+userlist.get(position).phone));
                context.startActivity(intent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //parent.getChildAt(pr).setBackgroundColor(Color.WHITE);
                //parent.getChildAt(position).setBackgroundColor(0xFFEFBBCD);
                String s = "Name: " + userlist.get(position).name;
                String s1 = "Blood Group: " + userlist.get(position).blood;
                String s2 = "District: " + userlist.get(position).district;
                String s3 = "Number: +880" + userlist.get(position).phone;
                String s4 = "#infinty_Blood_bank";

                String ss = s + System.lineSeparator() + s1 + System.lineSeparator() + s2 + System.lineSeparator() + s3 + System.lineSeparator() + System.lineSeparator() + s4;

                ClipboardManager cm = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(ss);
                Toast.makeText(context, "Information copied to clipboard", Toast.LENGTH_SHORT).show();
                pr = position;
                //parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                return false;
            }
        });
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<User> results = new  ArrayList<User>();
                if (orig == null)
                    orig = userlist;
                if (constraint != null) {
                    results.clear();
                    if (orig != null && orig.size() > 0) {

                        for (final User g : orig) {

                            String t1 = g.district + ", " + g.blood;
                            String t2 = g.district + "," + g.blood;
                            String t3 = g.blood + ", " + g.district;
                            String t4 = g.blood + "," + g.district;
                            String t5 = g.blood + " " + g.district;
                            String t6 = g.district + " " + g.blood;
                            String t11 = g.district + ", " + g.blood+ " ";
                            String t21 = g.district + "," + g.blood+ " ";
                            String t31 = g.blood + ", " + g.district+ " ";
                            String t41 = g.blood + "," + g.district+ " ";
                            String t51 = g.blood + " " + g.district+ " ";
                            String t61 = g.district + " " + g.blood+ " ";
                            String t22 = g.district + " ";
                            String t32 = g.blood + " ";
                            if (g.district
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (g.blood
                                    .contains(constraint.toString()))
                                results.add(g);

                            else if (t1
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t2
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t3
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t4
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t5
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t6
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t11
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t21
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t31
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t41
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t51
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t61
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t22
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (t32
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                userlist = (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public User getItem(int position) {
        return userlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*private View.OnClickListener mMyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+"+880"+userlist.get(flag).phone));
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