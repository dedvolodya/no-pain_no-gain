package com.nopain_nogain.npng;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.nopain_nogain.npng.dbtables.ExerciseTable;
import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;


public class TrainList extends AppCompatActivity {
    ListView myList;
    Button getChoice;
    ArrayAdapter<ExerciseTable> adapter = null;
    ArrayList<ExerciseTable> exerciseId = new ArrayList<>();


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
                db.addExercise(new ExerciseTable(0, ex, null, -1));
            }
        }

        ArrayList<ExerciseTable> table = db.getAllExercise();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, table);
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                if (sparseBooleanArray.get(position)) {
                    exerciseId.add(new ExerciseTable(adapter.getItem(position).getId(),
                            adapter.getItem(position).getName(), null, -1));
                } else {
                    for (ExerciseTable exercise : exerciseId) {
                        if (exercise.getId() == adapter.getItem(position).getId()){
                            exerciseId.remove(exercise);
                            break;
                        }
                    }
                }
            }
        });


        getChoice.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String trainName = intent.getStringExtra("trainName");

                TrainTable trainTable = new TrainTable(0, trainName, -1, exerciseId);
                long id = db.addTrain(trainTable);

                for (ExerciseTable exercise :exerciseId) {
                    exercise.setTrainId(id);
                    db.addExercise(exercise);
                }
                Intent intent2 = new Intent(getApplicationContext(),RepeatApproachActivity.class);
                intent2.putExtra("trainId", id);
                startActivity(intent2);
           }
        });
        }
    }



