package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;


public class ViewExerciseFromTrainActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    ArrayList<ExerciseTable> table = null;
    ArrayAdapter<ExerciseTable> adapter;
    long trainId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_from_train);

        listView = (ListView) findViewById(R.id.exerciseListFromTrain);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        trainId = intent.getLongExtra("trainId", -1);

        table = dbHelper.getAllExerciseByTrainId(trainId);

        adapter = new ArrayAdapter<ExerciseTable>(this,
                android.R.layout.simple_list_item_1, table);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();

                if (sparseBooleanArray.get(position)) {
                    Intent intent2 = new Intent(getApplicationContext(),RepeatWeightActivity.class);
                    intent2.putExtra("flag",true);
                    intent2.putExtra("exerciseId",adapter.getItem(position).getId());
                    intent2.putExtra("trainId",trainId);
                    startActivity(intent2);
                }
            }
        });

    }


}
