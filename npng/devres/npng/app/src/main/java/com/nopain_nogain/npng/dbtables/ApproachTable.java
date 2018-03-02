package com.nopain_nogain.npng.dbtables;

import java.util.ArrayList;

public class ApproachTable {
    private long id;
    private ArrayList<RepeatTable> repeat;
    private long exercise_id;

    public ApproachTable(long id, ArrayList<RepeatTable> repeat, long exercise_id) {
        this.id = id;
        this.repeat = new ArrayList<>();
        if (repeat != null) {
            this.repeat.addAll(repeat);
        }
        this.exercise_id = exercise_id;
    }

    public long getId() {
        return this.id;
    }

    public ArrayList<RepeatTable> getRepeat() {
        return this.repeat;
    }

    public long getExerciseId() {
        return this.exercise_id;
    }
}
