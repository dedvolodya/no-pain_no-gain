package com.nopain_nogain.npng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = findViewById(R.id.calendarView1);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView cw, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                //Toast.makeText(getApplicationContext(),"" + dayOfWeek,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ShowTrainByDateActivity.class);
                intent.putExtra("dayOfWeek",dayOfWeek);
                startActivity(intent);
            }
        });
    }

}
