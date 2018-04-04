package com.nopain_nogain.npng;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;


public class AddExerciseActivity extends AppCompatActivity {
    EditText editText;
    DBHelper db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        editText = findViewById(R.id.nameExerciseEditText);
        db = new DBHelper(this);


        Button button = findViewById(R.id.saveExerciseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.existNameExercise(editText.getText().toString()) == 0) {
                    db.addExercise(new ExerciseTable(-1, editText.getText().toString(), null, -1));
                    Intent intent = new Intent(getApplicationContext(), CreateExercise.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Exercise name, try again",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
