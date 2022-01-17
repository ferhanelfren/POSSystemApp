package com.example.pos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DBClass extends SQLiteOpenHelper {




    public static final String tablename = "userdetails";
    private static DBClass databaseInstance = null;


    public DBClass(Context context) {

        super(context, "pointonsale.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table categorytable(id INTEGER primary key, category TEXT, categorydescription TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("DROP TABLE IF EXISTS categorytable");
        onCreate(DB);

    }




    public Boolean insertuserdata(String category, String categorydescription) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("category", category);
        contentvalues.put("categorydescription", categorydescription);

        long result = DB.insert("categorytable", null, contentvalues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateuserdata(String id, String lastname, String firstname, String middlename) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("id", id);
        contentvalues.put("lastname", lastname);
        contentvalues.put("firstname", firstname);
        contentvalues.put("middlename", middlename);
        DB.update(tablename, contentvalues, "id = ?", new String[]{id});
        return true;


    }


    public Integer deleteuserdata(String id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.delete(tablename, "id = ?", new String[]{id});


    }


    public boolean getalldata() {

        SQLiteDatabase DB = this.getWritableDatabase();
        //Cursor cursor = DB.rawQuery("Select * from userdetails where lastname = ?", null);
        Cursor c = DB.rawQuery("Select * from userdetails", null);
        return true;
    }



}
