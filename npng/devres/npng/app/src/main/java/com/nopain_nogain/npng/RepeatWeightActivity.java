package com.nopain_nogain.npng;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ApproachTable;
import com.nopain_nogain.npng.dbtables.RepeatTable;

public class RepeatWeightActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    private long exerciseId = -1;
    DBHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_weight);
        exerciseId = getIntent().getLongExtra("exerciseId", -1);
        parentLinearLayout = findViewById(R.id.parentLinearLayout);

        db = new DBHelper(this);

        // create default rowView
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_repat_weight,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount() - 1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == R.id.backItem) {
            finish();
        }

        if (item.getItemId() == R.id.saveItem) {
            SaveApproachAndRepeat();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void SaveApproachAndRepeat() {
        for(int i = 1; i < parentLinearLayout.getChildCount() - 1; i++) {
            long approachId = db.addApproach(new ApproachTable(0, null, exerciseId));

            View rowView = parentLinearLayout.getChildAt(i);
            EditText repeatText = rowView.findViewById(R.id.repeatEditText);
            EditText weightText = rowView.findViewById(R.id.weightEditText);
            Spinner type = rowView.findViewById(R.id.typeWeight);

           db.addRepeat(new RepeatTable(0, Integer.parseInt(repeatText.getText().toString()),
                    Double.parseDouble(weightText.getText().toString()),
                    type.getSelectedItem().toString(), approachId));
        }

    }


    public void onAddField(View v){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_repat_weight,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount() - 1);
    }

    public void onDeleteField(View v){
        parentLinearLayout.removeView((View)v.getParent());
    }
}
