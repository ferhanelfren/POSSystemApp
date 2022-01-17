package com.example.pos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class CartLists extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pointonsale";
    public static final String TABLE_NAME_CART = "deletepostables";
    public static final String ROW_1 = "total";

    public CartLists(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    private static CartLists sDatabaseHelper;

    public CartLists(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static CartLists getInstance(Context context) {
        if (sDatabaseHelper == null) {
            sDatabaseHelper = new CartLists(context);
        }
        return sDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



    }


    public int sumTotalItems() {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum("+ ROW_1 +") from " + TABLE_NAME_CART, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }

    public int AllTotall() {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum("+ ROW_1 +") from " + TABLE_NAME_CART, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }


}