package com.grocery.daniel.kitchenkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DANIEL on 20-03-2017.
 */

public class purchasedorderadapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> CID;
    private  ArrayList<String> CNAME;
    private  ArrayList<Integer> CTOTAL;
    private  ArrayList<String> CSTATUS;

    public purchasedorderadapter(Context c,ArrayList<Integer> i,ArrayList<String> n,ArrayList<Integer> t,ArrayList<String> s){
        this.context=c;
        this.CID=i;
        this.CNAME=n;
        this.CTOTAL=t;
        this.CSTATUS=s;
    }

    @Override
    public int getCount() {
        return CID.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView name;
        TextView id;
        TextView total;
        TextView status;
        Button cancel;

    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {

        final ViewHolder hold, hold2;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hold = new ViewHolder();
        if (v == null) {
            v = inflater.inflate(R.layout.purchasedlistrow, null);
            v.setTag(hold);
        } else {
            hold2 = (ViewHolder) v.getTag();
            //hold2.title.setText(getItem(position));
        }

        hold.id = (TextView) v.findViewById(R.id.invoiceid);
        hold.name = (TextView) v.findViewById(R.id.cname);
        hold.total = (TextView) v.findViewById(R.id.ctotal);
        hold.status = (TextView) v.findViewById(R.id.cstatus);
        hold.cancel = (Button) v.findViewById(R.id.ccancel);

        hold.id.setText(Integer.toString(CID.get(position)));
        hold.name.setText(CNAME.get(position));
        hold.total.setText(Integer.toString(CTOTAL.get(position)));
        hold.status.setText(CSTATUS.get(position));





        hold.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=hold.id.getText().toString();
                InvoiceDB in=new InvoiceDB(context);
                in.removeInvoiceDB(t1);
                in.close();
                CNAME.remove(position);
                CID.remove(position);
                CTOTAL.remove(position);
                CSTATUS.remove(position);
                notifyDataSetChanged();
            }
        });
        return v;
    }

}

