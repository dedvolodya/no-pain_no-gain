package com.nopain_nogain.npng.dbtables;

import java.util.ArrayList;

public class ExerciseTable {
    private long id;
    private String name;
    private ArrayList<ApproachTable> approach;
    private long train_id;

    public ExerciseTable(long id, String name, ArrayList<ApproachTable> approach, long train_id) {
        this.id = id;
        this.name = "";
        if (name != null) {
            this.name = name;
        }
        this.approach = new ArrayList<>();
        if (approach != null) {
            this.approach.addAll(approach);
        }
        this.train_id = train_id;
    }

    public long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public ArrayList<ApproachTable> getApproachesString () {
        return approach;
    }

    public long getTrainId () {
        return this.train_id;
    }

    @Override
    public String toString() {
        return name;
    }
}
