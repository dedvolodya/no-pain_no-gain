package com.nopain_nogain.npng;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;
import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;

public class DeleteTrainActivity extends AppCompatActivity{
    ListView listView;
    DBHelper dbHelper;
    ArrayAdapter<TrainTable> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_train);
        listView = findViewById(R.id.trainListForDeleting);
        dbHelper = new DBHelper(this);

        ArrayList<TrainTable> table = dbHelper.getAllTrain();

        adapter = new ArrayAdapter<>(this,
                R.layout.item_exercise_train,R.id.nameExercise, table);

        listView.setAdapter(adapter);
    }

    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),CreateTrain.class));
    }

    public void onClickDeleteItem(final View v){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DeleteTrainActivity.this);
        alertDialog.setTitle("Are you sure?");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // empty
            }
        } );

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int positionView = listView.getPositionForView(v);
                Toast.makeText(getApplicationContext(), "Training was deleted", Toast.LENGTH_LONG).show();
                long idTrain = adapter.getItem(positionView).getId();
                // TODO: Need add delete row table approach and repeat depends this exercise
                dbHelper.deleteTrain(idTrain);
                recreate();
            }
        } );

        alertDialog.show();
    }
}
