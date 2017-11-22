package com.example.vlad.npng;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManeger;
    private  RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDataset;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mDataset = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            mDataset.add("element" + i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManeger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManeger);
        mAdapter = new MainAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
