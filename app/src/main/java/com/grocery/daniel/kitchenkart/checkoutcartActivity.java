package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.Attributes;

import static com.grocery.daniel.kitchenkart.cartdb.CART_TABLE;
import static com.grocery.daniel.kitchenkart.cartdb.COLUMN_TITLE;

public class checkoutcartActivity extends AppCompatActivity {
    ListView v2;
    ArrayList<String> Name=new ArrayList<String>(50);
    ArrayList<Integer> Price=new ArrayList<Integer>(50);
    ArrayList<Integer> Count=new ArrayList<Integer>(50);
    int i,j;
    cartdb cart=new cartdb(this);
    Button b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutcart);
        getcartitem();
        cartcustomlist adapter = new cartcustomlist(checkoutcartActivity.this, Name, Price, Count);
        v2 = (ListView) findViewById(R.id.listview2);
        v2.setAdapter(adapter);

        b=(Button) findViewById(R.id.proceed);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* OrderActivity oa=new OrderActivity();
                cartcustomlist a=new cartcustomlist();
                int length=a.Itemname.size();
                for(int j=0;j<length;j++)
                {
                    String s,s1;
                    s=null;
                    s1=null;
                    s=Integer.toString(a.Price.get(j));
                    s1=Integer.toString(a.Counter.get(j));
                        DB.addorder(a.Itemname.get(j),s,s1);
                    DB.close();
                }
                */

                Intent n= new Intent(checkoutcartActivity.this,OrderActivity.class);
                startActivity(n);
            }
        });
    }

    public void getcartitem(){
        String selectQ="select name,price,count from cart";
        SQLiteDatabase db=cart.getReadableDatabase();
        Cursor c=db.rawQuery(selectQ,null);
        c.moveToFirst();
        Name.clear();
        Price.clear();
        Count.clear();
        if(c.getCount()>0)
        {
            do {
                Name.add(c.getString(c.getColumnIndex("name")));
                j=Integer.parseInt(c.getString(c.getColumnIndex("price")));
                Price.add(j);
                i=Integer.parseInt(c.getString(c.getColumnIndex("count")));
                Count.add(i);

            }while (c.moveToNext());

        }

        c.close();
        db.close();


    }

}

