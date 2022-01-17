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
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddCartActivity extends AppCompatActivity {

    ListView listcart;
    ArrayList<String> arraylistcart= new ArrayList<String>();
    ArrayAdapter arrayadapter;

    CartLists crl;
    TextView first, second,third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cart);
        getSupportActionBar().setTitle("POS Cart");


        //first = findViewById(R.id.first);
       // second = findViewById(R.id.second);
       // third = findViewById(R.id.third);




        //first.setText(one);
        //second.setText(two);
       // third.setText(three);

        listcart = findViewById(R.id.listcart);

        yow();




            }

            public  void yow(){

                    try {

                        Class myCalss = CartLists.class;
                        //String name = String.valueOf(myCalss.getDeclaredField("posname"));
                        //String qty = String.valueOf(myCalss.getDeclaredField("qty"));
                        //String prices = String.valueOf(myCalss.getDeclaredField("price"));

                       // Field field = myCalss.getField("posname");
                       // Object value = field.get(null);
                       // String name = (String)value;

                       // Field field1 = myCalss.getField("qty");
                       // Object value1 = field1.get(null);
                        //String qtyyy = (String)value1;

                       // Field field2 = myCalss.getField("qty");
                       // Object value2 = field2.get(null);
                       // String priceee = (String)value2;


                        Intent intent = getIntent();
                        String one = intent.getStringExtra("productnames");
                        String two = intent.getStringExtra("prices");
                        String three = intent.getStringExtra("qtyy");



                        List<String> listItems = new ArrayList<String>();
                        String[] items = {};
                            //listItems.add("\t\t\t"+one+"\t\t\t\t\t\t"+two+"\t\t\t\t\t\t"+three);
                            listItems.add("\t\t\t"+one+"\t\t\t\t\t\t"+two+"\t\t\t\t\t\t"+three);
                        items = listItems.toArray(new String[listItems.size()]);
                        listcart.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));









                    }catch (Exception e){

                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }

            }







    }




