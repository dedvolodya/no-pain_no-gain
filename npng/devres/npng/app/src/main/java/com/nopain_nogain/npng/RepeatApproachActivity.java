package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;

public class RepeatApproachActivity extends Activity{
    ListView myList;
    ArrayAdapter<ExerciseTable> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_approach);

        DBHelper db = new DBHelper(this);
        Intent intent = getIntent();
        long trainId = intent.getLongExtra("trainId", -1);

        ArrayList<ExerciseTable> table = db.getAllExerciseByTrainId(trainId);


        myList = findViewById(R.id.exerciseList);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, table);

        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                if (sparseBooleanArray.get(position)) {
                    Intent intent2 = new Intent(getApplicationContext(),RepeatWeightActivity.class);
                    intent2.putExtra("exerciseId",adapter.getItem(position).getId());
                    startActivity(intent2);
                }
            }
        });

    }

    public void onClickTest(View v){
        Intent intent8 = new Intent(getApplicationContext(),RepeatWeightActivity.class);
        startActivity(intent8);
    }
}
