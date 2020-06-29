package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout detail, updates;
    String  tname, tmail, taddress, tpass, trepass, oldpass, tcnfrmoldpass;
    String  tcontact="";
    currentuserDB DB = new currentuserDB(this);
    DBHelper db = new DBHelper(this);
    Integer mobno,tusid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        detail = (LinearLayout) findViewById(R.id.details_of_user);
        updates = (LinearLayout) findViewById(R.id.update_detail);
        updates.setVisibility(View.INVISIBLE);
        getuserdetail();
        declaredetails();
    }

    public void declaredetails() {
        TextView NAME, EMAIL, ADDRESS, CONTACT;
        Button Edit;
        NAME = (TextView) findViewById(R.id.usname);
        EMAIL = (TextView) findViewById(R.id.emailid);
        ADDRESS = (TextView) findViewById(R.id.addres);
        CONTACT = (TextView) findViewById(R.id.Mobile);
        Edit = (Button) findViewById(R.id.buttonedit);
        NAME.setText(tname);
        EMAIL.setText(tmail);
        ADDRESS.setText(taddress);
        CONTACT.setText(tcontact);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setVisibility(View.INVISIBLE);
                updates.setVisibility(View.VISIBLE);
                updateinfo();
            }
        });

    }

    public void updateinfo() {
        final TextView  ADDRES, PASSWOR, PASSWORR, OLDPASSWOR;
        Button save;


        ADDRES = (TextView) findViewById(R.id.address);

        PASSWOR = (TextView) findViewById(R.id.password);
        OLDPASSWOR = (TextView) findViewById(R.id.oldpassword);
        PASSWORR = (TextView) findViewById(R.id.repeatpassword);
        save = (Button) findViewById(R.id.save_button);

        ADDRES.setText(taddress);
        OLDPASSWOR.setText("");
        PASSWOR.setText("");
        PASSWORR.setText("");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getusrpassword();
                tcnfrmoldpass = OLDPASSWOR.getText().toString();
                tpass = PASSWOR.getText().toString();
                trepass = PASSWORR.getText().toString();
                taddress = ADDRES.getText().toString();


                Boolean P = tpass.equals(trepass);
                Boolean OP = tcnfrmoldpass.equals(oldpass);




                if (tpass.isEmpty() || trepass.isEmpty() || taddress.isEmpty()) {
                    displayToast("NO FIELD CAN BE EMPTY");
                } else if (!OP) {
                    displayToast("OLD PASSWORD ENTER WRONG");
                } else if (!P) {
                    displayToast("PASSWORD MISMATCH");
                } else {

                   // db.updateDB(tname,tpass,tmail,taddress,mobno,tusid);


                    SQLiteDatabase sqdb=db.getWritableDatabase();
                    ContentValues val=new ContentValues();


                    val.put("password",tpass);
                    val.put("address",taddress);
                    //Toast.makeText(getApplicationContext(), ""+tusid, Toast.LENGTH_SHORT).show();
                    long id=sqdb.update("user ",val,"id = ?",new String[]{tusid + ""});

                    displayToast("user detail updated");
                    finish();
                }
            }
        });
    }

    public void getuserdetail() {
        String selectQ = "select usid,Name,mail,address,mob from currentuser";
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor c = db.rawQuery(selectQ, null);
        c.moveToFirst();

        if (c.getCount() > 0) {
            do {
                tusid = c.getInt(c.getColumnIndex("usid"));
                tcontact = c.getString(c.getColumnIndex("mob"));
                tname = c.getString(c.getColumnIndex("Name"));
                tmail = c.getString(c.getColumnIndex("mail"));
                taddress = c.getString(c.getColumnIndex("address"));

            } while (c.moveToNext());

        }
        c.close();
        db.close();


    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void getusrpassword() {

        String selectQ = "SELECT password FROM user WHERE id = ?";
        SQLiteDatabase DB = db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, new String[]{tusid + ""});
        if (c.getCount() > 0) {
            c.moveToFirst();

            oldpass = c.getString(c.getColumnIndex("password"));

        }
        c.close();
        db.close();

    }
}
