package View;

import Controller.WelcomeController;
import Model.Coach;
import Model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class WelcomeView extends JFrame {

    // Create buttons for create a team, register an athlete, absences
    public JButton createTeamButton, registerAthleteButton, absencesButton;
    public WelcomeView(){
        super("Welcome Coach!!!");

        List<Team> teams = new ArrayList<>();
        List<Coach> coaches = new ArrayList<>();
        // Listeners
        WelcomeController controller = new WelcomeController(this, teams, coaches);

        // Window features
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 150);

        // Add form panel to the window
        add(WelcomeComponents());

        // Add action listeners for clear and submit
        createTeamButton.addActionListener(controller);
        registerAthleteButton.addActionListener(controller);
        absencesButton.addActionListener(controller);

        setVisible(true);
    }
    public JPanel WelcomeComponents(){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20,20));
        panel.setLayout(null);

        createTeamButton = new JButton("Create Team");
        createTeamButton.setBounds(20, 30, 150, 75);
        panel.add(createTeamButton);

        registerAthleteButton = new JButton("Register Athlete");
        registerAthleteButton.setBounds(200, 30, 150, 75);
        panel.add(registerAthleteButton);

        absencesButton = new JButton("Absences");
        absencesButton.setBounds(380, 30, 150,75);
        panel.add(absencesButton);

        return panel;
    }
}
