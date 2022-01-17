package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BrandEdit extends AppCompatActivity {

    EditText brandname, branddes;
    TextView branid;
    Button editbut, delbut, cancelbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_edit);


        branid = findViewById(R.id.brandid);
        brandname = findViewById(R.id.brandtext);
        branddes = findViewById(R.id.branddescrp);

        editbut = findViewById(R.id.editcategorbrand);



        //from CategoryViewActivity epasa diria para ma set ang text gikan
        //sa List view hangtod diria sa EditText
        Intent intent = getIntent();
        String id = intent.getStringExtra("id").toString();
        String brand = intent.getStringExtra("brand").toString();
        String branddescription = intent.getStringExtra("branddescription").toString();

        branid.setText(id);
        brandname.setText(brand);
        branddes.setText(branddescription);



        editbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit();

            }
        });


        delbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();


            }
        });

        cancelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);

            }
        });


    }


    public void edit(){

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
            Toast.makeText(this, "Brand Successfully Updated", Toast.LENGTH_SHORT).show();
            brandname.setText("");
            branddes.setText("");
            branid.setText("");
            brandname.requestFocus();
            //After ma Update Deretso Dayun sa Table
            Intent intent = new Intent(getApplicationContext(), CategoryActivityList.class);
            startActivity(intent);



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }


    public void delete(){


        try {

            String idcat = branid.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
            String SQL = "delete from brandtable where id=?";
            SQLiteStatement statement = db.compileStatement(SQL);
            statement.bindString(1, idcat);
            statement.execute();
            Toast.makeText(this, "Brand Successfully Deleted", Toast.LENGTH_SHORT).show();
            brandname.setText("");
            branddes.setText("");
            branid.setText("");
            brandname.requestFocus();
            //After ma Update Deretso Dayun sa Table
            Intent intent = new Intent(getApplicationContext(), CategoryActivityList.class);
            startActivity(intent);



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }




}