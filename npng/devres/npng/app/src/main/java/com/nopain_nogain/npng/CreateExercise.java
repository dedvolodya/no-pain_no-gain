package com.nopain_nogain.npng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class CreateExercise extends AppCompatActivity{

    private DBHelper dbHelper;
    ListView listView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        listView = (ListView) findViewById(R.id.editingExerciseList);
        dbHelper =  new DBHelper(this);

        ArrayList<ExerciseTable> table = new ArrayList<>();
        table = dbHelper.getAllExercise();

        ArrayAdapter<ExerciseTable> adapter = new ArrayAdapter<ExerciseTable>(this,
                R.layout.item_exercise_train,R.id.nameExercise, table);

        listView.setAdapter(adapter);


    }

    public void onClickAddExercise(View v){
        Intent intent = new Intent(getApplicationContext(),AddExerciseActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteItem(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateExercise.this);
        alertDialog.setTitle("Are you sure?");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        } );

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //////////////DELETING FOR MISHGUN!!!!!!!!!!!!!!!!!!
            }
        } );

        alertDialog.show();
    }
}


