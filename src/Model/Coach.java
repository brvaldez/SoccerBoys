package Model;

import javax.swing.*;

/**
 * Class: Coach
 * Purpose: Represents a coach who observes and responds to changes in the system (e.g., athlete absences).
 * Implements the Observer interface for receiving notifications about events.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class Coach implements Observer {
    private String name; // Name of the coach

    /**
     * Constructor for Coach.
     * @param name The name of the coach.
     */
    public Coach(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the coach.
     * @return Coach's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the coach with a notification about an event.
     * Displays a pop-up message for the event.
     * @param event The event message to display.
     */
    @Override
    public void update(String event) {
        JOptionPane.showMessageDialog(null, event, "Absence Alert", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Provides a string representation of the coach.
     * @return Coach's name.
     */
    @Override
    public String toString() {
        return name;
    }
}
