package com.grocery.daniel.kitchenkart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class orderadapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> Itemname;
    private  ArrayList<Integer> Price;
    private  ArrayList<Integer> Counter;


    public orderadapter(Context c,ArrayList<String> itemname, ArrayList<Integer> price,ArrayList<Integer> counter) {

        context = c;
        this.Itemname = itemname;
        this.Price = price;
        this.Counter =counter;

    }


    public class ViewHolder {
        TextView name;
        TextView price;
        TextView Kg;


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Itemname.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(final int position, View v, ViewGroup parent) {

        final ViewHolder hold, hold2;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hold = new ViewHolder();
        if (v == null) {
            v = inflater.inflate(R.layout.orderlistrow, null);
            v.setTag(hold);
        } else {
            hold2 = (ViewHolder) v.getTag();
            //hold2.title.setText(getItem(position));
        }

        hold.name = (TextView) v.findViewById(R.id.title_name);
        hold.price = (TextView) v.findViewById(R.id.checkoutprice);
        hold.Kg = (TextView) v.findViewById(R.id.kg);


        hold.name.setText(Itemname.get(position));
        String s,s1;
        s=null;
        s1=null;
        s=Integer.toString(Price.get(position));
        s1=Integer.toString(Counter.get(position));
        hold.price.setText(s);
        hold.Kg.setText(s1);



        return v;
    }


}