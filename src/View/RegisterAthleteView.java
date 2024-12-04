package View;

import Controller.RegisterAthleteController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterAthleteView extends JFrame {
    private JLabel nameLabel, firstNameLabel, lastNameLabel, dobLabel, idLabel, emailLabel, sportLabel, classesLabel;
    public JTextField firstNameField, lastNameField, dobField, idField, emailField, class1Field, class2Field, class3Field;
    public JButton registerButton, clearButton, returnButton;
    public JComboBox<String> sportDropdown;

    // Create the frame
    public RegisterAthleteView(WelcomeView welcomeView) {
        super("Register Student Athlete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        add(RegisterAthleteComponents());

        RegisterAthleteController controller = new RegisterAthleteController(this, welcomeView);

        // Add action listeners for clear and submit
        registerButton.addActionListener(controller);
        clearButton.addActionListener(controller);
        returnButton.addActionListener(controller);

        setVisible(true);
    }

    public JPanel RegisterAthleteComponents(){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20,20));
        panel.setLayout(null);

        nameLabel = new JLabel("Student Athlete Name");
        nameLabel.setBounds(20, 20, 200, 20);
        panel.add(nameLabel);

        // First name label and field
        firstNameLabel = new JLabel("First");
        firstNameLabel.setBounds(20, 50, 50, 20);
        panel.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(70, 50, 120, 20);
        panel.add(firstNameField);

        // Last name label and field
        lastNameLabel = new JLabel("Last");
        lastNameLabel.setBounds(200, 50, 50, 20);
        panel.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(250, 50, 120, 20);
        panel.add(lastNameField);

        // Date of birth label and field
        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(20, 90, 100, 20);
        panel.add(dobLabel);
        dobField = new JTextField();
        dobField.setBounds(120, 90, 120, 20);
        panel.add(dobField);

        // Email label and field
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(20, 130, 50, 20);
        panel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(70, 130, 120, 20);
        panel.add(emailField);

        // ID label and field
        idLabel = new JLabel("ID");
        idLabel.setBounds(200, 130, 50, 20);
        panel.add(idLabel);
        idField = new JTextField();
        idField.setBounds(250, 130, 120, 20);
        panel.add(idField);

        // Sport label and field
        sportLabel = new JLabel("Sport");
        sportLabel.setBounds(20, 170, 50, 20);
        panel.add(sportLabel);
        String[] sports = {"Football", "Basketball", "Baseball", "Soccer", "Tennis"};
        sportDropdown = new JComboBox<>(sports);
        sportDropdown.setBounds(70, 170, 120, 20);
        panel.add(sportDropdown);

        // Classes label and fields
        classesLabel = new JLabel("Classes");
        classesLabel.setBounds(200, 170, 60, 20);
        panel.add(classesLabel);
        class1Field = new JTextField();
        class1Field.setBounds(250, 170, 60, 20);
        panel.add(class1Field);
        class2Field = new JTextField();
        class2Field.setBounds(320, 170, 60, 20);
        panel.add(class2Field);
        class3Field = new JTextField();
        class3Field.setBounds(390, 170, 60, 20);
        panel.add(class3Field);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 250, 100, 30);
        panel.add(registerButton);

        // Clear button
        clearButton = new JButton("Clear");
        clearButton.setBounds(260, 250, 100, 30);
        panel.add(clearButton);

        // Return button
        returnButton = new JButton("Menu");
        returnButton.setBounds(300, 300, 50,20);
        panel.add(returnButton);

        return panel;
    }
}

