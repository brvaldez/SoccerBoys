// Composite class to organize athletes into teams
import java.util.ArrayList;
import java.util.List;

public class Team implements Component {
    private String teamName;
    private List<Component> members; // List to hold TeamComponent objects

    public Team(String teamName) {
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

    // Method to calculate the total absences (100 - attendance)
    public int getTotalAbsences() {
        int totalAbsences = 0;
        for (Component member : members) {
            totalAbsences += (100 - member.getAttendanceRecord());
        }
        return totalAbsences;
    }

    @Override
    public int getAttendanceRecord() {
        // Average attendance record for the team
        if (members.isEmpty()) return 0;

        int totalAttendance = 0;
        for (Component member : members) {
            totalAttendance += member.getAttendanceRecord();
        }
        return totalAttendance / members.size();
    }

    @Override
    public void displayDetails() {
        System.out.println("Team: " + teamName);
        for (Component member : members) {
            member.displayDetails();
        }
        System.out.println("Total Absences: " + getTotalAbsences());
    }
}
