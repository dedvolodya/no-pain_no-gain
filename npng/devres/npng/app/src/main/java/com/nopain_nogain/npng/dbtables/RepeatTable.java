package com.nopain_nogain.npng.dbtables;

public class RepeatTable {
    private long id;
    private int count;
    private double weight;
    private String type;
    private long approachId;

    public RepeatTable (long id, int count, double weight, String type, long approach_id) {
        this.id = id;
        this.count = count;
        this.weight = weight;
        this.type = "Kg";
        if (type != null && (type.equals("Kg") || type.equals("sec") || type.equals("Km"))) {
            this.type = type;
        }
        this.approachId = approach_id;
    }

    public long getId () {
        return this.id;
    }

    public int getCount () {
        return this.count;
    }

    public double getWeight () {
        return this.weight;
    }

    public String getType () {
        return this.type;
    }

    public long getApproachId () {
        return this.approachId;
    }

    public void setApproachId (long approachId) {
        this.approachId = approachId;
    }
}
