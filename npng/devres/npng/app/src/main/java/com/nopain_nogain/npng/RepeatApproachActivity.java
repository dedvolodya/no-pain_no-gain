package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;

public class RepeatApproachActivity extends Activity{
    String singleExercise, buf = "";
    ListView myList;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_approach);

        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        singleExercise = intent.getStringExtra("exLst");

        ArrayList<ExerciseTable> table = new ArrayList<ExerciseTable>();

        String [] str = singleExercise.split(" ");
        for(int i  = 0; i < str.length; i++){
            table.add(dbHelper.getExerciseById(Integer.parseInt(str[i])+1));
        }

        myList = (ListView) findViewById(R.id.exerciseList);

        ArrayAdapter<ExerciseTable> adapter = new ArrayAdapter<ExerciseTable>(this,
                android.R.layout.simple_list_item_1, table);

        myList.setAdapter(adapter);

    }

    public void onClickToast(View v) {
        Toast.makeText(getApplicationContext(),singleExercise + "    " + buf,Toast.LENGTH_LONG).show();
    }

    public void onClickTest(View v){
        Intent intent8 = new Intent(getApplicationContext(),RepeatWeightActivity.class);
        startActivity(intent8);
    }
}
