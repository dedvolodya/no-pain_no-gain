package com.nopain_nogain.npng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ArrayList<String> qText = new ArrayList<>();

        qText.add("“I always felt that my greatest asset was not my physical ability, it was my mental ability.”\n" +
                "\n" +
                "– Bruce Jenner");
        qText.add("“Persistence can change failure into extraordinary achievement.”\n" +
                "\n" +
                "– Marv Levy");
        qText.add("“You were born to be a player. You were meant to be here. This moment is yours.”\n" +
                "\n" +
                "– Herb Brooks");
        qText.add("“Nobody who ever gave his best regretted it.”\n" +
                "\n" +
                "– George Halas");
        qText.add("“Always make a total effort, even when the odds are against you.”\n" +
                "\n" +
                "– Arnold Palmer");
        qText.add("“If you can believe it, the mind can achieve it.”\n" +
                "\n" +
                "– Ronnie Lott");
        qText.add("“If you don’t have confidence, you’ll always find a way not to win.”\n" +
                "\n" +
                "– Carl Lewis");
        qText.add("“You’re never a loser until you quit trying.”\n" +
                "\n" +
                "– Mike Ditka");
        qText.add("“Never give up! Failure and rejection are only the first step to succeeding.”\n" +
                "\n" +
                "– Jim Valvano");
        qText.add("“Make each day your masterpiece.”\n" +
                "\n" +
                "– John Wooden");
        qText.add("«It is not the strongest of the species that survives, nor the most intelligent, but the one most responsive to change».\n" +
                "        \n" +
                "        -Charles Darwin");
        qText.add("«Fall seven times and stand up eight». \n" +
                "        \n" +
                "        -Japanese Proverb");
        qText.add("«There are no shortcuts to any place worth going».\n" +
                "        \n" +
                "        -Helen Keller");



        Random rnd = new Random(System.currentTimeMillis());

        int number = 0 + rnd.nextInt(13 - 0 - 1);


        TextView textView = findViewById(R.id.quoteText);
        textView.setText(qText.get(number));


    }


   protected void onClickCalendar (View v) {
        Intent intent  = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    protected void onClickAdding (View v) {
        Intent intent4  = new Intent(this, CreateExercise.class);
        startActivity(intent4);
    }

    protected void onClickTrain (View v) {
        Intent intent5  = new Intent(this, CreateTrain.class);
        startActivity(intent5);
    }
}
