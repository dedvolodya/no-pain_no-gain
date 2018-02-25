package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TrainList extends Activity {


    ListView myList;

    Button getChoice;


    List<String> listContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);

        final DBHelper dbHelper = new DBHelper(this);
        myList = (ListView) findViewById(R.id.TrainListView);
        getChoice = (Button) findViewById(R.id.AcceptTrain);

        if(dbHelper.getAllContacts().size() == 0) {
            dbHelper.addExercise(new ExerciseTable(0, "Barbell Full Squat"));
            dbHelper.addExercise(new ExerciseTable(1, "Barbell Walking Lunge"));
            dbHelper.addExercise(new ExerciseTable(2, "Wide-Grip Standing Barbell Curl"));
            dbHelper.addExercise(new ExerciseTable(3, "Hammer Curls"));
            dbHelper.addExercise(new ExerciseTable(4, "Pullups"));
            dbHelper.addExercise(new ExerciseTable(5, "Close-Grip Front Lat Pulldown"));
            dbHelper.addExercise(new ExerciseTable(6, "Smith Machine Calf Raise"));
            dbHelper.addExercise(new ExerciseTable(7, "Plank"));
            dbHelper.addExercise(new ExerciseTable(8, "Cocoons"));
            dbHelper.addExercise(new ExerciseTable(9, "Pushups"));
            dbHelper.addExercise(new ExerciseTable(10, "Cocoons"));
            dbHelper.addExercise(new ExerciseTable(11, "Barbell Bench Press - Medium Grip"));
            dbHelper.addExercise(new ExerciseTable(12, "Dumbbell Bench Press"));
            dbHelper.addExercise(new ExerciseTable(13, "Dips"));
            dbHelper.addExercise(new ExerciseTable(14, "Close-Grip Barbell Bench Press"));
            dbHelper.addExercise(new ExerciseTable(15, "Seated Triceps Press"));
            dbHelper.addExercise(new ExerciseTable(16, "Side Laterals to Front Raise"));
            dbHelper.addExercise(new ExerciseTable(17, "Standing Palm-In One-Arm Dumbbell Press"));
        }


        String [] table = dbHelper.StrExerciseTable();

     /*  for (ExerciseTable cn : table) {
           String log = "Id: " + cn.id + " ,Name: " + cn.nameExercise;

           Log.d("Name: ", log);
        }*/



            ArrayAdapter<String> adapter

                    = new ArrayAdapter<String>(this,

                    android.R.layout.simple_list_item_multiple_choice,

                    table);
            myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            myList.setAdapter(adapter);


            getChoice.setOnClickListener(new Button.OnClickListener() {


                @Override

                public void onClick(View v) {

                    String exLst = "";

                    Intent intent = getIntent();
                    String tName = intent.getStringExtra("trainName");

                    int cntChoice = myList.getCount();

                    SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();

                    for (int i = 0; i < cntChoice; i++) {

                        if (sparseBooleanArray.get(i)) {
                            exLst += i + " ";
                        }
                    }

                    //Toast.makeText(getApplicationContext(), exLst, Toast.LENGTH_LONG).show();
                    dbHelper.addNewTableTrain(tName,exLst);

                    Intent intent2 = new Intent(getApplicationContext(),CreateTrain.class);
                    startActivity(intent2);
               }
            });

        }

    }



