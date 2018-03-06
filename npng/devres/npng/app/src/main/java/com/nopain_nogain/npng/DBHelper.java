package com.nopain_nogain.npng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ApproachTable;
import com.nopain_nogain.npng.dbtables.ExerciseTable;
import com.nopain_nogain.npng.dbtables.RepeatTable;
import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private final String TAG = "[ERROR-DB]";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "trainingDb";

    private static final String TABLE_TRAINING = "train";
    private static final String TABLE_EXERCISE = "exercise";
    private static final String TABLE_APPROACH = "approach";
    private static final String TABLE_REPEAT = "repeat";

    // COMMON
    private static final String key_id = "id";
    private static final String key_name = "name";

    // TRAIN TABLE <id, name>
    private static final String key_day_week = "day_week";

    // EXERCISE TABLE <id, name>
    private static final String key_train_id = "train_id";

    // APPROACH TABLE <id>
    private static final String key_exercise_id = "exercise_id";

    // REPEAT TABLE <id>
    private static final String key_count = "count";
    private static final String key_weight = "weight";
    private static final String key_type = "type";
    private static final String key_approach_id = "approach_id";

    DBHelper(Context context) {
        //noinspection deprecation
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_TRAINING +
                " (" +
                key_id + " integer primary key autoincrement," +
                key_name + " varchar(50)," +
                key_day_week + " integer" +
                " )");

        db.execSQL("create table if not exists " + TABLE_EXERCISE +
                " (" +
                key_id + " integer primary key autoincrement," +
                key_name + " varchar(50)," +
                key_train_id + " integer" +
                " )");

        db.execSQL("create table if not exists " + TABLE_APPROACH +
                " (" +
                key_id + " integer primary key autoincrement," +
                key_exercise_id + " integer" +
                " )");

        db.execSQL("create table if not exists " + TABLE_REPEAT +
                " (" +
                key_id + " integer primary key autoincrement," +
                key_count + " integer," +
                key_weight + " real," +
                key_type + " varchar(5)," +
                key_approach_id + " integer" +
                " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String[] tables = {TABLE_TRAINING, TABLE_EXERCISE, TABLE_APPROACH, TABLE_REPEAT};
            for (String table : tables) {
                db.execSQL("drop table if exists " + table);
            }
            onCreate(db);
        }
    }

    // TRAIN IMPLEMENTATION
    long addTrain(TrainTable trainTable){
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_name, trainTable.getName());
            values.put(key_day_week, trainTable.getDayWeek());

            id = db.insertOrThrow(TABLE_TRAINING, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying add train to database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    long getCountTrain () {
        String query = "SELECT COUNT(*) FROM " + TABLE_TRAINING;
        long count = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            count = cursor.getInt(0);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get all train from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    TrainTable getTrainById(long id) {
        String query = "SELECT * FROM " + TABLE_TRAINING + " WHERE " + key_id + "=" + id;
        TrainTable trainTable = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            trainTable = new TrainTable(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), null);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get by id TrainTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return trainTable;
    }
    TrainTable getTrainByDayWeek(int id) {
        String query = "SELECT * FROM " + TABLE_TRAINING + " WHERE " + key_day_week + "=" + id;
        TrainTable trainTable = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            trainTable = new TrainTable(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), null);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get by id TrainTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return trainTable;
    }

    ArrayList<TrainTable> getAllTrain() {
        String selectQuery = "SELECT  * FROM " + TABLE_TRAINING;
        ArrayList<TrainTable> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    contactList.add(new TrainTable(cursor.getInt(0),
                            cursor.getString(1), cursor.getInt(2),
                            null));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying all TrainTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return contactList;
    }

    public long deleteTrain (long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_TRAINING, key_id + "=" + id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete train from database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public long existNameTrain (String name) {
        Log.d("[NAME-TRAIN]", name);
        String query = "SELECT COUNT(*) FROM " + TABLE_TRAINING +
                " WHERE " + key_name + " = '" + name + "'";
        long count = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            count = cursor.getInt(0);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get all train from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    // EXERCISE IMPLEMENTATION
    public long  addExercise(ExerciseTable exerciseTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_name, exerciseTable.getName());
            values.put(key_train_id, exerciseTable.getTrainId());

            id = db.insertOrThrow(TABLE_EXERCISE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying add exercise to database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    long getCountExercise () {
        String query = "SELECT COUNT(*) FROM " + TABLE_EXERCISE;
        long count = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            count = cursor.getInt(0);
        } catch (Exception e) {
            Log.d(TAG,"Error while trying get count exercise from database");
        } finally {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    ExerciseTable getExerciseById(long id) {
        String query = "SELECT * FROM " + TABLE_EXERCISE +
                " WHERE " + key_id + "=" + id;
        ExerciseTable exerciseTable = null;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            exerciseTable = new ExerciseTable(cursor.getInt(0),
                    cursor.getString(1), null, cursor.getInt(2));
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get exercise by id from database");
        } finally {
            if (!cursor.isClosed()){
                cursor.close();
            }
        }
        return exerciseTable;
    }

    ArrayList<ExerciseTable> getAllExercise() {
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE +
                " WHERE " + key_train_id + "=-1";
        ArrayList<ExerciseTable> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    contactList.add(new ExerciseTable(cursor.getInt(0),
                            cursor.getString(1), null, cursor.getInt(2)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get all ExerciseTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return contactList;
    }

    ArrayList<ExerciseTable> getAllExerciseByTrainId (long trainId) {
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE +
                " WHERE " + key_train_id + "=" + trainId;
        ArrayList<ExerciseTable> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    contactList.add(new ExerciseTable(cursor.getInt(0),
                            cursor.getString(1), null, cursor.getInt(2)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "while while trying get all ExerciseTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return contactList;
    }

    public long deleteExercise (long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_EXERCISE, key_id + "=" + id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete exercise from database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public long existNameExercise (String name) {
        Log.d("[NAME-EXRC]", "+"+name+"+");
        String query = "SELECT COUNT(*) FROM " + TABLE_EXERCISE +
                " WHERE " + key_name + " = '" + name + "'";
        long count = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            count = cursor.getInt(0);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get count exercise from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    // APPROACH IMPLEMENTATION
    long addApproach(ApproachTable approachTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_exercise_id, approachTable.getExerciseId());

            id = db.insertOrThrow(TABLE_APPROACH, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying add approach to database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    ApproachTable getApproachById(long id) {
        String query = "SELECT * FROM " + TABLE_APPROACH +
                " WHERE " + key_id + "=" + id;
        ApproachTable approachTable = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            approachTable = new ApproachTable(cursor.getInt(0),null,
                    cursor.getInt(1));
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get approach by id from database");
        } finally {
            if (!cursor.isClosed()){
                cursor.close();
            }
        }
        return approachTable;
    }

    long getCountApproach (long exerciseId) {
        String query = "SELECT COUNT(*) FROM " + TABLE_APPROACH +
                " WHERE " + key_exercise_id + "=" + exerciseId;
        long count = 0;
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            cursor = db.rawQuery(query, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.d(TAG,"Error while trying get count approach from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    ArrayList<ApproachTable> getAllApproachByExerciseId(long exerciseId) {
        String selectQuery = "SELECT  * FROM " + TABLE_APPROACH +
                " WHERE " + key_exercise_id + "=" + exerciseId;
        ArrayList<ApproachTable> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    contactList.add(new ApproachTable(cursor.getInt(0), null,
                            cursor.getInt(1)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "while while trying get all ExerciseTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return contactList;
    }

    public long deleteApprpach (long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_APPROACH, key_id + "=" + id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete approach from database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    // REPEAT IMPLEMENTATION
    long addRepeat(RepeatTable repeatTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(key_count, repeatTable.getCount());
            values.put(key_weight, repeatTable.getWeight());
            values.put(key_type, repeatTable.getType());
            values.put(key_approach_id, repeatTable.getApproachId());


            id = db.insertOrThrow(TABLE_REPEAT, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying add repeat to database");
        } finally {
            db.endTransaction();
        }
        return id;
    }

    RepeatTable getRepeatById(int id) {
        String query = "SELECT * FROM " + TABLE_REPEAT + " WHERE " + key_id + "=" + id;
        RepeatTable repeatTable = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            repeatTable = new RepeatTable(cursor.getInt(0), cursor.getInt(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3), cursor.getInt(4));
        } catch (Exception e) {
            Log.d(TAG, "Error while trying get approach by id from database");
        } finally {
            if (!cursor.isClosed()){
                cursor.close();
            }
        }
        return repeatTable;
    }

    ArrayList<RepeatTable> getAllRepeatByApproachId(long approachId) {
        String selectQuery = "SELECT  * FROM " + TABLE_REPEAT +
                " WHERE " + key_approach_id + "=" + approachId;
        ArrayList<RepeatTable> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    contactList.add(new RepeatTable(cursor.getInt(0),
                            cursor.getInt(1),
                            Double.parseDouble(cursor.getString(2)),
                            cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "while while trying get all ExerciseTable from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return contactList;
    }

    public long deleteRepeat (long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_REPEAT, key_id + "=" + id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete repeat from database");
        } finally {
            db.endTransaction();
        }
        return id;
    }
}
