package ohtu;

import java.util.ArrayList;

public class Course {
    private String name;
    private String fullName;
    private String term;
    private int year;
    private ArrayList<Integer> exercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    

    public int getExerciseCount() {
        return exercises.size();
    }

    public int getExerciseCountOfWeek(int week) {
        return exercises.get(week);
    }

    @Override
    public String toString() {
        return fullName + "";
    }
    
}