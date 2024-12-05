package Model;

import javax.swing.JOptionPane;

public class Coach implements Observer {
    private String name;

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String playerName, int absences, int maxAbsences) {
        String message = playerName + " has reached " + absences + " absences, exceeding the limit of " + maxAbsences + "!";
        // Display pop-up notification
        JOptionPane.showMessageDialog(null, message, "Absence Alert", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public String toString() {
        return name;
    }
}
