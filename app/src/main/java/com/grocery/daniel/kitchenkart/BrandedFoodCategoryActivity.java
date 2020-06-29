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

public class BrandedFoodCategoryActivity extends AppCompatActivity {
    ListView listo;
    cartdb DB;
    String[] itemname = {
            "Maggi",
            "Oreo",
            "Lays",
            "Honey",
            "Marie Gold",
            "Corn flakes",
            "Chocos"
    };
    Integer[] imageId = {

            R.drawable.ic_maggi,
            R.drawable.ic_oreo,
            R.drawable.ic_lays,
            R.drawable.ic_honey,
            R.drawable.ic_marie,
            R.drawable.ic_flake,
            R.drawable.ic_choco,


    };
    String[] Price = {
            "10",
            "10",
            "10",
            "60",
            "20",
            "160",
            "190",


    };
    Integer[] counter = {0,0,0,0,0,0,0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branded_food_category);
        DB=new cartdb(this);
        customlist adapter=new customlist(BrandedFoodCategoryActivity.this,itemname,imageId,Price,counter,DB);
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
            // action with ID action_refresh was selected
            case R.id.home:
                Intent i=new Intent(BrandedFoodCategoryActivity.this,categoryMain.class);
                startActivity(i);
                break;
            case R.id.action_cart:
                Intent intent = getIntent();
                String Email = intent.getStringExtra("email");
                Intent in=new Intent(BrandedFoodCategoryActivity.this,checkoutcartActivity.class);
                in.putExtra("email",Email);
                startActivity(in);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
