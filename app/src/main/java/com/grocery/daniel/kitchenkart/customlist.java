package com.grocery.daniel.kitchenkart;

/**
 * Created by DANIEL on 30-12-2016.
 */


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.widget.Toast.*;
import static com.grocery.daniel.kitchenkart.R.id.CounterText;
import static com.grocery.daniel.kitchenkart.R.id.name;
import static java.security.AccessController.getContext;


public class customlist extends BaseAdapter {

    private Context context;
    private final String[] itemname;
    private final Integer[] imageId;
    private final String[] Price;
    private final Integer[] counter;
    private cartdb db;

    public customlist(Context c, String[] itemname, Integer[] imageId, String[] price, Integer[] counter,cartdb DB) {

        context = c;
        this.itemname = itemname;
        this.imageId = imageId;
        this.Price = price;
        this.counter = counter;
        db=DB;


    }


    public class ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        TextView Rsprice;
        TextView CounterText;
        //  Spinner spin;
        ImageButton addcart;
        ImageButton cart_minus_img;
        ImageButton cart_plus_img;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemname.length;
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
            v = inflater.inflate(R.layout.listrow, null);
            v.setTag(hold);
        } else {
            hold2 = (ViewHolder) v.getTag();
            //hold2.title.setText(getItem(position));
        }

        hold.name = (TextView) v.findViewById(R.id.from_name);
        hold.price = (TextView) v.findViewById(R.id.price);
        hold.Rsprice = (TextView) v.findViewById(R.id.Rs);
        hold.CounterText = (TextView) v.findViewById(R.id.CounterText);
        hold.img = (ImageView) v.findViewById(R.id.list_image);
        hold.cart_minus_img = (ImageButton) v.findViewById(R.id.cart_minus_img);
        hold.cart_plus_img = (ImageButton) v.findViewById(R.id.cart_plus_img);
        hold.addcart = (ImageButton) v.findViewById(R.id.addcartbutton);

        hold.name.setText(itemname[position]);
        hold.price.setText(Price[position]);
        hold.img.setImageResource(imageId[position]);
        hold.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View W) {


                if (counter[position] > 0) {
                    counter[position]--;
                }

                String l = new String();
                String pr = new String();
                l = Integer.toString(counter[position]);
                pr = (Price[position]);
                int s;
                s = Integer.parseInt(pr);
                s = s * counter[position];
                pr = Integer.toString(s);
                hold.CounterText.setText(l);
                hold.price.setText(pr);
               // counter[position]=0;
            }


        });
        hold.cart_plus_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (counter[position] < 100) {
                    counter[position]++;
                }
                String l = new String();
                String pr = new String();
                l = Integer.toString(counter[position]);
                pr = (Price[position]);
                int s;
                s = Integer.parseInt(pr);
                s = s * counter[position];
                pr = Integer.toString(s);
                hold.CounterText.setText(l);
                hold.price.setText(pr);
                //counter[position]=0;


            }


        });

        hold.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(hold.CounterText.getText().toString()) >= 1) {
                    setinfo(hold.name.getText().toString(),hold.price.getText().toString(), Integer.parseInt(hold.CounterText.getText().toString()));
                   // Toast.makeText(context, " content added ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }

    public void setinfo(String title, String prices, Integer kg) {


        String kgtocount = Integer.toString(kg);
        try {
            db.addcart(title, prices, kgtocount);
            Toast.makeText(context, " content added ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}