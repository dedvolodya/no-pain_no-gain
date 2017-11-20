package com.example.vlad.npng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateExercise extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd, btnRead, btnClear;
    EditText etNameExercise, etCountAttempt, etCountRepeat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnClear);
        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
