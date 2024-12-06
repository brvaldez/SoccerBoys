package Controller;

import Model.Coach;
import Model.Team;
import View.CreateTeamView;
import View.RegisterAthleteView;
import View.WelcomeView;
import View.AbsencesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeamController implements ActionListener {
    private CreateTeamView createTeamView;
    private WelcomeView welcomeView;
    private RegisterAthleteView registerAthleteView;
    private AbsencesView absencesView;

    public CreateTeamController(CreateTeamView createTeamView, WelcomeView welcomeView, AbsencesView absencesView) {
        this.createTeamView = createTeamView;
        this.welcomeView = welcomeView;
        this.absencesView = absencesView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTeamView.addTeamButton) {
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
            System.out.println("Sport: " + sport);
            System.out.println("Coach: " + coach.getName());
            System.out.println("Absences: " + absences);
            System.out.println(team.getMembers());
        } else if (e.getSource() == createTeamView.changeCoachButton) {
            Team team = (Team) createTeamView.sportDropdown2.getSelectedItem();
            Coach newCoach = (Coach) createTeamView.newCoachDropDown.getSelectedItem();
            changeCoach(team, newCoach);
        } else if (e.getSource() == createTeamView.removeTeamButton) {
            Team sport = (Team) createTeamView.removeSportDropdown.getSelectedItem();
            removeTeam(sport);
        } else if (e.getSource() == createTeamView.returnButton) {
            createTeamView.setVisible(false);
            welcomeView.setVisible(true);
        } else if (e.getSource() == createTeamView.sportDropdown2) {
            Team selectedTeam = (Team) createTeamView.sportDropdown2.getSelectedItem();
            if (selectedTeam != null) {
                createTeamView.updateNewCoachDropDown(selectedTeam);
            }
        }
    }

    public void addTeam(Team team) {
        welcomeView.getTeams().add(team);
        createTeamView.updateTeamsDropDown();
        absencesView.updateTeamsDropDown();
        registerAthleteView.updateTeamsDropDown();
    }

    public void changeCoach(Team sport, Coach newCoach) {
        sport.setCoach(newCoach);
    }

    public void removeTeam(Team team) {
        welcomeView.getTeams().remove(team);
        createTeamView.updateTeamsDropDown();
    }
}