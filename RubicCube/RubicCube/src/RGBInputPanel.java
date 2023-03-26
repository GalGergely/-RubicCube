import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RGBInputPanel extends JPanel {
    private JTextField[] rgbFields;

    public RGBInputPanel() {
        setLayout(new GridLayout(1, 3));

        rgbFields = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            rgbFields[i] = new JTextField(3);
            add(rgbFields[i]);
        }
    }

    public JTextField[] getRGBFields() {
        return Arrays.copyOf(rgbFields, rgbFields.length);
    }
}
