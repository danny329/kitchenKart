package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 05-03-2017.
 */

public class OrderDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "myorder1.db";
    public static final int DB_VERSION = 1;


    public static final String ORDER_TABLE = "ordertable";

    public static final String COLUMN_CUSID = "custid";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_ITEMPRICE = "itemprice";
    public static final String COLUMN_ITEMCOUNT = "itemcount";


/*
* create table order(
* orderid integer
* custid integer REFERENCES id userinfo
* itemname text
* itemprice text
* itemcount text)*/


    public static final String CREATE_TABLE_ORDER = "CREATE TABLE " + ORDER_TABLE + "("

            + COLUMN_CUSID + " INTEGER, "
            + COLUMN_ITEMNAME + " TEXT, "
            + COLUMN_ITEMPRICE + " INTEGER, "
            + COLUMN_ITEMCOUNT + " INTEGER);";

    public OrderDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS order");
        onCreate(db);
    }

    public void addorder(Integer cid,String itemname, Integer itemprice, Integer itemcount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();


        val.put(COLUMN_CUSID,cid);
        val.put(COLUMN_ITEMNAME, itemname);
        val.put(COLUMN_ITEMPRICE, itemprice);
        val.put(COLUMN_ITEMCOUNT, itemcount);


        long id = db.insert(ORDER_TABLE, null, val);
        db.close();
        Log.d(TAG, "addorder: order inserted" + id);
    }
    public void removeorder()
    {

        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(ORDER_TABLE, null,null);
        db.close();

    }

}
