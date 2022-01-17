package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_ActivityMain extends AppCompatActivity {
    EditText names, sub;
    Button save, dis;
    Delete_DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_main);
        dBmain = new Delete_DBmain(this);
        //create method
        findId();
        insert();
        cleardata();

    }

    private void cleardata() {
        names.setText("");
        sub.setText("");


    }

    private void insert(){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = dBmain.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name", names.getText().toString());
                cv.put("subject", sub.getText().toString());

                Long recid = sqLiteDatabase.insert("course", null, cv);

                    if(recid!=null){

                        Toast.makeText(Delete_ActivityMain.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();


                    } else{

                        Toast.makeText(Delete_ActivityMain.this, "Something Wrong, please try again later", Toast.LENGTH_SHORT).show();

                    }


            }
        });



        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Delete_ActivityMain.this, Delete_DisplayActivity.class));


            }
        });


    }

    private void findId(){
        names = (EditText) findViewById(R.id.name);
        sub = (EditText) findViewById(R.id.subject);
        save = (Button) findViewById(R.id.submit);
        dis =(Button) findViewById(R.id.display);
    }


}
