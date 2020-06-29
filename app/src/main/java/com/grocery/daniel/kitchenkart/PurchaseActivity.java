package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {

    TextView q;
    InvoiceDB d=new InvoiceDB(this);
    currentuserDB DB=new currentuserDB(this);
    Long t;
    ArrayList<Integer> cID=new ArrayList<Integer>(50);
    ArrayList<String> cNAME=new ArrayList<String>(50);
    ArrayList<Integer> cTOTAL=new ArrayList<Integer>(50);
    ArrayList<String> cSTATUS=new ArrayList<String>(50);
    ListView v6;
    Integer cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        getcid();
        getinvoiceitem();
        purchasedorderadapter c=new purchasedorderadapter(PurchaseActivity.this,cID,cNAME,cTOTAL,cSTATUS);
        v6=(ListView)findViewById(R.id.listview6);
        v6.setAdapter(c);


    }
    public void getinvoiceitem(){
        String selectQ="select invoiceid,usrname,total,status from invoice  WHERE cid = ?";
        SQLiteDatabase db=d.getReadableDatabase();
        Cursor c=db.rawQuery(selectQ,new String[]{cid+""});
        c.moveToFirst();
        cID.clear();
        cNAME.clear();
        cTOTAL.clear();
        cSTATUS.clear();
        if(c.getCount()>0)
        {
            do {
                cID.add(c.getInt(c.getColumnIndex("invoiceid")));
                cTOTAL.add(c.getInt(c.getColumnIndex("total")));
                cNAME.add(c.getString(c.getColumnIndex("usrname")));
                cSTATUS.add(c.getString(c.getColumnIndex("status")));

            }while (c.moveToNext());

        }

        c.close();
        db.close();


    }

    public void getcid(){
        String selectQ="select usid from currentuser";
        SQLiteDatabase db=DB.getReadableDatabase();
        Cursor c=db.rawQuery(selectQ,null);
        c.moveToFirst();
        cid=c.getInt(c.getColumnIndex("usid"));
        c.close();
        db.close();
    }
}
