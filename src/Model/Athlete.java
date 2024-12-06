package Model;

import java.util.List;
import java.util.Map;

public class Athlete implements Component{
    private String name;
    private String dob;
    private String email;
    private String id;
    private Map<String, Integer> classes;
    private int absencesLimit;

    public Athlete(String name, String dob, String email, String id, Map<String, Integer> classes, int absencesLimit) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.classes = classes;
        this.absencesLimit = absencesLimit;
    }

    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }

    public Map<String, Integer> getClasses() {
        return classes;
    }

    @Override
    public void setAbsencesLimit(int absencesLimit) {
        this.absencesLimit = absencesLimit;
    }

    @Override
    public void displayDetails(){
        System.out.println("Student: " + getName()+ "Absences: " + getClasses());
    }

    @Override
    public String toString(){
        return name;
    }
}
