package com.example.pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class POSActivity extends AppCompatActivity {

    EditText productids, posquantities;
    TextView posprices, posnames, postotals, cartotalsss, cartotals2, exitnii;
    Button savess, searching, cancel,cart;

    //int total = 0;
    //int posqty = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pos);
        getSupportActionBar().setTitle("Point on Sale System");

        productids = findViewById(R.id.productid);
        posprices = findViewById(R.id.posprice);
        posquantities = findViewById(R.id.posquantity);
        posnames = findViewById(R.id.possname);
        postotals = findViewById(R.id.postotal);
        cartotalsss = findViewById(R.id.cartotals);
        cartotals2 = findViewById(R.id.tsss);
        searching = findViewById(R.id.searchbutton);
        savess = findViewById(R.id.savebutton);
        cancel = findViewById(R.id.cancelbutton);
        cart = findViewById(R.id.cartbutton);


        totalprices();






        posquantities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (posquantities.length() != 0) {
                   textsing();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
               //totalprices();

            }
        });



        savess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //inserts();
                trylang();
               // load();
            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // deleteAll();
                Intent intent = new Intent(POSActivity.this, RecieptActivity.class);
                startActivity(intent);
                inserts();
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), YourCartList.class);
                startActivity(intent);
            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.itemni){
            Intent intent = new Intent (POSActivity.this, HomeActivity.class);
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


    public void textsing() {
        try {

            int total = 0;
            int posqty = Integer.parseInt(posquantities.getText().toString());
            int pospricessss = Integer.parseInt(posprices.getText().toString());
            total = posqty * pospricessss;
            //String tots = posquantities.getText().toString();
            postotals.setText(Integer.toString(total));

        } catch (Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    public void trylang(){


        try {

            int tot;
            String names = postotals.getText().toString();
            String productnames = posnames.getText().toString();
            String prices = posprices.getText().toString();
            String qtyy = posquantities.getText().toString();
            int qty1s = Integer.parseInt(posquantities.getText().toString().trim());


            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            String id = productids.getText().toString();
            final Cursor c = DB.rawQuery("select * from producttable where product = '" + id + "' ", null);
            int qty = c.getColumnIndex("qty");

            final ArrayList<ArrayProductLists1> poslistsss = new ArrayList<ArrayProductLists1>();
            if (c.moveToFirst()) {
                do {
                    ArrayProductLists1 us = new ArrayProductLists1();
                    us.qty = c.getString(qty);
                    poslistsss.add(us);


                    int oldqty = Integer.parseInt(c.getString(qty));

                    if (qty1s > oldqty) {

                        Toast.makeText(this, "Quantity is Not Enough" + "\t\t\t\t" + String.valueOf(oldqty), Toast.LENGTH_LONG).show();
                        posquantities.setText("");

                    } else {

                        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                        db.execSQL("CREATE TABLE IF NOT EXISTS  deletepostable(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,qty VARCHAR, price VARCHAR)");
                        String SQL = "insert into deletepostable(name,qty,price)values(?,?,?)";
                        SQLiteStatement statement = db.compileStatement(SQL);
                        statement.bindString(1, productnames);
                        statement.bindString(2, qtyy);
                        statement.bindString(3, prices);
                        statement.execute();
                        Toast.makeText(this, "Total Successfully Added", Toast.LENGTH_SHORT).show();
                        totalprices();



                        String SQL1 = "update producttable set qty = qty - ? where product = ?";
                        SQLiteStatement statement1 = DB.compileStatement(SQL1);
                        statement1.bindLong(1, qty1s);
                        statement1.bindString(2, productnames);
                        statement1.execute();
                        productids.setText("");
                        posnames.setText("");
                        posquantities.setText("");
                        posprices.setText("");
                        postotals.setText("");
                        productids.requestFocus();

                    }
                } while (c.moveToNext());
            }






           // cartotalsss.setText(String.valueOf(sum));



        }catch (Exception e){


            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();


        }



    }


    public void totalprices(){

        try {
            CartLists db = CartLists.getInstance(this);
            cartotalsss.setText("" + db.sumTotalItems());


        } catch (Exception e) {

           // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }


    }

    public void search() {
        String id = productids.getText().toString();
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from producttable where product = '" + id + "'", null);
        //int productid = c.getColumnIndex("id");
        int productnames = c.getColumnIndex("product");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        //postitles = new ArrayList<>();
        //postitles.clear();

        final ArrayList<ArrayProductLists1> poslists = new ArrayList<ArrayProductLists1>();
        if (c.moveToFirst()) {

            do {

                ArrayProductLists1 us = new ArrayProductLists1();
                // us.id = c.getString(productid);
                us.product = c.getString(productnames);
                us.qty = c.getString(qty);
                us.price = Integer.parseInt(c.getString(price));
                poslists.add(us);

                posnames.setText(c.getString(productnames));
                posprices.setText(c.getString(price));


            } while (c.moveToNext());
        }
    }


    public  void inserts() {


        try{

            String proids = productids.getText().toString();
            String pronames = posnames.getText().toString();
            int qty1s = Integer.parseInt(posquantities.getText().toString().trim());
            String prices = posprices.getText().toString();
            String to = postotals.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            String id = productids.getText().toString();
            final Cursor c = DB.rawQuery("select * from producttable where product = '" + id + "' ", null);
            int qty = c.getColumnIndex("qty");

            final ArrayList<ArrayProductLists1> poslistsss = new ArrayList<ArrayProductLists1>();
            if (c.moveToFirst()) {
                do {
                    ArrayProductLists1 us = new ArrayProductLists1();
                    us.qty = c.getString(qty);
                    poslistsss.add(us);


                    int oldqty = Integer.parseInt(c.getString(qty));

                    if (qty1s > oldqty) {

                        Toast.makeText(this, "Quantity is Not Enough" + "\t\t\t\t" + String.valueOf(oldqty), Toast.LENGTH_LONG).show();
                        posquantities.setText("");

                    } else {

                        DB.execSQL("CREATE TABLE IF NOT EXISTS posstable(proid VARCHAR, productname VARCHAR, qty VARCHAR, price VARCHAR, total VARCHAR, timestamp TEXT)");
                        String SQL = "insert into posstable(proid, productname, qty, price, total, timestamp)values(?,?,?,?,?,?)";
                        SQLiteStatement statement = DB.compileStatement(SQL);
                        statement.bindString(1, proids);
                        statement.bindString(2, pronames);
                        statement.bindString(3, String.valueOf(qty1s));
                        statement.bindString(4, prices);
                        statement.bindString(5, to);
                        statement.bindString(6, currentTimeStamp);
                        statement.execute();
                        //addtableanddeletetable();
                        //totalcount();
                        //Toast.makeText(this, "Point on Sale", Toast.LENGTH_SHORT).show();


                        String SQL1 = "update producttable set qty = qty - ? where product = ?";
                        SQLiteStatement statement1 = DB.compileStatement(SQL1);
                        statement1.bindLong(1, qty1s);
                        statement1.bindString(2, pronames);
                        statement1.execute();
                        productids.setText("");
                        posnames.setText("");
                        posquantities.setText("");
                        posprices.setText("");
                        postotals.setText("");
                        productids.requestFocus();

                    }


                } while (c.moveToNext());

            }


        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }



}