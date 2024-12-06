package View;

import Controller.AbsencesController;
import Model.Athlete;
import Model.Component;
import Model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AbsencesView extends JFrame {
    public JTextField limitAbsencesField;
    public JComboBox<Team> sportDropDown;
    public JComboBox<Component> athleteDropDown;
    public JComboBox<Component> athleteDropDown2;
    public JComboBox<String> classDropDown;
    public JButton changeLimitButton, insertAbsenceButton, removeAbsenceButton, checkAbsencesButton, reportButton, returnButton;
    private List<Team> members;

    public AbsencesView(WelcomeView welcomeView, List<Team> members) {
        super("Absence Management");
        this.members = members;

        // Window setup
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen

        // Add components to the frame
        add(AbsencesComponents(), BorderLayout.CENTER);

        AbsencesController controller = new AbsencesController(this, welcomeView);

        // Add action listeners for buttons
        changeLimitButton.addActionListener(controller);
        insertAbsenceButton.addActionListener(controller);
        removeAbsenceButton.addActionListener(controller);
        checkAbsencesButton.addActionListener(controller);
        reportButton.addActionListener(controller);
        returnButton.addActionListener(controller);


        // Disable controls if no teams are available
        if (members.isEmpty()) {
            disableAbsenceControls();
        } else enableAbsenceControls();

        setVisible(true);
    }

    // Components for the Absences View
    public JPanel AbsencesComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Absence Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Central components panel
        JPanel componentsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Section 1: Manage Team Absences
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        componentsPanel.add(new JLabel("Manage Team Absences"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        componentsPanel.add(new JLabel("Sport:"), gbc);
        sportDropDown = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(sportDropDown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        componentsPanel.add(new JLabel("Limit of Absences:"), gbc);
        limitAbsencesField = new JTextField(15);
        gbc.gridx = 1;
        componentsPanel.add(limitAbsencesField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        changeLimitButton = new JButton("Change Limit");
        componentsPanel.add(changeLimitButton, gbc);

        // Section 2: Athletes Absences
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        componentsPanel.add(new JLabel("Athletes Absences"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        componentsPanel.add(new JLabel("Athlete:"), gbc);
        athleteDropDown = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(athleteDropDown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        componentsPanel.add(new JLabel("Class:"), gbc);
        classDropDown = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(classDropDown, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        insertAbsenceButton = new JButton("Insert Absence");
        componentsPanel.add(insertAbsenceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        removeAbsenceButton = new JButton("Remove Absence");
        componentsPanel.add(removeAbsenceButton, gbc);

        // Section 3: General Information
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        componentsPanel.add(new JLabel("General Information"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        componentsPanel.add(new JLabel("Athlete:"), gbc);
        athleteDropDown2 = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(athleteDropDown2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        checkAbsencesButton = new JButton("Check Absences");
        componentsPanel.add(checkAbsencesButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        reportButton = new JButton("Generate Report");
        componentsPanel.add(reportButton, gbc);

        // Return Button
        gbc.gridx = 1;
        gbc.gridy = 13;
        returnButton = new JButton("Return to Menu");
        componentsPanel.add(returnButton, gbc);

        mainPanel.add(componentsPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    public void updateTeamsDropDown() {
        sportDropDown.removeAllItems();
        for (Component team : Team.getMembers()) {
            sportDropDown.addItem((Team) team);
        }
        repaint();
        revalidate();
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

    // Disable controls if there are no teams available
    private void disableAbsenceControls() {
        sportDropDown.setEnabled(false);
        athleteDropDown.setEnabled(false);
        limitAbsencesField.setEnabled(false);
        classDropDown.setEnabled(false);
        changeLimitButton.setEnabled(false);
        insertAbsenceButton.setEnabled(false);
        removeAbsenceButton.setEnabled(false);
        athleteDropDown2.setEnabled(false);
        checkAbsencesButton.setEnabled(false);
        reportButton.setEnabled(false);
    }

    // Enable absence controls when a team is created
    public void enableAbsenceControls() {
        sportDropDown.setEnabled(true);
        athleteDropDown.setEnabled(true);
        limitAbsencesField.setEnabled(true);
        classDropDown.setEnabled(true);
        changeLimitButton.setEnabled(true);
        insertAbsenceButton.setEnabled(true);
        removeAbsenceButton.setEnabled(true);
        athleteDropDown2.setEnabled(false);
        checkAbsencesButton.setEnabled(true);
        reportButton.setEnabled(true);
    }
}
