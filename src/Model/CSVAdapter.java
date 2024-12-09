package Model;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class: CSVAdapter
 * Purpose: Provides functionality to create or append data to a CSV file for reporting purposes.
 * Authors: Bruno Valdez
 */
public class CSVAdapter {

    /**
     * Creates or appends data to a CSV file.
     *
     * @param data   A list of string arrays, where each array represents a row of data to be written.
     * @param append If true, appends data to the existing file. If false, overwrites the file.
     */
    public static void createCSV(List<String[]> data, boolean append) {
        String fileName = "Athlete Data - Sheet1.csv"; // Name of the CSV file to write data to.

        // Validate file name.
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty.");
        }

        // Check if there is data to write.
        if (data == null || data.isEmpty()) {
            System.out.println("No data to write to CSV file.");
            return;
        }

        // Attempt to write data to the CSV file.
        try (FileWriter writer = new FileWriter(fileName, append)) {
            for (String[] dataRow : data) {
                writer.append(String.join(",", dataRow)); // Join row elements with commas.
                writer.append("\n"); // Add a newline after each row.
            }
            // Show a notification when the CSV creation is successful.
            JOptionPane.showMessageDialog(null, "Report created Successfully!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            // Handle exceptions related to file I/O.
            e.printStackTrace();
            System.out.println("Error creating CSV file.");
        }
    }
}
