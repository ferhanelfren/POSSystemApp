package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductActivityLists extends AppCompatActivity {

    ListView listproduct;
    ArrayList<String> arraylistproduct= new ArrayList<String>();
    ArrayAdapter arrayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_lists);
        getSupportActionBar().setTitle("Product Table");

        listproduct = findViewById(R.id.listviewproduct);

        load();


    }



    public void load() {
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from producttable", null);

        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("product");
        int productdescription = c.getColumnIndex("productdescription");
        int productbrand = c.getColumnIndex("brand");
        int productcategory = c.getColumnIndex("category");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        int timestamp = c.getColumnIndex("timestamp");
        arraylistproduct.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistproduct);
        listproduct.setAdapter(arrayadapter);
        final ArrayList<ArrayProductLists> productlists = new ArrayList<ArrayProductLists>();
        if (c.moveToFirst()) {

            do {

                ArrayProductLists us = new ArrayProductLists();
                us.id = c.getString(id);
                us.product = c.getString(product);
                us.productdescription = c.getString(productdescription);
                us.brand = c.getString(productbrand);
                us.category = c.getString(productcategory);
                us.qty = c.getString(qty);
                us.price = c.getString(price);
                us.timestamp = c.getString(timestamp);
                productlists.add(us);
                arraylistproduct.add("\t" + c.getString(id) + "\t\t\t\t\t" + c.getString(product) + "\t\t\t\t\t" + c.getString(productdescription) + "\t\t\t" +
                        c.getString(productbrand) + "\t\t\t\t\t" + c.getString(productcategory) + "\t\t\t\t\t" + c.getString(qty) + "\t\t\t\t\t" + c.getString(price) + "\t\t\t\t\t" + c.getString(timestamp));


            } while (c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listproduct.invalidateViews();

        }

    }


}