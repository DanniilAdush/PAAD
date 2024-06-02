package com.example.nornal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;

import HelperClass.HelperClass;
import Model.Tamagochi;


public class DbTamagochi  {

    public static final String DATABASE_NAME = "tamagoshi.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Record";
    public static final String TABLE_NAME2 = "Tamagoch1";
    // РќР°Р·РІР°РЅРёРµ СЃС‚РѕР»Р±С†РѕРІ
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_LivedDay = "LivedDay";
    public static final String COLUMN_ID = "Id";
    public static final int NUM_COLUMN_LivedDay = 1;
    public static final int NUM_COLUMN_DATE = 1;
    public static final int NUM_COLUMN_ID = 1;

    public static final int NUM_COLUMN_HUNGER = 1;
    public static final int NUM_COLUMN_THIRST = 1;
    public static final int NUM_COLUMN_DEPRES = 1;
    public static final int NUM_COLUMN_Number = 1;
    public static final String COLUMN_HUNGER = "hunger";
    public static final String COLUMN_THIRST = "thirst";
    public static final String COLUMN_DEPRES = "depres";
    public static final String COLUMN_Number = "number";

    SQLiteDatabase mDataBase;

    public DbTamagochi(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }
    public void insert(Tamagochi actor) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_LivedDay, actor.getLivedDay());
        cv.put(COLUMN_DATE, actor.getLivedDay());
        mDataBase.insert(TABLE_NAME, null, cv);
    }public int update(Tamagochi md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_LivedDay, md.getLivedDay());
        cv.put(COLUMN_DATE, md.getDate());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getNumber())});
    }
    public long insert2(Tamagochi actor) {
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_HUNGER, actor.getHunger());
        cv.put(COLUMN_THIRST, actor.getThirst());
        cv.put(COLUMN_DEPRES, actor.getDepres());
        return mDataBase.insert(TABLE_NAME2, null, cv);
    }
    public int update2(Tamagochi md2) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_LivedDay, md2.getLivedDay());
        cv.put(COLUMN_HUNGER, md2.getHunger());
        cv.put(COLUMN_THIRST, md2.getThirst());
        cv.put(COLUMN_DEPRES, md2.getDepres());

        return mDataBase.update(TABLE_NAME2, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md2.getNumber())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public ArrayList<Tamagochi> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Tamagochi> arr = new ArrayList<Tamagochi>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                long LivedDay = mCursor.getLong(NUM_COLUMN_LivedDay);
                String date = mCursor.getString(NUM_COLUMN_DATE);
                long hunger = mCursor.getLong(NUM_COLUMN_HUNGER);
                long thirst = mCursor.getLong(NUM_COLUMN_THIRST);
                String depres = mCursor.getString(NUM_COLUMN_DEPRES);
                arr.add(new Tamagochi(id, String.valueOf(LivedDay), date));
            } while (mCursor.moveToNext());
        }
        return arr;
    }




    class OpenHelper  extends SQLiteOpenHelper {
        // Р”Р°РЅРЅС‹Рµ Р±Р°Р·С‹ РґР°РЅРЅС‹С… Рё С‚Р°Р±Р»РёС†

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_Name + " TEXT, " +
                    COLUMN_DATE + " TEXT); ";

            db.execSQL(query);

            String CREATE_TAMAGOCHI_TABLE = "CREATE TABLE " + HelperClass.TABLE_NAME + " ("
                    + HelperClass.Key_Date + " INTEGER, "
                    + HelperClass.Key_Number + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HelperClass.Key_LivedDay + " INTEGER, "
                    + HelperClass.Key_HUNGER + " INTEGER, "
                    + HelperClass.Key_THIRST + " INTEGER, "
                    + HelperClass.Key_DEPRES + " INTEGER  " + " )";
            db.execSQL(CREATE_TAMAGOCHI_TABLE);//РїРµСЂРµРґР°С‡Р°
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + HelperClass.TABLE_NAME2);//СѓРґР°Р»РµРЅРёРµ С‚Р°Р±Р»РёС†С‹, РµСЃР»Рё РѕРЅР° СЃСѓС‰РµСЃС‚РІСѓРµС‚
            onCreate(db);
        }
        public Tamagochi loadCurrentGame() {
            // Р’С‹РїРѕР»РЅРµРЅРёРµ Р·Р°РїСЂРѕСЃР° Рє Р±Р°Р·Рµ РґР°РЅРЅС‹С… РґР»СЏ РёР·РІР»РµС‡РµРЅРёСЏ РїРѕСЃР»РµРґРЅРµР№ СЃРѕС…СЂР°РЅРµРЅРЅРѕР№ РёРіСЂС‹
            Cursor cursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

            // РР·РІР»РµС‡РµРЅРёРµ РґР°РЅРЅС‹С… РёР· Cursor РІ РѕР±СЉРµРєС‚ GameData
            if (cursor.moveToNext()) {
                Tamagochi gameData = new Tamagochi();
                gameData.setLivedDay(cursor.getInt(Integer.parseInt("LivedDay")));
                gameData.setThirst(cursor.getInt(Integer.parseInt("thirst")));
                gameData.setDepres(cursor.getInt(Integer.parseInt("depres")));
                gameData.setHunger(cursor.getInt(Integer.parseInt("hunger")));


                // Р—Р°РєСЂС‹С‚РёРµ Cursor
                cursor.close();
                return gameData;

            }
            else {
                return null;
            }
        }
    }



//        public static final String DATABASE_NAME = "tamagochi.db";
//        public static final int DATABASE_VERSION = 1;
//        public static final String TABLE_NAME = "Tamagochi";
//        // РќР°Р·РІР°РЅРёРµ СЃС‚РѕР»Р±С†РѕРІ
//
//        public static final String COLUMN_LivedDay = "LivedDay";
//        public static final String COLUMN_DATE = "Date";
//
//        // РќРѕРјРµСЂР° СЃС‚РѕР»Р±С†РѕРІ
//        public static final int NUM_COLUMN_Name = 1;
//        public static final int NUM_COLUMN_DATE = 1;
//
//        private SQLiteDatabase mDataBase;
//
//        public DbTamagochi(Context context) {
//            OpenHelper mOpenHelper = new OpenHelper(context);
//            mDataBase = mOpenHelper.getWritableDatabase();
//        }
//
//        public long insert(Tamagochi tamagochi) {
//            ContentValues cv=new ContentValues();
//            cv.put(COLUMN_LivedDay, tamagochi.LivedDay);
//            cv.put(COLUMN_DATE, tamagochi.Date);
//            return mDataBase.insert(TABLE_NAME, null, cv);
//        }
//
//
//        public void deleteAll() {
//            mDataBase.delete(TABLE_NAME, null, null);
//        }
//
//
//
//        public ArrayList<Tamagochi> selectAll() {
//            Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);
//
//            ArrayList<Tamagochi> arr = new ArrayList<Tamagochi>();
//            mCursor.moveToFirst();
//            if (!mCursor.isAfterLast()) {
//                do {
//                    String livedDay = mCursor.getString(NUM_COLUMN_Name);
//                    String date = mCursor.getString(NUM_COLUMN_DATE);
//                    arr.add(new Tamagochi (livedDay, date));
//                } while (mCursor.moveToNext());
//            }
//            return arr;
//        }
//        class OpenHelper  extends SQLiteOpenHelper {
//            // Р”Р°РЅРЅС‹Рµ Р±Р°Р·С‹ РґР°РЅРЅС‹С… Рё С‚Р°Р±Р»РёС†
//
//            OpenHelper(Context context) {
//                super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            }
//
//            @Override
//            public void onCreate(SQLiteDatabase db) {
//                String query = "CREATE TABLE " + TABLE_NAME + " (" +
//                        COLUMN_LivedDay + " TEXT, " +
//                        COLUMN_DATE + " TEXT); ";
//
//                db.execSQL(query);
//            }
//
//            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//                onCreate(db);
//            }
//        }
}
//    public int updateTamagochi(Tamagochi tamagochi){
//        SQLiteDatabase sql = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(HelperClass.Key_LivedDay,tamagochi.getLivedDay());
//        contentValues.put(HelperClass.Key_Date,tamagochi.getDate());
//        sql.update(HelperClass.TABLE_NAME,contentValues,HelperClass.Key_Number + "=?", new String[]{
//                tamagochi.getNumber()
//        });



