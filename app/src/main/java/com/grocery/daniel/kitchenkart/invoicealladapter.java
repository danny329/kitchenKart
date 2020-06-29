package com.grocery.daniel.kitchenkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DANIEL on 18-03-2017.
 */

public class invoicealladapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> NAME;
    private ArrayList<String> STATUS;
    private  ArrayList<Integer> TOTAL;
    private  ArrayList<Integer> ID;

    public invoicealladapter(Context c,ArrayList<String> n,ArrayList<String> s,ArrayList<Integer> t,ArrayList<Integer> i)
    {
        context = c;
        this.NAME = n;
        this.STATUS = s;
        this.TOTAL =t;
        this.ID =i;
    }

    public class ViewHolder {
        TextView nname;
        TextView nstatus;
        TextView ntotal;
        TextView nid;


    }


    @Override
    public int getCount() {
        return NAME.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        final ViewHolder Hold, hold2;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Hold = new ViewHolder();
        if (v == null) {
            v = inflater.inflate(R.layout.invoicealllistview, null);
            v.setTag(Hold);
        } else {
            hold2 = (ViewHolder) v.getTag();
            //hold2.title.setText(getItem(position));
        }

        Hold.nname = (TextView) v.findViewById(R.id.usNAME);
        Hold.nstatus = (TextView) v.findViewById(R.id.STATUS);
        Hold.nid = (TextView) v.findViewById(R.id.usID);
        Hold.ntotal = (TextView) v.findViewById(R.id.TOTAL);


        Hold.nname.setText(NAME.get(position));
        Hold.ntotal.setText((Integer.toString(TOTAL.get(position))));
        Hold.nid.setText((Integer.toString(ID.get(position))));
        Hold.nstatus.setText(STATUS.get(position));



        return v;
    }

}
