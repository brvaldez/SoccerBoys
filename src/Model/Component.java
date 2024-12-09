package Model;

/**
 * Interface: Component
 * Purpose: Defines the structure for objects that can display details and manage an absences limit.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public interface Component {

    /**
     * Displays the details of the implementing class.
     * Intended to provide information specific to the class, such as its name or attributes.
     */
    void displayDetails();

    /**
     * Sets the limit for absences.
     * @param absencesLimit The maximum number of absences allowed.
     */
    void setAbsencesLimit(int absencesLimit);
}
