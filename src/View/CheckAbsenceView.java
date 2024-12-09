package View;

import Model.Athlete;
import Model.Component;
import Model.Team;

import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * CheckAbsenceView class displays the absence information of athletes and teams.
 * It can show the entire team's details or individual athlete's data, depending on the input.
 * This class extends JFrame to create a GUI interface for checking absences.
 *
 * @authors Bruno Valdez & Manuel Rodriguez
 */
public class CheckAbsenceView extends JFrame {

    /**
     * Constructor to initialize and display the absence report window.
     *
     * @param sport The Team whose information is to be displayed
     * @param athlete The Athlete whose individual information is to be displayed, if available
     */
    public CheckAbsenceView(Team sport, Athlete athlete) {
        // Create a new JFrame for the report
        JFrame checkAbsencesFrame = new JFrame("Athlete/Team Report");
        checkAbsencesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkAbsencesFrame.setLayout(new BorderLayout());

        // Create a panel to hold the information
        JPanel checkAbsencesPanel = new JPanel(new GridLayout(0, 1));
        checkAbsencesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // If no athlete is selected, show the whole team's details
        if (athlete == null) {
            checkAbsencesPanel.add(new JLabel("Team Name: " + sport.toString()));
            checkAbsencesPanel.add(new JLabel("Absence Limit: " + sport.absencesLimit));

            checkAbsencesPanel.add(new JLabel("Team Members:"));
            for (Component member : sport.getMembers()) {
                if (member instanceof Athlete) {
                    Athlete teamAthlete = (Athlete) member;
                    checkAbsencesPanel.add(new JLabel("Name: " + teamAthlete.getName()));
                    checkAbsencesPanel.add(new JLabel("Email: " + teamAthlete.getEmail()));
                    checkAbsencesPanel.add(new JLabel("ID: " + teamAthlete.getId()));

                    Map<String, Integer> classes = teamAthlete.getClasses();
                    for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                        checkAbsencesPanel.add(new JLabel("Class: " + entry.getKey() + ", Absences: " + entry.getValue()));
                    }
                }
            }
        } else {
            // Add athlete's information to the panel
            checkAbsencesPanel.add(new JLabel("Athlete Name: " + athlete.getName()));
            checkAbsencesPanel.add(new JLabel("Date of Birth: " + athlete.getDob()));
            checkAbsencesPanel.add(new JLabel("Email: " + athlete.getEmail()));
            checkAbsencesPanel.add(new JLabel("ID: " + athlete.getId()));

            // Add classes and absences information
            Map<String, Integer> classes = athlete.getClasses();
            for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                checkAbsencesPanel.add(new JLabel("Class: " + entry.getKey() + ", Absences: " + entry.getValue()));
            }
        }

        // Add the panel to the frame
        checkAbsencesFrame.add(checkAbsencesPanel, BorderLayout.CENTER);

        // Set the frame size and make it visible
        checkAbsencesFrame.setSize(400, 300);
        checkAbsencesFrame.setLocationRelativeTo(null); // Center the frame
        checkAbsencesFrame.setVisible(true);
    }
}
