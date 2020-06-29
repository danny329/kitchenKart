package com.grocery.daniel.kitchenkart;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class InvoiceAllActivity extends AppCompatActivity {

    private ListView v5;
    ArrayList<String> lname = new ArrayList<String>(50);
    ArrayList<String> lstatus = new ArrayList<String>(50);
    ArrayList<Integer> ltotal = new ArrayList<Integer>(50);
    ArrayList<Integer> lid = new ArrayList<Integer>(50);
    private InvoiceDB d=new InvoiceDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_all);
        getallinvoicedetail();
        invoicealladapter Ad = new invoicealladapter(InvoiceAllActivity.this, lname, lstatus, ltotal, lid);
        v5 = (ListView) findViewById(R.id.listview5);
        v5.setAdapter(Ad);
    }


    public void getallinvoicedetail() {
        String selectQ = "select cid,usrname,total,status from invoice";
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();
        lname.clear();
        lstatus.clear();
        ltotal.clear();
        lid.clear();
        if (c.getCount() > 0) {
            do {
                lname.add(c.getString(c.getColumnIndex("usrname")));
                lid.add(c.getInt(c.getColumnIndex("cid")));
                ltotal.add(c.getInt(c.getColumnIndex("total")));
                lstatus.add(c.getString(c.getColumnIndex("status")));

            } while (c.moveToNext());

        }

        c.close();
        db.close();


    }
}
