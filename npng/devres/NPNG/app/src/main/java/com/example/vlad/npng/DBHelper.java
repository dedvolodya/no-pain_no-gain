package com.example.vlad.npng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trainingDb";

    public static final String TABLE_TRAINING = "training";
    public static final String TABLE_EXERCISE = "exercise";

    // EXERCISE TABL
    public static final String key_id = "_id";
    public static final String key_name_exercise = "name_exercise";

    // TRAIN TABL
    public static final String key_train_id = "_id";
    public static final String key_name_train = "name_train";
    public static final String key_keys_exercise = "keys_exercise";


public DBHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_EXERCISE + " ("
            + key_id + " integer primary key," + key_name_exercise + " text )");

        db.execSQL("create table " + TABLE_TRAINING + " ("
                + key_id + " integer primary key," + key_name_train + " text,"
                + key_keys_exercise + " text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_EXERCISE);
        db.execSQL("drop table if exists " + TABLE_TRAINING);

        onCreate(db);
    }

    public void addExercise(ExerciseTable exerciseTable) {
        ContentValues values = new ContentValues();
        values.put(key_name_exercise, exerciseTable.nameExercise);
		 SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }

    public void addExerciseToTrainTable() {

    }



    // Getting single contact
    ExerciseTable getContact(int id) {
       SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISE, new String[] { key_id,
                        key_name_exercise}, key_id + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ExerciseTable exerciseTable = new ExerciseTable(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

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
                exerciseTable.id = Integer.parseInt(cursor.getString(0));
                exerciseTable.nameExercise = cursor.getString(1);

                contactList.add(exerciseTable);
            } while (cursor.moveToNext());
        }


        return contactList;
    }

    /*public String[] getAllTrainTables() {
        ArrayList<String> contactList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                ExerciseTable exerciseTable = new ExerciseTable();
                exerciseTable.id = Integer.parseInt(cursor.getString(0));
                exerciseTable.nameExercise = cursor.getString(1);

                contactList.add(exerciseTable);
            } while (cursor.moveToNext());
        }


        return contactList;
    }*/

    public void deleteExercise(ExerciseTable exerciseTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXERCISE, key_id + " = ?",
                new String[] { String.valueOf(exerciseTable.id) });
        db.close();
    }



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

    public void addNewTableTrain(String nameTrain, String exId){
        ContentValues values = new ContentValues();
        values.put(key_train_id,0);
        values.put(key_name_train,nameTrain);
        values.put(key_keys_exercise,exId);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_TRAINING, null, values);
        db.close();
    }
}
