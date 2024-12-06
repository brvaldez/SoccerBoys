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

public class WelcomeController implements ActionListener {
    private WelcomeView welcomeView;
    private List<Component> teams;
    private List<Coach> coaches;
    private CreateTeamView createTeamView;
    private RegisterAthleteView registerAthleteView;
    private AbsencesView absencesView;

    public WelcomeController(WelcomeView welcomeView,AbsencesView absencesView, RegisterAthleteView registerAthleteView, List<Component> teams, List<Coach> coaches) {
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
        this.registerAthleteView = registerAthleteView;
        this.teams = Team.getMembers();
        this.coaches = coaches;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == welcomeView.createTeamButton) {
            if (createTeamView == null) {
                createTeamView = new CreateTeamView(welcomeView, absencesView, registerAthleteView, coaches);
            } else {
                createTeamView.setVisible(true);
            }
            welcomeView.setVisible(false);
        } else if (e.getSource() == welcomeView.registerAthleteButton) {
            if (registerAthleteView == null) {
                registerAthleteView = new RegisterAthleteView(welcomeView, absencesView);
            } else {
                registerAthleteView.setVisible(true);
            }
            welcomeView.setVisible(false);
        } else if (e.getSource() == welcomeView.absencesButton) {
            if (absencesView == null) {
                absencesView = new AbsencesView(welcomeView, teams);
            } else {
                absencesView.setVisible(true);
            }
            welcomeView.setVisible(false);
        }
    }
}

