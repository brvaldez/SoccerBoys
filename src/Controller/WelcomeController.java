package Controller;

import View.RegisterAthleteView;
import View.WelcomeView;
import View.CreateTeamView;
import View.AbsencesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeController implements ActionListener {
    private WelcomeView welcomeView;

    public WelcomeController(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the submit button is clicked
        if (e.getSource() == welcomeView.createTeamButton) {
            new CreateTeamView(welcomeView);
            welcomeView.setVisible(false);  // Hide the welcome window
        }
        else if(e.getSource() == welcomeView.registerAthleteButton){
            new RegisterAthleteView(welcomeView);
            welcomeView.setVisible(false);
        }
        else if (e.getSource() == welcomeView.absencesButton){
            new AbsencesView(welcomeView);
            welcomeView.setVisible(false);
        }
    }
}
