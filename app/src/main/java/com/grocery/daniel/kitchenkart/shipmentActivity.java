package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class shipmentActivity extends AppCompatActivity {
    currentuserDB Db=new currentuserDB(this);
    TextView username, address, contact;
    Button confirm;
    String name, adres,email, contct;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);
        getusrdetail();
        username = (TextView) findViewById(R.id.Username);
        address = (TextView) findViewById(R.id.Address);
        contact = (TextView) findViewById(R.id.contact);
        confirm = (Button) findViewById(R.id.next);
        username.setText(name);
        address.setText(adres);
        contact.setText(contct);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent u=new Intent(shipmentActivity.this,paymentActivity.class);
                startActivity(u);

            }
        });

    }
    public void getusrdetail() {

        String selectQ = "SELECT Name,mail,address,mob FROM currentuser";
        SQLiteDatabase DB = Db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            name = c.getString(c.getColumnIndex("Name"));
            email = c.getString(c.getColumnIndex("mail"));
            adres = c.getString(c.getColumnIndex("address"));
            contct = c.getString(c.getColumnIndex("mob"));
        }
        c.close();
        Db.close();

    }



}
