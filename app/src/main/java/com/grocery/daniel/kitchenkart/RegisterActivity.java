package com.grocery.daniel.kitchenkart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uname, email, password, repassword, address, mob;
    Button register;
    String name, mail, pass, repass, mobile, addr;
    Integer mobno;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);
        uname = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repeatpassword);
        address = (EditText) findViewById(R.id.address);
        mob = (EditText) findViewById(R.id.mobilE);
        register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        register();

    }

    private void register() {

        name = uname.getText().toString();
        mail = email.getText().toString();
        pass = password.getText().toString();
        repass = repassword.getText().toString();
        addr = address.getText().toString();
        mobile = mob.getText().toString();

        // Boolean PL=Pattern.matches("^[0-9][A-Z][a-z]{8,20}$",pass);

        Boolean P = pass.equals(repass);
        Boolean E = Pattern.matches("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", mail);




        if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || repass.isEmpty() || addr.isEmpty() || mobile.isEmpty()) {
            displayToast("NO FIELD CAN BE EMPTY");
        } else if (!E) {
            displayToast("EMAIL FORMAT WRONG");
        } else if (!P) {
            displayToast("PASSWORD MISMATCH");
        }  else {
            db.adduser(name,mail,pass,addr,mobile);
            displayToast("user registered");
            finish();
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
