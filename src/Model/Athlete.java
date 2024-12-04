package Model;

import java.util.Map;

public class Athlete implements Component{
    private String name;
    private String dob;
    private String email;
    private String id;
    private String sport;
    private Map<String, Integer> classes;
    private int absencesLimit;
    public Athlete(String name, String dob, String email, String id, String sport, Map<String, Integer> classes) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.sport = sport;
        this.classes = classes;
        this.absencesLimit = 3;
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
    public String getSport() {
        return sport;
    }
    public Map<String, Integer> getClasses() {
        return classes;
    }
    public int getLimitOfAbsences(){
        return absencesLimit;
    }
    public void setAbsencesLimit(int absencesLimit){
        this.absencesLimit = absencesLimit;
    }
    public void displayDetails(){
        System.out.println("Student: " + getName()+ ", Sport: " +getSport() + "Absences: " + getClasses());
    }
}
