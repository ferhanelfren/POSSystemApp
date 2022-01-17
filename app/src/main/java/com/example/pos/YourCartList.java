package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class YourCartList extends AppCompatActivity {

    ListView listcart;
    ArrayList<String> postitles;
    ArrayAdapter arrayadpaterpos;
    Button cancelss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_cartlist);

        listcart = findViewById(R.id.listcart);
        postitles = new ArrayList<>();

        //kining listview_item_color kay para malahi ang color sa item sa listview naa sa layout folder ang xml niya
        arrayadpaterpos = new ArrayAdapter<String>(getApplicationContext(), R.layout.listviewi_item_color, postitles);

        load();


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.itemni){
            Intent intent = new Intent (YourCartList.this, POSActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_pos, menu);

        return true;
    }


    public void load(){




        try{


            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            final Cursor c = DB.rawQuery("select * from deletepostable", null);
            int id = c.getColumnIndex("id");
            int name = c.getColumnIndex("name");
            int qtyy = c.getColumnIndex("qty");
            int price = c.getColumnIndex("price");
            //postitles.clear();
            arrayadpaterpos = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, postitles);
            listcart.setAdapter(arrayadpaterpos);
            final ArrayList<ArrayPOSLists1> brandlists = new ArrayList<ArrayPOSLists1>();
            if(c.moveToFirst())
            {

                do{
                    ArrayPOSLists1 us = new ArrayPOSLists1();
                    us.id = c.getString(id);
                    us.productname = c.getString(name);
                    us.qty = c.getString(qtyy);
                    us.price = c.getString(price);
                    brandlists.add(us);
                    postitles.add("\t"+c.getString(id) +"\t\t\t"+ c.getString(name) + "\t\t\t\t\t" + c.getString(qtyy) +
                            "\t\t\t\t\t"+ c.getString(price));
                }while(c.moveToNext());
                arrayadpaterpos.notifyDataSetChanged();
                listcart.invalidateViews();

            }

            listcart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    String x = postitles.get(position).toString();
                    ArrayPOSLists1 us = brandlists.get(position);
                    Intent intent = new Intent(getApplicationContext(), Pop.class);
                    intent.putExtra("id", us.id);
                    intent.putExtra("productname", us.productname);
                    intent.putExtra("qty", us.qty);
                    intent.putExtra("prices", us.price);
                    startActivity(intent);

                }
            });


        }catch (Exception e){

        }


    }



}