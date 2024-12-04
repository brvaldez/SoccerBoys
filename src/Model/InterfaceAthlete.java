package Model;

import java.util.Map;

public interface InterfaceAthlete {
    String getName();
    String getDob();
    String getEmail();
    String getId();
    String getSport();
    Map<String, Integer> getClasses();
    int getLimitOfAbsences();
    void displayDetails();
}
