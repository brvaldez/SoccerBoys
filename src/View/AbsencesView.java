package View;

import Controller.AbsencesController;
import Model.Athlete;
import Model.Component;
import Model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.ArrayList;

public class AbsencesView extends JFrame {
    private JLabel manageTeamLabel, limitAbsencesLabel, sportLabel, athletesAbsencesLabel, dateLabel, classLabel, generalInfoLabel, athleteReportLabel;
    public JTextField limitAbsencesField, dateField;
    public JComboBox<Team> sportDropDown;
    public JComboBox<Component> athleteDropDown;
    public JComboBox<Component> athleteDropDown2;
    public JComboBox<String> classDropDown;
    public JComboBox<String> absenceDropDown;
    public JButton changeLimitButton, insertAbsenceButton, removeAbsenceButton, checkAbsencesButton, reportButton, returnButton;
    private List<Team> teams;
    private List<Athlete> athletes;

    public AbsencesView(WelcomeView welcomeView, List<Team> teams) {
        super("Absence Management");
        this.teams = teams;
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(AbsencesComponents());

        AbsencesController controller = new AbsencesController(this, welcomeView);

        // Add action listeners for buttons
        changeLimitButton.addActionListener(controller);
        insertAbsenceButton.addActionListener(controller);
        removeAbsenceButton.addActionListener(controller);
        checkAbsencesButton.addActionListener(controller);
        reportButton.addActionListener(controller);
        returnButton.addActionListener(controller);

        // Disable absence controls if no teams are available
        if (teams.isEmpty()) {
            disableAbsenceControls();
        }
        /*else {
            sportDropDown.setEnabled(true); // Enable dropdown if there are teams
        }*/

        setVisible(true);
    }

    // Components for the Absences View
    public JPanel AbsencesComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(null);

        // Section 1: Manage Team Absences
        JLabel manageTeamLabel = new JLabel("Manage Team Absences");
        manageTeamLabel.setBounds(50, 20, 200, 20);
        panel.add(manageTeamLabel);

        JLabel sportLabel = new JLabel("Sport");
        sportLabel.setBounds(50, 50, 100, 20);
        panel.add(sportLabel);

        // Sport DropDown
        sportDropDown = new JComboBox<>(new DefaultComboBoxModel<>(teams.toArray(new Team[0])));
        sportDropDown.setBounds(50, 70, 150, 25);
        panel.add(sportDropDown);

        JLabel limitAbsencesLabel = new JLabel("Limit of Absences");
        limitAbsencesLabel.setBounds(250, 50, 150, 20);
        panel.add(limitAbsencesLabel);

        limitAbsencesField = new JTextField();
        limitAbsencesField.setBounds(250, 70, 150, 25);
        panel.add(limitAbsencesField);

        changeLimitButton = new JButton("Change Limit");
        changeLimitButton.setBounds(450, 70, 150, 30);
        panel.add(changeLimitButton);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(50, 110, 800, 10);
        panel.add(separator1);

        // Section 2: Athletes Absences
        JLabel athletesAbsencesLabel = new JLabel("Athletes Absences");
        athletesAbsencesLabel.setBounds(50, 120, 200, 20);
        panel.add(athletesAbsencesLabel);

        JLabel athleteLabel = new JLabel("Athlete");
        athleteLabel.setBounds(50, 150, 100, 20);
        panel.add(athleteLabel);

        athleteDropDown = new JComboBox<>();
        athleteDropDown.setBounds(50, 170, 150, 25);
        panel.add(athleteDropDown);

        JLabel dateLabel = new JLabel("Date (dd/mm/yyyy)");
        dateLabel.setBounds(250, 150, 150, 20);
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(250, 170, 150, 25);
        panel.add(dateField);

        JLabel classLabel = new JLabel("Class");
        classLabel.setBounds(450, 150, 100, 20);
        panel.add(classLabel);

        classDropDown = new JComboBox<>();
        classDropDown.setBounds(450, 170, 150, 25);
        panel.add(classDropDown);

        insertAbsenceButton = new JButton("Insert Absence");
        insertAbsenceButton.setBounds(650, 170, 150, 30);
        panel.add(insertAbsenceButton);

        JLabel absenceLabel = new JLabel("Absences");
        absenceLabel.setBounds(450, 210, 100, 20);
        panel.add(absenceLabel);

        absenceDropDown = new JComboBox<>();
        absenceDropDown.setBounds(450, 230, 150, 25);
        panel.add(absenceDropDown);

        removeAbsenceButton = new JButton("Remove Absence");
        removeAbsenceButton.setBounds(650, 230, 150, 30);
        panel.add(removeAbsenceButton);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(50, 280, 800, 10);
        panel.add(separator2);

        // Section 3: General Information
        JLabel generalInfoLabel = new JLabel("General Information");
        generalInfoLabel.setBounds(50, 290, 200, 20);
        panel.add(generalInfoLabel);

        JLabel athleteReportLabel = new JLabel("Athlete");
        athleteReportLabel.setBounds(50, 320, 100, 20);
        panel.add(athleteReportLabel);

        athleteDropDown2 = new JComboBox<>();
        athleteDropDown2.setBounds(50, 340, 150, 25);
        panel.add(athleteDropDown2);

        checkAbsencesButton = new JButton("Check Absences");
        checkAbsencesButton.setBounds(250, 340, 150, 30);
        panel.add(checkAbsencesButton);

        reportButton = new JButton("Generate Report");
        reportButton.setBounds(450, 340, 150, 30);
        panel.add(reportButton);

        // Return Button
        returnButton = new JButton("Menu");
        returnButton.setBounds(650, 400, 150, 30);
        panel.add(returnButton);

        return panel;
    }

    // Update Athlete list based on the selected team
    public void updateAthletesList(List<Component> athletes) {
        athleteDropDown.removeAllItems();
        for (Component athlete : athletes) {
            athleteDropDown.addItem(athlete);
        }
    }

    // Update class list for the selected athlete
    public void updateClassDropDown(List<String> classes) {
        classDropDown.removeAllItems();
        for (String className : classes) {
            classDropDown.addItem(className);
        }
    }
    public void updateAbsenceDropDown
    // Disable controls if there are no teams available
    private void disableAbsenceControls() {
        sportDropDown.setEnabled(false);
        athleteDropDown.setEnabled(false);
        limitAbsencesField.setEnabled(false);
        dateField.setEnabled(false);
        classDropDown.setEnabled(false);
        changeLimitButton.setEnabled(false);
        insertAbsenceButton.setEnabled(false);
        removeAbsenceButton.setEnabled(false);
        athleteDropDown2.setEnabled(false);
        checkAbsencesButton.setEnabled(false);
        reportButton.setEnabled(false);
    }

    /* Enable absence controls when a team is created
    public void enableAbsenceControls() {
        sportDropDown.setEnabled(true);
        athleteDropDown.setEnabled(true);
        limitAbsencesField.setEnabled(true);
        dateField.setEnabled(true);
        classDropDown.setEnabled(true);
        changeLimitButton.setEnabled(true);
        insertAbsenceButton.setEnabled(true);
        removeAbsenceButton.setEnabled(true);
        athleteDropDown2.setEnabled(false);
        checkAbsencesButton.setEnabled(true);
        reportButton.setEnabled(true);
    }*/
}
