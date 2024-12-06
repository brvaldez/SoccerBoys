package Model;
import java.util.ArrayList;
import java.util.List;

public class Team implements Component {
    private String teamName;
    public static List<Component> members = new ArrayList<>(); // List to hold TeamComponent objects
    private static Observer observer;
    public static int absencesLimit;
    private Coach coach;

    public Team(String teamName, Coach coach, int absencesLimit) {
        this.teamName = teamName;
        this.coach = coach;
        this.absencesLimit = absencesLimit;
        this.observer = coach;
    }

    public static void notifyObservers(String event) {
            observer.update(event);
    }

    // Method to add a member (Athlete or another Team)
    public static void addMember(Component member) {
        members.add(member);
    }

    // Method to remove a member
    public static void removeMember(Component member) {
        members.remove(member);
    }

    public static List<Component> getMembers() {
        return members;
    }

    public void setAbsencesLimit(int absencesLimit) {
        this.absencesLimit = absencesLimit;
        for (Component member : members){
            if (member instanceof Team) {
                ((Team) member).setAbsencesLimit(absencesLimit); // Avoid infinite loop by ensuring no circular dependencies
            }
        }
    }
    @Override
    public void displayDetails() {
        System.out.println("Team: " + teamName);
        for (Component member : members) {
            member.displayDetails();
        }
    }
    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return teamName;
    }
}
