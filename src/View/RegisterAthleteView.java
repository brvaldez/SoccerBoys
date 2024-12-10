package View;

import Controller.RegisterAthleteController;
import Model.Team;
import Model.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * RegisterAthleteView class provides the graphical interface for registering student athletes.
 * This includes input fields for athlete details, sports selection, and buttons to register, clear, or return to the menu.
 *
 * @authors Bruno Valdez & Manuel Rodriguez
 */
public class RegisterAthleteView extends JFrame {

    public JTextField firstNameField, lastNameField, dobField, idField, emailField, class1Field, class2Field, class3Field;
    public JButton registerButton, clearButton, returnButton;
    public JComboBox<Team> sportDropDown;
    private List<Component> members;
    private AbsencesView absencesView;

    /**
     * Constructor to initialize the RegisterAthleteView with necessary components and action listeners.
     *
     * @param welcomeView The WelcomeView instance to return to the main menu
     * @param absencesView The AbsencesView instance to manage absences
     */
    public RegisterAthleteView(WelcomeView welcomeView, AbsencesView absencesView) {
        super("Register Student Athlete");
        this.absencesView = absencesView;
        //this.members = Team.getRootTeam().getMembers();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add components to the frame
        add(RegisterAthleteComponents(), BorderLayout.CENTER);

        RegisterAthleteController controller = new RegisterAthleteController(this, welcomeView, this.absencesView);

        // Add action listeners for buttons
        registerButton.addActionListener(controller);
        clearButton.addActionListener(controller);
        returnButton.addActionListener(controller);
        sportDropDown.addActionListener(controller);

        // Pack to dynamically size the window
        pack();
        setMinimumSize(new Dimension(600, 400)); // Set a minimum size for usability
        setLocationRelativeTo(null); // Ensure the window opens in the center of the screen
        setVisible(true);
    }

    /**
     * Creates and returns the main panel for the athlete registration form.
     *
     * @return The JPanel containing all components for the registration view
     */
    public JPanel RegisterAthleteComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Register Student Athlete", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // First Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Date of Birth: (MM/DD/YYYY"), gbc);
        dobField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(dobField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // ID
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("ID:"), gbc);
        idField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        // Sport
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Sport:"), gbc);
        sportDropDown = new JComboBox<>();
        gbc.gridx = 1;
        formPanel.add(sportDropDown, gbc);

        // Classes
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Classes:"), gbc);

        JPanel classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        class1Field = new JTextField(5);
        class2Field = new JTextField(5);
        class3Field = new JTextField(5);
        classPanel.add(class1Field);
        classPanel.add(class2Field);
        classPanel.add(class3Field);
        gbc.gridx = 1;
        formPanel.add(classPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");
        returnButton = new JButton("Menu");
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(returnButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    /**
     * Updates the sport dropdown menu with the current list of teams.
     */
    public void updateTeamsDropDown() {
        sportDropDown.removeAllItems();
        for (Component team : Team.getRootTeam().getMembers()) {
            sportDropDown.addItem((Team) team);
        }
        repaint();
        revalidate();
    }
}

