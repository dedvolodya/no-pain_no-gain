package com.example.vlad.npng;


import java.util.ArrayList;
import java.util.List;

public class ExerciseTableAdapter {
   public DBHelper db;
   public ExerciseTableAdapter(DBHelper dbHelper){
       db = dbHelper;
   }

    ArrayList<ExerciseTable> table = db.getAllContacts();

    public String [] StrExerciseTable () {
        String [] stringList = new String[table.size()];

        for(int i = 0; i < table.size(); i++){
            stringList[i] = table.get(i).toString();
        }

        return stringList;
    }
}
