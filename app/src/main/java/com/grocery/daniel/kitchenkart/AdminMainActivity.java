package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {
    Button customer, invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        customer = (Button) findViewById(R.id.Customers);
        invoice = (Button) findViewById(R.id.invoice);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMainActivity.this,ViewCustomerActivity.class);
                startActivity(i);
            }
        });
        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMainActivity.this,InvoiceAllActivity.class);
                startActivity(i);
            }
        });

    }
}
