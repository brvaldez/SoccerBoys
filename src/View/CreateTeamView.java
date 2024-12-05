package View;

import Controller.CreateTeamController;
import Model.Coach;
import Model.Team;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class CreateTeamView extends JFrame{
    private JLabel sportLabel, coachLabel, absencesLabel, sportChangeLabel, newCoachLabel, removeSportLabel;
    public JTextField sportField, absencesField;
    public JButton addTeamButton, changeCoachButton, removeTeamButton, returnButton;
    public JComboBox<Team> sportDropdown, removeSportDropdown;
    public JComboBox<Coach> coachDropDown, newCoachDropDown;
    private ArrayList<Coach> coaches;
    public CreateTeamView(WelcomeView welcomeView) {
        super("Manage Teams");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);


        coaches.add(new Coach("Bruno Valdez"));
        coaches.add(new Coach("Manuel Rodriguez"));
        coaches.add(new Coach("Tania Roy"));

        add(CreateTeamComponents());

        CreateTeamController controller = new CreateTeamController(this, welcomeView);

        // Add action listeners for clear and submit
        addTeamButton.addActionListener(controller);
        changeCoachButton.addActionListener(controller);
        removeTeamButton.addActionListener(controller);
        returnButton.addActionListener(controller);
        setVisible(true);
    }
    public JPanel CreateTeamComponents(){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20,20));
        panel.setLayout(null);

        /*/ Title
        JLabel titleLabel = new JLabel("Manage Teams");
        titleLabel.setBounds(250, 20, 150, 20);
        frame.add(titleLabel);*/

        // Sport Field with Label
        sportField = new JTextField();
        sportField.setBounds(50, 70, 120, 20);
        panel.add(sportField);

        sportLabel = new JLabel("Sport");
        sportLabel.setBounds(50, 95, 120, 20);
        panel.add(sportLabel);

        // Coach Field with Label
        coachDropDown = new JComboBox<>();
        coachDropDown.setBounds(250, 70, 120, 20);
        panel.add(coachDropDown);

        coachLabel = new JLabel("Coach");
        coachLabel.setBounds(250, 95, 120, 20);
        panel.add(coachLabel);

        // Absences Field with Label
        absencesField = new JTextField();
        absencesField.setBounds(450, 70, 120, 20);
        panel.add(absencesField);

        absencesLabel = new JLabel("Absences");
        absencesLabel.setBounds(450, 95, 120, 20);
        panel.add(absencesLabel);

        // Add Team Button below Sport, Coach, and Absences fields
        addTeamButton = new JButton("Add Team");
        addTeamButton.setBounds(250, 130, 120, 30);
        panel.add(addTeamButton);

        // Change Coach Section
        sportDropdown = new JComboBox<>();
        sportDropdown.setBounds(50, 200, 120, 20);
        panel.add(sportDropdown);

        sportChangeLabel = new JLabel("Sport");
        sportChangeLabel.setBounds(50, 225, 120, 20);
        panel.add(sportChangeLabel);

        newCoachDropDown = new JComboBox<>();
        newCoachDropDown.setBounds(250, 200, 120, 20);
        panel.add(newCoachDropDown);

        newCoachLabel = new JLabel("New Coach");
        newCoachLabel.setBounds(250, 225, 120, 20);
        panel.add(newCoachLabel);

        changeCoachButton = new JButton("Change Coach");
        changeCoachButton.setBounds(250, 255, 120, 30);
        panel.add(changeCoachButton);

        // Remove Team Section
        removeSportDropdown = new JComboBox<>();
        removeSportDropdown.setBounds(250, 325, 120, 20);
        panel.add(removeSportDropdown);

        removeSportLabel = new JLabel("Sport");
        removeSportLabel.setBounds(250, 350, 120, 20);
        panel.add(removeSportLabel);

        removeTeamButton = new JButton("Remove Team");
        removeTeamButton.setBounds(250, 380, 120, 30);
        panel.add(removeTeamButton);

        // Return button
        returnButton = new JButton("Menu");
        returnButton.setBounds(500, 450, 50,20);
        panel.add(returnButton);

        return panel;
    }
    public void updateNewCoachDropDown(Team selectedTeam, List<Coach> allCoaches) {
        // Criação de uma lista temporária para armazenar os coaches, excluindo o coach atual
        List<Coach> filteredCoaches = new ArrayList<>();

        // Adicionando todos os coaches que não são o coach atual
        for (Coach coach : allCoaches) {
            if (!coach.equals(selectedTeam.getCoach())) {
                filteredCoaches.add(coach);
            }
        }

        // Atualizando o JComboBox com os coaches filtrados
        newCoachDropDown.removeAllItems();
        for (Coach coach : filteredCoaches) {
            newCoachDropDown.addItem(coach);
        }
    }
}
