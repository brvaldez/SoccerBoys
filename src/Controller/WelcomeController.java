package Controller;

import Model.Team;
import View.RegisterAthleteView;
import View.WelcomeView;
import View.CreateTeamView;
import View.AbsencesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WelcomeController implements ActionListener {
    private WelcomeView welcomeView;
    private List<Team> teams;
    public WelcomeController(WelcomeView welcomeView, List<Team> teams) {
        this.welcomeView = welcomeView;
        this.teams = teams;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the submit button is clicked
        if (e.getSource() == welcomeView.createTeamButton) {
            new CreateTeamView(welcomeView);
            welcomeView.setVisible(false);  // Hide the welcome window
        }
        else if(e.getSource() == welcomeView.registerAthleteButton){
            new RegisterAthleteView(welcomeView, teams);
            welcomeView.setVisible(false);
        }
        else if (e.getSource() == welcomeView.absencesButton){
            if (teams == null) {
                teams = new ArrayList<>();
                new AbsencesView(welcomeView, teams);
            }
            welcomeView.setVisible(false);
        }
    }
}
