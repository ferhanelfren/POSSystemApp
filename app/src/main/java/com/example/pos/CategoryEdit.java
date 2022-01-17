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

public class CategoryEdit extends AppCompatActivity {

    EditText catname, catdes;
    TextView catid;
    Button editbut, delbut, cancelbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_edit);

        catid = findViewById(R.id.brandid);
        catname = findViewById(R.id.brandtext);
        catdes = findViewById(R.id.branddescrp);

        editbut = findViewById(R.id.editcategorbrand);




        //from CategoryViewActivity epasa diria para ma set ang text gikan
        //sa List view hangtod diria sa EditText
        Intent intent = getIntent();
        String id = intent.getStringExtra("id").toString();
        String category = intent.getStringExtra("category").toString();
        String categorydescription = intent.getStringExtra("categorydescription").toString();

        catid.setText(id);
        catname.setText(category);
        catdes.setText(categorydescription);



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

            String idcat = catid.getText().toString();
            String category_EditText = catname.getText().toString();
            String categoryview = catdes.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
            String SQL = "update categorytable set category=?, categorydescription=?, timestamp=? where id=?";
            SQLiteStatement statement = db.compileStatement(SQL);
            statement.bindString(1, category_EditText);
            statement.bindString(2, categoryview);
            statement.bindString(3, currentTimeStamp);
            statement.bindString(4, idcat);
            statement.execute();
            Toast.makeText(this, "Category Successfully Updated", Toast.LENGTH_SHORT).show();
            catname.setText("");
            catdes.setText("");
            catid.setText("");
            catname.requestFocus();
            //After ma Update Deretso Dayun sa Table
            Intent intent = new Intent(getApplicationContext(), CategoryActivityList.class);
            startActivity(intent);



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }


    public void delete(){


        try {

            String idcat = catid.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            //String SQL = "insert into categorytable(category, categorydescription, timestamp)values(?,?,?)";
            String SQL = "delete from categorytable where id=?";
            SQLiteStatement statement = db.compileStatement(SQL);
            statement.bindString(1, idcat);
            statement.execute();
            Toast.makeText(this, "Category Successfully Deleted", Toast.LENGTH_SHORT).show();
            catname.setText("");
            catdes.setText("");
            catid.setText("");
            catname.requestFocus();
            //After ma Update Deretso Dayun sa Table
            Intent intent = new Intent(getApplicationContext(), CategoryActivityList.class);
            startActivity(intent);



        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }





}