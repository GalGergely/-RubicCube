/**
 * Represents a LogViewer that displays logs from a LogReader.
 * Provides methods to create and show a graphical user interface (GUI) to display logs.
 */
package logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class LogViewer {
    private final LogReader logReader;
    private JFrame frame;
    private JTextArea textArea;
    private Timer timer;
    public Thread thread;


    public LogViewer(LogReader logReader) {
        this.logReader = logReader;
        this.frame = new JFrame("Log Viewer");
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
        thread.run();
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

        timer = new Timer(1000, new ActionListener() {
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

    /**
     * Sets the LogViewer's frame to always be on top.
     *
     * @param b A boolean indicating if the frame should always be on top.
     */
    public void setAlwaysOnTop(boolean b) {
        this.frame.setAlwaysOnTop(b);
    }
}
