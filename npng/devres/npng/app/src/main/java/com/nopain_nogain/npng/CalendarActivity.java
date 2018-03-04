package com.nopain_nogain.npng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;


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
                String selectedDate = String.valueOf(month + 1) +
                        "-" + day + "-" + year + " ";
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), ShowTrainByDateActivity.class);
                startActivity(intent1);

            }
        });
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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
