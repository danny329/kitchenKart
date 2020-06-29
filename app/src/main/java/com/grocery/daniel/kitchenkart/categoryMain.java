package com.grocery.daniel.kitchenkart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class categoryMain extends AppCompatActivity {
    GridView grid;
    Intent i;
    String s1;
    private session s;
    cartdb d;
    currentuserDB DB;

    String[] web = {
            "Branded Foods",
            "        Meat",
            "Fruit & Vegetables",
            "Grocery & Staples",
            "       Beverages",
            "Diary Products",
    };
    int[] imageId = {
            R.drawable.ic_branded,
            R.drawable.ic_meat,
            R.drawable.ic_fruit,
            R.drawable.ic_grocery,
            R.drawable.ic_beverage,
            R.drawable.ic_diary,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);
        s=new session(this);
        if(!s.loggedin()){
            logout();
        }
        Intent intent = getIntent();
        final String mail = intent.getStringExtra("email");
        customgrid adapter = new customgrid(categoryMain.this, web, imageId);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(categoryMain.this, "Branded Foods", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,BrandedFoodCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;
                    case 1:
                        Toast.makeText(categoryMain.this, "Meat", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,MeatCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;
                    case 2:
                        Toast.makeText(categoryMain.this, "Fruit & Vegetables", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,FruitVegCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;
                    case 3:
                        Toast.makeText(categoryMain.this, "Grocery & Staples", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,GroceryStapleCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;
                    case 4:
                        Toast.makeText(categoryMain.this, "Beverages", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,BeveragesCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;
                    case 5:
                        Toast.makeText(categoryMain.this, "Diary Products", Toast.LENGTH_SHORT).show();
                        i=new Intent(categoryMain.this,DairyCategoryActivity.class);
                        i.putExtra("email",mail);
                        startActivity(i);
                        break;

                }
                //Toast.makeText(categoryMain.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.action_logout:
                logout();
                break;
            case R.id.action_cart:

                Intent in=new Intent(categoryMain.this,checkoutcartActivity.class);
                startActivity(in);
                break;
            // action with ID action_settings was selected
            case R.id.myorder:
                Intent inte=new Intent(categoryMain.this,PurchaseActivity.class);
                startActivity(inte);
                Toast.makeText(this, "order selected", Toast.LENGTH_SHORT)
                        .show();
                break;

            case R.id.account:
                Intent t=new Intent(categoryMain.this,ProfileActivity.class);
                startActivity(t);
                Toast.makeText(this, "order selected", Toast.LENGTH_SHORT)
                        .show();
                break;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
private void logout(){
    d=new cartdb(getApplicationContext());
    d.removeCartAllDB();
    d.close();
    DB=new currentuserDB(this);
    DB.removecurrentuser();
    DB.close();
    s.setloggedin(false);
    finish();
    startActivity(new Intent(categoryMain.this,LoginActivity.class));
}

}
