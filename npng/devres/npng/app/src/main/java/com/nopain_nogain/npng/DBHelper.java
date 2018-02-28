package com.nopain_nogain.npng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trainingDb";

    public static final String TABLE_TRAINING = "training";
    public static final String TABLE_EXERCISE = "exercise";
    public static final String TABLE_APPROACH = "approach";
    public static final String TABLE_REPEAT = "repeat";

    // EXERCISE TABL
    public static final String key_id_exercise = "_id";
    public static final String key_name_exercise = "name_exercise";
    public static final String key_approach_exercise = "approach";

    // TRAIN TABL
    public static final String key_id_train = "_id";
    public static final String key_name_train = "name_train";
    public static final String key_keys_exercise = "keys_exercise";
    public static final String key_week_day = "week_day";

    // COUNT APPROACH TABLE
    public static final String key_id_approach = "_id";
    public static final String key_repeats_exercise = "repeats";

    // COUNT REPEAT TABLE
    public static final String key_id_repeat = "_id";
    public static final String key_weight = "weight";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        db.execSQL("drop table if exists " + TABLE_EXERCISE);
        db.execSQL("drop table if exists " + TABLE_TRAINING);
        db.execSQL("drop table if exists " + TABLE_APPROACH);
        db.execSQL("drop table if exists " + TABLE_REPEAT);

        onCreate(db);
    }

    public void addExercise(ExerciseTable exerciseTable) {
        ContentValues values = new ContentValues();
        values.put(key_name_exercise, exerciseTable.nameExercise);
		 SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }

    // Getting single contact
    ExerciseTable getContact(int id) {
       SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISE, new String[] { key_id_exercise,
                        key_name_exercise}, key_id_exercise + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ExerciseTable exerciseTable = new ExerciseTable(cursor.getString(1));

        return exerciseTable;
    }

    public ArrayList<ExerciseTable> getAllContacts() {
        ArrayList<ExerciseTable> contactList = new ArrayList<ExerciseTable>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE;

       SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                ExerciseTable exerciseTable = new ExerciseTable();

                exerciseTable.nameExercise = cursor.getString(1);

                contactList.add(exerciseTable);
            } while (cursor.moveToNext());
        }


        return contactList;
    }

    public String getExerciseById(int id) {
        String singleExercise = "";
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISE, new String[] { key_id_exercise,
                        key_name_exercise }, key_id_exercise + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        singleExercise = cursor.getString(1);

        return singleExercise;
    }

    public ArrayList<TrainTable> getAllTrainTables() {
        ArrayList<TrainTable> contactList = new ArrayList<TrainTable>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRAINING;

       SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                TrainTable trainTable = new TrainTable();
                trainTable.id = Integer.parseInt(cursor.getString(0));
                trainTable.nameTrain = cursor.getString(1);

                String log = "id: " + trainTable.id + ", name: " + trainTable.nameTrain;
                Log.d("TRAINING: ", log);

                contactList.add(trainTable);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    /*public void deleteExercise(ExerciseTable exerciseTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXERCISE, key_id_exercise + " = ?",
                new String[] { String.valueOf(exerciseTable.id) });
        db.close();
    }*/


    public int getExerciseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EXERCISE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public String [] StrExerciseTable () {
        ArrayList<ExerciseTable> table = getAllContacts();

        String [] stringList = new String[table.size()];

        for(int i = 0; i < table.size(); i++){
            stringList[i] = table.get(i).nameExercise;
        }

        return stringList;
    }

    public String [] StrTrainingTable () {
        ArrayList<TrainTable> table = getAllTrainTables();

        String [] stringList = new String[table.size()];

        for(int i = 0; i < table.size(); i++){
            stringList[i] = table.get(i).nameTrain;
        }

        return stringList;
    }

    public void addNewTableTrain(String nameTrain, String exId){
        ContentValues values = new ContentValues();
        values.put(key_name_train,nameTrain);
        values.put(key_keys_exercise,exId);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_TRAINING, null, values);
        db.close();
    }






       /* String queryCountExercise = "select count(*) from " + TABLE_EXERCISE;
        String queryCountTrain = "select count(*) from " + TABLE_TRAINING;
        String queryCountRepeat = "select count(*) from " + TABLE_REPEAT;
        String queryCountApproach = "select count(*) from " + TABLE_APPROACH;

        int countExercise = (int) DatabaseUtils.longForQuery(db, queryCountExercise, null);
        int countTrain = (int) DatabaseUtils.longForQuery(db, queryCountTrain, null);
        int countRepeat = (int) DatabaseUtils.longForQuery(db, queryCountRepeat, null);
        int countApproach = (int) DatabaseUtils.longForQuery(db, queryCountApproach, null);

        */


}
