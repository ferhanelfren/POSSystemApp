package com.example.pos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
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
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Product extends AppCompatActivity {

    TextView t1;
    EditText ed1, ed2, ed3, ed4;
    Button b1, b2, b3;
    ListView listss;
    Spinner braidspinner, caidspinner;
    ArrayList<String> productlist = new ArrayList<String>();
    ArrayList<String> productlist1 = new ArrayList<String>();
    ArrayAdapter productadapter;
    ArrayAdapter productadapter1;
    String filePath;
    public static SQLHelper sqlHelper;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imgview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //t1 = findViewById(R.id.imageid);
        ed1 = findViewById(R.id.posname1);
        ed2 = findViewById(R.id.pospqty1);
        ed3 = findViewById(R.id.posprice1);
        b1 = findViewById(R.id.searchbutton1);
        b2 = findViewById(R.id.savebutton1);
        b3 = findViewById(R.id.cancelbutton1);
        imgview = findViewById(R.id.imageView3);
        //listss = findViewById(R.id.listview1);


        braidspinner = findViewById(R.id.branid1);
        caidspinner = findViewById(R.id.categorid1);

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





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(Intent.ACTION_PICK,
                //        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

              // final int ACTIVITY_SELECT_IMAGE = 1234;
               // startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

                ActivityCompat.requestPermissions(
                        Product.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );


            }
        });






        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    sqlHelper.insertData(

                    imageViewToBytes(imgview),
                    ed1.getText().toString().trim(),
                    braidspinner.getSelectedItem().toString().trim(),
                    caidspinner.getSelectedItem().toString().trim(),
                    ed2.getText().toString().trim(),
                    ed3.getText().toString().trim()

                    );

                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    imgview.setImageResource(R.mipmap.ic_launcher);


                }catch (Exception e){

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product.this,ProductList.class);
                startActivity(intent);
            }
        });



        sqlHelper = new SQLHelper(this, "pointonsale", null, 1);
        sqlHelper.queryData("CREATE TABLE IF NOT EXISTS productabless(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, product VARCHAR,  brand VARCHAR, category VARCHAR, qty VARCHAR, price VARCHAR)");
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

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
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

    private void init(){

    }


}