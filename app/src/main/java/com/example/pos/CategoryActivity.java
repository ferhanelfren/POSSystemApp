package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CategoryActivity extends AppCompatActivity {

    EditText cat, catv;
    Button saveb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        getSupportActionBar().setTitle("Categories");

        cat = findViewById(R.id.editedonecategory);
        catv = findViewById(R.id.editedtwocategory);



        //button
        saveb = findViewById(R.id.savebuttoncategory);

        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

    }

    public void insert(){

        try {

            String category_EditText = cat.getText().toString();
            String categoryview = catv.getText().toString();
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
            Toast.makeText(this, "Category Successfully Added", Toast.LENGTH_SHORT).show();
            cat.setText("");
            catv.setText("");
            cat.requestFocus();



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}