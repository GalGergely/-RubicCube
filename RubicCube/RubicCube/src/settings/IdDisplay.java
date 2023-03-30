/**
 * IdDisplay is a class responsible for displaying face IDs on the cube.
 */
package settings;

import processing.core.PApplet;

public class IdDisplay {
    private final PApplet sketch;
    int id;

    /**
     * Constructor for the IdDisplay class.
     *
     * @param sketch PApplet object representing the main sketch.
     * @param id Integer representing the face ID.
     */
    public IdDisplay(PApplet sketch, int id) {
        this.sketch = sketch;
        this.id = id;
    }

    /**
     * Displays the face ID on the cube.
     */
    public void show() {
        this.sketch.textSize(15);
        this.sketch.fill(0);
        this.sketch.scale(0.02f);
        this.sketch.text(id, 0 , 0 , 1 );
        this.sketch.scale(1, -1);
        this.sketch.text(id, 0 , 0 , -1 );
        this.sketch.scale(1, -1);
    }
}
