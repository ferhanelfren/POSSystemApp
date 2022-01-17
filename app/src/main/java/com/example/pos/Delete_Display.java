package com.example.pos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Delete_Display extends AppCompatActivity {

    Delete_DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Delete_Model> modelArrayList;
    Delete_MyAdapter myAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_displaydata);

        modelArrayList = new ArrayList<>();
        dBmain = new Delete_DBmain(this);

        //create method
        findId();
        modelArrayList = displayData();
        myAdapter = new Delete_MyAdapter(modelArrayList, Delete_Display.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Delete_Display.this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter   );

    }

    private ArrayList<Delete_Model> displayData() {

        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from course", null);
        ArrayList<Delete_Model> modelArrayList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                modelArrayList.add(new Delete_Model(cursor.getString(1), cursor.getString(2)));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;

    }

    private void findId() {

        recyclerView = findViewById(R.id.rv);

    }


}
