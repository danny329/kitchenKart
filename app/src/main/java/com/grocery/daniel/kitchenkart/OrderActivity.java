package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    cartdb cart = new cartdb(this);
    currentuserDB Db = new currentuserDB(this);
    OrderDB odb = new OrderDB(this);
    ListView v2;
    int i, j, custid;
    Button purchase;
    ArrayList<String> title = new ArrayList<String>(50);
    ArrayList<Integer> P = new ArrayList<Integer>(50);
    ArrayList<Integer> Cont = new ArrayList<Integer>(50);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getcartitem();

        orderadapter adapter = new orderadapter(OrderActivity.this, title, P, Cont);
        v2 = (ListView) findViewById(R.id.listview3);
        v2.setAdapter(adapter);
        v2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });


        getusrid();

        purchase = (Button) findViewById(R.id.purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmorder();
                Intent u = new Intent(OrderActivity.this, shipmentActivity.class);
                startActivity(u);
            }
        });
    }


    public void getcartitem() {
        String selectQ = "select name,price,count from cart";
        SQLiteDatabase db = cart.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();
        title.clear();
        P.clear();
        Cont.clear();
        if (c.getCount() > 0) {
            do {
                title.add(c.getString(c.getColumnIndex("name")));
                j = Integer.parseInt(c.getString(c.getColumnIndex("price")));
                P.add(j);
                i = Integer.parseInt(c.getString(c.getColumnIndex("count")));
                Cont.add(i);

            } while (c.moveToNext());

        }

        c.close();
        db.close();


    }


    public void confirmorder() {
        getcartitem();
       //Toast.makeText(this,""+g.getData(),Toast.LENGTH_LONG).show();
        for (int m = 0; m < P.size(); m++) {
            odb.addorder(custid, title.get(m), P.get(m), Cont.get(m));
        }


      // odb.removeorder();


    }


    public void getusrid() {

        String selectQ = "SELECT usid FROM currentuser";
        SQLiteDatabase DB = Db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            custid = c.getInt(c.getColumnIndex("usid"));
        }
        c.close();
        Db.close();

    }

}
