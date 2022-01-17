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

public class BrandActivityList extends AppCompatActivity {

    ListView listbrand;
    ArrayList<String> arraylistbrand= new ArrayList<String>();
    ArrayAdapter arrayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_list);
        getSupportActionBar().setTitle("Brand Table");

        listbrand = findViewById(R.id.listviewbrand);

        load();

    }

    public void load(){
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from brandtable", null);
        int id = c.getColumnIndex("id");
        int brand = c.getColumnIndex("brand");
        int branddescription = c.getColumnIndex("branddescription");
        int timestamp = c.getColumnIndex("timestamp");
        arraylistbrand.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistbrand);
        listbrand.setAdapter(arrayadapter);

        final ArrayList<ArrayBrandLists> brandlists = new ArrayList<ArrayBrandLists>();
        if(c.moveToFirst())
        {

            do{

                ArrayBrandLists us = new ArrayBrandLists();
                us.id = c.getString(id);
                us.brand = c.getString(brand);
                us.branddescription = c.getString(branddescription);
                us.timestamp = c.getString(timestamp);
                brandlists.add(us);
                arraylistbrand.add("\t"+ c.getString(id) + "\t\t\t\t\t" + c.getString(brand) +
                        "\t\t\t\t\t"+ c.getString(branddescription) + "\t\t\t\t\t" + c.getString(timestamp));


            }while(c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listbrand.invalidateViews();

        }


        //From listview to Editlayout pag e click mag set dayun ang text
        listbrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String x = arraylistbrand.get(position).toString();
                ArrayBrandLists us = brandlists.get(position);
                Intent intent = new Intent(getApplicationContext(), CategoryEdit.class);
                intent.putExtra("id", us.id);
                intent.putExtra("category", us.brand);
                intent.putExtra("categorydescription", us.branddescription);
                startActivity(intent);


            }
        });



}




}