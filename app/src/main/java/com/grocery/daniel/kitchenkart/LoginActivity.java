package com.grocery.daniel.kitchenkart;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {

   EditText email,password;
    Button signup,signin;
    private DBHelper db;
    private currentuserDB CDB;
    private session s;
    String name, adres,mail, contct;
    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DBHelper(this);
        CDB=new currentuserDB(this);
        s=new session(this);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signin=(Button)findViewById(R.id.sign_in_button);
        signup=(Button)findViewById(R.id.sign_up_button);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

        if(s.loggedin()){
            startActivity(new Intent(LoginActivity.this,categoryMain.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sign_in_button:
                  login();
               // db.removealluser();
                break;
            case R.id.sign_up_button:
                Intent j=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(j);
                break;
            default:
                break;
        }
    }

    private void login()
    {
        mail=email.getText().toString();
        String pass=password.getText().toString();
        if(mail.equals("Admin") && pass.equals("Admin")){
            Intent y=new Intent(LoginActivity.this,AdminMainActivity.class);
            startActivity(y);
        }
        else if(db.getuser(mail,pass))
        {

                s.setloggedin(true);
                getusrdetail();

                CDB.addcurrentuser(id, name, mail, adres, contct);
                Intent intent = new Intent(this, categoryMain.class);
                startActivity(intent);
                finish();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"WRONG EMAIL/PASSWORD",Toast.LENGTH_SHORT).show();
        }

    }
    public void getusrdetail() {
        mail=email.getText().toString();
        String selectQ = "SELECT id, name, address, mobile FROM user WHERE email = ?";
        SQLiteDatabase DB = db.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQ, new String[]{mail + ""});
        if (c.getCount() > 0) {
            c.moveToFirst();

            id=c.getInt(c.getColumnIndex("id"));
            name = c.getString(c.getColumnIndex("name"));
            adres = c.getString(c.getColumnIndex("address"));
            contct = c.getString(c.getColumnIndex("mobile"));
        }
        c.close();
        db.close();

    }

}