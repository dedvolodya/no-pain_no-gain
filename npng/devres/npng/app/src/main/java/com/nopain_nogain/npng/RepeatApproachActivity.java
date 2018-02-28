package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RepeatApproachActivity extends Activity{
    String singleExercise;
    ListView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_approach);

        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        singleExercise = intent.getStringExtra("exLst");

        ArrayList<String> table = new ArrayList<String>();

        for(int i  = 0; i < singleExercise.length(); i+=2){
            table.add(dbHelper.getExerciseById((int)(singleExercise.charAt(i)) - 48));
        }



       //table.add("abcdefghi");

        myList = (ListView) findViewById(R.id.exerciseList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, table);

        myList.setAdapter(adapter);

    }

    public void onClickToast(View v) {
        Toast.makeText(getApplicationContext(),singleExercise+"    "+singleExercise.length(),Toast.LENGTH_LONG).show();
    }
}
