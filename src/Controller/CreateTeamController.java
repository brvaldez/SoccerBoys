package Controller;

import Model.Coach;
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
            Coach coach = (Coach)createTeamView.coachDropDown.getSelectedItem();
            int absences = Integer.parseInt(createTeamView.absencesField.getText().trim());
            Team team = new Team(sport, coach, absences);
            addTeam(team);
            System.out.println("Sport: " + sport);
            System.out.println("Coach: " + coach.getName());
            System.out.println("Absences: " + absences);
        }
        else if (e.getSource() == createTeamView.changeCoachButton){
            Team team = (Team)createTeamView.sportDropdown.getSelectedItem();
            Coach newcoach = (Coach)createTeamView.newCoachDropDown.getSelectedItem();
            changeCoach(team, newcoach);
        }
        else if (e.getSource() == createTeamView.removeTeamButton){
            Team sport = (Team)createTeamView.removeSportDropdown.getSelectedItem();
            removeTeam(sport);
        }
        else if(e.getSource() == createTeamView.returnButton){
            createTeamView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
    public void addTeam(Team team){
        Team.addMember(team);
    }
    public void changeCoach(Team sport, Coach newcoach){
        sport.setCoach(newcoach);
    }
    public void removeTeam(Team team){
        Team.removeMember(team);
    }
}
