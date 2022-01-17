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

public class CategoryActivityList extends AppCompatActivity {

    ListView listcategory;
    ArrayList<String> arraylistscategory = new ArrayList<String>();
    ArrayAdapter arrayadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);
        getSupportActionBar().setTitle("Category Table");


        listcategory = findViewById(R.id.listviewcategory);

        loadcategory();


    }


    public void loadcategory() {
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from categorytable", null);

        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int categorydescription = c.getColumnIndex("categorydescription");
        int timestamp = c.getColumnIndex("timestamp");
        arraylistscategory.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistscategory);
        listcategory.setAdapter(arrayadapter);
        final ArrayList<ArrayCategoryLists> catlists = new ArrayList<ArrayCategoryLists>();
        if(c.moveToFirst())
        {

            do{

                ArrayCategoryLists us = new ArrayCategoryLists();
                us.id = c.getString(id);
                us.category = c.getString(category);
                us.categorydescription = c.getString(categorydescription);
                us.timestamp = c.getString(timestamp);
                catlists.add(us);
                arraylistscategory.add("\t"+ c.getString(id) + "\t\t\t\t\t" + c.getString(category) +
                        "\t\t\t\t\t"+ c.getString(categorydescription) + "\t\t\t\t\t" + c.getString(timestamp));


            }while(c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listcategory.invalidateViews();

        }


        //From listview to Editlayout pag e click mag set dayun ang text
        listcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String  x = arraylistscategory.get(position).toString();

                ArrayCategoryLists us = catlists.get(position);
                Intent intent = new Intent(getApplicationContext(),CategoryEdit.class);

                intent.putExtra("id", us.id);
                intent.putExtra("category", us.category);
                intent.putExtra("categorydescription", us.categorydescription);
                startActivity(intent);

            }
        });


    }
}