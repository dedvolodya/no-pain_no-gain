package com.nopain_nogain.npng;

public class GenerationID {
    public static int _ID_exercise;
    public static int _ID_train;
    public static int _ID_repeat;
    public static int _ID_approach;

    GenerationID () {
        _ID_exercise = 0;
        _ID_train = 0;
        _ID_repeat = 0;
        _ID_approach = 0;
    }

    GenerationID (int id_e, int id_t, int id_r, int id_a) {
        _ID_exercise = id_e;
        _ID_train = id_t;
        _ID_repeat = id_r;
        _ID_approach = id_a;
    }

    int getNewIDExercise () {
        return _ID_exercise++;
    }

    int getNewIDTrain () {
        return _ID_train++;
    }

    int getNewIDRepeat () {
        return _ID_repeat++;
    }

    int getNewIDApproach () {
        return _ID_approach++;
    }

}
