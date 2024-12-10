package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Team class represents a group of members, including athletes, managed by a coach.
 * It implements the Component interface, allowing it to participate in a composite structure with other Components.
 * This class also incorporates the Observer pattern for notifying the coach about specific events such as exceeding absence limits.
 */
public class Team implements Component {

    private String teamName;
    private List<Component> members = new ArrayList<>(); // List to hold Athletes Component objects
    public static Team rootTeam;
    private static Observer observer;
    public static int absencesLimit;
    private Coach coach;

    /**
     * Using a static block to make sure the rootTeam is initiated
     */
    static {
        rootTeam = new Team("All Teams", null, 0); // Prevent adding itself to root
    }

    /**
     * Constructs a new Team with the specified name, coach, and absences limit.
     * The team is automatically added to the root team list upon creation.
     *
     * @param teamName The name of the team.
     * @param coach The coach associated with the team, who acts as an observer.
     * @param absencesLimit The maximum number of absences allowed for the team.
     */
    public Team(String teamName, Coach coach, int absencesLimit) {
        this.teamName = teamName;
        this.coach = coach;
        this.absencesLimit = absencesLimit;
        this.observer = coach;
    }

    /**
     * Retrieves the list of all teams in the team root list.
     *
     * @return The list of all teams.
     */
    public static Team getRootTeam() {
        return rootTeam;
    }

    /**
     * Adds a member (Team) to the root team.
     *
     * @param team The member (Team) to be added to the root team.
     */
    private static void addTeamToRoot(Team team) {
        Team.rootTeam.addMember(team);
    }

    /**
     * Removes a member (Team) to the root team.
     *
     * @param team The member (Team) to be removed to the root team.
     */
    private static void removeTeamToRoot(Team team) {
        Team.rootTeam.removeMember(team);
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
     * Adds a member (Athlete) to the team.
     *
     * @param member The member (Athlete) to be added to the team.
     */
    public void addMember(Component member) {
        members.add(member);
    }

    /**
     * Removes a member (Athlete) from the team.
     *
     * @param member The member (Athlete) to be removed from the team.
     */
    public void removeMember(Component member) {
        members.remove(member);
    }

    /**
     * Retrieves the list of all members in the team.
     *
     * @return The list of members in the team.
     */
    public List<Component> getMembers() {
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
    public List<String[]> collectDataForCSV() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Name", "Team", "Absences"});

        for (Component member : members) {
            if (member instanceof Athlete) {
                Athlete athlete = (Athlete) member;
                Map<String, Integer> classes = athlete.getClasses();

                for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                    data.add(new String[]{
                            athlete.getName(),
                            teamName,
                            entry.getKey() + ": " + entry.getValue()
                    });
                }
            }
        }
        return data;
    }
}
