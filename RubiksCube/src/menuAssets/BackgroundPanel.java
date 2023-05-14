package menuAssets;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * BackgroundPanel is a custom JPanel subclass that displays an image as its background.
 */
public class BackgroundPanel extends JPanel {
    private final Image image;

    /**
     * Constructor for the BackgroundPanel class.
     *
     * @param image The Image object to be used as the background of the panel.
     */
    public BackgroundPanel(Image image) {
        this.image = image;
    }

    /**
     * Overrides the paintComponent method from the JPanel class to draw the background image.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}