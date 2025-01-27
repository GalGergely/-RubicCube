package logger;

import java.io.*;
import java.nio.file.*;
import java.util.*;
/**
 * Represents a LogReader that reads logs from a file.
 * Provides methods to read logs from the file.
 */
public class LogReader {
    private final String LOG_FILE = "src/inputs/application.log";

    /**
     * Retrieves logs from the log file.
     *
     * @return A list of log strings.
     */
    public List<String> getLogs() {
        return readLogs();
    }

    /**
     * Reads logs from the log file.
     *
     * @return A list of log strings.
     */
    private List<String> readLogs() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}