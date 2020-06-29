package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FruitVegCategoryActivity extends AppCompatActivity {
    ListView listo;
    cartdb DB;
    String[] itemname = {
            "Pineapple",
            "Cherry",
            "Banana",
            "Avocado",
            "Blackberry",
            "Black plum",
            "Custard apple"
    };
    Integer[] imageId = {

            R.drawable.ic_pine,
            R.drawable.ic_cherry,
            R.drawable.ic_banana,
            R.drawable.ic_avocado,
            R.drawable.ic_blackberry,
            R.drawable.ic_berry,
            R.drawable.ic_cust,


    };
    String[] Price = {
            "30",
            "40",
            "50",
            "60",
            "70",
            "80",
            "90",


    };
    Integer[] counter = {0,0,0,0,0,0,0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fruit_veg_category);
        DB=new cartdb(this);
        customlist adapter=new customlist(FruitVegCategoryActivity.this,itemname,imageId,Price,counter,DB);
        listo = (ListView) findViewById(R.id.listview);
        listo.setAdapter(adapter);
        listo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.commonmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.home:
                Intent i=new Intent(FruitVegCategoryActivity.this,categoryMain.class);
                startActivity(i);
                break;
            case R.id.action_cart:
                Intent intent = getIntent();
                String Email = intent.getStringExtra("email");
                Intent in=new Intent(FruitVegCategoryActivity.this,checkoutcartActivity.class);
                in.putExtra("email",Email);
                startActivity(in);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}