package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nopain_nogain.npng.dbtables.ApproachTable;
import com.nopain_nogain.npng.dbtables.ExerciseTable;
import com.nopain_nogain.npng.dbtables.RepeatTable;
import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;


public class ShowTrainByDateActivity extends AppCompatActivity {
    DBHelper dbHelper = null;
    TextView trainName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_train_by_date);

        Intent intent = getIntent();
        int dayWeek = intent.getIntExtra("dayOfWeek",-1);
        dbHelper = new DBHelper(this);

        trainName = findViewById(R.id.calendarNameTrain);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TrainTable trainTable = dbHelper.getTrainByDayWeek(dayWeek);
        if (trainTable != null) {
            trainName.setText(trainTable.getName());
            LinearLayout trainLayout = findViewById(R.id.ShowTrainLayout);
            LinearLayout exerciseLayout = findViewById(R.id.field_calendar_exercise);
            ArrayList<ExerciseTable> exerciseTable = dbHelper.getAllExerciseByTrainId(trainTable.getId());
            for (ExerciseTable ex : exerciseTable) {

                final View rowView = inflater.inflate(R.layout.field_calendar_exercises, null);
                TextView exerciseName = rowView.findViewById(R.id.calendarNameExercise);
                exerciseName.setText(ex.getName());
                trainLayout.addView(rowView,trainLayout.getChildCount() - 1);


                ArrayList<ApproachTable> approachTable = dbHelper.getAllApproachByExerciseId(ex.getId());
                ArrayList<RepeatTable> repeatTable = new ArrayList<>();

int i = 0;
                for(ApproachTable app : approachTable) {
                    repeatTable.addAll(dbHelper.getAllRepeatByApproachId(app.getId()));
                    final View view = inflater.inflate(R.layout.field_calendar_approach_repeat, null);
                    TextView repeatText = view.findViewById(R.id.calendarRepeat);
                    repeatText.setText("    " + repeatTable.get(i).getCount() );

                    TextView weightText = view.findViewById(R.id.calendarWeight);
                    weightText.setText("" + repeatTable.get(i).getWeight() + "  " + repeatTable.get(i).getType());
                    trainLayout.addView(view,trainLayout.getChildCount() - 1);
                    i++;
                }




            }


        }
        else{
            Toast.makeText(getApplicationContext(),"You have not train this day",Toast.LENGTH_LONG).show();
            finish();
        }

    }


}
