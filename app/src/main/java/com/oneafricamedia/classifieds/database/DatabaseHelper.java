package com.oneafricamedia.classifieds.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Brad on 10/18/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    private static final String LOG = DatabaseHelper.class.getSimpleName();

    //Database Version
    private static final int DATABASE_VERSION = 4;

    //Database Name
    private static final String DATABASE_NAME = "listingsWeightManager";

    //Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_WEIGHT = "weight";

    //Table names
    private static final String TABLE_MAKE = "make";
    private static final String TABLE_MODEL = "model";
    private static final String TABLE_PRICE = "price";
    private static final String TABLE_CURRENCY = "currency";
    private static final String TABLE_NEGOTIABLE = "negotiable";
    private static final String TABLE_YEAR = "year";
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_BODY_TYPE = "body_type";
    private static final String TABLE_DRIVER_SETUP = "driver_setup";
    private static final String TABLE_MONEY_BACK = "money_back";


    private static final String CREATE_TABLE_MONEY_BACK = "CREATE TABLE " + TABLE_MONEY_BACK +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_DRIVER_SETUP = "CREATE TABLE " + TABLE_DRIVER_SETUP +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_BODY_TYPE = "CREATE TABLE " + TABLE_BODY_TYPE +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_LOCATION = "CREATE TABLE " + TABLE_LOCATION +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_MAKE = "CREATE TABLE " + TABLE_MAKE +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_MODEL = "CREATE TABLE " + TABLE_MODEL +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_PRICE = "CREATE TABLE " + TABLE_PRICE +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_CURRENCY = "CREATE TABLE " + TABLE_CURRENCY +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_NEGOTIABLE = "CREATE TABLE " + TABLE_NEGOTIABLE +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    private static final String CREATE_TABLE_YEAR = "CREATE TABLE " + TABLE_YEAR +
            " (" + KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_WEIGHT + " INTEGER NOT NULL );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating required tables

        db.execSQL(CREATE_TABLE_BODY_TYPE);
        db.execSQL(CREATE_TABLE_CURRENCY);
        db.execSQL(CREATE_TABLE_DRIVER_SETUP);
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_MAKE);
        db.execSQL(CREATE_TABLE_MODEL);
        db.execSQL(CREATE_TABLE_NEGOTIABLE);
        db.execSQL(CREATE_TABLE_PRICE);
        db.execSQL(CREATE_TABLE_YEAR);
        db.execSQL(CREATE_TABLE_MONEY_BACK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_MAKE);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_MODEL);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PRICE);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CURRENCY);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NEGOTIABLE);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_YEAR);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_BODY_TYPE);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_DRIVER_SETUP);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_MONEY_BACK);

        //creates new tables
        onCreate(db);
    }


    public long insertToTable(String tableName, String key, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, key);
        values.put(KEY_WEIGHT, weight);

        //insert row
        long insert_id = db.insert(tableName, null, values);

        return insert_id;
    }

    public int getWeightFromTable(String tableName, String key) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + tableName + " WHERE "
                + KEY_ID + " = " + key + ";";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        } else {
            return -1;
        }

        if(c.getCount() == 0){
            return 0;
        }
        int weight = c.getInt(c.getColumnIndex(KEY_ID));
        return weight;
    }

    public boolean doesRecordExist(String tableName, String key) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + tableName + " WHERE "
                + KEY_ID + " = " + key + ";";

        Log.e(LOG, selectQuery);

        try {
            Cursor c = db.rawQuery(selectQuery, null);
            return c != null;
        }catch (Exception e){
            return false;
        }
    }

    public long updateTable(String tableName, String key, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WEIGHT, getWeightFromTable(tableName, key) + weight);

        //updating row
        long update_id = db.update(tableName, values, KEY_ID + " = ?",
                new String[]{key});
        return update_id;
    }

    public void deleteFromTable(String tableName, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, KEY_ID + " = ?", new String[]{key});
    }

    //closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

}
