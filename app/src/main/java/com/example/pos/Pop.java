package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pop extends AppCompatActivity {

    TextView t1,t2,t3,t4, x;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop);
       // getSupportActionBar().setTitle("Item List");

        Intent intent = getIntent();
        String v1 = intent.getStringExtra("id");
        String v2 = intent.getStringExtra("productname");
        String v3 = intent.getStringExtra("qty");
        String v4 = intent.getStringExtra("prices");

        t1 = findViewById(R.id.idss);
        t2 = findViewById(R.id.nameproduct);
        t3 = findViewById(R.id.quantproduct);
        t4 = findViewById(R.id.priceproduct);
        b1 = findViewById(R.id.removeb);


        t1.setText(v1);
        t2.setText(v2);
        t3.setText(v3);
        t4.setText(v4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();

            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.itemni){
            Intent intent = new Intent (Pop.this, HomeActivity.class);
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

    public void delete(){


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
            String SQL = "delete from deletepostable where id=?";
            SQLiteStatement statement = db.compileStatement(SQL);
            statement.bindString(1, idcat);
            statement.execute();
            Toast.makeText(this, "Item Successfully Removed", Toast.LENGTH_SHORT).show();



            Intent intent = new Intent(getApplicationContext(), YourCartList.class);
            startActivity(intent);





        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }


}