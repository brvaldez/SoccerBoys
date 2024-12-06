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

public class RegisterAthleteController implements ActionListener {
    private RegisterAthleteView registerAthleteView;
    private WelcomeView welcomeView;
    private AbsencesView absencesView;

    public RegisterAthleteController(RegisterAthleteView registerAthleteView, WelcomeView welcomeView, AbsencesView absencesView) {
        this.registerAthleteView = registerAthleteView;
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerAthleteView.registerButton) {
            String firstName = registerAthleteView.firstNameField.getText().trim();
            String lastName = registerAthleteView.lastNameField.getText().trim();
            String dob = registerAthleteView.dobField.getText().trim();
            String email = registerAthleteView.emailField.getText().trim();
            String id = registerAthleteView.idField.getText().trim();
            String class1 = registerAthleteView.class1Field.getText().trim();
            String class2 = registerAthleteView.class2Field.getText().trim();
            String class3 = registerAthleteView.class3Field.getText().trim();
            Team athleteTeam = (Team) registerAthleteView.sportDropDown.getSelectedItem();
            if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || email.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = firstName + lastName;
            Map<String, Integer> classAbsences = new HashMap<>();
            classAbsences.put(class1, 0);
            classAbsences.put(class2, 0);
            classAbsences.put(class3, 0);
            Athlete athlete = new Athlete(name, dob, email, id, classAbsences, athleteTeam.absencesLimit);
            addAthlete(athlete, athleteTeam);

            clearFields();
        } else if (e.getSource() == registerAthleteView.clearButton) {
             clearFields();
        } else if (e.getSource() == registerAthleteView.returnButton) {
            registerAthleteView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
    public void addAthlete(Athlete athlete, Team athleteTeam) {
        athleteTeam.addMember(athlete);
    }
    public void clearFields(){
        registerAthleteView.firstNameField.setText("");
        registerAthleteView.lastNameField.setText("");
        registerAthleteView.dobField.setText("");
        registerAthleteView.emailField.setText("");
        registerAthleteView.idField.setText("");
        registerAthleteView.sportDropDown.setSelectedIndex(0);
        registerAthleteView.class1Field.setText("");
        registerAthleteView.class2Field.setText("");
        registerAthleteView.class3Field.setText("");
    }
}
