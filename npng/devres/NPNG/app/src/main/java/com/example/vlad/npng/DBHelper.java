package com.example.vlad.npng;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trainingDb";

    public static final String TABLE_TRAINING = "training";
    public static final String TABLE_EXERCISE = "exercise";

    // EXERCISE TABL
    public static final String key_id = "_id";
    public static final String key_name_exercise = "name_exercise";
    public static final String key_count_attempt = "count_attempt";
    public static final String key_count_repeat = "count_repeat";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_EXERCISE + " ("
            + key_id + " integer primary key," + key_name_exercise + " text,"
            + key_count_attempt + " integer," + key_count_repeat + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_EXERCISE);

        onCreate(db);
    }
}
