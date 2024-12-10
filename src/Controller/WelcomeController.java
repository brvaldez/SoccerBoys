package Controller;

import Model.Component;
import Model.Coach;
import Model.Team;
import View.RegisterAthleteView;
import View.WelcomeView;
import View.CreateTeamView;
import View.AbsencesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class: WelcomeController
 * Purpose: Manages the navigation and logic from the WelcomeView to other views
 *          such as CreateTeamView, RegisterAthleteView, and AbsencesView.
 * Authors: Bruno Valdez & Manuel Rodriguez
 */
public class WelcomeController implements ActionListener {
    private WelcomeView welcomeView;
    //private List<Component> teams; // List of all teams
    private List<Coach> coaches;  // List of all coaches
    private CreateTeamView createTeamView;
    private RegisterAthleteView registerAthleteView;
    private AbsencesView absencesView;

    /**
     * Constructor for WelcomeController.
     * @param welcomeView the main welcome screen view.
     * @param absencesView the view for managing absences.
     * @param registerAthleteView the view for registering athletes.
     * @param teams the list of teams available.
     * @param coaches the list of coaches available.
     */
    public WelcomeController(WelcomeView welcomeView, AbsencesView absencesView, RegisterAthleteView registerAthleteView, List<Coach> coaches) {
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
        this.registerAthleteView = registerAthleteView;
        //this.teams = Team.getRootTeam().getMembers();
        this.coaches = coaches; // Store the list of coaches
    }

    /**
     * Handles user actions from the welcome view.
     * @param e the action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == welcomeView.createTeamButton) {
            // Navigate to CreateTeamView
            if (createTeamView == null) {
                // Initialize CreateTeamView if not already created
                createTeamView = new CreateTeamView(welcomeView, absencesView, registerAthleteView, coaches);
            } else {
                // Make the view visible
                createTeamView.setVisible(true);
            }
            welcomeView.setVisible(false); // Hide the welcome view
        } else if (e.getSource() == welcomeView.registerAthleteButton) {
            // Navigate to RegisterAthleteView
            if (registerAthleteView == null) {
                // Initialize RegisterAthleteView if not already created
                registerAthleteView = new RegisterAthleteView(welcomeView, absencesView);
            } else {
                // Make the view visible
                registerAthleteView.setVisible(true);
            }
            welcomeView.setVisible(false); // Hide the welcome view
        } else if (e.getSource() == welcomeView.absencesButton) {
            // Navigate to AbsencesView
            if (absencesView == null) {
                // Initialize AbsencesView if not already created
                absencesView = new AbsencesView(welcomeView);
            } else {
                // Make the view visible
                absencesView.setVisible(true);
            }
            welcomeView.setVisible(false); // Hide the welcome view
        }
    }
}
