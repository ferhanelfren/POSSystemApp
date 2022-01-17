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

public class BrandActivity extends AppCompatActivity {

    EditText cat, catv;
    Button saveb;
    //DBClass DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand);
        getSupportActionBar().setTitle("Brands");
        cat = findViewById(R.id.editedone);
        catv = findViewById(R.id.editedtwo);
        //DB = new DBClass(this);


        //button
        saveb = findViewById(R.id.savebutton);

        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert();

            }
        });


    }

    public String insert(){

        try {

            String brandtext = cat.getText().toString();
            String branddes = catv.getText().toString();
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
            Toast.makeText(this, "Brand Successfully Added", Toast.LENGTH_SHORT).show();
            cat.setText("");
            catv.setText("");
            cat.requestFocus();

            return currentTimeStamp;

        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return null;
    }


    public void save(){


        String category_EditText = cat.getText().toString();
        String categoryview = catv.getText().toString();

      //  Boolean savedata = DB.insertuserdata(category_EditText,categoryview);

       // if(savedata==true){

         //   Toast.makeText(this, "Category Added", Toast.LENGTH_SHORT).show();


       // } else{

            Toast.makeText(this, "Category Failed", Toast.LENGTH_SHORT).show();


       // }

    }

}