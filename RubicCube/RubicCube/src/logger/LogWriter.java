package logger;

import java.io.*;
import java.nio.file.*;

public class LogWriter {
    private static final String LOG_FILE = "src/inputs/application.log";

    public void log(String message) {
        synchronized (LOG_FILE) {
            writeLog(message);
        }
    }

    private void writeLog(String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearLogs() {
        synchronized (LOG_FILE) {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                // A TRUNCATE_EXISTING opció miatt a fájl tartalma törlődik
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
