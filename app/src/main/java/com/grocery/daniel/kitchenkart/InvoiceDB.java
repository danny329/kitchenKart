package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 16-03-2017.
 */

public class InvoiceDB extends SQLiteOpenHelper {
    public static final String DB_NAME="myinvoice.db";
    public static final int DB_VERSION=1;

    public static final String INVOICE_TABLE="invoice";
    public static final String COLUMN_ID="invoiceid";
    public static final String COLUMN_CID="cid";
    public static final String COLUMN_NAME="usrname";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_MOB="mobile";
    public static final String COLUMN_TOTAL="total";
    public static final String COLUMN_STATUS="status";

    /*
* create table userinfo(
* invoiceid integer primary key autoincrement
* cid integer
* usrname text
* address text
* mobile Integer
* status text)*/


    public static final String CREATE_TABLE_INVOICE = "CREATE TABLE " + INVOICE_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CID + " INTEGER, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_ADDRESS + " TEXT, "
            + COLUMN_MOB + " INTEGER, "
            + COLUMN_TOTAL + " INTEGER, "
            + COLUMN_STATUS + " TEXT);";



    public InvoiceDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INVOICE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + INVOICE_TABLE);
        onCreate(db);

    }
    public void addinvoice(Integer cid,String name,String address,Integer mobile,Integer total,String status){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues val=new ContentValues();

        val.put(COLUMN_CID,cid);
        val.put(COLUMN_NAME,name);
        val.put(COLUMN_ADDRESS,address);
        val.put(COLUMN_MOB,mobile);
        val.put(COLUMN_TOTAL,total);
        val.put(COLUMN_STATUS,status);



        long id=db.insert(INVOICE_TABLE,null,val);
        db.close();
        Log.d(TAG, "addinvoice: Invoice inserted"+ id );

    }
    public void removeallinvoice()
    {

        SQLiteDatabase db=this.getWritableDatabase();


        db.delete(INVOICE_TABLE, null,null);
        db.close();

    }
    public void removeInvoiceDB(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + cart+ " WHERE name"+"='"+Name+"'");

        db.delete(INVOICE_TABLE, COLUMN_ID+"=?", new String[]{id});
        db.close();
    }
}
