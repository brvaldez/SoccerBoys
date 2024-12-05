package Controller;

import Model.Coach;
import Model.Team;
import View.CreateTeamView;
import View.WelcomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeamController implements ActionListener {
    private CreateTeamView createTeamView;
    private WelcomeView welcomeView;

    public CreateTeamController(CreateTeamView createTeamView, WelcomeView welcomeView) {
        this.createTeamView = createTeamView;
        this.welcomeView = welcomeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTeamView.addTeamButton) {
            String sport = createTeamView.sportField.getText().trim();
            Coach coach = (Coach) createTeamView.coachDropDown.getSelectedItem();
            String absencesText = createTeamView.absencesField.getText().trim();

            // Validate fields
            if (sport.isEmpty() || coach == null || absencesText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int absences;
            try {
                absences = Integer.parseInt(absencesText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Absences must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Team team = new Team(sport, coach, absences);
            addTeam(team);
            System.out.println("Sport: " + sport);
            System.out.println("Coach: " + coach.getName());
            System.out.println("Absences: " + absences);
        }
        else if (e.getSource() == createTeamView.changeCoachButton) {
            Team team = (Team) createTeamView.sportDropdown.getSelectedItem();
            Coach newCoach = (Coach) createTeamView.newCoachDropDown.getSelectedItem();
            changeCoach(team, newCoach);
        }
        else if (e.getSource() == createTeamView.removeTeamButton) {
            Team sport = (Team) createTeamView.removeSportDropdown.getSelectedItem();
            removeTeam(sport);
        }
        else if (e.getSource() == createTeamView.returnButton) {
            createTeamView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }

    public void addTeam(Team team) {
        Team.addMember(team);
    }

    public void changeCoach(Team sport, Coach newCoach) {
        sport.setCoach(newCoach);
    }

    public void removeTeam(Team team) {
        Team.removeMember(team);
    }
}
