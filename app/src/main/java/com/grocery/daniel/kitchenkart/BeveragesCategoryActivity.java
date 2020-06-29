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

public class BeveragesCategoryActivity extends AppCompatActivity {
    ListView listo;
    cartdb DB;
    String[] itemname = {
            "Nescafe",
            "Real juice",
            "Tropicana",
            "Tetley",
            "Red Label",
            "Diet coke",
            "Horlicks"
    };
    Integer[] imageId = {

            R.drawable.ic_nescafe,
            R.drawable.ic_real,
            R.drawable.ic_tropi,
            R.drawable.ic_tetley,
            R.drawable.ic_redlabel,
            R.drawable.ic_dietcoke,
            R.drawable.ic_horlicks,


    };
    String[] Price = {
            "40",
            "40",
            "30",
            "160",
            "220",
            "80",
            "150",


    };
    Integer[] counter = {0,0,0,0,0,0,0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_category);
        DB=new cartdb(this);
        customlist adapter=new customlist(BeveragesCategoryActivity.this,itemname,imageId,Price,counter,DB);
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
                Intent i=new Intent(BeveragesCategoryActivity.this,categoryMain.class);
                startActivity(i);
                break;
            case R.id.action_cart:
                Intent intent = getIntent();
                String Email = intent.getStringExtra("email");
                Intent in=new Intent(BeveragesCategoryActivity.this,checkoutcartActivity.class);
                in.putExtra("email",Email);
                startActivity(in);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
