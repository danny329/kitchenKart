package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 15-03-2017.
 */

public class currentuserDB extends SQLiteOpenHelper {
    public static final String DB_NAME="currentus.db";
    public static final int DB_VERSION=1;



    public static final String CURRENT_TABLE="currentuser";
    public static final String COLUMN_ID="usid";
    public static final String COLUMN_USERNAME="Name";
    public static final String COLUMN_EMAIL="mail";
    public static final String COLUMN_ADDR="address";
    public static final String COLUMN_CONTACT="mob";
    public static final String CREATE_TABLE_CURRENT = "CREATE TABLE " + CURRENT_TABLE + "("
            + COLUMN_ID + " INTEGER, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_ADDR + " TEXT, "
            + COLUMN_CONTACT + " TEXT);";

    public currentuserDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CURRENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CURRENT_TABLE);
        onCreate(db);
    }


    public void addcurrentuser(int usid,String name,String email,String address,String mobile){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues val=new ContentValues();
        val.put(COLUMN_ID,usid);
        val.put(COLUMN_USERNAME,name);
        val.put(COLUMN_EMAIL,email);
        val.put(COLUMN_ADDR,address);
        val.put(COLUMN_CONTACT,mobile);

        long id=db.insert(CURRENT_TABLE,null,val);
        db.close();
        Log.d(TAG, "addcurrentuser: User inserted"+ id );

    }
    public void removecurrentuser()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + cart+ " WHERE name"+"='"+Name+"'");

        db.delete(CURRENT_TABLE, null,null);
        db.close();

    }

}
