package com.nopain_nogain.npng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ApproachTable;
import com.nopain_nogain.npng.dbtables.RepeatTable;

import java.util.ArrayList;

public class RepeatWeightActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    private long exerciseId = -1;
    private long trainId = -1;
    ArrayList<ApproachTable> approachTable = null;
    ArrayList<RepeatTable> repeatTable = null;
    DBHelper db = null;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_weight);
        exerciseId = getIntent().getLongExtra("exerciseId", -1);
        trainId = getIntent().getLongExtra("trainId", -1);
        parentLinearLayout = findViewById(R.id.parentLinearLayout);
        flag = getIntent().getBooleanExtra("flag",false);

        db = new DBHelper(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (db.getCountApproach(exerciseId) == 0) {
            final View rowView = inflater.inflate(R.layout.field_repat_weight, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        }
        else {
            approachTable = db.getAllApproachByExerciseId(exerciseId);
            repeatTable = new ArrayList<>();

            for (ApproachTable approach : approachTable) {
                repeatTable.addAll(db.getAllRepeatByApproachId(approach.getId()));
                db.deleteApprpach(approach.getId());
            }


            for (RepeatTable repeat : repeatTable) {
                View rowView = inflater.inflate(R.layout.field_repat_weight, null);

                EditText repeatText = rowView.findViewById(R.id.repeatEditText);

                repeatText.setText(Long.toString(repeat.getCount()));
                EditText weightText = rowView.findViewById(R.id.weightEditText);
                weightText.setText(Double.toString(repeat.getWeight()));
                Spinner type = rowView.findViewById(R.id.typeWeight);
                int typePos = 0;
                switch (repeat.getType()) {
                    case "Kg": typePos = 0; break;
                    case "Sec": typePos = 1; break;
                    case "Km": typePos = 2; break;
                }
                type.setSelection(typePos);

                db.deleteRepeat(repeat.getId());
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
            }
        }

        Button button = findViewById(R.id.addFieldButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.field_repat_weight,null);
                parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount() - 1);
            }
        });

        Button button1 = findViewById(R.id.deleteButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.field_repat_weight,null);
                db.deleteApprpach(approachTable.get((int)rowView.getVerticalScrollbarPosition()).getId());
                parentLinearLayout.removeView((View)view.getParent());
            }
        });

    }

    public void onBackPressed() {
        if (!flag) {
            if (repeatTable != null) {
                ArrayList<Long> repeatId = new ArrayList<>();
                for (ApproachTable approach : approachTable) {
                    repeatId.add(db.addApproach(approach));
                }
                int i = 0;
                for (RepeatTable repeat : repeatTable) {
                    repeat.setApproachId(repeatId.get(i));
                    db.addRepeat(repeat);
                }
            }

            Intent intent = new Intent(getApplicationContext(), RepeatApproachActivity.class);
            intent.putExtra("trainId",trainId);
            startActivity(intent);
        }

        if (flag) {
            if (repeatTable != null) {
                ArrayList<Long> repeatId = new ArrayList<>();
                for (ApproachTable approach : approachTable) {
                    repeatId.add(db.addApproach(approach));
                }
                int i = 0;
                for (RepeatTable repeat : repeatTable) {
                    repeat.setApproachId(repeatId.get(i));
                    db.addRepeat(repeat);
                }
            }
        }

            Intent intent = new Intent(getApplicationContext(), RepeatApproachActivity.class);
            intent.putExtra("trainId",trainId);
            startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if (item.getItemId() == R.id.saveItem) {
            if (!flag) {
                if (repeatTable != null) {
                    ArrayList<Long> repeatId = new ArrayList<>();
                    for (ApproachTable approach : approachTable) {
                        repeatId.add(db.addApproach(approach));
                    }
                    int i = 0;
                    for (RepeatTable repeat : repeatTable) {
                        repeat.setApproachId(repeatId.get(i));
                        db.addRepeat(repeat);
                    }
                }

                Intent intent = new Intent(getApplicationContext(), RepeatApproachActivity.class);
                intent.putExtra("trainId",trainId);
                startActivity(intent);
            }

            if (flag) {
                if (repeatTable != null) {
                    ArrayList<Long> repeatId = new ArrayList<>();
                    for (ApproachTable approach : approachTable) {
                        repeatId.add(db.addApproach(approach));
                    }
                    int i = 0;
                    for (RepeatTable repeat : repeatTable) {
                        repeat.setApproachId(repeatId.get(i));
                        db.addRepeat(repeat);
                    }
                }
            }
            Toast.makeText(getApplicationContext(),"Wtf?",Toast.LENGTH_SHORT);

           if(SaveApproachAndRepeat()) {
               Intent intent = new Intent(getApplicationContext(), RepeatApproachActivity.class);
               intent.putExtra("trainId", trainId);
               startActivity(intent);
           }
           else{
               Toast.makeText(getApplicationContext(),"Incorrect symbol",Toast.LENGTH_SHORT);
           }
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean SaveApproachAndRepeat() {
        for(int i = 1; i < parentLinearLayout.getChildCount() - 1; i++) {
            long approachId = db.addApproach(new ApproachTable(0, null, exerciseId));

            View rowView = parentLinearLayout.getChildAt(i);
            EditText repeatText = rowView.findViewById(R.id.repeatEditText);
            EditText weightText = rowView.findViewById(R.id.weightEditText);
            Spinner type = rowView.findViewById(R.id.typeWeight);

            if(repeatText.getText().toString().matches("[0-9]+")
                    && weightText.getText().toString().matches("[0-9]+") ){
                db.addRepeat(new RepeatTable(0, Integer.parseInt(repeatText.getText().toString()),
                        Double.parseDouble(weightText.getText().toString()),
                        type.getSelectedItem().toString(), approachId));
            }
            else {
                return false;
            }


        }
        return true;
    }


    public void onDeleteField(View v){
        parentLinearLayout.removeView((View)v.getParent());
    }
}
