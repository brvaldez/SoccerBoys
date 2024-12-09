package View;

import Controller.AbsencesController;
import Model.Athlete;
import Model.Component;
import Model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class: AbsencesView
 * Purpose: The graphical user interface for managing and tracking athlete absences.
 *          Provides options for changing the absence limit, adding/removing absences, checking absence records,
 *          and generating reports.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class AbsencesView extends JFrame {

    public JTextField limitAbsencesField;
    public JComboBox<Team> sportDropDown2;
    public JComboBox<Component> athleteDropDown;
    public JComboBox<Component> athleteDropDown2;
    public JComboBox<String> classDropDown;
    public JButton changeLimitButton, insertAbsenceButton, removeAbsenceButton, checkAbsencesButton, reportButton, returnButton;
    private List<Component> members;

    /**
     * Constructor to initialize the AbsencesView with the provided welcome view and list of members.
     * Sets up the window and attaches action listeners for button events.
     *
     * @param welcomeView The WelcomeView that leads to this AbsencesView.
     * @param members     The list of team members to be displayed in drop-downs.
     */
    public AbsencesView(WelcomeView welcomeView, List<Component> members) {
        super("Absence Management");
        this.members = members;

        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        add(AbsencesComponents(), BorderLayout.CENTER);

        // Automatically size window based on content
        pack();
        setMinimumSize(new Dimension(800, 600)); // Minimum size to ensure usability
        setLocationRelativeTo(null); // Ensure window always appears centered

        AbsencesController controller = new AbsencesController(this, welcomeView);

        // Add action listeners for buttons
        changeLimitButton.addActionListener(controller);
        insertAbsenceButton.addActionListener(controller);
        removeAbsenceButton.addActionListener(controller);
        checkAbsencesButton.addActionListener(controller);
        reportButton.addActionListener(controller);
        returnButton.addActionListener(controller);
        sportDropDown2.addActionListener(controller);
        athleteDropDown.addActionListener(controller);
        athleteDropDown2.addActionListener(controller);
        checkAbsencesButton.addActionListener(controller);

        // Check if there are members, enable controls accordingly
        if (members.isEmpty()) {
            disableAbsenceControls();
        } else {
            enableAbsenceControls();
        }

        setVisible(true);
    }

    /**
     * Sets up the main components for the Absences View with labels, text fields, drop-downs, and buttons.
     *
     * @return The main JPanel with all the components for Absences management.
     */
    public JPanel AbsencesComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Absence Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Central components panel with scrollable content
        JPanel componentsPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(componentsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Section 1: Manage Team Absences
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        componentsPanel.add(new JLabel("Manage Team Absences"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        componentsPanel.add(new JLabel("Sport:"), gbc);
        sportDropDown2 = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(sportDropDown2, gbc);

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

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    /**
     * Updates the Teams drop-down menu based on available teams.
     */
    public void updateTeamsDropDown() {
        sportDropDown2.removeAllItems();
        for (Component team : Team.getMembers()) {
            sportDropDown2.addItem((Team) team);
        }
        repaint();
        revalidate();
    }

    /**
     * Updates the Athlete drop-down menus based on the selected team.
     *
     * @param teamAthletes The list of athletes in the selected team.
     */
    public void updateAthletesDropDown(List<Component> teamAthletes) {
        athleteDropDown.removeAllItems();
        athleteDropDown2.removeAllItems();
        for (Component athlete : teamAthletes) {
            if (athlete instanceof Athlete) {
                athleteDropDown.addItem(athlete);
                athleteDropDown2.addItem(athlete);
            }
        }
        repaint();
        revalidate();
    }

    /**
     * Updates the Class drop-down menu based on the selected athlete.
     *
     * @param athlete The athlete whose classes are to be displayed.
     */
    public void updateClassDropDown(Athlete athlete) {
        if (athlete == null) {
            System.out.println("Athlete is null");
            return;
        }

        Map<String, ?> classesMap = athlete.getClasses();
        if (classesMap.isEmpty()) {
            System.out.println("No classes found for athlete: " + athlete.getName());
        } else {
            List<String> classNames = new ArrayList<>(classesMap.keySet());
            classDropDown.removeAllItems();
            for (String className : classNames) {
                classDropDown.addItem(className);
            }
            repaint();
            revalidate();
        }
    }

    /**
     * Disables all absence-related controls if no teams are available.
     */
    private void disableAbsenceControls() {
        sportDropDown2.setEnabled(false);
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

    /**
     * Enables all absence-related controls when a team is created or available.
     */
    public void enableAbsenceControls() {
        sportDropDown2.setEnabled(true);
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
