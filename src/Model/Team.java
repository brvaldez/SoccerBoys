package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class: Team
 * Purpose: Represents a sports team, managing members (Athletes) and their coach.
 *          Handles the tracking of absences, notifying the coach, and displaying member details.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class Team implements Component {

    private String teamName;
    public static List<Component> members = new ArrayList<>(); // List to hold TeamComponent objects
    private static Observer observer;
    public static int absencesLimit;
    private Coach coach;

    /**
     * Constructor to initialize a Team with a name, coach, and absences limit.
     *
     * @param teamName   The name of the team.
     * @param coach      The coach of the team.
     * @param absencesLimit The maximum allowed absences for the team.
     */
    public Team(String teamName, Coach coach, int absencesLimit) {
        this.teamName = teamName;
        this.coach = coach;
        this.absencesLimit = absencesLimit;
        this.observer = coach;
    }

    /**
     * Notifies the observer (coach) of an event, such as exceeding the absences limit.
     *
     * @param event A string representing the event to notify the coach about.
     */
    public static void notifyObservers(String event) {
        observer.update(event);
    }

    /**
     * Adds a member (Athlete or another Team) to the team.
     *
     * @param member The member (Athlete or Team) to be added to the team.
     */
    public static void addMember(Component member) {
        members.add(member);
    }

    /**
     * Removes a member (Athlete or another Team) from the team.
     *
     * @param member The member (Athlete or Team) to be removed from the team.
     */
    public static void removeMember(Component member) {
        members.remove(member);
    }

    /**
     * Retrieves the list of all members in the team.
     *
     * @return The list of members in the team.
     */
    public static List<Component> getMembers() {
        return members;
    }

    /**
     * Sets the absences limit for the team and updates the absences limit for each member.
     * If a member exceeds the limit, the observer (coach) will be notified.
     *
     * @param absencesLimit The new absences limit for the team.
     */
    public void setAbsencesLimit(int absencesLimit) {
        this.absencesLimit = absencesLimit;
        for (Component member : members) {
            if (member instanceof Athlete) {
                member.setAbsencesLimit(absencesLimit); // Avoid infinite loop by ensuring no circular dependencies
            }
        }

        // Trigger the observer if the new limit is equal to or less than the current number of absences
        for (Component member : members) {
            if (member instanceof Athlete) {
                Athlete athlete = (Athlete) member;
                Map<String, Integer> classes = athlete.getClasses();
                for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                    if (entry.getValue() > absencesLimit) {
                        notifyObservers("Athlete " + athlete.getName() + " has exceeded the absences limit!");
                    }
                }
            }
        }
    }

    /**
     * Displays the details of the team and its members.
     */
    @Override
    public void displayDetails() {
        System.out.println("Team: " + teamName);
        for (Component member : members) {
            member.displayDetails();
        }
    }

    /**
     * Gets the coach of the team.
     *
     * @return The coach of the team.
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     * Sets the coach of the team.
     *
     * @param coach The new coach for the team.
     */
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    /**
     * Returns a string representation of the team.
     *
     * @return The name of the team.
     */
    @Override
    public String toString() {
        return teamName;
    }

    /**
     * Collects data for the team, including athletes' names and their absences for CSV export.
     *
     * @return A list of string arrays containing the data for CSV export.
     */
    public static List<String[]> collectDataForCSV() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Name", "Absences"});

        for (Component member : members) {
            if (member instanceof Athlete) {
                Athlete athlete = (Athlete) member;
                Map<String, Integer> classes = athlete.getClasses();

                for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                    data.add(new String[]{
                            athlete.getName(),
                            entry.getKey() + ": " + entry.getValue()
                    });
                }
            }
        }
        return data;
    }
}
