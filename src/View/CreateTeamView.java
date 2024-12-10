package View;

import Controller.CreateTeamController;
import Model.Coach;
import Model.Component;
import Model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * CreateTeamView class provides the graphical interface for managing teams.
 * This includes adding new teams, changing coaches for teams, and removing teams.
 * It is designed as a JFrame with various input fields and buttons for interacting with team data.
 *
 * @authors Bruno Valdez & Manuel Rodriguez
 */
public class CreateTeamView extends JFrame {

    public JTextField sportField, absencesField;
    public JButton addTeamButton, changeCoachButton, removeTeamButton, returnButton;
    public JComboBox<Component> sportDropdown2;
    public JComboBox<Component> removeSportDropdown;
    public JComboBox<Coach> coachDropDown, newCoachDropDown;
    private List<Coach> coaches;
    //private List<Component> teams;
    private AbsencesView absencesView;
    private RegisterAthleteView registerAthleteView;

    /**
     * Constructor to initialize the CreateTeamView with necessary components and action listeners.
     *
     * @param welcomeView The WelcomeView instance to return to the main menu
     * @param absencesView The AbsencesView instance to manage absences
     * @param registerAthleteView The RegisterAthleteView instance for athlete registration
     * @param coaches List of coaches to populate dropdown menus
     */
    public CreateTeamView(WelcomeView welcomeView, AbsencesView absencesView, RegisterAthleteView registerAthleteView, List<Coach> coaches) {
        super("Manage Teams");
        this.absencesView = absencesView;
        this.registerAthleteView = registerAthleteView;
        this.coaches = coaches;
        //this.teams = Team.getRootTeam().getMembers();

        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window on the screen

        // Add components to the frame
        add(CreateTeamComponents(), BorderLayout.CENTER);

        CreateTeamController controller = new CreateTeamController(this, welcomeView, this.absencesView, this.registerAthleteView);

        // Populate coach dropdown
        for (Coach coach : coaches) {
            coachDropDown.addItem(coach);
        }

        // Add action listeners
        addTeamButton.addActionListener(controller);
        changeCoachButton.addActionListener(controller);
        removeTeamButton.addActionListener(controller);
        returnButton.addActionListener(controller);
        sportDropdown2.addActionListener(controller);

        setVisible(true);
    }

    /**
     * Creates and returns the main panel for the team management interface.
     *
     * @return The JPanel containing all components for the team management view
     */
    public JPanel CreateTeamComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Team Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Central components panel
        JPanel componentsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components
        gbc.gridx = 0;
        gbc.gridy = 0;
        componentsPanel.add(new JLabel("Sport:"), gbc);
        sportField = new JTextField(15);
        gbc.gridx = 1;
        componentsPanel.add(sportField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        componentsPanel.add(new JLabel("Coach:"), gbc);
        coachDropDown = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(coachDropDown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        componentsPanel.add(new JLabel("Absences:"), gbc);
        absencesField = new JTextField(15);
        gbc.gridx = 1;
        componentsPanel.add(absencesField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addTeamButton = new JButton("Add Team");
        componentsPanel.add(addTeamButton, gbc);

        // Change Coach Section
        gbc.gridx = 0;
        gbc.gridy = 4;
        componentsPanel.add(new JLabel("Change Coach - Sport:"), gbc);
        sportDropdown2 = new JComboBox<Component>();
        gbc.gridx = 1;
        componentsPanel.add(sportDropdown2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        componentsPanel.add(new JLabel("New Coach:"), gbc);
        newCoachDropDown = new JComboBox<>();
        gbc.gridx = 1;
        componentsPanel.add(newCoachDropDown, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        changeCoachButton = new JButton("Change Coach");
        componentsPanel.add(changeCoachButton, gbc);

        // Remove Team Section
        gbc.gridx = 0;
        gbc.gridy = 7;
        componentsPanel.add(new JLabel("Remove Team - Sport:"), gbc);
        removeSportDropdown = new JComboBox<Component>();
        gbc.gridx = 1;
        componentsPanel.add(removeSportDropdown, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        removeTeamButton = new JButton("Remove Team");
        componentsPanel.add(removeTeamButton, gbc);

        // Return Button
        gbc.gridx = 1;
        gbc.gridy = 9;
        returnButton = new JButton("Return to Menu");
        componentsPanel.add(returnButton, gbc);

        mainPanel.add(componentsPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    /**
     * Updates the dropdown menus with the current list of teams.
     */
    public void updateTeamsDropDown() {
        sportDropdown2.removeAllItems();
        removeSportDropdown.removeAllItems();
        for (Component team : Team.getRootTeam().getMembers()) {
            sportDropdown2.addItem(team);
            removeSportDropdown.addItem(team);
        }
        repaint();
        revalidate();
    }

    /**
     * Updates the new coach dropdown to exclude the current coach of the selected team.
     *
     * @param selectedTeam The team for which the coach dropdown should be updated
     */
    public void updateNewCoachDropDown(Team selectedTeam) {
        newCoachDropDown.removeAllItems();
        // Add only the coaches not assigned to the selected team
        for (Coach coach : coaches) {
            if (!coach.equals(selectedTeam.getCoach())) {
                newCoachDropDown.addItem(coach);
            }
        }
    }
}
