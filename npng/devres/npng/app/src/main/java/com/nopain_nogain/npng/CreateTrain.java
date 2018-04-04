package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;


public class CreateTrain extends AppCompatActivity {
    ArrayList<TrainTable> trainTable;
    TextView textView;
    DBHelper db = null;
    ListView trainList;
    ArrayAdapter<TrainTable> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_train);

        db = new DBHelper(this);
        trainTable = db.getAllTrain();

        trainList = findViewById(R.id.TrainList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trainTable);
        trainList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        trainList.setAdapter(adapter);

        trainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray sparseBooleanArray = trainList.getCheckedItemPositions();

                if (sparseBooleanArray.get(position)) {

                    Intent intent2 = new Intent(getApplicationContext(),ViewExerciseFromTrainActivity.class);
                    intent2.putExtra("trainId",adapter.getItem(position).getId());
                    startActivity(intent2);
                }
            }
        });

        FloatingActionButton button = findViewById(R.id.addNewTrain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTrain.this);
                alertDialog.setTitle("NAME");
                alertDialog.setMessage("Enter the name");
                final EditText input = new EditText(CreateTrain.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (0 == db.existNameTrain(input.getText().toString())) {
                            Intent intent7 = new Intent(getApplicationContext(), TrainList.class);
                            intent7.putExtra("trainName", input.getText().toString());
                            startActivity(intent7);
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Train name, try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
                alertDialog.show();

            }

        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if (item.getItemId() == R.id.editItem) {
            Intent intent = new Intent(getApplicationContext(),DeleteTrainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
