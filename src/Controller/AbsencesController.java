package Controller;

import Model.Athlete;
import Model.Component;
import View.AbsencesView;
import View.WelcomeView;
import Model.Team;
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
            int absences =  Integer.parseInt(absencesView.limitAbsencesField.getText().trim());
            changeTeamAbsencesLimit(sport, absences);
        }
        else if (e.getSource() == absencesView.insertAbsenceButton) {
            Athlete athlete = (Athlete) absencesView.athleteDropDown.getSelectedItem();
            String date = absencesView.dateField.getText();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            insertAbsence(athlete, date, classMissed);
        }
        else if (e.getSource() == absencesView.removeAbsenceButton) {
            Athlete athlete = (Athlete)absencesView.athleteDropDown.getSelectedItem();
            String date = absencesView.dateField.getText();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            removeAbsence(athlete, date,classMissed);
        }
        else if (e.getSource() == absencesView.checkAbsencesButton) {
            Team sport = (Team)absencesView.sportDropDown.getSelectedItem();
            Athlete athlete = (Athlete)absencesView.athleteDropDown.getSelectedItem();
            checkAbsences(sport, athlete);
        }
        else if (e.getSource() == absencesView.reportButton) {
            Team sport = (Team)absencesView.sportDropDown.getSelectedItem();
            Athlete athlete = (Athlete)absencesView.athleteDropDown.getSelectedItem();
            createReport(sport, athlete);
        }
        else if(e.getSource() == absencesView.returnButton){
            absencesView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
    // Update the athletes list in the view based on the selected team
    public void updateAthletesList(Team selectedTeam) {
        List<Component> teamAthletes = selectedTeam.getMembers(); // Assuming `getMembers` returns the list of athletes
        absencesView.updateAthletesList(teamAthletes);
    }

    // Update the class list for the selected athlete
    public void updateClassList(Athlete selectedAthlete) {
        List<String> classes = new ArrayList<>(selectedAthlete.getClasses().keySet());
        absencesView.updateClassDropDown(classes);
    }
        public void changeTeamAbsencesLimit(Team sport, int absences){
        Team team; // Implement this method to fetch the appropriate team
        team = sport;
        if (team != null) {
            team.setAbsencesLimit(absences);
        }
    }
    public void insertAbsence(Athlete athlete, String date, String classMissed){
        Map<String, Integer> classes = athlete.getClasses(); // Assuming getClasses() returns the HashMap
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        classes.put(classMissed, currentAbsences + 1);
    }
    public void removeAbsence(Athlete athlete, String date, String classMissed){}
    public void checkAbsences(Team sport, Athlete athlete){}
    public void createReport(Team sport, Athlete athlete){}
}
