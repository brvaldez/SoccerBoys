package Model;
import java.util.ArrayList;
import java.util.List;

public class Team implements Component {
    private String teamName;
    private List<Component> members; // List to hold TeamComponent objects
    private int teamAbsencesLimit;

    public Team(String teamName, int teamAbsencesLimit) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
        this.teamAbsencesLimit = teamAbsencesLimit;
    }


    // Method to add a member (Athlete or another Team)
    public void addMember(Component member) {
        members.add(member);
    }

    // Method to remove a member
    public void removeMember(Component member) {
        members.remove(member);
    }

    public void setTeamAbsencesLimit(int limitOfAbsences) {
        this.teamAbsencesLimit = 3;
        for (Component Athlete : members){
            Athlete.setAbsencesLimit(teamAbsencesLimit);
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
