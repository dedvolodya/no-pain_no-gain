package com.nopain_nogain.npng;

import android.util.Log;

public class ExerciseTable {
    private long id;
    private String nameExercise;

    private long[] approachesLong;
    private String approachesString;

    ExerciseTable(long id, String nameExercise, String approachesString) {
        this.id = id;
        if (nameExercise == null) {
            this.nameExercise = "";
        } else {
            this.nameExercise = nameExercise;
        }
        this.approachesString = approachesString;
        this.approachesLong = approachesToLong(approachesString);
    }

    ExerciseTable(long id, String nameExercise, long[] approachesLong) {
        this.id = id;
        if (nameExercise == null) {
            this.nameExercise = "";
        } else {
            this.nameExercise = nameExercise;
        }
        this.approachesLong = new long[approachesLong.length];
        System.arraycopy(approachesLong, 0,
                this.approachesLong, 0, approachesLong.length);
        this.approachesString = approachesToString(approachesLong);
    }

    public long getId () {
        return id;
    }

    public String getName () {
        return nameExercise;
    }

    public String getApproachesString () {
        return approachesString;
    }

    public long[] getApproachesLong () {
        return approachesLong;
    }


    static public long[] approachesToLong (String approaches) {
        Log.d("[ERROR]", ":" + approaches + ":");
        if (approaches == null || approaches.equalsIgnoreCase("")) {
            Log.d("[ERROR]", ":" + approaches + ":");
            return null;
        }
        int i = 0;
        long[] apprL = new long[approaches.split(" ").length];
        for (String apprS : approaches.split(" ")) apprL[i++] = Integer.parseInt(apprS);
        return apprL;
    }

    static public String approachesToString (long[] approaches) {
        if (approaches == null) {
            return null;
        }
        StringBuilder apprS = new StringBuilder();
        for (long appr : approaches) apprS.append(appr).append(" ");
        return apprS.toString();
    }

    @Override
    public String toString() {
        return nameExercise;
    }
}
