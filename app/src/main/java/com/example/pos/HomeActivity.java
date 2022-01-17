package com.example.pos;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity<galleryPermissions> extends AppCompatActivity {

    GridView gridpos;
    ArrayList<Product_Item> arraylistpos = new ArrayList<>();
    Product_CustomAdapter adapter = null;
    ImageButton brandy, categoryy, producty, listsy, cartbutton;
    //ImageView imgs;
    public static SQLHelper sqlHelper, sqlHelperbrand, sqlHelpercategory;
    final int REQUEST_CODE_GALLERY = 999;
    Dialog dialogproduct;
    String username;
    ImageView imgview;
    //for search to se4t text to pos dialog
    Cursor c;
    int productnames;
    int qtys;
    int price;
    TextView cartitemCountss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set user name
        names(HomeActivity.this);

        brandy = findViewById(R.id.imageButton1);
        categoryy = findViewById(R.id.imageButton2);
        producty = findViewById(R.id.imageButton3);
        listsy = findViewById(R.id.imageButton4);
        cartbutton = findViewById(R.id.imageButton5);
        cartitemCountss = findViewById(R.id.countingitem);





        gridpos = (GridView) findViewById(R.id.gridviews);
        arraylistpos = new ArrayList<>();
        adapter = new Product_CustomAdapter(this, R.layout.product_items, arraylistpos);
        gridpos.setAdapter(adapter);

       // imgview.setImageResource(R.drawable.dashboard_24);
        //set totalprice
        //totalprices();
        //Gridview products
        gridViews();
        try {
            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            String countQuery = "SELECT  * FROM  deletepostables" ;
            Cursor cursor = DB.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cartitemCountss.setText(String.valueOf(count));
            cursor.close();

        }catch (Exception e){

            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }







    //    posquantities.addTextChangedListener(new TextWatcher() {
    //        @Override
    //        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
   //         }

    //        @Override
    //        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    //            if (posquantities.length() != 0) {
    //                textsing();

     //           }
    //        }

   //         @Override
    //        public void afterTextChanged(Editable editable) {
    //        }
    //    });


   //     savess.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View view) {

                //trylang();
               // gridViews(HomeActivity.this);

    //            pointonsales(HomeActivity.this);


    //        }
    //    });




    //    cancel.setOnClickListener(new View.OnClickListener() {
    //        @Override
     //       public void onClick(View view) {
                // deleteAll();
      //          reciepts(HomeActivity.this);
     //           //recieptstable(HomeActivity.this);
      //          inserts();
     //       }
     //   });



        brandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brands(HomeActivity.this);
            }
        });

        categoryy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categors(HomeActivity.this);
            }
        });


        producty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producstss(HomeActivity.this);
            }
        });


        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catlists(HomeActivity.this);
                countcart();

            }
        });

        listsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence [] items = {"Brand Lists", "Category Lists", "Product Lists"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
                dialog.setTitle("Point on Sale Databases");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                            if(item == 0){
                                brandlists(HomeActivity.this);
                            }else if(item == 1){
                                categorlists(HomeActivity.this);
                            }else{
                                productlistss(HomeActivity.this );

                            }


                    }
                });
                dialog.show();

            }
        });




    }




    private void brands(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.brand);
        dialog.setTitle("Insert Brand");

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.6);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        Button button1, button2;
        EditText ed1, ed2;

        button1= dialog.findViewById(R.id.savebutton);
        button2 =dialog.findViewById(R.id.cancelbutton);
        ed1 =dialog.findViewById(R.id.editedone);
        ed2 = dialog.findViewById(R.id.editedtwo);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String brandtext = ed1.getText().toString();
                    String branddes = ed2.getText().toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

                    SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS  brandtable(id INTEGER PRIMARY KEY AUTOINCREMENT, brand VARCHAR, branddescription VARCHAR, timestamp TEXT)");
                    String SQL = "insert into brandtable(brand, branddescription, timestamp)values(?,?,?)";
                    SQLiteStatement statement = db.compileStatement(SQL);
                    statement.bindString(1, brandtext);
                    statement.bindString(2, branddes);
                    statement.bindString(3, currentTimeStamp);
                    statement.execute();
                    Toast.makeText(getApplicationContext(), "Brand Successfully Added", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed1.requestFocus();
                    dialog.dismiss();


                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    private void categors(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.category);
        dialog.setTitle("Insert Category");

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.6);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        Button button1, button2;
        EditText ed1, ed2;

        button1= dialog.findViewById(R.id.savebuttoncategory);
        button2 =dialog.findViewById(R.id.cancelbuttoncategory);
        ed1 =dialog.findViewById(R.id.editedonecategory);
        ed2 = dialog.findViewById(R.id.editedtwocategory);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String category_EditText = ed1.getText().toString();
                    String categoryview = ed2.getText().toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

                    SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS categorytable(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, categorydescription VARCHAR, timestamp TEXT)");
                    String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
                    SQLiteStatement statement = db.compileStatement(SQL);
                    statement.bindString(1, category_EditText);
                    statement.bindString(2, categoryview);
                    statement.bindString(3, currentTimeStamp);
                    statement.execute();
                    Toast.makeText(getApplicationContext(), "Category Successfully Added", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed1.requestFocus();
                    dialog.dismiss();



                }catch (Exception ex){

                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });






    }


    public void producstss(Activity activity){

        final Dialog dialogproduct = new Dialog(activity);
        dialogproduct.setContentView(R.layout.activity_product);
        dialogproduct.setTitle("Insert Product");


        imgview = dialogproduct.findViewById(R.id.imageView3);
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.9);
        dialogproduct.getWindow().setLayout(width, height);
        dialogproduct.show();

        Button button1, button2,button3;
        EditText ed1, ed2, ed33;


        button1= dialogproduct.findViewById(R.id.savebutton1);
        button2 =dialogproduct.findViewById(R.id.cancelbutton1);
        button3 = dialogproduct.findViewById(R.id.searchbutton1);
        ed1 =dialogproduct.findViewById(R.id.posname1);
        ed2 = dialogproduct.findViewById(R.id.pospqty1);
        ed33 = dialogproduct.findViewById(R.id.posprice1);
        ArrayList<String> productlists = new ArrayList<String>();
        ArrayList<String> productlists1 = new ArrayList<String>();
        ArrayAdapter productadapters;
        ArrayAdapter productadapters1;
        Spinner braidspinner, caidspinner;

        //for products
        braidspinner = dialogproduct.findViewById(R.id.branid11);
        caidspinner = dialogproduct.findViewById(R.id.categorid1);

        sqlHelper = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelper.queryData("CREATE TABLE IF NOT EXISTS productabless(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, product VARCHAR,  brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR)");



        //diria mao ning para ma kuha ang brand name sa brand og e load sa spinner(combobox)
        try{
            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            final Cursor c = DB.rawQuery("select * from brandtable", null);
            int branding = c.getColumnIndex("brand");
            productlists.clear();
            productadapters = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productlists);
            braidspinner.setAdapter(productadapters);
            final ArrayList<ArrayBrandLists> brandlists = new ArrayList<ArrayBrandLists>();
            if(c.moveToFirst())
            {
                do{
                    ArrayBrandLists us = new ArrayBrandLists();
                    us.brand = c.getString(branding);
                    brandlists.add(us);
                    productlists.add(c.getString(branding));
                }while(c.moveToNext());
                productadapters.notifyDataSetChanged();

            }

        }catch (Exception e){

            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        
        //diria mao ning para ma kuha ang category name sa category og e load sa spinner(combobox)
        try {
            SQLiteDatabase DB1 = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            final Cursor cc = DB1.rawQuery("select * from categorytable", null);

            int categoriess = cc.getColumnIndex("category");

            productlists1.clear();
            productadapters1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productlists1);
            caidspinner.setAdapter(productadapters1);
            final ArrayList<ArrayCategoryLists> categorylists = new ArrayList<ArrayCategoryLists>();
            if(cc.moveToFirst())
            {
                do{
                    ArrayCategoryLists uss = new ArrayCategoryLists();
                    uss.category = cc.getString(categoriess);
                    categorylists.add(uss);
                    productlists1.add(cc.getString(categoriess));

                }while(cc.moveToNext());
                productadapters1.notifyDataSetChanged();
            }
            
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        HomeActivity.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqlHelper.insertData(

                            imageViewToBytes(imgview),
                            ed1.getText().toString().trim(),
                            braidspinner.getSelectedItem().toString().trim(),
                            caidspinner.getSelectedItem().toString().trim(),
                            ed2.getText().toString().trim(),
                            ed33.getText().toString().trim()

                    );

                    Toast.makeText(getApplicationContext(), "Add to Cart Successfully", Toast.LENGTH_SHORT).show();
                    gridViews();
                    ed1.setText("");
                    ed2.setText("");
                    ed33.setText("");
                    dialogproduct.dismiss();
                    imgview.setImageResource(R.mipmap.ic_launcher);


                }catch (Exception e){

                    Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }



            }
        });





    }

    private void categorlists(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.category_list);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        ListView listbrands;
        ArrayList<String> arraylistbrand= new ArrayList<String>();
        ArrayAdapter arrayadapter;

        listbrands = dialog.findViewById(R.id.listviewcategory);


        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        final Cursor c = DB.rawQuery("select * from categorytable", null);

        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int categorydescription = c.getColumnIndex("categorydescription");
        int timestamp = c.getColumnIndex("timestamp");
        arraylistbrand.clear();
        arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistbrand);
        listbrands.setAdapter(arrayadapter);
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
                arraylistbrand.add("\t"+ c.getString(id) + "\t\t\t\t\t" + c.getString(category) +
                        "\t\t\t\t\t"+ c.getString(categorydescription) + "\t\t\t\t\t" + c.getString(timestamp));


            }while(c.moveToNext());
            arrayadapter.notifyDataSetChanged();
            listbrands.invalidateViews();

        }
        listbrands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                final Dialog dialog2 = new Dialog(activity);
                dialog2.setContentView(R.layout.category_edit);

                int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
                int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.65);
                dialog2.getWindow().setLayout(width, height);


                CharSequence [] items = {"Update", "Delete"};
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(HomeActivity.this);

                alertdialog.setTitle("Choose an action");
                alertdialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if(item == 0){


                            dialog2.show();

                            EditText brandname, branddes;
                            TextView branid;
                            Button editbut, delbut, cancelbut;

                            branid = dialog2.findViewById(R.id.brandid);
                            brandname = dialog2.findViewById(R.id.brandtext);
                            branddes = dialog2.findViewById(R.id.branddescrp);
                            cancelbut = dialog2.findViewById(R.id.backbrand);
                            editbut = dialog2.findViewById(R.id.editcategorbrand);

                            String x = arraylistbrand.get(position).toString();
                            ArrayCategoryLists us = catlists.get(position);

                            branid.setText(us.id);
                            brandname.setText(us.category);
                            branddes.setText(us.categorydescription);

                            editbut.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {

                                        String idcat = branid.getText().toString();
                                        String category_EditText = brandname.getText().toString();
                                        String categoryview = branddes.getText().toString();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

                                        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                                        //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
                                        //String SQL = "update brandtable set brand=?, branddescription=?, timestamp=? where id=?";
                                        String SQL = "update categorytable set category=?, categorydescription=?, timestamp=? where id=?";
                                        SQLiteStatement statement = db.compileStatement(SQL);
                                        statement.bindString(1, category_EditText);
                                        statement.bindString(2, categoryview);
                                        statement.bindString(3, currentTimeStamp);
                                        statement.bindString(4, idcat);
                                        statement.execute();
                                        Toast.makeText(getApplicationContext(), "Brand Successfully Updated", Toast.LENGTH_SHORT).show();
                                        brandname.setText("");
                                        branddes.setText("");
                                        branid.setText("");
                                        brandname.requestFocus();
                                        dialog2.dismiss();
                                        dialog.dismiss();

                                    }catch (Exception ex){

                                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                            cancelbut.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog2.dismiss();
                                }
                            });

                        } else {

                            sqlHelpercategory = new SQLHelper(getApplicationContext(), "pointonsale", null, 1);
                            sqlHelpercategory.queryData("CREATE TABLE IF NOT EXISTS categorytable(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, categorydescription VARCHAR, timestamp TEXT)");

                            Cursor c = HomeActivity.sqlHelpercategory.getData("SELECT id FROM categorytable");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();

                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show dialog delete here
                            showDialogDeleteCategory(arrID.get(position));
                            dialog.dismiss();

                        }

                    }
                });

                alertdialog.show();

            }
        });

    }


    private void brandlists(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.brand_list);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        ListView listbrand;
        ArrayList<String> arraylistbrand= new ArrayList<String>();
        ArrayAdapter arrayadapter;

        listbrand = dialog.findViewById(R.id.listviewbrand);


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

        listbrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                final Dialog dialog2 = new Dialog(activity);
                dialog2.setContentView(R.layout.brand_edit);

                int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
                int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.65);
                dialog2.getWindow().setLayout(width, height);


                CharSequence [] items = {"Update", "Delete"};
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(HomeActivity.this);

                alertdialog.setTitle("Choose an action");
                alertdialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if(item == 0){


                            dialog2.show();

                            EditText brandname, branddes;
                            TextView branid;
                            Button editbut, delbut, cancelbut;

                            branid = dialog2.findViewById(R.id.brandid);
                            brandname = dialog2.findViewById(R.id.brandtext);
                            branddes = dialog2.findViewById(R.id.branddescrp);
                            cancelbut = dialog2.findViewById(R.id.backbrand);
                            editbut = dialog2.findViewById(R.id.editcategorbrand);

                            String x = arraylistbrand.get(position).toString();
                            ArrayBrandLists us = brandlists.get(position);

                            branid.setText(us.id);
                            brandname.setText(us.brand);
                            branddes.setText(us.branddescription);

                            editbut.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {

                                        String idcat = branid.getText().toString();
                                        String category_EditText = brandname.getText().toString();
                                        String categoryview = branddes.getText().toString();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

                                        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                                        //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
                                        String SQL = "update brandtable set brand=?, branddescription=?, timestamp=? where id=?";
                                        SQLiteStatement statement = db.compileStatement(SQL);
                                        statement.bindString(1, category_EditText);
                                        statement.bindString(2, categoryview);
                                        statement.bindString(3, currentTimeStamp);
                                        statement.bindString(4, idcat);
                                        statement.execute();
                                        Toast.makeText(getApplicationContext(), "Brand Successfully Updated", Toast.LENGTH_SHORT).show();
                                        brandname.setText("");
                                        branddes.setText("");
                                        branid.setText("");
                                        brandname.requestFocus();
                                        dialog2.dismiss();
                                        dialog.dismiss();

                                    }catch (Exception ex){

                                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                            cancelbut.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog2.dismiss();
                                }
                            });

                        } else {

                            sqlHelperbrand = new SQLHelper(getApplicationContext(), "pointonsale", null, 1);
                            sqlHelperbrand.queryData("CREATE TABLE IF NOT EXISTS  brandtable(id INTEGER PRIMARY KEY AUTOINCREMENT, brand VARCHAR, branddescription VARCHAR, timestamp TEXT)");

                            Cursor c = HomeActivity.sqlHelperbrand.getData("SELECT id FROM brandtable");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();

                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show dialog delete here
                            showDialogDeleteBrand(arrID.get(position));
                            dialog.dismiss();

                        }

                    }
                });

                alertdialog.show();

            }
        });




    }

    private void productlistss(Activity activity){
        final Dialog dialogs = new Dialog(activity);
        dialogs.setContentView(R.layout.productgridviewlists);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.99);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialogs.getWindow().setLayout(width, height);
        dialogs.show();

        ArrayList<Product_Item> arraylistpos2 = new ArrayList<>();
        Product_CustomAdapter adapter2 = null;
        GridView grid;

        sqlHelper = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelper.queryData("CREATE TABLE IF NOT EXISTS productabless(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, product VARCHAR,  brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR)");




        grid = (GridView) dialogs.findViewById(R.id.gridviews2);
        arraylistpos2 = new ArrayList<>();
        adapter2 = new Product_CustomAdapter(this, R.layout.product_items, arraylistpos2);
        grid.setAdapter(adapter2);

        //get all data from sqlite

        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from productabless", null);
        arraylistpos2.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            String product = cursor.getString(2);
            String brands = cursor.getString(3);
            String category = cursor.getString(4);
            String qty = cursor.getString(5);
            String price = cursor.getString(6);
            arraylistpos2.add(new Product_Item(id, image, product, brands, category, qty, price));

        }
        adapter2.notifyDataSetChanged();


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positions, long l) {


                CharSequence [] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if(item == 0){
                            //update
                            Cursor c = HomeActivity.sqlHelper.getData("SELECT id FROM productabless");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }


                            //show dialog update at here

                            showDialogUpdate(HomeActivity.this, arrID.get(positions));
                            dialogs.dismiss();

                        } else {
                            Cursor c = HomeActivity.sqlHelper.getData("SELECT id FROM productabless");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();

                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show dialog delete here
                            showDialogDelete(arrID.get(positions));
                            dialogs.dismiss();
                        }

                    }
                });
                dialog.show();

            }
        });

        sqlHelper = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelper.queryData("CREATE TABLE IF NOT EXISTS productabless(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, product VARCHAR,  brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR)");

    }

    private void showDialogUpdate(Activity activity, final int positions){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.updatepos);
        dialog.setTitle("Update");
        final Dialog dialog2 = new Dialog(activity);
        dialog2.setContentView(R.layout.product_items);
        dialog2.setTitle("Update");


        final TextView edd1 = (TextView) dialog2.findViewById(R.id.Productnames);
        final EditText ed11 =(EditText) dialog.findViewById(R.id.updateposname1);
        final EditText ed21 =(EditText) dialog.findViewById(R.id.updatepospqty1);
        final EditText ed31 =(EditText) dialog.findViewById(R.id.updateposprice1);
        final Button b11 = dialog.findViewById(R.id.updatesearchbutton1);
        final Button b21 = dialog.findViewById(R.id.updatesavebutton1);
        //final Button b31 = dialog.findViewById(R.id.updatecancelbutton1);
        imgview = dialog.findViewById(R.id.updateimageView3);


        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //request photo library
                ActivityCompat.requestPermissions(
                        HomeActivity.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    HomeActivity.sqlHelper.updateData(
                            imageViewToBytes(imgview),
                            ed11.getText().toString().trim(),
                            ed21.getText().toString().trim(),
                            ed31.getText().toString().trim(),
                            positions
                    );
                    dialog.dismiss();
                    gridViews();
                    //productlistss(HomeActivity.this);
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();

                }catch(Exception error){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Update Error: ", error.getMessage());
                }



            }
        });

    }


    private void updateTables(){


        //get all data from sqlite

        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        Cursor c = DB.rawQuery("select * from productabless", null);
        arraylistpos.clear();
        final ArrayList<ArrayPOSLists2> brandlists = new ArrayList<>();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            byte[] image = c.getBlob(1);
            String product = c.getString(2);
            String brand = c.getString(3);
            String category = c.getString(4);
            String qty = c.getString(5);
            String price = c.getString(6);
            arraylistpos.add(new Product_Item(id, image, product, brand, category, qty, price));

        }
        adapter.notifyDataSetChanged();


    }


    private void showDialogDelete(final int id){
        sqlHelper = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelper.queryData("CREATE TABLE IF NOT EXISTS productabless(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, product VARCHAR,  brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR)");

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(HomeActivity.this);
        dialogDelete.setTitle("Warning!!!");
        dialogDelete.setMessage("Do you want to Remove this product?");

        dialogDelete.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try{
                    HomeActivity.sqlHelper.deleteData(id);
                    Toast.makeText(getApplicationContext(), "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
                    gridViews();
                    //updateTables();
                }catch (Exception error){

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Delete Error: ", error.getMessage());

                }


            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogDelete.show();

    }

    private void showDialogDeleteBrand(final int id){
        sqlHelperbrand = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelperbrand.queryData("CREATE TABLE IF NOT EXISTS  brandtable(id INTEGER PRIMARY KEY AUTOINCREMENT, brand VARCHAR, branddescription VARCHAR, timestamp TEXT)");

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(HomeActivity.this);
        dialogDelete.setTitle("Warning!!!");
        dialogDelete.setMessage("Do you want to Remove this Brand?");

        dialogDelete.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try{
                    HomeActivity.sqlHelperbrand.deleteBrandData(id);
                    Toast.makeText(getApplicationContext(), "Brand Deleted Successfully", Toast.LENGTH_SHORT).show();
                    gridViews();
                    //updateTables();
                }catch (Exception error){

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Delete Error: ", error.getMessage());

                }


            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogDelete.show();

    }

    private void showDialogDeleteCategory(final int id){
        sqlHelpercategory = new SQLHelper(getApplicationContext(), "pointonsale", null, 1);
        sqlHelpercategory.queryData("CREATE TABLE IF NOT EXISTS categorytable(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, categorydescription VARCHAR, timestamp TEXT)");

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(HomeActivity.this);
        dialogDelete.setTitle("Warning!!!");
        dialogDelete.setMessage("Do you want to Remove this Category?");

        dialogDelete.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try{
                    HomeActivity.sqlHelpercategory.deleteCategoryData(id);
                    Toast.makeText(getApplicationContext(), "Category Deleted Successfully", Toast.LENGTH_SHORT).show();
                    gridViews();
                    //updateTables();
                }catch (Exception error){

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Delete Error: ", error.getMessage());

                }


            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogDelete.show();

    }








    private void catlists(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.your_cartlist);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.6);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        ListView listing;
        ArrayList<String> postitles= new ArrayList<String>();
        ArrayAdapter arrayadpaterpos;
        Button cancel;
        TextView carts;

        cancel = dialog.findViewById(R.id.cancelbutton);
        listing = dialog.findViewById(R.id.listsproduct);
        carts = dialog.findViewById(R.id.cartotals);


        try {
            CartLists db = CartLists.getInstance(this);
            carts.setText("" + db.sumTotalItems());


        } catch (Exception e) {

            // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }




            try{

                SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                final Cursor c = DB.rawQuery("select * from deletepostables", null);
                int id = c.getColumnIndex("id");
                int name = c.getColumnIndex("name");
                int qtyy = c.getColumnIndex("qty");
                int price = c.getColumnIndex("price");
                int t = c.getColumnIndex("total");
                postitles.clear();
                arrayadpaterpos = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, postitles);
                listing.setAdapter(arrayadpaterpos);

                final ArrayList<ArrayPOSLists1> brandlists = new ArrayList<ArrayPOSLists1>();
                if(c.moveToFirst())
                {

                    do{
                        ArrayPOSLists1 us = new ArrayPOSLists1();
                        us.id = c.getString(id);
                        us.productname = c.getString(name);
                        us.qty = c.getString(qtyy);
                        us.price = c.getString(price);
                        us.total = c.getString(t);
                        brandlists.add(us);
                        postitles.add("\t\t"+c.getString(id) +"\t\t\t\t\t\t\t"+ c.getString(name) +"\t\t\t\t\t\t\t"+ c.getString(qtyy) +
                                "\t\t\t\t\t\t\t"+c.getString(price)+"\t\t\t\t\t\t\t"+ c.getString(t)+"\t\t\t\t\t\t\t");
                    }while(c.moveToNext());
                    arrayadpaterpos.notifyDataSetChanged();
                    listing.invalidateViews();

                }

                listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        String x = postitles.get(position).toString();
                        ArrayPOSLists1 uss = brandlists.get(position);

                        final Dialog dialogs = new Dialog(activity);
                        dialogs.setContentView(R.layout.pop);


                        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
                        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.5);
                        dialogs.getWindow().setLayout(width, height);

                        dialogs.show();


                        TextView t1,t2,t3,t4, t5;
                        Button bb;

                        bb = dialogs.findViewById(R.id.removeb);
                        t1 = dialogs.findViewById(R.id.idss);
                        t2 = dialogs.findViewById(R.id.nameproduct);
                        t3 =dialogs.findViewById(R.id.quantproduct);
                        t4 = dialogs.findViewById(R.id.priceproduct);


                        t1.setText(uss.id);
                        t2.setText(uss.productname);
                        t3.setText(uss.qty);
                        t4.setText(uss.price);

                        bb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                try {

                                    String idcat = t1.getText().toString();
                                    String productnames = t2.getText().toString();
                                    int qty1s = Integer.parseInt(t3.getText().toString());


                                    SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                                    String SQL1 = "update productabless set qty = qty + ? where product =?";
                                    SQLiteStatement statement1 = db.compileStatement(SQL1);
                                    statement1.bindLong(1, qty1s);
                                    statement1.bindString(2, productnames);
                                    statement1.execute();
                                    //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
                                    String SQL = "delete from deletepostables where id=?";
                                    SQLiteStatement statement = db.compileStatement(SQL);
                                    statement.bindString(1, idcat);
                                    statement.execute();
                                    Toast.makeText(getApplicationContext(), "Item Successfully Removed", Toast.LENGTH_SHORT).show();
                                    dialogs.dismiss();
                                    //countcart();
                                    //totalprices();


                                    //para kung mag close ang remove na dialog automatic mag refresh or load ang bawas nga item sa list
                                    SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                                    final Cursor c = DB.rawQuery("select * from deletepostables", null);
                                    int id = c.getColumnIndex("id");
                                    int name = c.getColumnIndex("name");
                                    int qtyy = c.getColumnIndex("qty");
                                    int price = c.getColumnIndex("price");
                                    int t = c.getColumnIndex("total");
                                    postitles.clear();
                                    listing.setAdapter(arrayadpaterpos);

                                    final ArrayList<ArrayPOSLists1> brandlists = new ArrayList<ArrayPOSLists1>();
                                    if(c.moveToFirst())
                                    {

                                        do{
                                            ArrayPOSLists1 us = new ArrayPOSLists1();
                                            us.id = c.getString(id);
                                            us.productname = c.getString(name);
                                            us.qty = c.getString(qtyy);
                                            us.price = c.getString(price);
                                            us.total = c.getString(t);
                                            brandlists.add(us);
                                            postitles.add("\t\t"+c.getString(id) +"\t\t\t\t\t\t\t"+ c.getString(name) +"\t\t\t\t\t\t\t"+ c.getString(qtyy) +
                                                    "\t\t\t\t\t\t\t"+c.getString(price)+"\t\t\t\t\t\t\t"+ c.getString(t)+"\t\t\t\t\t\t\t");
                                        }while(c.moveToNext());
                                        arrayadpaterpos.notifyDataSetChanged();
                                        listing.invalidateViews();

                                    }
                                    gridViews();
                                    countcart();
                                }catch (Exception ex){

                                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }



                        });


                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        final Dialog dialogg = new Dialog(activity);
                        dialogg.setContentView(R.layout.reciept);
                        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
                        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.77);
                        dialogg.getWindow().setLayout(width, height);
                        dialog.dismiss();
                        dialogg.show();

                        TextView dates,hours, totals, itemsss, cash, change;
                        Button placeorder,backs;
                        ListView listbrands;
                        ArrayList<String> arraylistbrands= new ArrayList<String>();
                        ArrayAdapter arrayadapters;


                        listbrands = dialogg.findViewById(R.id.lists);
                        totals = dialogg.findViewById(R.id.totals);
                        itemsss = dialogg.findViewById(R.id.items);
                        cash = dialogg.findViewById(R.id.cash);
                        change = dialogg.findViewById(R.id.change);
                        placeorder = dialogg.findViewById(R.id.plorder);
                        backs = dialogg.findViewById(R.id.back);



                        dates =  dialogg.findViewById(R.id.editTextDate);
                        hours =  dialogg.findViewById(R.id.TextDate);
                        //totals = findViewById(R.id.totals);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        dates.setText(dateFormat.format(calendar.getTime()));

                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss a");
                        Calendar calendar2 = Calendar.getInstance();
                        hours.setText(dateFormat2.format(calendar2.getTime()));




                        //item lists
                        try {
                            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                            final Cursor c = DB.rawQuery("select * from deletepostables", null);

                            int id = c.getColumnIndex("id");
                            int name = c.getColumnIndex("name");
                            int qtyy = c.getColumnIndex("qty");
                            int price = c.getColumnIndex("price");
                            arraylistbrands.clear();
                            arrayadapters = new ArrayAdapter(getApplicationContext(), R.layout.listviewi_item_color, arraylistbrands);
                            listbrands.setAdapter(arrayadapters);
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
                                    arraylistbrands.add("\t\t"+c.getString(id)+"\t\t"+ c.getString(name) + "\t\t\t\t\t\t\t" + c.getString(qtyy) +
                                            "\t\t\t\t\t\t\t"+ c.getString(price)+"\t\t\t\t\t\t\t");
                                }while(c.moveToNext());
                                arrayadapters.notifyDataSetChanged();
                                listbrands.invalidateViews();

                            }

                        }catch (Exception e){

                            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                        backs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialogg.dismiss();
                                dialog.dismiss();

                            }
                        });



                        //totalcount for items
                        try {
                            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                            String countQuery = "SELECT  * FROM  deletepostable" ;
                            Cursor cursor = DB.rawQuery(countQuery, null);
                            int count = cursor.getCount();
                            itemsss.setText("You've purchased"+"\t"+String.valueOf(count)+"\t"+"items");
                            cursor.close();

                        }catch (Exception e){

                            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        //set text to totalprice
                        try {
                            CartLists db = CartLists.getInstance(HomeActivity.this);

                            totals.setText("" + db.sumTotalItems());


                        } catch (Exception e) {

                            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }


                        float tt = Float.parseFloat(totals.getText().toString().trim());

                        if(tt < 1){

                            placeorder.setEnabled(false);

                        } else {



                            placeorder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    try {

                                        String text = cash.getText().toString();
                                        float t = Float.parseFloat(totals.getText().toString());
                                        float c = Float.parseFloat(cash.getText().toString());

                                        if(TextUtils.isEmpty(text)){
                                            cash.setError("Money cannot be empty");
                                            return;
                                        }else if(t > c){

                                            cash.setError("Your cash is not Enough");
                                            cash.setText("");

                                        } else if (t < c){

                                            float tot=0;
                                            tot = t - c;
                                            change.setText(String.valueOf(tot));
                                            totals.setText("");
                                            cash.setText("");
                                            listbrands.setAdapter(null);
                                            deleteReciept();
                                            Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT).show();
                                            cartitemCountss.setText("");

                                        } else if (t == c){

                                            float tot;

                                            tot = t - c;
                                            change.setText(String.valueOf(tot));
                                            totals.setText("");
                                            cash.setText("");
                                            listbrands.setAdapter(null);
                                            deleteReciept();
                                            Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT).show();
                                            cartitemCountss.setText("");

                                            //totalprices();

                                        }

                                    }catch (Exception e){
                                        change.setError("Error");
                                    }

                                    countcart();
                                    //catlists(HomeActivity.this);
                                    //backtobasicclose(HomeActivity.this);

                                       // dialog.dismiss();
                                       // dialogg.dismiss();

                                }
                            });


                        }



                    }
                });

            }catch (Exception e){

            }

    }




    private void names (Activity activity){

        //for usernames
        Intent intent = getIntent();
        username = intent.getStringExtra("names").toString();
        ActionBar ab = getSupportActionBar();


        //String n = ab.getTitle().toString();
        SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        //email,fullname,password
        final Cursor c = DB.rawQuery("select * from usertable where email = '" + username + "'", null);
        //int productid = c.getColumnIndex("id");

        int fname = c.getColumnIndex("fullname");

        final ArrayList<ArrayProductLists1> poslists = new ArrayList<ArrayProductLists1>();
        if (c.moveToFirst()) {

            do {

                ArrayProductLists1 us = new ArrayProductLists1();
                // us.id = c.getString(productid);
                us.fullname = c.getString(fname);
                poslists.add(us);

                //diri fullname
                ab.setTitle("\t\t"+c.getString(fname));



            } while (c.moveToNext());
        }

    }


    public void gridViews(){
        //get all data from sqlite
        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from productabless", null);
        arraylistpos.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            String product = cursor.getString(2);
            String brands = cursor.getString(3);
            String category = cursor.getString(4);
            String qty = cursor.getString(5);
            String price = cursor.getString(6);
            arraylistpos.add(new Product_Item(id, image, product, brands, category, qty, price));

        }
        adapter.notifyDataSetChanged();

    }


    private void reciepts(Activity activity){

        final Dialog dialogg = new Dialog(activity);
        dialogg.setContentView(R.layout.reciept);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialogg.getWindow().setLayout(width, height);
        dialogg.show();

        TextView dates,hours, totals, itemsss, cash, change;
        Button placeorder;
        ListView listbrand;
        ArrayList<String> arraylistbrand= new ArrayList<String>();
        ArrayAdapter arrayadapter;


        listbrand = dialogg.findViewById(R.id.lists);
        totals = dialogg.findViewById(R.id.totals);
        itemsss = dialogg.findViewById(R.id.items);
        cash = dialogg.findViewById(R.id.cash);
        change = dialogg.findViewById(R.id.change);
        placeorder = dialogg.findViewById(R.id.plorder);



        dates =  dialogg.findViewById(R.id.editTextDate);
        hours =  dialogg.findViewById(R.id.TextDate);
        //totals = findViewById(R.id.totals);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        dates.setText(dateFormat.format(calendar.getTime()));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss a");
        Calendar calendar2 = Calendar.getInstance();
        hours.setText(dateFormat2.format(calendar2.getTime()));




        //item lists
        try {
            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            final Cursor c = DB.rawQuery("select * from deletepostables", null);

            int id = c.getColumnIndex("id");
            int name = c.getColumnIndex("name");
            int qtyy = c.getColumnIndex("qty");
            int price = c.getColumnIndex("price");
            arraylistbrand.clear();
            arrayadapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraylistbrand);
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
                    arraylistbrand.add("\t\t"+c.getString(id)+"\t\t"+ c.getString(name) + "\t\t\t\t\t\t\t" + c.getString(qtyy) +
                            "\t\t\t\t\t\t\t"+ c.getString(price)+"\t\t\t\t\t\t\t");
                }while(c.moveToNext());
                arrayadapter.notifyDataSetChanged();
                listbrand.invalidateViews();

            }

        }catch (Exception e){

            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }



        //totalcount for items
        try {
            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            String countQuery = "SELECT  * FROM  deletepostable" ;
            Cursor cursor = DB.rawQuery(countQuery, null);
            int count = cursor.getCount();
            itemsss.setText("You've purchased"+"\t"+String.valueOf(count)+"\t"+"items");
            cursor.close();

        }catch (Exception e){

            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        //set text to totalprice
        try {
            CartLists db = CartLists.getInstance(this);

            totals.setText("" + db.sumTotalItems());


        } catch (Exception e) {

            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }





        float tt = Float.parseFloat(totals.getText().toString().trim());

        if(tt < 1){

            placeorder.setEnabled(false);

        } else {



            placeorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                try {

                    String text = cash.getText().toString();
                    float t = Float.parseFloat(totals.getText().toString());
                    float c = Float.parseFloat(cash.getText().toString());

                    if(TextUtils.isEmpty(text)){
                        cash.setError("Money cannot be empty");
                        return;
                    }else if(t > c){

                        cash.setError("Your cash is not Enough");
                        cash.setText("");

                    } else if (t < c){

                        float tot=0;
                        tot = t - c;
                        change.setText(String.valueOf(tot));
                        totals.setText("");
                        cash.setText("");
                        listbrand.clearFocus();
                        deleteReciept();
                        countcart();
                        //catlists(HomeActivity.this);
                        //backtobasicclose(HomeActivity.this);
                        dialogg.dismiss();


                    } else if (t == c){

                        float tot;

                        tot = t - c;
                        change.setText(String.valueOf(tot));
                        totals.setText("");
                        cash.setText("");
                        listbrand.clearFocus();
                        countcart();
                        //catlists(HomeActivity.this);
                        //backtobasicclose(HomeActivity.this);
                        deleteReciept();
                        dialogg.dismiss();
                        //totalprices();
                       // countcart();
                    }

                }catch (Exception e){
                    change.setError("Error");
                }

                    countcart();
                    //catlists(HomeActivity.this);
                    //backtobasicclose(HomeActivity.this);
                }




            });




        }


    }


    public void deleteReciept() {
        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS deletepostables");
        // Toast.makeText(this, "Database Successfully Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }






    private byte[] imageViewToBytes(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY );
            } else {
                Toast.makeText(getApplicationContext(), "No permission", Toast.LENGTH_SHORT).show();
            }
            return;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgview.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {

            prof(HomeActivity.this);
            return true;

        } else if (id == R.id.logout) {

            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearch, menu);
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                String id = searchView.getQuery().toString();
                SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                c = DB.rawQuery("select * from productabless where product = '" + id + "'", null);
                //int productid = c.getColumnIndex("id");

                productnames = c.getColumnIndex("product");
                qtys = c.getColumnIndex("qty");
                price = c.getColumnIndex("price");

                final ArrayList<ArrayProductLists1> poslists = new ArrayList<ArrayProductLists1>();
                if (c.moveToFirst()) {

                    do {

                        ArrayProductLists1 us = new ArrayProductLists1();
                        // us.id = c.getString(productid);
                        us.product = c.getString(productnames);
                        us.qty = c.getString(qtys);
                        us.price = Integer.parseInt(c.getString(price));
                        poslists.add(us);
                        pointonsales(HomeActivity.this);
                        gridViews();

                    } while (c.moveToNext());
                } else {
                    //Toast.makeText(getApplicationContext(), "Product not Found", Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(HomeActivity.this)
                            .setMessage("Product not Found")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    closeContextMenu();
                                    closeOptionsMenu();
                                }
                            }).show();

                }


                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               // arraylistpos.clear();
                return true;
            }
        });


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  arraylistpos.clear();
            }
        });

        return true;

    }



    public void pointonsales(Activity activity){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.pos);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        TextInputEditText posquantities;
        TextView posprices, posnames, postotals, cartotalsss, cartotals2;
        Button savess, searching, cancel,cart;



        posprices = dialog.findViewById(R.id.posprice);
        posquantities = dialog.findViewById(R.id.posquantity);
        posnames = dialog.findViewById(R.id.possname);
        postotals = dialog.findViewById(R.id.postotal);
        cartotalsss = dialog.findViewById(R.id.cartotals);
        cartotals2 = dialog.findViewById(R.id.tsss);
        searching = dialog.findViewById(R.id.searchbutton);
        savess = dialog.findViewById(R.id.savebutton);
        imgview = dialog.findViewById(R.id.imageView55);


        try {
            CartLists db = CartLists.getInstance(this);
            cartotalsss.setText("" + db.sumTotalItems());


        } catch (Exception e) {

            // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }



        //to set ni
        byte [] image = c.getBlob(1);
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        imgview.setImageBitmap(bmp);
        posnames.setText(c.getString(productnames));
        posprices.setText(c.getString(price));


        //automatic na mag compute sa quantities x  price
        posquantities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (posquantities.length() != 0) {

                            try {

                                int total = 0;
                                int posqty = Integer.parseInt(posquantities.getText().toString());
                                int pospricessss = Integer.parseInt(posprices.getText().toString());
                                total = posqty * pospricessss;
                                //String tots = posquantities.getText().toString();
                                postotals.setText(Integer.toString(total));

                            } catch (Exception ex) {

                                // Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //add to cart
        savess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    int tot;
                    String tots = postotals.getText().toString();
                    String productnames = posnames.getText().toString();
                    String prices = posprices.getText().toString();
                    String qtyy = posquantities.getText().toString();
                    int qty1s = Integer.parseInt(String.valueOf(posquantities.getText().toString().trim()));
                    String id = posnames.getText().toString();


                    SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                    final Cursor c = DB.rawQuery("select * from productabless where product = '" + id + "' ", null);
                    int qty = c.getColumnIndex("qty");

                    final ArrayList<ArrayProductLists1> poslistsss = new ArrayList<ArrayProductLists1>();
                    if (c.moveToFirst()) {
                        do {
                            ArrayProductLists1 us = new ArrayProductLists1();
                            us.qty = c.getString(qty);
                            poslistsss.add(us);


                            int oldqty = Integer.parseInt(c.getString(qty));

                            if (qty1s > oldqty) {

                                Toast.makeText(getApplicationContext(), "Sold Out", Toast.LENGTH_LONG).show();
                                dialog.dismiss();

                            } else {

                                SQLiteDatabase dbs = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                                dbs.execSQL("CREATE TABLE IF NOT EXISTS  deletepostables(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,qty VARCHAR, price VARCHAR, total VARCHAR)");
                                String SQLs = "insert into deletepostables(name,qty,price,total)values(?,?,?,?)";
                                SQLiteStatement statements = dbs.compileStatement(SQLs);
                                statements.bindString(1, productnames);
                                statements.bindString(2, qtyy);
                                statements.bindString(3, prices);
                                statements.bindString(4, tots);
                                statements.execute();
                                posnames.setText("");
                                posprices.setText("");
                                posquantities.setText("");
                                postotals.setText("");

                                countcart();
                                gridViews();

                                //total prices all of your added items
                                try {
                                    CartLists db = CartLists.getInstance(HomeActivity.this);
                                    cartotalsss.setText("" + db.sumTotalItems());

                                } catch (Exception e) {

                                    // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }


                                //imgview.setImageResource(R.drawable.dashboard_24);
                                Toast.makeText(getApplicationContext(), "Total Successfully Added", Toast.LENGTH_SHORT).show();
                                gridViews();
                                dialog.dismiss();
                                gridViews();

                                String SQL1 = "update productabless set qty = qty - ? where product = ?";
                                SQLiteStatement statement1 = DB.compileStatement(SQL1);
                                statement1.bindLong(1, qty1s);
                                statement1.bindString(2, productnames);
                                statement1.execute();
                                gridViews();
                            }
                        } while (c.moveToNext());
                    }



                }catch (Exception e){

                }



            }
        });



    }



    public void prof(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.profilenames);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        TextView tt, ttt, tttt;
        TextInputEditText passs, passs1, passs2;
        Button b;

        tt = dialog.findViewById(R.id.users);
        ttt = dialog.findViewById(R.id.fullnamess);
        tttt = dialog.findViewById(R.id.userid);
        passs = dialog.findViewById(R.id.currentPassword);
        passs1 = dialog.findViewById(R.id.newPassword);
        passs2 = dialog.findViewById(R.id.typenewPassword);
        b = dialog.findViewById(R.id.updatebutton);


        //to set this name to another layuout from action bar to textview of profile layout
        tt.setText(username);


        //to set fullname to the profile layout using search and set it
        try {

            String id = tt.getText().toString();
            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            //usertable(email,fullname,password
            final Cursor c = DB.rawQuery("select * from usertable where email = '" + id + "'", null);
            //int productid = c.getColumnIndex("id");

            int idhan = c.getColumnIndex("id");
            int fnamess = c.getColumnIndex("fullname");

            final ArrayList<ArrayUsers> poslists = new ArrayList<ArrayUsers>();
            if (c.moveToFirst()) {

                do {

                    ArrayUsers us = new ArrayUsers();
                    // us.id = c.getString(productid);
                    us.id = c.getInt(idhan);
                    us.fullname = c.getString(fnamess);


                    ttt.setText(c.getString(fnamess));
                    tttt.setText(String.valueOf(c.getInt(idhan)));

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //to update the profile acccount


                String w = passs.getText().toString();
                String ww = passs1.getText().toString();
                String www = passs2.getText().toString();
                String idss = tttt.getText().toString();

                SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM usertable WHERE password = '" + w + "'", null);

                if (cursor.moveToFirst()==true){

                            if(passs1.getText().toString().equals(passs2.getText().toString())){


                                try {

                                    //usertable(email,fullname,password
                                    String SQL = "update usertable set  password=? where id=?";
                                    SQLiteStatement statement = db.compileStatement(SQL);
                                    statement.bindString(1, www);
                                    statement.bindString(2, idss);
                                    statement.execute();
                                    Toast.makeText(getApplicationContext(), "Your Account Successfully Updated", Toast.LENGTH_SHORT).show();
                                    passs.setText("");
                                    passs1.setText("");
                                    passs2.setText("");
                                    passs.requestFocus();


                                }catch (Exception ex){

                                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            } else if(passs.equals("")||passs1.equals("")||passs2.equals("")){
                                passs.setError("Field is blank");
                                passs1.setError("Field is blank");
                                passs2.setError("Field is blank");
                            } else if(passs1.equals("")){

                                passs1.setError("Field is blank");

                            }else if(passs2.equals("")){

                                passs2.setError("Field is blank");

                            }


                } else if(cursor.moveToFirst()==false){
                    passs.setError("Your Password do not match, Please try again");
                }else if(passs1.equals("")){

                    passs1.setError("Field is blank");

                }else if(passs2.equals("")){

                    passs2.setError("Field is blank");

                }

            }
        });





    }


    public void countcart(){
        try {


            SQLiteDatabase DB = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            String countQuery = "SELECT  * FROM  deletepostables" ;
            Cursor cursor = DB.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cartitemCountss.setText(String.valueOf(count));
            cursor.close();

        }catch (Exception e){

            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }









}


