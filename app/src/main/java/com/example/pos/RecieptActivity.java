package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class RecieptActivity extends AppCompatActivity {

    TextView dates,hours, totals, items, cash, change;
    Button placeorder;
    ListView listbrand;
    ArrayList<String> arraylistbrand= new ArrayList<String>();
    ArrayAdapter arrayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciept);

       listbrand = findViewById(R.id.lists);
       totals = findViewById(R.id.totals);
       items = findViewById(R.id.items);
       cash = findViewById(R.id.cash);
       change = findViewById(R.id.change);
        placeorder = findViewById(R.id.plorder);



        try {

            totalprices();
            loads();
            totalcount();



        }catch (Exception e){

           // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }



       dates = findViewById(R.id.editTextDate);
      hours = findViewById(R.id.TextDate);
       //totals = findViewById(R.id.totals);

       SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
       Calendar calendar = Calendar.getInstance();
       dates.setText(dateFormat.format(calendar.getTime()));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss a");
        Calendar calendar2 = Calendar.getInstance();
        hours.setText(dateFormat2.format(calendar2.getTime()));

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sukli();
                deleteAll();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }


    public void loads(){
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from deletepostable", null);

        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int qtyy = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        arraylistbrand.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.listviewi_item_color, arraylistbrand);
        listbrand.setAdapter(arrayadapter);
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
                arraylistbrand.add("\t\t\t\t\t\t\t"+ c.getString(name) + "\t\t\t\t\t" + c.getString(qtyy) +
                        "\t\t\t\t\t"+ c.getString(price));
            }while(c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listbrand.invalidateViews();

        }

    }


    public void totalprices(){

        try {
            CartLists db = CartLists.getInstance(this);

           totals.setText("" + db.sumTotalItems());


        } catch (Exception e) {

          //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }


    }

    public void totalcount(){
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        String countQuery = "SELECT  * FROM  deletepostable" ;
        Cursor cursor = DB.rawQuery(countQuery, null);
        int count = cursor.getCount();
        items.setText("You've purchased"+"\t"+String.valueOf(count)+"\t"+"items");
        cursor.close();
    }


    public void sukli(){

        String text = cash.getText().toString();

        if(TextUtils.isEmpty(text)){

            cash.setError("Money cannot be empty");
            return;

        }else{
            float d = Float.valueOf(totals.getText().toString());
            float a = Float.valueOf(cash.getText().toString());
            String c = change.getText().toString();
            float tot=0;
            tot = d - a;
            change.setText(String.valueOf(tot));
        }

        totals.setText("");
        cash.setText("");
        //change.setText("");

    }



    public void deleteAll() {
        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS deletepostable");
        // Toast.makeText(this, "Database Successfully Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }




}