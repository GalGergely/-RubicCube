/**
 * MainApp is the entry point for the Gergos Cube application.
 * It creates and displays the main menu with buttons to access different parts of the application,
 * such as playing the game, viewing algorithm data, modifying settings, displaying info, and quitting the application.
 * @author Gál Gergely
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import logger.*;
import menuAssets.AlgorithmApp;
import menuAssets.BackgroundPanel;
import menuAssets.SettingsApp;
import processing.core.PApplet;
import selfSolvingCube.GergosCube;
import settings.Settings;

import static processing.core.PApplet.runSketch;

public class MainApp {

    private static final Settings settings = new Settings();
    private static final String[] appletArgs = new String[]{"Gergos Cube"};
    private static final PApplet cube = new GergosCube();

    /**
     * Main method of the MainApp class and the entry point of the application.
     *
     * @param args command-line arguments passed to the main method.
     */
    public static void main(String[] args) {
        String libPath = System.getProperty("user.dir") + File.separator + "lib";
        System.setProperty("java.library.path", libPath);
        SwingUtilities.invokeLater(MainApp::createAndShowGUI);
    }

    /**
     * Creates and displays the main menu GUI.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Gergos Cube Menu");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(MainApp.class.getClassLoader().getResource("inputs/icon.png")));
        frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton playButton = new JButton("Play");
        JButton algorithmDataButton = new JButton("Algorithm Data");
        JButton settingsButton = new JButton("Settings");
        JButton infoButton = new JButton("Info");
        JButton quitButton = new JButton("Quit");

        playButton.setPreferredSize(new Dimension(150, playButton.getPreferredSize().height));
        algorithmDataButton.setPreferredSize(new Dimension(150, algorithmDataButton.getPreferredSize().height));
        settingsButton.setPreferredSize(new Dimension(150, settingsButton.getPreferredSize().height));
        infoButton.setPreferredSize(new Dimension(150, infoButton.getPreferredSize().height));
        quitButton.setPreferredSize(new Dimension(150, quitButton.getPreferredSize().height));

        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(playButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(algorithmDataButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(settingsButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(infoButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        mainPanel.add(quitButton, constraints);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogReader logReader = new LogReader();
                LogViewer logViewer = new LogViewer(logReader);
                logViewer.start();

                frame.setVisible(false);

                runSketch(appletArgs, cube);
            }
        });

        algorithmDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlgorithmApp app = new AlgorithmApp(frame);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsApp settingsApp = new SettingsApp(settings);
                settingsApp.openSettingsWindow();
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://people.inf.elte.hu/gt8yb1/"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        try {
            BufferedImage backgroundImage = ImageIO.read(Objects.requireNonNull(AlgorithmApp.class.getResource("/inputs/background.png")));
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            frame.setContentPane(backgroundPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel.setBackground(new Color(0, 0, 0, 0));
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
