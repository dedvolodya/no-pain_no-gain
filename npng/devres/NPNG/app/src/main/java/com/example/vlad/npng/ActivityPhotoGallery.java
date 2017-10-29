package com.example.vlad.npng;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vlad on 23.10.2017.
 */

public class ActivityPhotoGallery extends Activity {
    Long data;

    private ImageView mImageView;
    private String mImageAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    public void onClick(View view) {
        // Загружаем картинку
     /*   Glide
                .with(this)
                .load(mImageAddress)
                .into(mImageView);
    }*/
    }

}
