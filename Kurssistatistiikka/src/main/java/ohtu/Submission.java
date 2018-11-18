package ohtu;

import java.util.ArrayList;

public class Submission {
    private String course;
    private int week;
    private double hours;
    private ArrayList<Integer> exercises;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
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

    @Override
    public String toString() {
        return course + ", viikko " + week + " tehtyjä tehtäviä yhteensä " + getExerciseCount()  + " aikaa kului " + hours + " tehdyt tehtävät: " + exercises;
    }
    
}