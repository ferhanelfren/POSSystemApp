package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    GridView gridpos;
    ArrayList<Product_Item> arraylistpos= new ArrayList<>();
    //ArrayAdapter arrayadapter;
    byte [] image;
    Product_CustomAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        gridpos = (GridView) findViewById(R.id.gridview);
        arraylistpos = new ArrayList<>();
        adapter = new Product_CustomAdapter(this, R.layout.product_items, arraylistpos);
        gridpos.setAdapter(adapter);

        //get all data from sqlite
        Cursor c = Product.sqlHelper.getData("SELECT * FROM produtable");
        arraylistpos.clear();
        while(c.moveToNext()){
            int id = c.getInt(1);
            byte [] image = c.getBlob(2);
            String product = c.getString(3);
            String brand = c.getString(4);
            String category = c.getString(5);
            String qty = c.getString(6);
            String price =  c.getString(7);

            arraylistpos.add(new Product_Item(id, image, product,brand,category, qty, price));
        }
        adapter.notifyDataSetChanged();


    }


}