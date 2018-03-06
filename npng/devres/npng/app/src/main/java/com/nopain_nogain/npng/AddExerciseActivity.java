package com.nopain_nogain.npng;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    }

    public void onClickSaveExercise(View v) {
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == R.id.backItem) {
            Intent intent = new Intent(getApplicationContext(),CreateExercise.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
