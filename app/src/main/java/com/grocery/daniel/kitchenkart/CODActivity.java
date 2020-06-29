package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CODActivity extends AppCompatActivity {
    TextView t;
    Button h;
    private currentuserDB Db = new currentuserDB(this);
    private InvoiceDB DB=new InvoiceDB(this);
    String name, adres, status;
    Integer cid, amt,mob, contct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod);
        t = (TextView) findViewById(R.id.textView);
        String s = "Your order have been place.\ntotal amount = " + getIntent().getStringExtra("price") + ".\n Purchased item will reach at your destination in one day.";
        t.setText(s);
        status = "PENDING";
        amt = Integer.parseInt(getIntent().getStringExtra("price"));
        addtoinvoice();
        h=(Button)findViewById(R.id.HOME);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CODActivity.this,categoryMain.class);
                startActivity(i);
            }
        });
    }

    public void addtoinvoice() {
        getusrdetail();
        DB.addinvoice(cid,name,adres,contct,amt,status);

    }

    public void getusrdetail() {

        String selectQ = "SELECT usid,Name,address,mob FROM currentuser";
        SQLiteDatabase DB = Db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            cid = c.getInt(c.getColumnIndex("usid"));
            name = c.getString(c.getColumnIndex("Name"));
            adres = c.getString(c.getColumnIndex("address"));
            contct = c.getInt(c.getColumnIndex("mob"));
        }
        c.close();
        Db.close();

    }
}
