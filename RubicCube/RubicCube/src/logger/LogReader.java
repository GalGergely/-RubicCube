package logger;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
public class LogReader {
    private static final String LOG_FILE = "application.log";

    public List<String> getLastLogs(int numberOfLogs) {
        synchronized (LOG_FILE) {
            return readLastLogs(numberOfLogs);
        }
    }

    private List<String> readLastLogs(int numberOfLogs) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(LOG_FILE))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int startIndex = Math.max(0, lines.size() - numberOfLogs);
            for (int i = startIndex; i < lines.size(); i++) {
                result.add(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}