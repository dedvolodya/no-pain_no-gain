package com.nopain_nogain.npng.dbtables;

import java.util.ArrayList;

public class ExerciseTable {
    private long id;
    private String name;
    private ArrayList<ApproachTable> approach;
    private long trainId;

    public ExerciseTable(long id, String name, ArrayList<ApproachTable> approach, long trainId) {
        this.id = id;
        this.name = "";
        if (name != null) {
            this.name = name;
        }
        this.approach = new ArrayList<>();
        if (approach != null) {
            this.approach.addAll(approach);
        }
        this.trainId = trainId;
    }

    public long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public ArrayList<ApproachTable> getApproach () {
        return approach;
    }

    public long getTrainId () {
        return this.trainId;
    }

    public void setTrainId (long trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return name;
    }
}
