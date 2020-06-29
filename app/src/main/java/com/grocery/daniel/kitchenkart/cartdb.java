package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 23-01-2017.
 */

public class cartdb extends SQLiteOpenHelper {

    public static final String DB_NAME="mycart.db";
    public static final int DB_VERSION=1;


    public static final String CART_TABLE="cart";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITLE="name";
    public static final String COLUMN_PRICE="price";
    public static final String COLUMN_COUNT="count";




/*
* create table cart(
* id integer primary key autoincrement
* name text
* price text
* count text
*/
public static final String CREATE_TABLE_CART ="CREATE TABLE " + CART_TABLE + "("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_TITLE + " TEXT, "
        + COLUMN_PRICE + " TEXT, "
        + COLUMN_COUNT + " TEXT);";

    public cartdb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CART_TABLE);
        onCreate(db);
    }
    public void addcart(String name,String price,String count){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues val=new ContentValues();

        val.put(COLUMN_TITLE,name);
        val.put(COLUMN_PRICE,price);
        val.put(COLUMN_COUNT,count);
        Cursor cursor = db.query(CART_TABLE,
                new String[] { COLUMN_TITLE },
                COLUMN_TITLE + " = ?" ,
                new String[] {name},
                null, null, null, null);
        if(cursor.moveToFirst()) {

            //row exists
            db.close();
            Log.d(TAG, "addcart: Cart item exists");
        }
        else {


            long id = db.insert(CART_TABLE, null, val);

            db.close();
            Log.d(TAG, "addcart: Cart inserted" + id);
        }

    }

    public void removeCartDB(String s1)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + cart+ " WHERE name"+"='"+Name+"'");

        db.delete(CART_TABLE, COLUMN_TITLE+"=?", new String[]{s1});
        db.close();

    }

    public void removeCartAllDB()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + cart+ " WHERE name"+"='"+Name+"'");

        db.delete(CART_TABLE, null,null);
        db.close();

    }

}
