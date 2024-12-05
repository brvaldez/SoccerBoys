package Model;
import java.util.ArrayList;
import java.util.List;

public class Team implements Component {
    private String teamName;
    public static List<Component> members; // List to hold TeamComponent objects
    private int absencesLimit;

    public Team(String teamName, int teamAbsencesLimit) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    // Method to add a member (Athlete or another Team)
    public void addMember(Component member) {
        members.add(member);
    }

    // Method to remove a member
    public void removeMember(Component member) {
        members.remove(member);
    }

    public List<Component> getMembers() {
        return members;
    }

    public void setAbsencesLimit(int absencesLimit) {
        this.absencesLimit = absencesLimit;
        for (Component member : members){
            Component.setAbsencesLimit(absencesLimit);
        }
    }
    @Override
    public void displayDetails() {
        System.out.println("Team: " + teamName);
        for (Component member : members) {
            member.displayDetails();
        }
    }
}
