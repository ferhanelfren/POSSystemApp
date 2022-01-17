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

public class PosActivityLists extends AppCompatActivity {

    ListView listpos;
    ArrayList<String> arraylistpos= new ArrayList<String>();
    ArrayAdapter arrayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pos_lists);
        getSupportActionBar().setTitle("Point on Sale Table");

        listpos = findViewById(R.id.listviewpos);

        load();

    }


    public void load(){
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from posstable", null);

        int id = c.getColumnIndex("proid");
        int productname = c.getColumnIndex("productname");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        int totals = c.getColumnIndex("total");
        int timestamp = c.getColumnIndex("timestamp");
        arraylistpos.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistpos);
        listpos.setAdapter(arrayadapter);
        final ArrayList<ArrayPOSLists> poslists = new ArrayList<ArrayPOSLists>();
        if(c.moveToFirst())
        {

            do{

                ArrayPOSLists us = new ArrayPOSLists();
                us.proid = c.getString(id);
                us.productname = c.getString(productname);
                us.qty = c.getString(qty);
                us.price = c.getInt(price);
                us.total = c.getString(totals);
                us.timestamp = c.getString(timestamp);
                poslists.add(us);
                arraylistpos.add("\t"+ c.getString(id) + "\t\t\t\t\t" + c.getString(productname) +
                        "\t\t\t\t\t"+ c.getString(qty) + "\t\t\t\t\t" + c.getString(price) +
                         "\t\t\t\t\t" + c.getString(totals) +"\t\t\t\t\t" + c.getString(timestamp));


            }while(c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listpos.invalidateViews();

        }




    }


}