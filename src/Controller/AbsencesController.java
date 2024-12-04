package Controller;

import Model.Athlete;
import Model.Component;
import View.AbsencesView;
import View.RegisterAthleteView;
import View.WelcomeView;
import Model.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbsencesController implements ActionListener {
    private AbsencesView absencesView;
    private WelcomeView welcomeView;
    public AbsencesController(AbsencesView absencesView, WelcomeView welcomeView) {
        this.absencesView = absencesView;
        this.welcomeView = welcomeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the register athlete button is clicked
        if (e.getSource() == absencesView.changeLimitButton) {
            String sport = absencesView.sportDropDown.getSelectedItem().toString();
            int absences =  Integer.parseInt(absencesView.limitAbsencesField.getText().trim());
            changeTeamAbsencesLimit(absences);
        }
        else if (e.getSource() == absencesView.insertAbsenceButton) {
            String sport = absencesView.sportDropDown.getSelectedItem().toString();
            String athlete = absencesView.athleteDropDown.getSelectedItem().toString();
            String date = absencesView.dateField.getText();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
            insertAbsence(athlete, date, classMissed);
        }
        else if (e.getSource() == absencesView.removeAbsenceButton) {
            String sport = absencesView.sportDropDown.getSelectedItem().toString();
            String athlete = absencesView.athleteDropDown.getSelectedItem().toString();
            String date = absencesView.dateField.getText();
            String classMissed = absencesView.classDropDown.getSelectedItem().toString();
        }
        else if (e.getSource() == absencesView.checkAbsencesButton) {
            String sport = absencesView.sportDropDown.getSelectedItem().toString();
            String athlete = absencesView.athleteDropDown.getSelectedItem().toString();
        }
        else if (e.getSource() == absencesView.reportButton) {
            String sport = absencesView.sportDropDown.getSelectedItem().toString();
            String athlete = absencesView.athleteDropDown.getSelectedItem().toString();
        }
        else if(e.getSource() == absencesView.returnButton){
            absencesView.setVisible(false);
            welcomeView.setVisible(true);
        }
    }
    public void changeTeamAbsencesLimit(int absences){
        Team.setTeamAbsencesLimit(absences);
    }

    public void insertAbsence(athlete, date, classMissed){
        for(class class: classes){
            if classMissed == class
        }
    }
}
