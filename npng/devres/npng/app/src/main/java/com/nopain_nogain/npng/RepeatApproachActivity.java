package com.nopain_nogain.npng;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;

public class RepeatApproachActivity extends AppCompatActivity {
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
                    //Toast.makeText(this,tr)
                    Intent intent2 = new Intent(getApplicationContext(),RepeatWeightActivity.class);
                    intent2.putExtra("exerciseId",adapter.getItem(position).getId());
                    intent2.putExtra("trainId", trainId);
                    startActivity(intent2);
                }
            }
        });

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
            Intent intent = new Intent(getApplicationContext(), TrainList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnClickAddExercisesToTrain (View v) {


        Intent intent8 = new Intent(getApplicationContext(), CreateTrain.class);
        startActivity(intent8);

    }

}
