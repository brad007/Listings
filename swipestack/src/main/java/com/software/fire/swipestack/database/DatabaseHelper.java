package com.software.fire.swipestack.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Brad on 10/18/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    private static final String LOG = "DatabseHelper";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "listingsWeightManager";

    //Table Names
    ArrayList<String> tableNames = new ArrayList<>();

    //Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_WEIGHT = "weight";


    public DatabaseHelper(Context context, ArrayList<String> tableNamesParam) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        tableNamesParam = tableNames;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating required tables
        for (String tableName : tableNames) {
            createTable(tableName);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String tableName : tableNames) {
            db.execSQL("DROP TABLE IF EXIST " + tableName);
        }
    }

    private String createTable(String tableName) {
        return "CREATE TABLE " + tableName + "(" + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_WEIGHT + " INTEGER);";
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
                + KEY_ID + " = " + key;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        }

        int weight = c.getInt(c.getColumnIndex(KEY_ID));
        return weight;
    }

    public int updateTable(String tableName, String key, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WEIGHT, getWeightFromTable(tableName, key) + weight);

        //updating row
        return db.update(tableName, values, KEY_ID + " = ?",
                new String[]{key});
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
