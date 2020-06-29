package com.grocery.daniel.kitchenkart;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomerActivity extends AppCompatActivity {
    LinearLayout detailview, forlistview;
    TextView nam, address, mail, mobile, labelcid;
    String NAME, ADDRESS, MAIL, ID,MOBILE;
    Button submit, viewall;
    Spinner spinner;
    ListView v4;
    DBHelper d = new DBHelper(this);
    List<Integer> list = new ArrayList<Integer>();
    ArrayList<String> dname = new ArrayList<String>(50);
    ArrayList<String> daddress = new ArrayList<String>(50);
    ArrayList<String> demail = new ArrayList<String>(50);
    ArrayList<String> dmobile = new ArrayList<String>(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        additemtospinner();
        detailview = (LinearLayout) findViewById(R.id.detailview);
        forlistview = (LinearLayout) findViewById(R.id.forlistview);
        detailview.setVisibility(View.INVISIBLE);
        forlistview.setVisibility(View.INVISIBLE);
        nam = (TextView) findViewById(R.id.usname);
        address = (TextView) findViewById(R.id.addres);
        mail = (TextView) findViewById(R.id.emailid);
        mobile = (TextView) findViewById(R.id.Mobile);
        submit = (Button) findViewById(R.id.submit);
        viewall = (Button) findViewById(R.id.viewall);
        labelcid = (TextView) findViewById(R.id.labelcid);

/*
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),""+label,Toast.LENGTH_LONG).show();
            }
        });
        */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();
                getselectedcustomer();
                nam.setText(NAME);
                mail.setText(MAIL);
                mobile.setText(MOBILE);
                address.setText(ADDRESS);
                forlistview.setVisibility(View.INVISIBLE);
                detailview.setVisibility(View.VISIBLE);

            }
        });


        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // labelcid.setVisibility(View.INVISIBLE);
                //spinner.setVisibility(View.INVISIBLE);
                //submit.setVisibility(View.INVISIBLE);
                //viewall.setVisibility(View.INVISIBLE);
                detailview.setVisibility(View.INVISIBLE);
                forlistview.setVisibility(View.VISIBLE);
                getalluserdetail();
                customeradapter Ad = new customeradapter(ViewCustomerActivity.this, dname, daddress, demail, dmobile);
                v4 = (ListView) findViewById(R.id.listview4);
                v4.setAdapter(Ad);

            }
        });
    }


    public void additemtospinner() {

        spinner = (Spinner) findViewById(R.id.spinner);
        getiduser();
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


    public void getiduser() {
        String selectQ = "select id from user";
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();
        list.clear();
        if (c.getCount() > 0) {
            do {

                list.add(c.getInt(c.getColumnIndex("id")));

            } while (c.moveToNext());

        }
        c.close();
        db.close();


    }

    public void getselectedcustomer() {

        String selectQ = "SELECT name, address, email, mobile FROM user WHERE id = ?";
        SQLiteDatabase DB = d.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, new String[]{ID + ""});
        if (c.getCount() > 0) {
            c.moveToFirst();

            MAIL = c.getString(c.getColumnIndex("email"));
            NAME = c.getString(c.getColumnIndex("name"));
            ADDRESS = c.getString(c.getColumnIndex("address"));
            MOBILE = c.getString(c.getColumnIndex("mobile"));
        }
        c.close();
        d.close();

    }


    public void getalluserdetail() {
        String selectQ = "select name,email,address,mobile from user";
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();
        dname.clear();
        daddress.clear();
        demail.clear();
        dmobile.clear();
        if (c.getCount() > 0) {
            do {
                dname.add(c.getString(c.getColumnIndex("name")));
                dmobile.add(c.getString(c.getColumnIndex("mobile")));
                demail.add(c.getString(c.getColumnIndex("email")));
                daddress.add(c.getString(c.getColumnIndex("address")));

            } while (c.moveToNext());

        }

        c.close();
        db.close();


    }

}
