package logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogViewer {
    private final LogReader logReader;
    private JFrame frame;
    private JTextArea textArea;
    private Timer timer;

    public LogViewer(LogReader logReader) {
        this.logReader = logReader;
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Log Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshLogs();
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private void refreshLogs() {
        List<String> logs = logReader.getLastLogs(20);
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append(log);
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }
}
