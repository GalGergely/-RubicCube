/**
 * Represents a LogViewer that displays logs from a LogReader.
 * Provides methods to create and show a graphical user interface (GUI) to display logs.
 */
package logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Objects;

public class LogViewer {
    private final LogReader logReader;
    private final JFrame frame;
    private JTextArea textArea;
    public Thread thread;


    public LogViewer(LogReader logReader) {
        this.logReader = logReader;
        this.frame = new JFrame("Log Viewer");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("inputs/icon.png")));
        this.frame.setIconImage(logo.getImage());
    }

    /**
     * Starts the LogViewer by creating and displaying the GUI.
     */
    public void start() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
        thread.start();
    }

    /**
     * Creates and shows the GUI for the LogViewer.
     */
    private void createAndShowGUI() {
        this.frame.setSize(600, 400);
        this.frame.setResizable(false);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshLogs();
            }
        });
        timer.start();

        this.frame.setVisible(true);
    }

    /**
     * Refreshes the logs displayed in the GUI.
     */
    private void refreshLogs() {
        List<String> logs = logReader.getLogs();
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append(log);
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }
}
