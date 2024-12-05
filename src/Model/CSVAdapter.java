package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVAdapter {
    private final String filePath;

    public CSVAdapter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends a new row to the CSV file.
     *
     * @param athleteData List of strings representing the athlete's data
     * @throws IOException if writing to the file fails
     */
    public void appendToCSV(List<String> athleteData) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(String.join(",", athleteData));
            writer.append("\n");
        }
    }
}
