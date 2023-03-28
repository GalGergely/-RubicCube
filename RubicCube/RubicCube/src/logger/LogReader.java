package logger;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LogReader {
    private static final String LOG_FILE = "src/inputs/application.log";
    public List<String> getLogs() {
        synchronized (LOG_FILE) {
            return readLogs();
        }
    }

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