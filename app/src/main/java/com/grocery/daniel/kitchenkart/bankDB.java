package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 27-03-2017.
 */

public class bankDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "mybank1.db";
    public static final int DB_VERSION = 1;


    public static final String BANK_TABLE = "banktable";
    public static final String COLUMN_CARDNUM = "cnumber";
    public static final String COLUMN_CVV = "cvv";
    public static final String COLUMN_CNAME = "cname";
    public static final String COLUMN_EXPIRY= "expiry";
    public static final String COLUMN_BAL= "bal";
    public static final String CREATE_TABLE_BANK = "CREATE TABLE " + BANK_TABLE + "("

            + COLUMN_CARDNUM + " TEXT, "
            + COLUMN_CNAME + " TEXT, "
            + COLUMN_CVV + " TEXT, "
            + COLUMN_BAL + " INTEGER, "
            + COLUMN_EXPIRY + " TEXT);";

    public bankDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BANK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS banktable");
        onCreate(db);
    }
    public void addcust(String cname, String cnum, String cvv,Integer bal, String exp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();


        val.put(COLUMN_CNAME,cname);
        val.put(COLUMN_CARDNUM, cnum);
        val.put(COLUMN_CVV, cvv);
        val.put(COLUMN_BAL, bal);
        val.put(COLUMN_EXPIRY, exp);


        long id = db.insert(BANK_TABLE, null, val);
        db.close();
        Log.d(TAG, "addcust: cust inserted" + id);
    }
}
