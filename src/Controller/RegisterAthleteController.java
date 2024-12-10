package Controller;

import Model.Athlete;
import View.AbsencesView;
import View.RegisterAthleteView;
import View.WelcomeView;
import Model.Team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: RegisterAthleteController
 * Purpose: This class handles the logic for registering athletes, including form validation,
 *          adding athletes to teams, and clearing form fields.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class RegisterAthleteController implements ActionListener {
    private RegisterAthleteView registerAthleteView;
    private WelcomeView welcomeView;
    private AbsencesView absencesView;

    /**
     * Constructor for RegisterAthleteController.
     * @param registerAthleteView the view for registering athletes.
     * @param welcomeView the welcome screen view.
     * @param absencesView the view for managing absences.
     */
    public RegisterAthleteController(RegisterAthleteView registerAthleteView, WelcomeView welcomeView, AbsencesView absencesView) {
        this.registerAthleteView = registerAthleteView;
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
    }

    /**
     * Handles user actions from the view.
     * @param e the action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerAthleteView.registerButton) {
            // Handle athlete registration
            String firstName = registerAthleteView.firstNameField.getText().trim();
            String lastName = registerAthleteView.lastNameField.getText().trim();
            String dob = registerAthleteView.dobField.getText().trim();
            String email = registerAthleteView.emailField.getText().trim();
            String id = registerAthleteView.idField.getText().trim();
            String class1 = registerAthleteView.class1Field.getText().trim();
            String class2 = registerAthleteView.class2Field.getText().trim();
            String class3 = registerAthleteView.class3Field.getText().trim();
            Team athleteTeam = (Team) registerAthleteView.sportDropDown.getSelectedItem();

            // Validate that all required fields are filled
            if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || email.isEmpty() || id.isEmpty() || class1.isEmpty() || class2.isEmpty() || class3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate date of birth format (MM-DD-YYYY)
            if (!dob.matches("^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$")) {
                JOptionPane.showMessageDialog(null, "Invalid date format! Use MM-DD-YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate email format
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Combine first and last name
            String name = firstName + " " + lastName;

            // Initialize absence counts for the classes
            Map<String, Integer> classAbsences = new HashMap<>();
            classAbsences.put(class1, 0);
            classAbsences.put(class2, 0);
            classAbsences.put(class3, 0);

            // Create an athlete object
            Athlete athlete = new Athlete(name, dob, email, id, classAbsences, athleteTeam.absencesLimit);

            // Add the athlete to the selected team
            addAthlete(athlete, athleteTeam);

            // Notification for the athlete just registered
            JOptionPane.showMessageDialog(null, "New athlete registered!", "Information", JOptionPane.INFORMATION_MESSAGE);

        // Clear the input fields
            clearFields();
        } else if (e.getSource() == registerAthleteView.clearButton) {
            // Clear input fields
            clearFields();
        } else if (e.getSource() == registerAthleteView.returnButton) {
            // Navigate back to the welcome screen
            registerAthleteView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }

    /**
     * Adds an athlete to the specified team.
     * @param athlete the athlete to be added.
     * @param athleteTeam the team to which the athlete belongs.
     */
    public void addAthlete(Athlete athlete, Team athleteTeam) {
        athleteTeam.addMember(athlete); // Add the athlete to the team
    }

    /**
     * Clears all input fields in the registration form.
     */
    public void clearFields() {
        registerAthleteView.firstNameField.setText("");
        registerAthleteView.lastNameField.setText("");
        registerAthleteView.dobField.setText("");
        registerAthleteView.emailField.setText("");
        registerAthleteView.idField.setText("");
        registerAthleteView.sportDropDown.setSelectedIndex(0); // Reset the sport dropdown
        registerAthleteView.class1Field.setText("");
        registerAthleteView.class2Field.setText("");
        registerAthleteView.class3Field.setText("");
    }
}
