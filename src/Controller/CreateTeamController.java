package Controller;

import Model.Coach;
import Model.Team;
import View.CreateTeamView;
import View.RegisterAthleteView;
import View.WelcomeView;
import View.AbsencesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class: CreateTeamController
 * Purpose: This class handles the creation and management of teams in the application.
 *          It manages user interactions related to adding, removing, and updating teams.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class CreateTeamController implements ActionListener {
    private CreateTeamView createTeamView;
    private WelcomeView welcomeView;
    private RegisterAthleteView registerAthleteView;
    private AbsencesView absencesView;

    /**
     * Constructor for CreateTeamController.
     * @param createTeamView the view for creating and managing teams.
     * @param welcomeView the welcome screen view.
     * @param absencesView the view for managing absences.
     * @param registerAthleteView the view for registering athletes.
     */
    public CreateTeamController(CreateTeamView createTeamView, WelcomeView welcomeView, AbsencesView absencesView, RegisterAthleteView registerAthleteView) {
        this.createTeamView = createTeamView;
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
        this.registerAthleteView = registerAthleteView;
    }

    /**
     * Handles user actions from the view.
     * @param e the action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTeamView.addTeamButton) {
            // Handle adding a new team
            String sport = createTeamView.sportField.getText().trim();
            Coach coach = (Coach) createTeamView.coachDropDown.getSelectedItem();
            String absencesText = createTeamView.absencesField.getText().trim();

            if (sport.isEmpty() || coach == null || absencesText.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled out!");
            }

            int absences = Integer.parseInt(absencesText);
            if (absences < 0) {
                throw new IllegalArgumentException("Absences must be a positive number!");
            }

            Team team = new Team(sport, coach, absences);
            addTeam(team);

        } else if (e.getSource() == createTeamView.changeCoachButton) {
            // Handle changing the coach for an existing team
            Team team = (Team) createTeamView.sportDropdown2.getSelectedItem();
            Coach newCoach = (Coach) createTeamView.newCoachDropDown.getSelectedItem();
            team.setCoach(newCoach);
        } else if (e.getSource() == createTeamView.removeTeamButton) {
            // Handle removing a team
            Team sport = (Team) createTeamView.removeSportDropdown.getSelectedItem();
            if (sport == null) {
                JOptionPane.showMessageDialog(absencesView, "Please select a valid athlete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            removeTeam(sport);
        } else if (e.getSource() == createTeamView.returnButton) {
            // Handle returning to the welcome screen
            createTeamView.setVisible(false);
            welcomeView.setVisible(true);
        } else if (e.getSource() == createTeamView.sportDropdown2) {
            // Handle updating the coach dropdown when a team is selected
            Team selectedTeam = (Team) createTeamView.sportDropdown2.getSelectedItem();
            if (selectedTeam != null) {
                createTeamView.updateNewCoachDropDown(selectedTeam);
            }
        }
    }

    /**
     * Adds a new team to the application.
     * @param team the team to be added.
     */
    public void addTeam(Team team) {
        Team.addMember(team); // Add the team to the model
        createTeamView.updateTeamsDropDown(); // Update the dropdown in the team creation view
        absencesView.updateTeamsDropDown(); // Update the dropdown in the absences view
        registerAthleteView.updateTeamsDropDown(); // Update the dropdown in the athlete registration view
    }

    /**
     * Changes the coach of a specific team.
     * @param sport the team whose coach needs to be changed.
     * @param newCoach the new coach for the team.
     */
    public void changeCoach(Team sport, Coach newCoach) {
        sport.setCoach(newCoach); // Update the team's coach
    }

    /**
     * Removes a team from the application.
     * @param team the team to be removed.
     */
    public void removeTeam(Team team) {
        Team.removeMember(team); // Remove the team from the model
        createTeamView.updateTeamsDropDown(); // Update the dropdown in the team creation view
        absencesView.updateTeamsDropDown(); // Update the dropdown in the absences view
        registerAthleteView.updateTeamsDropDown(); // Update the dropdown in the athlete registration view
    }
}
