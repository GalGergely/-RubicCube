/**
 * Represents a LogWriter that writes logs to a file.
 * Provides methods to log messages, and clear logs from the file.
 */
package logger;

import java.io.*;
import java.nio.file.*;

public class LogWriter {
    private static final String LOG_FILE = "src/inputs/application.log";

    /**
     * Logs a message to the log file.
     *
     * @param message The message to log.
     */
    public void log(String message) {
        synchronized (LOG_FILE) {
            writeLog(message);
        }
    }

    /**
     * Writes a message to the log file.
     *
     * @param message The message to write.
     */
    private void writeLog(String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears all logs from the log file.
     */
    public void clearLogs() {
        synchronized (LOG_FILE) {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                // Because of the TRUNCATE_EXISTING the contents of thi file will be deleted.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
