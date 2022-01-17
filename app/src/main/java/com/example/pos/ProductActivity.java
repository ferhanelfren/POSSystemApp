package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductActivity extends AppCompatActivity {

    EditText produc, productdes, producqty, producprice;
    Button saveb, cancelb;
    Spinner braidspinner, caidspinner;
    ArrayList<String> productlist = new ArrayList<String>();
    ArrayList<String> productlist1 = new ArrayList<String>();
    ArrayAdapter productadapter;
    ArrayAdapter productadapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        getSupportActionBar().setTitle("Products");

        produc = findViewById(R.id.productname);
        productdes = findViewById(R.id.producdiscription);
        producqty = findViewById(R.id.quantities);
        producprice = findViewById(R.id.prices);
        braidspinner = findViewById(R.id.branid);
        caidspinner = findViewById(R.id.categorid);

        saveb = findViewById(R.id.savebutton);
        cancelb = findViewById(R.id.cancelbutton);

        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });


        //diria mao ning para ma kuha ang brand name sa brand og e load sa spinner(combobox)
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from brandtable", null);

        int brand = c.getColumnIndex("brand");

        productlist.clear();
        productadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productlist);
        braidspinner.setAdapter(productadapter);
        final ArrayList<ArrayBrandLists> brandlists = new ArrayList<ArrayBrandLists>();
        if(c.moveToFirst())
        {
            do{
                ArrayBrandLists us = new ArrayBrandLists();
                us.brand = c.getString(brand);
                brandlists.add(us);
                productlist.add(c.getString(brand));
            }while(c.moveToNext());
            productadapter.notifyDataSetChanged();

        }


        //diria mao ning para ma kuha ang category name sa category og e load sa spinner(combobox)
        SQLiteDatabase DB1 = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor cc = DB1.rawQuery("select * from categorytable", null);

        int categoriess = cc.getColumnIndex("category");

        productlist1.clear();
        productadapter1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productlist1);
        caidspinner.setAdapter(productadapter1);
        final ArrayList<ArrayCategoryLists> categorylists = new ArrayList<ArrayCategoryLists>();
        if(cc.moveToFirst())
        {
            do{
                ArrayCategoryLists uss = new ArrayCategoryLists();
                uss.category = cc.getString(categoriess);
                categorylists.add(uss);
                productlist1.add(cc.getString(categoriess));
            }while(cc.moveToNext());
            productadapter1.notifyDataSetChanged();
        }

    }

    public void insert(){

        try {

            String product_EditText = produc.getText().toString();
            String productview = productdes.getText().toString();
            String spinnerbrand = braidspinner.getSelectedItem().toString();
            String spinnercategors = caidspinner.getSelectedItem().toString();
            String qtypro = producqty.getText().toString();
            String pricepro = producprice.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS producttable(id INTEGER PRIMARY KEY AUTOINCREMENT, product VARCHAR, productdescription VARCHAR, brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR,timestamp TEXT)");
            String SQL = "insert into producttable(product, productdescription, brand , category , qty , price, timestamp)values(?,?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(SQL);
            statement.bindString(1, product_EditText);
            statement.bindString(2, productview);
            statement.bindString(3, spinnerbrand);
            statement.bindString(4, spinnercategors);
            statement.bindString(5, qtypro);
            statement.bindString(6, pricepro);
            statement.bindString(7, currentTimeStamp);
            statement.execute();
            Toast.makeText(this, "Product Successfully Added", Toast.LENGTH_SHORT).show();
            produc.setText("");
            productdes.setText("");
            braidspinner.setSelection(-1);
            caidspinner.setSelection(-1);
            producqty.setText("");
            producprice.setText("");
            produc.requestFocus();



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }






}