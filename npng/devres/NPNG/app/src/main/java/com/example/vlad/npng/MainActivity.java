package com.example.vlad.npng;

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

    protected void onClickPhotoActivity (View v) {
        Intent intent2  = new Intent(this, ActivityPhotoGallery.class);
        startActivity(intent2);
    }

    protected void onClickTest (View v) {
        Intent intent3  = new Intent(this, Test.class);
        startActivity(intent3);
    }
}
