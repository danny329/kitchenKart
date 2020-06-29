package com.grocery.daniel.kitchenkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by DANIEL on 14-01-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="myreg.db";
    public static final int DB_VERSION=1;



    public static final String USER_TABLE="user";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_USERNAME="name";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASS="password";
    public static final String COLUMN_ADDR="address";
    public static final String COLUMN_MOB="mobile";

/*
* create table userinfo(
* id integer primary key autoincrement
* name text
* email text
* password text
* address text
* mobile integer)*/


    public static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PASS + " TEXT, "
            + COLUMN_ADDR + " TEXT, "
            + COLUMN_MOB + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + USER_TABLE);
        onCreate(db);
    }

    public void adduser(String name,String email,String password,String address,String mobile){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues val=new ContentValues();

        val.put(COLUMN_USERNAME,name);
        val.put(COLUMN_EMAIL,email);
        val.put(COLUMN_PASS,password);
        val.put(COLUMN_ADDR,address);
        val.put(COLUMN_MOB,mobile);

        long id=db.insert(USER_TABLE,null,val);
        db.close();
        Log.d(TAG, "adduser: User inserted"+ id );

    }
    public boolean getuser(String email,String password){

        String selectQ="select * from "+USER_TABLE+" where "+COLUMN_EMAIL+" = " +"'"+email+"'" +" and "+COLUMN_PASS+" = " +"'"+password+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(selectQ,null);
        c.moveToFirst();
        if(c.getCount()>0)
        {
            return true;
        }
        c.close();
        db.close();
        return false;

    }
    public void removealluser()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + cart+ " WHERE name"+"='"+Name+"'");

        db.delete(USER_TABLE, null,null);
        db.close();

    }

    public void updateDB(String tname,String tpass,String tmail,String taddress,int tcontact,int tusid)
    {
        //SQLiteDatabase db=this.getWritableDatabase();
        //String strSQL = "UPDATE user SET name = "+tname+",email = "+tmail+",password = "+tpass+",address = "+taddress+",mobile = "+tcontact+" WHERE id = "+ tusid;

       // db.execSQL(strSQL);



    }
}
