package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CardActivity extends AppCompatActivity {

    EditText holdername,expiry,cvv,cardnumber;
    String cname,exp,cardnum,CVV,checkcname,checkcvv,checkexpiry,name, adres, status;
    Button pay;
    int withdraw,bal;
    private bankDB db=new bankDB(this);
    private currentuserDB DK=new currentuserDB(this);
    private InvoiceDB DD =new InvoiceDB(this);
    Integer cid, amt, contct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        holdername=(EditText)findViewById(R.id.holdername);
        cardnumber=(EditText)findViewById(R.id.cardnumber);
        cvv=(EditText)findViewById(R.id.cvv);
        expiry=(EditText)findViewById(R.id.expiry);
        pay=(Button)findViewById(R.id.buttonpay);
        cname=holdername.getText().toString();
        cardnum=cardnumber.getText().toString();
        CVV=cvv.getText().toString();
        exp=expiry.getText().toString();
        withdraw= Integer.parseInt(getIntent().getStringExtra("price"));

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cname=holdername.getText().toString();
                cardnum=cardnumber.getText().toString();
                CVV=cvv.getText().toString();
                exp=expiry.getText().toString();

                getcardbal();
                Boolean cN = cname.equals(checkcname);
                Boolean cVV = CVV.equals(checkcvv);
                Boolean cex = exp.equals(checkexpiry);

                if (cname.isEmpty() || exp.isEmpty() || cardnum.isEmpty()||CVV.isEmpty()) {
                    displayToast("NO FIELD CAN BE EMPTY");
                }
                else if(!cN||!cex||!cVV){
                    displayToast("user doesn't exist");
                }
                else if(bal<withdraw){
                    displayToast("insufficient balance");
                }
                else {
                    bal=bal-withdraw;
                    SQLiteDatabase sqdb=db.getWritableDatabase();
                    ContentValues val=new ContentValues();


                    val.put("bal",bal);
                    long id=sqdb.update("banktable ",val,"cnumber = ?",new String[]{cardnum });

                    getusrdetail();
                    status = "SUCCESS";
                    addtoinvoice();

                    displayToast("Transaction complete.\ngo to order to see your purchase.");
                    Intent i=new Intent(CardActivity.this,categoryMain.class);
                    startActivity(i);

                }

            }
        });
    }
    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    public void getcardbal() {

        String selectQ = "SELECT cname,cvv,expiry,bal FROM banktable WHERE cnumber = ?";
        SQLiteDatabase DB = db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, new String[]{cardnum + ""});
        if (c.getCount() > 0) {
            c.moveToFirst();

            bal=c.getInt(c.getColumnIndex("bal"));
            checkcname=c.getString(c.getColumnIndex("cname"));
            checkcvv=c.getString(c.getColumnIndex("cvv"));
            checkexpiry=c.getString(c.getColumnIndex("expiry"));

        }
        c.close();
        db.close();

    }
    public void getusrdetail() {

        String selectQ = "SELECT usid,Name,address,mob FROM currentuser";
        SQLiteDatabase DN = DK.getReadableDatabase();
        Cursor c = DN.rawQuery(selectQ, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            cid = c.getInt(c.getColumnIndex("usid"));
            name = c.getString(c.getColumnIndex("Name"));
            adres = c.getString(c.getColumnIndex("address"));
            contct = c.getInt(c.getColumnIndex("mob"));
        }
        c.close();
        DN.close();

    }
    public void addtoinvoice() {
        getusrdetail();
        DD.addinvoice(cid,name,adres,contct,withdraw,status);

    }
}
