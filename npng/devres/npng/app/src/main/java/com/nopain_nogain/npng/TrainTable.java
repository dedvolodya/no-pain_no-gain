package com.nopain_nogain.npng;

import android.support.annotation.NonNull;

public class TrainTable {
    private long id;
    private String nameTrain;
    private int dayWeek;

    private String exercisesString;
    private long[] exercisesLong;

    TrainTable(long id, String nameTrain, String exercisesString, int day_week) {
        this.id = id;
        if (nameTrain == null) {
            this.nameTrain = "";
        } else {
            this.nameTrain = nameTrain;
        }
        this.dayWeek = day_week;
        if (exercisesString == null) {
            this.exercisesString = "";
        } else {
            this.exercisesString = exercisesString;
        }
        this.exercisesLong = exercisesToLong(exercisesString);
    }

    TrainTable(long id, String nameTrain, long[] exercisesLong, int day_week) {
        this.id = id;
        if (nameTrain == null) {
            this.nameTrain = "";
        } else {
            this.nameTrain = nameTrain;
        }
        this.dayWeek = day_week;
        if (exercisesLong == null){
            this.exercisesLong = null;
        } else {
            this.exercisesLong = new long[exercisesLong.length];
            System.arraycopy(exercisesLong, 0,
                    this.exercisesLong, 0, exercisesLong.length);
        }
        this.exercisesString = exercisesToString(exercisesLong);
    }

    long getId () {
        return id;
    }

    String getName () {
        return nameTrain;
    }

    String getExercisesString () {
        return exercisesString;
    }

    public long[] getExercisesLong () {
        return exercisesLong;
    }

    int getDayWeek () {
        return dayWeek;
    }

    public static long[] exercisesToLong(String exercisesString) {
        if (exercisesString == null || exercisesString == "") {
            return null;
        }
        int i = 0;
        long[] exerL = new long[exercisesString.split(" ").length];
        for (String exerS : exercisesString.split(" ")) {
            exerL[i++] = Integer.parseInt(exerS);
        }
        return exerL;
    }

    public static String exercisesToString (long[] exercisesLong) {
        if (exercisesLong == null) {
            return "";
        }
        StringBuilder exerS = new StringBuilder();
        for (long exerL : exercisesLong) {
            exerS.append(exerL).append(" ");
        }
        return exerS.toString();
    }

    @Override
    public String toString () {
        return nameTrain;
    }
}

