package com.nopain_nogain.npng.dbtables;

import java.util.ArrayList;

public class TrainTable {
    private long id;
    private String name;
    private int dayWeek;
    private ArrayList<ExerciseTable> exercise;

    public TrainTable(long id, String name, int dayWeek, ArrayList<ExerciseTable> exercise) {
        this.id = id;
        this.name = "";
        if (name != null) {
            this.name = name;
        }
        this.dayWeek = dayWeek;
        this.exercise = new ArrayList<>();
        if (exercise != null) {
            this.exercise.addAll(exercise);
        }
    }

    public long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public ArrayList<ExerciseTable> getExercise () {
        return exercise;
    }

    public int getDayWeek () {
        return dayWeek;
    }

    @Override
    public String toString () {
        return name;
    }
}
