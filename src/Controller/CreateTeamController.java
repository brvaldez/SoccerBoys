package Controller;

import Model.Team;
import View.CreateTeamView;
import View.WelcomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeamController implements ActionListener {
    private CreateTeamView createTeamView;
    private WelcomeView welcomeView;
    public CreateTeamController(CreateTeamView createTeamView, WelcomeView welcomeView) {
        this.createTeamView = createTeamView;
        this.welcomeView =  welcomeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTeamView.addTeamButton) {
            String sport = createTeamView.sportField.getText().trim();
            String coach = createTeamView.coachField.getText().trim();
            int absences = Integer.parseInt(createTeamView.absencesField.getText().trim());
            Team team = new Team(sport, absences);
        }
        else if (e.getSource() == createTeamView.changeCoachButton){
            String sport = createTeamView.sportDropdown.getSelectedItem().toString();
            String newcoach = createTeamView.newCoachField.getText().trim();
            //changeCoach(sport, newcoach);
        }
        else if (e.getSource() == createTeamView.removeTeamButton){
            String sport = createTeamView.removeSportDropdown.getSelectedItem().toString();
            //removeTeam(sport);
        }
        else if(e.getSource() == createTeamView.returnButton){
            createTeamView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
}
