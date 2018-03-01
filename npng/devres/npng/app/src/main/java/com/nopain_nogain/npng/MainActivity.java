package com.nopain_nogain.npng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   protected void onClickCalendar (View v) {
        Intent intent  = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    protected void onClickAdding (View v) {
        Intent intent4  = new Intent(this, CreateExercise.class);
        startActivity(intent4);
    }

    protected void onClickTrain (View v) {
        Intent intent5  = new Intent(this, CreateTrain.class);
        startActivity(intent5);
    }
}
