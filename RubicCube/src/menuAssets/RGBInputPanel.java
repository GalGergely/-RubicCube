/**
 * RGBInputPanel is a JPanel subclass that provides an input interface for RGB values.
 */
package menuAssets;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RGBInputPanel extends JPanel {
    private final JTextField[] rgbFields;

    /**
     * Constructor for the RGBInputPanel class.
     */
    public RGBInputPanel() {
        setLayout(new GridLayout(1, 3));

        rgbFields = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            rgbFields[i] = new JTextField(3);
            add(rgbFields[i]);
        }
    }

    /**
     * Returns a copy of the array containing the JTextField objects for inputting the RGB values.
     *
     * @return Copy of the array containing the JTextField objects for inputting the RGB values.
     */
    public JTextField[] getRGBFields() {
        return Arrays.copyOf(rgbFields, rgbFields.length);
    }
}
