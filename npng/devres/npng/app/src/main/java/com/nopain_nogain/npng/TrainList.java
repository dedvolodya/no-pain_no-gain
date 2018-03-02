package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;
import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;


public class TrainList extends Activity {
    ListView myList;
    Button getChoice;
    String tmpLst = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);

        final DBHelper db = new DBHelper(this);
        myList = findViewById(R.id.TrainListView);
        getChoice = findViewById(R.id.AcceptTrain);

        if(db.getCountExercise() == 0) {
            String[] exercises = {"Barbell Full Squat", "Barbell Walking Lunge",
                    "Wide-Grip Standing Barbell Curl", "Hammer Curls", "Pullups",
                    "Close-Grip Front Lat Pulldown", "Smith Machine Calf Raise",
                    "Plank", "Cocoons", "Pushups", "Cocoons", "Barbell Bench Press - Medium Grip",
                    "Dumbbell Bench Press", "Dips", "Close-Grip Barbell Bench Press",
                    "Seated Triceps Press", "Side Laterals to Front Raise",
                    "Standing Palm-In One-Arm Dumbbell Press"};

            for (String ex : exercises) {
                db.addExercise(new ExerciseTable(0, ex, ""));
            }
        }

        ArrayList<ExerciseTable> table = db.getAllExercise();
        ArrayAdapter<ExerciseTable> adapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_multiple_choice, table);
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                tmpLst += "id: " + adapterView +", ";
                adapter.getItem(position).getId();
            }
        });


        getChoice.setOnClickListener(new Button.OnClickListener() {


            @Override

            public void onClick(View v) {
                StringBuilder exLst = new StringBuilder();

                Intent intent = getIntent();
                String tName = intent.getStringExtra("trainName");

                int cntChoice = myList.getCount();

                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();

                for (int i = 0; i < cntChoice; i++) {
                    if (sparseBooleanArray.get(i)) {
                        exLst.append(i).append(" ");
                    }
                }

                Toast.makeText(getApplicationContext(), tmpLst, Toast.LENGTH_LONG).show();
                TrainTable trainTable = new TrainTable(0, tName, exLst.toString(), -1);
                db.addTrain(trainTable);

                Intent intent2 = new Intent(getApplicationContext(),RepeatApproachActivity.class);
                intent2.putExtra("exLst", exLst.toString());
                startActivity(intent2);
           }
        });

        }

    }



