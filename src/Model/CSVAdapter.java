package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVAdapter {
    // Overloaded method to append to a CSV file
    public static void createCSV(List<String[]> data, boolean append) {
        String fileName = "Athlete Data - Sheet1.csv";
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty.");
        }
        if (data == null || data.isEmpty()) {
            System.out.println("No data to write to CSV file.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileName, append)) {
            for (String[] dataRow : data) {
                writer.append(String.join(",", dataRow));
                writer.append("\n");
            }
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating CSV file.");
        }
    }
}
