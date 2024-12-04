package Controller;

import Model.Athlete;
import View.RegisterAthleteView;
import View.WelcomeView;
import Model.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class RegisterAthleteController implements ActionListener {
    private RegisterAthleteView registerAthleteView;
    private WelcomeView welcomeView;
    public RegisterAthleteController(RegisterAthleteView registerAthleteView, WelcomeView welcomeView) {
        this.registerAthleteView = registerAthleteView;
        this.welcomeView = welcomeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the register athlete button is clicked
        if (e.getSource() == registerAthleteView.registerButton) {
            // Collect the data from the form using getter methods
            String firstName = registerAthleteView.firstNameField.getText().trim();
            String lastName = registerAthleteView.lastNameField.getText().trim();
            String dob = registerAthleteView.dobField.getText().trim();
            String email = registerAthleteView.emailField.getText().trim();
            String id = registerAthleteView.idField.getText().trim();
            String sport = registerAthleteView.sportDropdown.getSelectedItem().toString();
            String class1 = registerAthleteView.class1Field.getText().trim();
            String class2 = registerAthleteView.class2Field.getText().trim();
            String class3 = registerAthleteView.class3Field.getText().trim();

            String name = firstName + lastName;

            // Create a map to store classes and their absences
            Map<String, Integer> classAbsences = new HashMap<>();

            // Add non-empty classes to the map, starting with 0 absences
            classAbsences.put(class1, 0);
            classAbsences.put(class2, 0);
            classAbsences.put(class3, 0);

            // Create the StudentAthlete object and pass the map of classes and absences
            Athlete athlete = new Athlete(name, dob, email, id, sport, classAbsences);

            Team.members.add(athlete);

            registerAthleteView.firstNameField.setText("");
            registerAthleteView.lastNameField.setText("");
            registerAthleteView.dobField.setText("");
            registerAthleteView.emailField.setText("");
            registerAthleteView.idField.setText("");
            registerAthleteView.sportDropdown.setSelectedIndex(0);
            registerAthleteView.class1Field.setText("");
            registerAthleteView.class2Field.setText("");
            registerAthleteView.class3Field.setText("");
        }
        else if (e.getSource() == registerAthleteView.clearButton){
            registerAthleteView.firstNameField.setText("");
            registerAthleteView.lastNameField.setText("");
            registerAthleteView.dobField.setText("");
            registerAthleteView.emailField.setText("");
            registerAthleteView.idField.setText("");
            registerAthleteView.sportDropdown.setSelectedIndex(0);
            registerAthleteView.class1Field.setText("");
            registerAthleteView.class2Field.setText("");
            registerAthleteView.class3Field.setText("");
        }
        else if(e.getSource() == registerAthleteView.returnButton){
            registerAthleteView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
}
