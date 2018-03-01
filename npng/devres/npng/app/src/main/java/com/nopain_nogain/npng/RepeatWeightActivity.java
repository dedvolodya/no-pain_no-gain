package com.nopain_nogain.npng;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class RepeatWeightActivity extends Activity {

    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_weight);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parentLinearLayout);
    }

    public void onAddField(View v){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_repat_weight,null);

        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v){
        parentLinearLayout.removeView((View)v.getParent());
    }
}
