package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.nopain_nogain.npng.dbtables.ExerciseTable;


public class AddExerciseActivity extends AppCompatActivity {
    EditText editText;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        editText = findViewById(R.id.nameExerciseEditText);
        dbHelper = new DBHelper(this);
    }

    public void onClickSaveExercise(View v) {
        dbHelper.addExercise(new ExerciseTable(-1,editText.getText().toString(),null,-1));
        Intent intent = new Intent(getApplicationContext(),CreateExercise.class);
        startActivity(intent);
    }
}
