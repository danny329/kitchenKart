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

public class GroceryStapleCategoryActivity extends AppCompatActivity {
    ListView listo;
    cartdb DB;
    String[] itemname = {
            "Mustard seed",
            "Black rice",
            "Cardamom green",
            "Chilli powder",
            "Green gram",
            "Jaggery powder",
            "Wheat sharbati"
    };
    Integer[] imageId = {

            R.drawable.ic_seeds_mustard,
            R.drawable.ic_black_rice,
            R.drawable.ic_cardamom_green,
            R.drawable.ic_chilli_powder,
            R.drawable.ic_green_gram,
            R.drawable.ic_powder_jaggery,
            R.drawable.ic_wheat_sharbati,


    };
    String[] Price = {
            "130",
            "99",
            "150",
            "60",
            "75",
            "80",
            "110",


    };
    Integer[] counter = {0,0,0,0,0,0,0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_staple_category);
        DB=new cartdb(this);
        customlist adapter=new customlist(GroceryStapleCategoryActivity.this,itemname,imageId,Price,counter,DB);
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
                Intent i=new Intent(GroceryStapleCategoryActivity.this,categoryMain.class);
                startActivity(i);
                break;
            case R.id.action_cart:
                Intent intent = getIntent();
                String Email = intent.getStringExtra("email");
                Intent in=new Intent(GroceryStapleCategoryActivity.this,checkoutcartActivity.class);
                in.putExtra("email",Email);
                startActivity(in);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
