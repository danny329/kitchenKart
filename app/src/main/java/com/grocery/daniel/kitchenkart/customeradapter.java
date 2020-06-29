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

public class customeradapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> name;
    private ArrayList<String> address;
    private  ArrayList<String> email;
    private  ArrayList<String> Mobile;

    public customeradapter(Context c,ArrayList<String> n,ArrayList<String> a,ArrayList<String> e,ArrayList<String> m)
    {
        context = c;
        this.name = n;
        this.address = a;
        this.email =e;
        this.Mobile =m;
    }

    public class ViewHolder {
        TextView nname;
        TextView naddress;
        TextView nemail;
        TextView nMobile;


    }


    @Override
    public int getCount() {
        return name.size();
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
            v = inflater.inflate(R.layout.customerlistrow, null);
            v.setTag(Hold);
        } else {
            hold2 = (ViewHolder) v.getTag();
            //hold2.title.setText(getItem(position));
        }

        Hold.nname = (TextView) v.findViewById(R.id.uname);
        Hold.naddress = (TextView) v.findViewById(R.id.uaddress);
        Hold.nemail = (TextView) v.findViewById(R.id.uemail);
        Hold.nMobile = (TextView) v.findViewById(R.id.uMobile);


        Hold.nname.setText(name.get(position));
        Hold.nMobile.setText(Mobile.get(position));
        Hold.naddress.setText(address.get(position));
        Hold.nemail.setText(email.get(position));



        return v;
    }

}

