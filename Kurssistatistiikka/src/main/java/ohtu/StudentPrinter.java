
package ohtu;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentPrinter {
    public void printStudentSubmissions(JsonWebParser parser, String studentNr) throws IOException {
        //Parse submissions and courses
        Submission[] subs = parser.parseStudent(studentNr);
        Course[] courses = parser.parseCourses();
        
        //Split submissions by courses
        HashMap<String, List<Submission>> subMap = new HashMap<>();
        for (Submission sub : subs) {
            if (!subMap.containsKey(sub.getCourse())) {
                subMap.put(sub.getCourse(), new ArrayList<>());
            }
            subMap.get(sub.getCourse()).add(sub);
        }
        
        //Split courses by short name
        HashMap<String, Course> courseMap = new HashMap<>();
        for (Course course : courses) {
            if (!courseMap.containsKey(course.getName())) {
                courseMap.put(course.getName(), course);
            }
        }
        
        //Print submissions by courses
        System.out.println("Opiskelijanumero " + studentNr);
        for (String key : subMap.keySet()) {
            //Get course and stats
            Course course = courseMap.get(key);
            JsonObject courseStats = parser.parseCourseStats(course.getName());
            
            //Print Course name
            System.out.println("\n" + course.getFullName() + " " + course.getTerm() + " " + course.getYear() + "\n");
            
            //Print submissions of course
            for (Submission sub : subMap.get(key)) {
                System.out.println("viikko " + sub.getWeek());
                System.out.println(" tehtyjä tehtäviä "
                        + sub.getExerciseCount() + "/" + course.getExerciseCountOfWeek(sub.getWeek())
                        + " aikaa kului " + sub.getHours() + " tehdyt tehtävät: " + sub.getExercises());
                System.out.println("\nyhteensä: "
                        + totalExercises(subs) + "/" + course.getExerciseCount()
                        + " tehtävää " + totalHours(subs) + " tuntia\n");
            }
            
            System.out.println("kurssilla yhteensä " + totalReturns(courseStats)
                    + " palautusta, palautettuja tehtäviä " 
                    + totalReturnExercises(courseStats) + " kpl, aikaa käytetty yhteensä "
                    + totalReturnHours(courseStats) + " tuntia");
        }
    }
    
    private double totalHours(Submission[] subs) {
        double hours = 0;
        for (Submission sub : subs) {
            hours += sub.getHours();
        }
        return hours;
    }
    
    private int totalExercises(Submission[] subs) {
        int exerciseCount = 0;
        for (Submission submission : subs) {
            exerciseCount += submission.getExerciseCount();
        }
        return exerciseCount;
    }
    
    private int totalReturns(JsonObject courseStats) {
        int returnCount = 0;
        for (String key : courseStats.keySet()) {
            returnCount += courseStats.get(key).getAsJsonObject().get("students").getAsInt();
        }
        return returnCount;
    }
    
    private int totalReturnExercises(JsonObject courseStats) {
        int exerciseCount = 0;
        for (String key : courseStats.keySet()) {
            exerciseCount += courseStats.get(key).getAsJsonObject().get("exercise_total").getAsInt();
        }
        return exerciseCount;
    }
    
    private int totalReturnHours(JsonObject courseStats) {
        int hours = 0;
        for (String key : courseStats.keySet()) {
            hours += courseStats.get(key).getAsJsonObject().get("hour_total").getAsDouble();
        }
        return hours;
    }
}
