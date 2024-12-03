package View;

import Controller.AbsencesController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AbsencesView extends JFrame {
    private JLabel manageTeamLabel, limitAbsencesLabel, sportLabel, athletesAbsencesLabel, dateLabel, classLabel, generalInfoLabel, athleteReportLabel;
    public JTextField limitAbsencesField, dateField;
    public JComboBox<String> sportDropDown, athleteDropDown, classDropDown;
    public JButton changeLimitButton, insertAbsenceButton, removeAbsenceButton, checkAbsencesButton, reportButton, returnButton;

    public AbsencesView(WelcomeView welcomeView) {
        super("Absence Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        add(AbsencesComponents());

        AbsencesController controller = new AbsencesController(this, welcomeView);

        // Add action listeners for clear and submit
        changeLimitButton.addActionListener(controller);
        insertAbsenceButton.addActionListener(controller);
        removeAbsenceButton.addActionListener(controller);
        checkAbsencesButton.addActionListener(controller);
        reportButton.addActionListener(controller);
        returnButton.addActionListener(controller);
        setVisible(true);
    }
    public JPanel AbsencesComponents(){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20,20));
        panel.setLayout(null);

        // Manage Team Absences Section
        manageTeamLabel = new JLabel("Manage Team Absences");
        manageTeamLabel.setBounds(50, 20, 200, 20);
        panel.add(manageTeamLabel);

        sportLabel = new JLabel("Sport");
        sportLabel.setBounds(50, 50, 100, 20);
        panel.add(sportLabel);

        sportDropDown = new JComboBox<>(new String[]{"Sport 1", "Sport 2", "Sport 3"});
        sportDropDown.setBounds(50, 70, 150, 25);
        panel.add(sportDropDown);

        limitAbsencesLabel = new JLabel("Limit of Absences");
        limitAbsencesLabel.setBounds(250, 50, 150, 20);
        panel.add(limitAbsencesLabel);

        limitAbsencesField = new JTextField();
        limitAbsencesField.setBounds(250, 70, 150, 25);
        panel.add(limitAbsencesField);

        changeLimitButton = new JButton("Change Limit");
        changeLimitButton.setBounds(450, 70, 150, 30);
        panel.add(changeLimitButton);

        // Athletes Absences Section
        athletesAbsencesLabel = new JLabel("Athletes Absences");
        athletesAbsencesLabel.setBounds(50, 120, 200, 20);
        panel.add(athletesAbsencesLabel);

        sportLabel = new JLabel("Sport");
        sportLabel.setBounds(50, 150, 100, 20);
        panel.add(sportLabel);

        sportDropDown = new JComboBox<>(new String[]{"Sport 1", "Sport 2", "Sport 3"});
        sportDropDown.setBounds(50, 170, 150, 25);
        panel.add(sportDropDown);

        athleteReportLabel = new JLabel("Athlete");
        athleteReportLabel.setBounds(250, 150, 100, 20);
        panel.add(athleteReportLabel);

        athleteDropDown = new JComboBox<>(new String[]{"Athlete 1", "Athlete 2", "Athlete 3"});
        athleteDropDown.setBounds(250, 170, 150, 25);
        panel.add(athleteDropDown);

        dateLabel = new JLabel("Date (dd/mm/yyyy)");
        dateLabel.setBounds(450, 150, 150, 20);
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(450, 170, 150, 25);
        panel.add(dateField);

        classLabel = new JLabel("Class");
        classLabel.setBounds(650, 150, 100, 20);
        panel.add(classLabel);

        classDropDown = new JComboBox<>(new String[]{"Class 1", "Class 2", "Class 3"});
        classDropDown.setBounds(650, 170, 150, 25);
        panel.add(classDropDown);

        insertAbsenceButton = new JButton("Insert Absence");
        insertAbsenceButton.setBounds(50, 210, 150, 30);
        panel.add(insertAbsenceButton);

        removeAbsenceButton = new JButton("Remove Absence");
        removeAbsenceButton.setBounds(250, 210, 150, 30);
        panel.add(removeAbsenceButton);

        // General Information Section
        generalInfoLabel = new JLabel("General Information");
        generalInfoLabel.setBounds(50, 260, 200, 20);
        panel.add(generalInfoLabel);

        sportLabel = new JLabel("Sport");
        sportLabel.setBounds(50, 290, 100, 20);
        panel.add(sportLabel);

        sportDropDown = new JComboBox<>(new String[]{"Sport 1", "Sport 2", "Sport 3"});
        sportDropDown.setBounds(50, 310, 150, 25);
        panel.add(sportDropDown);

        athleteReportLabel = new JLabel("Athlete");
        athleteReportLabel.setBounds(250, 290, 100, 20);
        panel.add(athleteReportLabel);

        athleteDropDown = new JComboBox<>(new String[]{"Athlete 1", "Athlete 2", "Athlete 3"});
        athleteDropDown.setBounds(250, 310, 150, 25);
        panel.add(athleteDropDown);

        checkAbsencesButton = new JButton("Check Absences");
        checkAbsencesButton.setBounds(50, 350, 150, 30);
        panel.add(checkAbsencesButton);

        reportButton = new JButton("Generate Report");
        reportButton.setBounds(250, 350, 150, 30);
        panel.add(reportButton);

        // Return button
        returnButton = new JButton("Menu");
        returnButton.setBounds(600, 500, 50,20);
        panel.add(returnButton);

        return panel;
    }
}

