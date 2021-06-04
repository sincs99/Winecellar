package com.example.winecellar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    List<Wine> wineList = new ArrayList<>();


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

    public int getAmountOfRedWines(){
        int redWines;
        String queryString = "SELECT Sum(STORE) FROM " + TABLE_NAME + " WHERE TYPE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{"Red"});

        if(cursor.moveToFirst()){
            redWines = cursor.getInt(0);
        } else {
            redWines = 0;
        }
        cursor.close();

        return redWines;
    }

    public int getAmountOfWhiteWines(){
        int whiteWines;
        String queryString = "SELECT Sum(STORE) FROM " + TABLE_NAME + " WHERE TYPE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{"White"});

        if(cursor.moveToFirst()){
            whiteWines = cursor.getInt(0);
        } else {
            whiteWines = 0;
        }
        cursor.close();

        return whiteWines;
    }

    public int getAmountOfRoseWines(){
        int roseWines;
        String queryString = "SELECT Sum(STORE) FROM " + TABLE_NAME + " WHERE TYPE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{"Rose"});

        if(cursor.moveToFirst()){
            roseWines = cursor.getInt(0);
        } else {
            roseWines = 0;
        }
        cursor.close();

        return roseWines;
    }

    public int getAmountOfSparklingWines(){
        int sparklingWines;
        String queryString = "SELECT Sum(STORE) FROM " + TABLE_NAME + " WHERE TYPE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{"Sparkling"});

        if(cursor.moveToFirst()){
            sparklingWines = cursor.getInt(0);
        } else {
            sparklingWines = 0;
        }
        cursor.close();

        return sparklingWines;
    }

    public int getTotalAmountOfBottles(){
        int totalWines;
        String queryString = "SELECT Sum(STORE) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            totalWines = cursor.getInt(0);
        } else {
            totalWines = 0;
        }
        cursor.close();

        return totalWines;
    }

    public double getTotalValue(){
        double totalValue;
        String queryString = "SELECT Sum(PRICE * STORE) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            totalValue = cursor.getDouble(0);
        } else {
            totalValue = 0.0;
        }
        cursor.close();

        return totalValue;
    }

    public List<Wine> getAllWines(){
        SQLiteDatabase db = this.getReadableDatabase();
        String columns[] = {DatabaseHelper.COL_1, DatabaseHelper.COL_2, DatabaseHelper.COL_3, DatabaseHelper.COL_4, DatabaseHelper.COL_5, DatabaseHelper.COL_6, DatabaseHelper.COL_7};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DatabaseHelper.COL_1);
            int rowId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.COL_2);
            String name = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.COL_3);
            String grape = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.COL_4);
            double price = cursor.getDouble(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.COL_5);
            int amount = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.COL_6);
            int age = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.COL_7);
            String type = cursor.getString(index7);
            Wine wine = new Wine(rowId, name, grape, price, amount, age, type);
            wineList.add(wine);
        }
        return wineList;
    }

    public boolean drinkWine(int id, int amount) {
        int newAmount = amount -1;

        if(amount > 1){
            String queryString = "UPDATE " + TABLE_NAME + " SET STORE = STORE -1 WHERE ID = " + id + " AND STORE > 1";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(queryString);
            return true;
        } else {
            String queryString2 = "DELETE FROM " + TABLE_NAME + " WHERE ID = " + id;
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(queryString2);
            return  false;
        }


    }
}
