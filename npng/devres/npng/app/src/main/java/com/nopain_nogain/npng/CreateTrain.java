package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


public class CreateTrain extends Activity {

    String [] numbers;
    Button addTrain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_train);
        final DBHelper dbHelper = new DBHelper(this);
        numbers = dbHelper.StrTrainingTable();
        ListView trainList = (ListView) findViewById(R.id.TrainList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
        trainList.setAdapter(adapter);

        String log = "sz " + numbers.length ;

        Log.d("Name: ", log);

    }
    public  void onClickAddTrain(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTrain.this);
        alertDialog.setTitle("NAME");
        alertDialog.setMessage("Enter the name");
        final EditText input = new EditText(CreateTrain.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent7 = new Intent (getApplicationContext(), TrainList.class);
                intent7.putExtra("trainName",input.getText().toString());
                startActivity(intent7);
            }
        } );


        alertDialog.show();
        }





}
