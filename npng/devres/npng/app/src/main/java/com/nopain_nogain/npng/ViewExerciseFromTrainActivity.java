package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;


public class ViewExerciseFromTrainActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    ArrayList<ExerciseTable> table = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_from_train);

        listView = (ListView) findViewById(R.id.exerciseListFromTrain);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        long trainId = intent.getLongExtra("trainId", -1);

        table = dbHelper.getAllExerciseByTrainId(trainId);

        ArrayAdapter<ExerciseTable> adapter = new ArrayAdapter<ExerciseTable>(this,
                android.R.layout.simple_list_item_1, table);

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == R.id.backItem) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
