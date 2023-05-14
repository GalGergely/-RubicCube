package menuAssets;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

import settings.Settings;
/**
 * SettingsApp is a class responsible for managing and displaying the settings of the Gergos Cube application.
 * It allows users to modify various settings such as cube colors, step-by-step solving, and showing face IDs.
 */
public class SettingsApp {

    private final Settings settings;

    public SettingsApp(Settings settings) {
        this.settings = settings;
    }

    /**
     * Opens the settings window with various settings controls.
     */
    public void openSettingsWindow() {
        JFrame settingsFrame = new JFrame("Settings");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("inputs/icon.png")));
        settingsFrame.setIconImage(logo.getImage());
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(600, 400);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        String[] sides = {"Cube", "Background", "Top", "Left", "Right", "Bottom", "Front", "Back"};
        JTextField[][] rgbInputFields = new JTextField[sides.length][3];
        JPanel[] colorDisplayPanels = new JPanel[sides.length];
        Color[] colors = {
                settings.strokeColor,
                settings.backgroundColor,
                settings.red,
                settings.orange,
                settings.yellow,
                settings.green,
                settings.blue,
                settings.white
        };

        constraints.gridy = sides.length;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;

        JLabel stepByStepLabel = new JLabel("Step by step solving");
        settingsPanel.add(stepByStepLabel, constraints);

        constraints.gridx = 1;
        JCheckBox stepByStepCheckBox = new JCheckBox("");
        stepByStepCheckBox.setSelected(settings.stepByStepSolving);
        settingsPanel.add(stepByStepCheckBox, constraints);

        constraints.gridy = sides.length + 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;

        JLabel faceIdsLabel = new JLabel("Show face ids");
        settingsPanel.add(faceIdsLabel, constraints);

        constraints.gridx = 1;
        JCheckBox faceIdsCheckBox = new JCheckBox("");
        faceIdsCheckBox.setSelected(settings.faceIdsCheckBox);
        settingsPanel.add(faceIdsCheckBox, constraints);


        for (int i = 0; i < sides.length; i++) {
            constraints.gridy = i;
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.WEST;
            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            labelPanel.add(new JLabel(sides[i] + " Color"));
            settingsPanel.add(labelPanel, constraints);

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

        constraints.gridy = sides.length + 2;
        constraints.gridx = 1;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveColorSettings(rgbInputFields);
            settings.stepByStepSolving = stepByStepCheckBox.isSelected();
            settings.faceIdsCheckBox = faceIdsCheckBox.isSelected();

            settings.saveSettings();
        });
        settingsPanel.add(saveButton, constraints);

        constraints.gridx = 2;
        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(e -> {
            saveColorSettings(rgbInputFields);
            settings.stepByStepSolving = stepByStepCheckBox.isSelected();
            settings.faceIdsCheckBox = faceIdsCheckBox.isSelected();

            settings.saveSettings();

            settingsFrame.dispose();
        });
        settingsPanel.add(saveAndQuitButton, constraints);

        settingsFrame.getContentPane().add(settingsPanel, BorderLayout.CENTER);
        settingsFrame.setLocationRelativeTo(null);

        settingsFrame.setVisible(true);
    }


    /**
     * Sets RGB values for the specified JTextField array and Color object.
     *
     * @param rgbFields JTextField array containing the RGB input fields.
     * @param color     Color object representing the color.
     */
    private void setRGBValues(JTextField[] rgbFields, Color color) {
        rgbFields[0].setText(String.valueOf(color.getRed()));
        rgbFields[1].setText(String.valueOf(color.getGreen()));
        rgbFields[2].setText(String.valueOf(color.getBlue()));
    }

    /**
     * Retrieves the color from the specified JTextField array.
     *
     * @param rgbFields JTextField array containing the RGB input fields.
     * @return Color object representing the color from the input fields.
     */
    private Color getColorFromRGBFields(JTextField[] rgbFields) {
        int r = Integer.parseInt(rgbFields[0].getText());
        int g = Integer.parseInt(rgbFields[1].getText());
        int b = Integer.parseInt(rgbFields[2].getText());
        return new Color(r, g, b);
    }


    /**
     * Saves the color settings from the specified JTextField array.
     *
     * @param rgbInputFields Two-dimensional array of JTextField objects representing RGB input fields.
     */
    private void saveColorSettings(JTextField[][] rgbInputFields) {
        settings.strokeColor = getColorFromRGBFields(rgbInputFields[0]);
        settings.backgroundColor = getColorFromRGBFields(rgbInputFields[1]);
        settings.red = getColorFromRGBFields(rgbInputFields[2]);
        settings.orange = getColorFromRGBFields(rgbInputFields[3]);
        settings.yellow = getColorFromRGBFields(rgbInputFields[4]);
        settings.green = getColorFromRGBFields(rgbInputFields[5]);
        settings.blue = getColorFromRGBFields(rgbInputFields[6]);
        settings.white = getColorFromRGBFields(rgbInputFields[7]);
    }


    /**
     * Updates the color display panel based on the RGB values entered in the JTextField array.
     *
     * @param rgbFields          JTextField array containing the RGB input fields.
     * @param colorDisplayPanels Array of JPanel objects representing color display panels.
     * @param index              Index of the color display panel to update.
     */
    private void updateColorListener(JTextField[] rgbFields, JPanel[] colorDisplayPanels, int index) {
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
                } catch (NumberFormatException ignored) {
                }
            }
        };

        for (JTextField rgbField : rgbFields) {
            rgbField.getDocument().addDocumentListener(dl);
        }
    }
}
