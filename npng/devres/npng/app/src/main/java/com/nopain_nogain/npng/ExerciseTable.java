package com.nopain_nogain.npng;


import android.app.Activity;

public class ExerciseTable {
    public String nameExercise;
    public int id;

    public ExerciseTable( ){}

    public ExerciseTable( String nameExercise) {
        this.nameExercise = nameExercise;
    }

    public ExerciseTable( int id , String nameExercise) {
        this.nameExercise = nameExercise;
        this.id = id;
    }

}
