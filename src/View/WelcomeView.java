// WelcomeView.java
package View;

import Controller.WelcomeController;
import Model.Coach;
import Model.Component;
import Model.Team;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WelcomeView extends JFrame {

    public JButton createTeamButton, registerAthleteButton, absencesButton;
    private List<Component> teams = new ArrayList<>();
    private List<Coach> coaches;
    private AbsencesView absencesView;
    private RegisterAthleteView registerAthleteView;

    public WelcomeView() {
        super("Welcome Coach!!!");
        this.registerAthleteView = new RegisterAthleteView(this, absencesView);
        this.absencesView= new AbsencesView(this, teams);
        coaches = new ArrayList<>();

        coaches.add(new Coach("Bruno Valdez"));
        coaches.add(new Coach("Manuel Rodriguez"));
        coaches.add(new Coach("Tania Roy"));

        // Listeners
        WelcomeController controller = new WelcomeController(this, absencesView, registerAthleteView, teams, coaches);

        registerAthleteView.setVisible(false);
        absencesView.setVisible(false);

        // Window features
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        // Add components to the window
        add(WelcomeComponents());

        // Add action listeners for the buttons
        createTeamButton.addActionListener(controller);
        registerAthleteButton.addActionListener(controller);
        absencesButton.addActionListener(controller);

        setVisible(true);
    }

    public JPanel WelcomeComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout(20, 20));

        // Title
        JLabel titleLabel = new JLabel("Welcome to the Coach Management System!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 20));

        createTeamButton = new JButton("Create Team");
        createTeamButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(createTeamButton);

        registerAthleteButton = new JButton("Register Athlete");
        registerAthleteButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(registerAthleteButton);

        absencesButton = new JButton("Absences");
        absencesButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(absencesButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }
}