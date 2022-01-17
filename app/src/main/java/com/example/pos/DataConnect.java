package com.example.pos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class DataConnect extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usernames.db";
    public static final String TABLE_NAME = "userdetail";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LASTNAME = "LASTNAME";
    public static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String COLUMN_MIDDLENAME = "MIDDLENAME";

    private static DataConnect databaseInstance = null;


    SQLiteDatabase sqLiteDatabase;

    public static final String TABLE_CREATE = "create Table userdetails(id INTEGER primary key,lastname TEXT, firstname TEXT, middlename TEXT)";



    public DataConnect(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static DataConnect getInstance(Context context) {

        if (databaseInstance == null) {

            databaseInstance = new DataConnect(context);

        }

        return databaseInstance;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;

    }


    public String searchPass(String uname) {
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);

                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }





    public Cursor viewData (){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
}
