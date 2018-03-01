package com.nopain_nogain.npng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "trainingDb";

    private static final String TABLE_TRAINING = "training";
    private static final String TABLE_EXERCISE = "exercise";
    private static final String TABLE_APPROACH = "approach";
    private static final String TABLE_REPEAT = "repeat";

    // EXERCISE TABLE
    private static final String key_id_exercise = "_id";
    private static final String key_name_exercise = "name_exercise";
    private static final String key_approach_exercise = "approach";

    // TRAIN TABLE
    private static final String key_id_train = "_id";
    private static final String key_name_train = "name_train";
    private static final String key_keys_exercise = "keys_exercise";
    private static final String key_week_day = "week_day";

    // COUNT APPROACH TABLE
    private static final String key_id_approach = "_id";
    private static final String key_repeats_exercise = "repeats";

    // COUNT REPEAT TABLE
    private static final String key_id_repeat = "_id";
    private static final String key_weight = "weight";



    DBHelper(Context context) {
        //noinspection deprecation
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_EXERCISE + " ("
            + key_id_exercise + " integer primary key autoincrement," + key_name_exercise +
                " text," + key_approach_exercise + " text )");

        db.execSQL("create table if not exists " + TABLE_TRAINING + " ("
                + key_id_train + " integer primary key autoincrement," + key_name_train + " text,"
                + key_keys_exercise + " text," + key_week_day + " integer )");

        db.execSQL("create table if not exists " + TABLE_APPROACH + " ("
                + key_id_approach + " integer primary key autoincrement," + key_repeats_exercise + " text )");

        db.execSQL("create table if not exists " + TABLE_REPEAT + " ("
                + key_id_repeat + " integer primary key autoincrement," + key_weight + " integer )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String[] tables = {TABLE_EXERCISE, TABLE_TRAINING, TABLE_APPROACH, TABLE_REPEAT};
            for (String table : tables) {
                db.execSQL("drop table if exists " + table);
            }
            onCreate(db);
        }
    }

    void addExercise(ExerciseTable exerciseTable) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_name_exercise, exerciseTable.getName());
            values.put(key_approach_exercise, exerciseTable.getApproachesString());

            db.insertOrThrow(TABLE_EXERCISE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("[ERROR-DB]: ", "Error while trying add exercise to database");
        } finally {
            db.endTransaction();
        }
    }

    long getCountExercise () {
        String query = "SELECT COUNT(*) FROM " + TABLE_EXERCISE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null || !cursor.moveToFirst()){
            return 0;
        }
        long count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    ExerciseTable getExerciseById(int id) {
        String query = "SELECT * FROM " + TABLE_EXERCISE + " WHERE " +key_id_exercise + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        ExerciseTable et = new ExerciseTable(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        return et;
    }

    ArrayList<ExerciseTable> getAllExercise() {
        ArrayList<ExerciseTable> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.d("[ERROR-DB]", "id: " + cursor.getInt(0) +
                    ", name: " + cursor.getString(1) +
                    ", appr: " + cursor.getString(2) +".");

                contactList.add(new ExerciseTable(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contactList;
    }

    void addTrain(TrainTable trainTable){
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_name_train, trainTable.getName());
            values.put(key_keys_exercise, trainTable.getExercisesString());
            values.put(key_week_day, trainTable.getDayWeek());

            db.insertOrThrow(TABLE_TRAINING, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("[ERROR-DB]: ", "Error while trying add training to database");
        } finally {
            db.endTransaction();
        }
    }

    long getCountTrain () {
        String query = "SELECT COUNT(*) FROM " + TABLE_TRAINING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null || !cursor.moveToFirst()){
            return 0;
        }
        long count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    TrainTable getTrainById(int id) {
        String query = "SELECT * FROM " + TABLE_TRAINING + " WHERE " +key_id_train + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        TrainTable tt = new TrainTable(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getInt(3));
        cursor.close();
        return tt;
    }

    ArrayList<TrainTable> getAllTrain() {
        ArrayList<TrainTable> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRAINING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                contactList.add(new TrainTable(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contactList;
    }
}
