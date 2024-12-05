package View;

import Controller.CreateTeamController;
import Model.Coach;
import Model.Component;
import Model.Team;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class CreateTeamView extends JFrame {
    private JLabel sportLabel, coachLabel, absencesLabel, sportChangeLabel, newCoachLabel, removeSportLabel;
    public JTextField sportField, absencesField;
    public JButton addTeamButton, changeCoachButton, removeTeamButton, returnButton;
    public JComboBox<Team> sportDropdown, sportDropdown2, removeSportDropdown;
    public JComboBox<Coach> coachDropDown, newCoachDropDown;
    private List<Coach> coaches;

    public CreateTeamView(WelcomeView welcomeView, List<Coach> coaches) {
        super("Manage Teams");
        this.coaches = coaches;

        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window on the screen

        // Add components to the frame
        add(CreateTeamComponents(), BorderLayout.CENTER);

        CreateTeamController controller = new CreateTeamController(this, welcomeView);

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
        sportDropdown2 = new JComboBox<>();
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
        removeSportDropdown = new JComboBox<>();
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

    public void updateTeamsDropDown() {
        sportDropdown2.removeAllItems();
        removeSportDropdown.removeAllItems();

        for (Component team : Team.getMembers()) {
            sportDropdown2.addItem((Team) team);
            removeSportDropdown.addItem((Team) team);
        }
        repaint();
        revalidate();
    }

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

