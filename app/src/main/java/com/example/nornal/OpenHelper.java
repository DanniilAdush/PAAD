package com.example.nornal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import HelperClass.HelperClass;
import Model.Tamagochi;

public class OpenHelper  extends SQLiteOpenHelper {
    // Данные базы данных и таблиц
    public static final String DATABASE_NAME = "tamagoshi.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Tamagochi";
    // Название столбцов
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_LivedDay = "LivedDay";
    public static final int NUM_COLUMN_Name = 1;
    public static final int NUM_COLUMN_DATE = 1;
    public static final int NUM_COLUMN_LivedDay = 1;
    public static final int NUM_COLUMN_HUNGER = 1;
    public static final int NUM_COLUMN_THIRST = 1;
    public static final int NUM_COLUMN_DEPRES = 1;
    public static final int NUM_COLUMN_Number = 1;
    public static final String COLUMN_HUNGER = "hunger";
    public static final String COLUMN_THIRST = "thirst";
    public static final String COLUMN_DEPRES = "depres";
    public static final String COLUMN_Number = "number";

    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TAMAGOCHI_TABLE = "CREATE TABLE " + HelperClass.TABLE_NAME + " ("
                + HelperClass.Key_Date + " INTEGER, "
                + HelperClass.Key_Number + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HelperClass.Key_LivedDay + " INTEGER, "
                + HelperClass.Key_HUNGER + " INTEGER, "
                + HelperClass.Key_THIRST + " INTEGER, "
                + HelperClass.Key_DEPRES + " INTEGER  " + " )";
        db.execSQL(CREATE_TAMAGOCHI_TABLE);//передача

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HelperClass.TABLE_NAME);//удаление таблицы, если она существует
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + HelperClass.TABLE_NAME2);//удаление таблицы, если она существует
        onCreate(db);
    }

    public void addTamagochi(Tamagochi tamagochi) {
        SQLiteDatabase sql = this.getWritableDatabase();//записать новое
        ContentValues contentValues = new ContentValues();

        contentValues.put(HelperClass.Key_LivedDay, tamagochi.getLivedDay());
        contentValues.put(HelperClass.Key_Date, tamagochi.getDate());

        sql.insert(HelperClass.TABLE_NAME, null, contentValues);//вроде как помещение в таблицу
        sql.close();
    }

    public void addTamagochi2(Tamagochi tamagochi) {
        SQLiteDatabase sql = this.getWritableDatabase();//записать новое
        ContentValues contentValues = new ContentValues();

        contentValues.put(HelperClass.Key_LivedDay, tamagochi.getLivedDay());
        contentValues.put(HelperClass.Key_HUNGER, tamagochi.getHunger());
        contentValues.put(HelperClass.Key_THIRST, tamagochi.getThirst());
        contentValues.put(HelperClass.Key_DEPRES, tamagochi.getDepres());
        sql.insert(HelperClass.TABLE_NAME, null, contentValues);//вроде как помещение в таблицу
        sql.close();
    }

    public Tamagochi getTamagochi(int number) {
        SQLiteDatabase sql = this.getReadableDatabase();//прочитать
        Cursor cursor = sql.query(HelperClass.TABLE_NAME, new String[]{HelperClass.Key_Number,
                        HelperClass.Key_LivedDay,
                        HelperClass.Key_Date},
                HelperClass.Key_Number + "=?", new String[]{String.valueOf(number)},
                null, null, null, null);//из какой таблицы берем какие данные
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Tamagochi tamagochi = new Tamagochi(Integer.parseInt(cursor.getString(0)),
                cursor.getInt(1),
                cursor.getInt(2));//столбики
        return tamagochi;
    }

    public List<Tamagochi> getAllTamagochi() {
        SQLiteDatabase sql = this.getReadableDatabase();
        List<Tamagochi> tamagochi = new ArrayList<>();
        String selectAllTamagochi = "Select * from " + HelperClass.TABLE_NAME;
        Cursor cursor = sql.rawQuery(selectAllTamagochi, null);
        if (cursor.moveToFirst()) {
            do {
                int d = 0;
                int date = 0;
                Tamagochi tamagochi1 = new Tamagochi();////////////////////////////////////////////////////
                tamagochi1.setDate(cursor.getString(2));
                tamagochi1.setNumber(Integer.parseInt(cursor.getString(0)));
                tamagochi1.setLivedDay(cursor.getInt(1));//столбики

                tamagochi.add(tamagochi1);
            } while (cursor.moveToNext());//будет возращать true,если будет сущ. элемент из массива


        }
        return tamagochi;
    }
}