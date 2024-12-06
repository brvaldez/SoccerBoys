package Controller;

import Model.Athlete;
import Model.Component;
import View.AbsencesView;
import View.WelcomeView;
import Model.Team;
import Model.Coach;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
            System.out.println("Selected athlete: " + selectedAthlete);
            absencesView.updateClassDropDown(selectedAthlete);
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
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            if (athlete == null || classMissed.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select an athlete and a class!", "Error", JOptionPane.ERROR_MESSAGE);}
            insertAbsence(athlete, classMissed);
        }
        else if (e.getSource() == absencesView.removeAbsenceButton) {
            Athlete athlete = (Athlete)absencesView.athleteDropDown.getSelectedItem();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            removeAbsence(athlete,classMissed);
        }
        else if (e.getSource() == absencesView.checkAbsencesButton) {
            Team sport = (Team)absencesView.sportDropDown.getSelectedItem();
            Athlete athlete = (Athlete)absencesView.athleteDropDown.getSelectedItem();
            checkAbsences(sport);
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
        absencesView.updateAthletesDropDown(teamAthletes);
    }

    public void changeTeamAbsencesLimit(Team sport, int absences){
        Team team; // Implement this method to fetch the appropriate team
        team = sport;
        if (team != null) {
            team.setAbsencesLimit(absences);
        }
    }
    public void insertAbsence(Athlete athlete, String classMissed){
        Map<String, Integer> classes = athlete.getClasses(); // Assuming getClasses() returns the HashMap
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        currentAbsences += 1;
        classes.put(classMissed, currentAbsences);
        System.out.println("Current absences: " + currentAbsences);
        if (currentAbsences >= Team.absencesLimit){
            Team.notifyObservers(athlete.getName() + " has reached the limit of absences in " + classMissed);
        }
    }
    public void removeAbsence(Athlete athlete, String classMissed){
        Map<String, Integer> classes = athlete.getClasses(); // Assuming getClasses() returns the HashMap
        int currentAbsences = classes.getOrDefault(classMissed, 0);
        if (currentAbsences > 0) {
            currentAbsences -= 1;
            classes.put(classMissed, currentAbsences);
        } else {
            JOptionPane.showMessageDialog(null, "0 absences on this class", "Absence Alert", JOptionPane.WARNING_MESSAGE);
        }
        System.out.println("Current absences: " + currentAbsences);
    }
    public void checkAbsences(Team sport){}
    public void createReport(Team sport, Athlete athlete){}
}
