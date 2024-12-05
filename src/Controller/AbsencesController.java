package Controller;

import Model.Athlete;
import Model.Component;
import View.AbsencesView;
import View.WelcomeView;
import Model.Team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbsencesController implements ActionListener {
    private AbsencesView absencesView;
    private WelcomeView welcomeView;

    public AbsencesController(AbsencesView absencesView, WelcomeView welcomeView) {
        this.absencesView = absencesView;
        this.welcomeView = welcomeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == absencesView.sportDropDown) {
            Team selectedTeam = (Team) absencesView.sportDropDown.getSelectedItem();
            updateAthletesList(selectedTeam);
        }
        else if (e.getSource() == absencesView.athleteDropDown) {
            Athlete selectedAthlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            updateClassList(selectedAthlete);
        }
        else if (e.getSource() == absencesView.changeLimitButton) {
            Team sport = (Team) absencesView.sportDropDown.getSelectedItem();
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
        }
        else if (e.getSource() == absencesView.insertAbsenceButton) {
            Athlete athlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString().trim();

            if (athlete == null || classMissed.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select an athlete and a class!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            insertAbsence(athlete, classMissed);
        }
    }

    public void updateAthletesList(Team selectedTeam) {
        List<Component> teamAthletes = selectedTeam.getMembers();
        absencesView.updateAthletesList(teamAthletes);
    }

    public void updateClassList(Athlete selectedAthlete) {
        List<String> classes = new ArrayList<>(selectedAthlete.getClasses().keySet());
        absencesView.updateClassDropDown(classes);
    }

    public void changeTeamAbsencesLimit(Team sport, int absences) {
        sport.setAbsencesLimit(absences);
    }

    public void insertAbsence(Athlete athlete, String classMissed) {
        Map<String, Integer> classes = athlete.getClasses();
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        classes.put(classMissed, currentAbsences + 1);
    }
}
