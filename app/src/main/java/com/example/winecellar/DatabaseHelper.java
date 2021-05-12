package com.example.winecellar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wine.db";
    private static final String TABLE_NAME = "wine_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "GRAPE";
    private static final String COL_4 = "PRICE";
    private static final String COL_5 = "STORE";
    private static final String COL_6 = "AGE";
    private static final String COL_7 = "TYPE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, " +
                "GRAPE TEXT, PRICE DOUBLE, STORE TEXT, AGE INTEGER, TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertWine(String name, String grape, String price, String amount, String age, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, grape);
        contentValues.put(COL_4, price);
        contentValues.put(COL_5, amount);
        contentValues.put(COL_6, age);
        contentValues.put(COL_7, type);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return  false;
        } else {
            return true;
        }
    }
}
