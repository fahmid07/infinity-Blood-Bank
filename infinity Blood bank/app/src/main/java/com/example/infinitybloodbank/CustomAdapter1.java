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

import static android.content.Context.CLIPBOARD_SERVICE;

public class CustomAdapter1 extends ArrayAdapter<Request> implements Filterable {
    private List<Request> requestlist;
    private List<Request> orig;
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
        t4.setText("Number: +880"+request.phone + " (" + request.name + ")");
        t5.setText("District: "+request.district  + "       Age: "+request.age + "      Gender: "+request.gender);


        //view.setOnClickListener(mMyButtonClickListener);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"+880"+requestlist.get(position).phone));
                context.startActivity(intent);


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
                final List<Request> results = new  ArrayList<Request>();
                if (orig == null)
                    orig = requestlist;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        results.clear();
                        for (final Request g : orig) {
                            String t1 = g.district + ", " + g.bg;
                            String t2 = g.district + "," + g.bg;
                            String t3 = g.bg + ", " + g.district;
                            String t4 = g.bg + "," + g.district;
                            String t5 = g.bg + " " + g.district;
                            String t6 = g.district + " " + g.bg;
                            if (g.district
                                    .contains(constraint.toString()))
                                results.add(g);
                            else if (g.bg
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
                requestlist = (List<Request>) results.values;
                notifyDataSetChanged();
            }
        };
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