package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class paymentActivity extends AppCompatActivity {
    TextView q;
    cartdb d = new cartdb(this);
    Long t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        q = (TextView) findViewById(R.id.TotalPrice);
        t = getcartitem();
        String s = "Your purchase amount is " + Long.toString(t) + ". Please select anyone option for payment method.";
        q.setText(s);

    }

    public long getcartitem() {
        int j;
        long total = 0;
        String selectQ = "select price from cart";
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {

                j = Integer.parseInt(c.getString(c.getColumnIndex("price")));
                total = total + j;

            } while (c.moveToNext());

        }

        c.close();
        db.close();

        return total;
    }
    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.cod:
                if (checked) {
                    Intent c = new Intent(paymentActivity.this, CODActivity.class);
                    c.putExtra("price",Long.toString(t));
                    startActivity(c);
                }
                break;
            case R.id.card:
                if (checked){
                    Intent c = new Intent(paymentActivity.this,CardActivity.class);
                    c.putExtra("price",Long.toString(t));
                    startActivity(c);
                }
                break;
        }
    }


}
