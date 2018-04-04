package com.nopain_nogain.npng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


        Button buttonCalendar = findViewById(R.id.buttonCalendar);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTrain = findViewById(R.id.button5);
        buttonTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4  = new Intent(getApplicationContext(), CreateTrain.class);
                startActivity(intent4);
            }
        });

        Button buttonExercise = findViewById(R.id.button4);
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5  = new Intent(getApplicationContext(), CreateExercise.class);
                startActivity(intent5);
            }
        });


    }
    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
