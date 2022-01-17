package com.example.pos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String SQL){
        SQLiteDatabase DB = getWritableDatabase();
        DB.execSQL(SQL);
    }

    public void insertData( byte[] image, String product, String brand, String category, String qty, String price){
        SQLiteDatabase DB = getWritableDatabase();
        String SQL = "insert into productabless(image , product, brand , category , qty , price)values(?,?,?,?,?,?)";
        SQLiteStatement statement = DB.compileStatement(SQL);
        statement.clearBindings();
        statement.bindBlob(1, image);
        statement.bindString(2, product);
        statement.bindString(3, brand);
        statement.bindString(4, category);
        statement.bindString(5, qty);
        statement.bindString(6, price);
        statement.executeInsert();
    }

    public void updateData( byte[] image, String product, String qty, String price, int id){

        SQLiteDatabase DB = getWritableDatabase();
        String SQL = "UPDATE productabless SET image = ?, product = ?,  qty = ?, price = ? WHERE id = ? ";
        SQLiteStatement statement = DB.compileStatement(SQL);
        statement.bindBlob(1, image);
        statement.bindString(2, product);
        statement.bindString(3, qty);
        statement.bindString(4, price);
        statement.bindDouble(5, (double) id);
        statement.execute();
        DB.close();


    }

    public void deleteData(int id){
            SQLiteDatabase DB = getWritableDatabase();
            String SQL = "DELETE FROM productabless WHERE id = ?";
            SQLiteStatement statement = DB.compileStatement(SQL);
            statement.clearBindings();
            statement.bindDouble(1, (double)id);
            statement.execute();
            DB.close();

    }

    public void deleteBrandData(int id){
        SQLiteDatabase DB = getWritableDatabase();
        String SQL = "DELETE FROM brandtable WHERE id = ?";
        SQLiteStatement statement = DB.compileStatement(SQL);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);
        statement.execute();
        DB.close();

    }

    public void deleteCategoryData(int id){
        SQLiteDatabase DB = getWritableDatabase();
        String SQL = "DELETE FROM categorytable WHERE id = ?";
        SQLiteStatement statement = DB.compileStatement(SQL);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);
        statement.execute();
        DB.close();

    }

    public Cursor getData(String SQL){
        SQLiteDatabase DB = getWritableDatabase();
        return DB.rawQuery(SQL, null);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
