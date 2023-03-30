/**
 * AlgorithmApp is a JFrame subclass that provides an interactive user interface
 * for managing, learning, and deleting Rubik's Cube algorithms.
 */
package menuAssets;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import algorithm.Algorithm;
import algorithm.AlgorithmCollection;
import algorithmCube.AlgorithmCube;
import logger.LogReader;
import logger.LogViewer;
import processing.core.PApplet;

import static processing.core.PApplet.runSketch;

public class AlgorithmApp extends JFrame {
    private static final String[] appletArgs = new String[]{"Algorithm Mode"};

    /**
     * Constructor for the AlgorithmApp class.
     */
    public AlgorithmApp() {
        SwingUtilities.invokeLater(AlgorithmApp::createAndShowGUI);
    }

    /**
     * Creates and displays the main GUI for the AlgorithmApp.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Algorithm Mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setLayeredPane(layeredPane);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        JPanel contentPanel = new JPanel(new FlowLayout());
        contentPanel.setOpaque(false);


        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JButton addAlgorithmButton = new JButton("Add Algorithm");
        JButton learnAlgorithmButton = new JButton("Learn Algorithm");
        JButton deleteAlgorithmButton = new JButton("Delete Algorithm");

        buttonPanel.add(addAlgorithmButton);
        buttonPanel.add(learnAlgorithmButton);
        buttonPanel.add(deleteAlgorithmButton);

        AlgorithmCollection algorithmCollection = new AlgorithmCollection();

        addAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();

                JPanel addAlgorithmPanel = new JPanel(new GridLayout(4, 1));
                addAlgorithmPanel.setBackground(new Color(0, 0, 0, 0));
                JTextField nameField = new JTextField("Name of algorithm", 20);
                JTextField movesField = new JTextField("Moves of the algorithm", 20);
                JButton saveButton = new JButton("Save");

                // Document filter for movesField
                DocumentFilter filter = new DocumentFilter() {
                    private final String allowedCharacters = "lLrRfFbBuUdD";

                    @Override
                    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                        if (string.chars().allMatch(c -> allowedCharacters.indexOf(c) != -1)) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        if (text.chars().allMatch(c -> allowedCharacters.indexOf(c) != -1)) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                };

                ((AbstractDocument) movesField.getDocument()).setDocumentFilter(filter);

                // Focus listener for nameField and movesField
                FocusAdapter focusAdapter = new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        JTextField source = (JTextField) e.getSource();
                        source.setText("");
                    }
                };

                nameField.addFocusListener(focusAdapter);
                movesField.addFocusListener(focusAdapter);

                addAlgorithmPanel.add(nameField);
                addAlgorithmPanel.add(movesField);
                addAlgorithmPanel.add(saveButton);
                contentPanel.add(addAlgorithmPanel);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String moves = movesField.getText();
                        algorithmCollection.add(name, moves.split(""));
                    }
                });

                frame.revalidate();
                frame.repaint();
            }
        });

        learnAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();

                JPanel learnAlgorithmPanel = new JPanel();
                learnAlgorithmPanel.setBackground(new Color(0, 0, 0, 0));
                JComboBox<String> algorithmList = new JComboBox<>();

                for (Algorithm algorithm : algorithmCollection.getAlgorithms()) {
                    algorithmList.addItem(algorithm.getName());
                }

                JButton playButton = new JButton("Play");

                learnAlgorithmPanel.add(algorithmList);
                learnAlgorithmPanel.add(playButton);
                contentPanel.add(learnAlgorithmPanel);

                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedAlgorithm = (String) algorithmList.getSelectedItem();
                        PApplet cube = new AlgorithmCube(selectedAlgorithm);

                        LogReader logReader = new LogReader();
                        LogViewer logViewer = new LogViewer(logReader);
                        logViewer.start();
                        logViewer.setAlwaysOnTop(true);

                        frame.setVisible(false);

                        runSketch(appletArgs, cube);
                        System.out.println(selectedAlgorithm);
                    }
                });

                frame.revalidate();
                frame.repaint();
            }
        });

        deleteAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();

                JPanel deleteAlgorithmPanel = new JPanel();
                deleteAlgorithmPanel.setBackground(new Color(0, 0, 0, 0));
                JComboBox<String> algorithmList = new JComboBox<>();

                for (Algorithm algorithm : algorithmCollection.getAlgorithms()) {
                    algorithmList.addItem(algorithm.getName());
                }

                JButton deleteButton = new JButton("Delete");

                deleteAlgorithmPanel.add(algorithmList);
                deleteAlgorithmPanel.add(deleteButton);
                contentPanel.add(deleteAlgorithmPanel);

                algorithmList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedAlgorithm = (String) algorithmList.getSelectedItem();
                        deleteButton.setEnabled(selectedAlgorithm == null || (!selectedAlgorithm.equals("jperm") && !selectedAlgorithm.equals("tperm") && !selectedAlgorithm.equals("yperm") && !selectedAlgorithm.equals("lperm")));
                    }
                });

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedAlgorithm = (String) algorithmList.getSelectedItem();
                        algorithmCollection.delete(selectedAlgorithm);

                        // Refresh the list
                        algorithmList.removeAllItems();
                        for (Algorithm algorithm : algorithmCollection.getAlgorithms()) {
                            algorithmList.addItem(algorithm.getName());
                        }
                    }
                });

                frame.revalidate();
                frame.repaint();
            }
        });
        try {
            BufferedImage backgroundImage = ImageIO.read(Objects.requireNonNull(AlgorithmApp.class.getResource("/inputs/background.png")));
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            frame.setContentPane(backgroundPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
