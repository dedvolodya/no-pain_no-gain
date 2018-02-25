package com.nopain_nogain.npng;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class CreateExercise extends AppCompatActivity{
    private EditText etNameExercise, etCountAttempt, etCountRepeat;
    private DBHelper db =  new DBHelper(this);
    private int i = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        etNameExercise = (EditText) findViewById(R.id.etNameExercise);
        etCountAttempt = (EditText) findViewById(R.id.etCountAttempt);
        etCountRepeat = (EditText) findViewById(R.id.etCountRepeat);


    }


   /* protected void onClickAdd(View v)  {


        ExerciseTable exTab = new ExerciseTable(etNameExercise.getText().toString(),
                                            parseInt(etCountAttempt.getText().toString()),
                                            parseInt(etCountRepeat.getText().toString()));

       WorkWithDataBase wdb = new WorkWithDataBase(db);

       wdb.addExercise(exTab);

        Toast.makeText(this,"Exercise was added",Toast.LENGTH_SHORT).show();
    }*/


}
