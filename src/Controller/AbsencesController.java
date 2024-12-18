package Controller;

import Model.*;
import View.AbsencesView;
import View.CheckAbsenceView;
import View.PlaceHolderAthlete;
import View.WelcomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Model.CSVAdapter.createCSV;

/**
 * Class: AbsencesController
 * Purpose: This class serves as the controller for managing absences in the application.
 *          It handles user interactions and updates the view and model accordingly.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class AbsencesController implements ActionListener {
    private AbsencesView absencesView;
    private WelcomeView welcomeView;

    /**
     * Constructor for AbsencesController.
     * @param absencesView the view for managing absences.
     * @param welcomeView the welcome screen view.
     */
    public AbsencesController(AbsencesView absencesView, WelcomeView welcomeView) {
        this.absencesView = absencesView;
        this.welcomeView = welcomeView;
    }

    /**
     * Handles user actions from the view.
     * @param e the action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == absencesView.sportDropDown2) {
            // Update athletes list when a team is selected
            Team selectedTeam = (Team) absencesView.sportDropDown2.getSelectedItem();
            //absencesView.enableAbsenceControls();
            updateAthletesList(selectedTeam);
        } else if (e.getSource() == absencesView.athleteDropDown) {
            // Update class dropdown when an athlete is selected
            Athlete selectedAthlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            if (selectedAthlete instanceof PlaceHolderAthlete) {
                absencesView.classDropDown.setEnabled(false);
                absencesView.classDropDown.removeAllItems();
            } else {
                absencesView.classDropDown.setEnabled(true);
                absencesView.updateClassDropDown(selectedAthlete);}
        } else if (e.getSource() == absencesView.changeLimitButton) {
            // Change the absence limit for the selected team
            Team sport = (Team) absencesView.sportDropDown2.getSelectedItem();
            String absencesText = absencesView.limitAbsencesField.getText().trim();
            if (sport == null || absencesText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a sport and enter a limit!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int absences;
            try {
                absences = Integer.parseInt(absencesText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Absence limit must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            changeTeamAbsencesLimit(sport, absences);
        } else if (e.getSource() == absencesView.insertAbsenceButton) {
            // Insert an absence for the selected athlete and class
            Athlete athlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            if (athlete instanceof PlaceHolderAthlete) {
                JOptionPane.showMessageDialog(null, "Please select an athlete!!!", "Error", JOptionPane.ERROR_MESSAGE);
            } else{
            JOptionPane.showMessageDialog(null, "Absence inserted!", "Information", JOptionPane.INFORMATION_MESSAGE);}
            insertAbsence(athlete, classMissed);
        } else if (e.getSource() == absencesView.removeAbsenceButton) {
            // Remove an absence for the selected athlete and class
            Athlete athlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Absence removed!", "Information", JOptionPane.INFORMATION_MESSAGE);
            removeAbsence(athlete, classMissed);
        } else if (e.getSource() == absencesView.reportButton) {
            // Generate and display a report
            Team team = (Team) absencesView.sportDropDown2.getSelectedItem();
            createReport(team);
        } else if (e.getSource() == absencesView.returnButton) {
            // Return to the welcome view
            absencesView.setVisible(false);
            welcomeView.setVisible(true);
        } else if (e.getSource() == absencesView.checkAbsencesButton) {
            // Open the view to check absences for a sport and athlete
            Team sport = (Team) absencesView.sportDropDown2.getSelectedItem();
            Athlete athlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            if (athlete instanceof PlaceHolderAthlete) {
                // Call checkAbsencesView with the team only
                checkAbsencesView(sport, null);
            } else if (athlete != null) {
                // Call checkAbsencesView with both team and athlete
                checkAbsencesView(sport, athlete);
            }
        }
    }

    /**
     * Updates the list of athletes in the dropdown based on the selected team.
     * @param selectedTeam the selected team.
     */
    public void updateAthletesList(Team selectedTeam) {
        if(selectedTeam == null) {
            return;
        }
        selectedTeam.getMembers(); // Assuming `getMembers` returns the list of athletes
        System.out.println(selectedTeam.getMembers());
        absencesView.updateAthletesDropDown(selectedTeam.getMembers());
    }

    /**
     * Changes the absence limit for a specific team.
     * @param sport the selected team.
     * @param absences the new absence limit.
     */
    public void changeTeamAbsencesLimit(Team sport, int absences) {
        Team team = sport; // Assigning the selected team
        if (team != null) {
            team.setAbsencesLimit(absences);
        }
    }

    /**
     * Inserts an absence for an athlete in a specific class.
     * @param athlete the selected athlete.
     * @param classMissed the class missed by the athlete.
     */
    public void insertAbsence(Athlete athlete, String classMissed) {
        Map<String, Integer> classes = athlete.getClasses(); // Assuming getClasses() returns the HashMap
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        currentAbsences += 1;
        classes.put(classMissed, currentAbsences);
        System.out.println("Current absences: " + currentAbsences);
        if (currentAbsences >= Team.absencesLimit) {
            Team.notifyObservers(athlete.getName() + " has reached the limit of absences in " + classMissed);
        }
    }

    /**
     * Removes an absence for an athlete in a specific class.
     * @param athlete the selected athlete.
     * @param classMissed the class to update.
     */
    public void removeAbsence(Athlete athlete, String classMissed) {
        Map<String, Integer> classes = athlete.getClasses(); // Assuming getClasses() returns the HashMap
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        if (currentAbsences > 0) {
            currentAbsences -= 1;
            classes.put(classMissed, currentAbsences);
        } else {
            JOptionPane.showMessageDialog(null, "0 absences on this class", "Absence Alert", JOptionPane.WARNING_MESSAGE);
        }
        System.out.println("Current absences: " + currentAbsences);
    }

    /**
     * Opens the Check Absence View for a specific sport and athlete.
     * @param sport the selected sport.
     * @param athlete the selected athlete.
     */
    public void checkAbsencesView(Team sport, Athlete athlete) {
        new CheckAbsenceView(sport, athlete);
    }

    /**
     * Creates a CSV report of the absences data.
     */
    public void createReport(Team team) {
        List<String[]> data = team.collectDataForCSV();
        createCSV(data, true);
    }
}
