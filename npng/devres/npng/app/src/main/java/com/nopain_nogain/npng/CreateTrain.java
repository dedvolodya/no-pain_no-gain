package com.nopain_nogain.npng;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nopain_nogain.npng.dbtables.TrainTable;

import java.util.ArrayList;


public class CreateTrain extends Activity {
    ArrayList<TrainTable> trainTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_train);

        final DBHelper db = new DBHelper(this);
        trainTable = db.getAllTrain();
        db.close();

        ListView trainList = findViewById(R.id.TrainList);
        ArrayAdapter<TrainTable> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trainTable);
        trainList.setAdapter(adapter);
    }

    public  void onClickAddTrain(View v){
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
                Intent intent7 = new Intent (getApplicationContext(), TrainList.class);
                intent7.putExtra("trainName",input.getText().toString());
                startActivity(intent7);
            }
        } );

        alertDialog.show();
        }

}
