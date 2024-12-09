package Model;

import java.util.Map;

/**
 * Class: Athlete
 * Purpose: Represents an athlete with personal information, enrolled classes, and absence limits.
 * Implements the Component interface for use in composite structures.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class Athlete implements Component {
    private String name;                  // Athlete's name
    private String dob;                   // Athlete's date of birth
    private String email;                 // Athlete's email address
    private String id;                    // Athlete's unique identifier
    private Map<String, Integer> classes; // Map of class names to absence counts
    private int absencesLimit;            // Maximum allowed absences for the athlete

    /**
     * Constructor for Athlete.
     * @param name Athlete's name.
     * @param dob Athlete's date of birth.
     * @param email Athlete's email address.
     * @param id Athlete's unique identifier.
     * @param classes Map of classes and their corresponding absence counts.
     * @param absencesLimit Maximum allowed absences.
     */
    public Athlete(String name, String dob, String email, String id, Map<String, Integer> classes, int absencesLimit) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.classes = classes;
        this.absencesLimit = absencesLimit;
    }

    // Getter for the athlete's name
    public String getName() {
        return name;
    }

    // Getter for the athlete's date of birth
    public String getDob() {
        return dob;
    }

    // Getter for the athlete's email address
    public String getEmail() {
        return email;
    }

    // Getter for the athlete's unique ID
    public String getId() {
        return id;
    }

    // Getter for the classes map (class name to absences)
    public Map<String, Integer> getClasses() {
        return classes;
    }

    /**
     * Sets the absences limit for the athlete.
     * @param absencesLimit New absences limit.
     */
    @Override
    public void setAbsencesLimit(int absencesLimit) {
        this.absencesLimit = absencesLimit;
    }

    /**
     * Displays the athlete's details, including name and absences for classes.
     */
    @Override
    public void displayDetails() {
        System.out.println("Student: " + getName() + " Absences: " + getClasses());
    }

    /**
     * Returns the athlete's name as a string representation.
     * @return Athlete's name.
     */
    @Override
    public String toString() {
        return name;
    }
}
