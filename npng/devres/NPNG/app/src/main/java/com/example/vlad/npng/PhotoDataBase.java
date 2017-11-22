package com.example.vlad.npng;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhotoDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userphoto.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "photos";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_YEAR = "year";


    public PhotoDataBase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
