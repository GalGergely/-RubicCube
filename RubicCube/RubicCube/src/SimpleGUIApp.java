import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import processing.core.PApplet;
import settings.Settings;

public class SimpleGUIApp {

    private static Settings settings = new Settings();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple GUI App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton playButton = new JButton("Play");
        JButton algorithmDataButton = new JButton("Algorithm Data");
        JButton settingsButton = new JButton("Settings");
        JButton quitButton = new JButton("Quit");

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
        mainPanel.add(quitButton, constraints);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] appletArgs = new String[] { "Main" };
                PApplet.main(appletArgs);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsWindow();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void openSettingsWindow() {
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(600, 400);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        String[] sides = {"Cube", "Top", "Left", "Right", "Bottom", "Front", "Back"};
        JTextField[][] rgbInputFields = new JTextField[sides.length][3];
        JPanel[] colorDisplayPanels = new JPanel[sides.length];
        Color[] colors = {
                settings.strokeColor,
                settings.red,
                settings.orange,
                settings.yellow,
                settings.green,
                settings.blue,
                settings.white
        };

        constraints.gridy = sides.length;
        constraints.gridx = 0;
        JCheckBox stepByStepCheckBox = new JCheckBox("Step by step solving");
        stepByStepCheckBox.setSelected(settings.stepByStepSolving);
        settingsPanel.add(stepByStepCheckBox, constraints);

        for (int i = 0; i < sides.length; i++) {
            constraints.gridy = i;

            constraints.gridx = 0;
            settingsPanel.add(new JLabel(sides[i] + " Color"), constraints);

            constraints.gridx = 1;
            RGBInputPanel inputPanel = new RGBInputPanel();
            rgbInputFields[i] = inputPanel.getRGBFields();
            setRGBValues(rgbInputFields[i], colors[i]);
            updateColorListener(rgbInputFields[i], colorDisplayPanels, i);
            settingsPanel.add(inputPanel, constraints);
            constraints.gridx = 2;
            JPanel colorDisplayPanel = new JPanel();
            colorDisplayPanel.setPreferredSize(new Dimension(50, 25));
            colorDisplayPanel.setBackground(colors[i]);
            colorDisplayPanels[i] = colorDisplayPanel;
            settingsPanel.add(colorDisplayPanel, constraints);
        }

        constraints.gridy = sides.length + 1;
        constraints.gridx = 1;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveColorSettings(rgbInputFields);
                settings.stepByStepSolving = stepByStepCheckBox.isSelected();

                settings.saveSettings();
            }
        });
        settingsPanel.add(saveButton, constraints);

        constraints.gridx = 2;
        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveColorSettings(rgbInputFields);
                settings.stepByStepSolving = stepByStepCheckBox.isSelected();

                settings.saveSettings();

                settingsFrame.dispose(); // Close the settings window
            }
        });
        settingsPanel.add(saveAndQuitButton, constraints);

        settingsFrame.getContentPane().add(settingsPanel, BorderLayout.CENTER);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    private static void setRGBValues(JTextField[] rgbFields, Color color) {
        rgbFields[0].setText(String.valueOf(color.getRed()));
        rgbFields[1].setText(String.valueOf(color.getGreen()));
        rgbFields[2].setText(String.valueOf(color.getBlue()));
    }

    private static Color getColorFromRGBFields(JTextField[] rgbFields) {
        int r = Integer.parseInt(rgbFields[0].getText());
        int g = Integer.parseInt(rgbFields[1].getText());
        int b = Integer.parseInt(rgbFields[2].getText());
        return new Color(r, g, b);
    }

    private static void saveColorSettings(JTextField[][] rgbInputFields) {
        settings.strokeColor = getColorFromRGBFields(rgbInputFields[0]);
        settings.red = getColorFromRGBFields(rgbInputFields[1]);
        settings.orange = getColorFromRGBFields(rgbInputFields[2]);
        settings.yellow = getColorFromRGBFields(rgbInputFields[3]);
        settings.green = getColorFromRGBFields(rgbInputFields[4]);
        settings.blue = getColorFromRGBFields(rgbInputFields[5]);
        settings.white = getColorFromRGBFields(rgbInputFields[6]);
    }

    private static void updateColorListener(JTextField[] rgbFields, JPanel[] colorDisplayPanels, int index) {
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateColor();
            }

            public void removeUpdate(DocumentEvent e) {
                updateColor();
            }

            public void changedUpdate(DocumentEvent e) {
                updateColor();
            }

            private void updateColor() {
                try {
                    Color newColor = getColorFromRGBFields(rgbFields);
                    colorDisplayPanels[index].setBackground(newColor);
                } catch (NumberFormatException e) {
                    // Do nothing if the input is invalid
                }
            }
        };

        for (JTextField rgbField : rgbFields) {
            rgbField.getDocument().addDocumentListener(dl);
        }
    }
}
