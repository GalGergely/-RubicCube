package cubeAssets;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import settings.Facing;
import settings.Settings;
/**
 * Represents a cubie (small cube) of a Rubik's cube with an associated position, faces, and a solved position.
 * Provides methods for rotating the cubie and displaying it.
 */
public class Cubie {
    private final PApplet sketch;
    private final Settings setting;
    private final PMatrix3D matrix;
    private final PVector position;
    private final Face[] faces = new Face[6];
    private final PVector solvedPosition;

    /**
     * Constructs a new Cubie with the specified attributes.
     *
     * @param sketch       The PApplet instance for rendering.
     * @param matrix       The PMatrix3D for transformations.
     * @param x            The x position of the cubie.
     * @param y            The y position of the cubie.
     * @param z            The z position of the cubie.
     * @param facecounter  The face counter for assigning face ids.
     * @param setting      The Settings object containing configuration.
     */
    public Cubie(PApplet sketch, PMatrix3D matrix, int x, int y, int z, int facecounter, Settings setting) {
        this.sketch = sketch;
        this.setting = setting;
        position = new PVector(x, y, z);
        solvedPosition = new PVector(x, y, z);
        this.matrix = matrix;
        faces[0] = new Face(this.sketch, new PVector(0, 0, -1), sketch.color(setting.blue.getRed(), setting.blue.getGreen(), setting.blue.getBlue()), facecounter++, Facing.BACK, this.setting);
        faces[1] = new Face(this.sketch, new PVector(0, 0, 1), sketch.color(setting.green.getRed(), setting.green.getGreen(), setting.green.getBlue()), facecounter++, Facing.FRONT, this.setting);
        faces[2] = new Face(this.sketch, new PVector(0, 1, 0), sketch.color(setting.white.getRed(), setting.white.getGreen(), setting.white.getBlue()), facecounter++, Facing.DOWN, this.setting);
        faces[3] = new Face(this.sketch, new PVector(0, -1, 0), sketch.color(setting.yellow.getRed(), setting.yellow.getGreen(), setting.yellow.getBlue()), facecounter++, Facing.UP, this.setting);
        faces[4] = new Face(this.sketch, new PVector(1, 0, 0), sketch.color(setting.orange.getRed(), setting.orange.getGreen(), setting.orange.getBlue()), facecounter++, Facing.RIGHT, this.setting);
        faces[5] = new Face(this.sketch, new PVector(-1, 0, 0), sketch.color(setting.red.getRed(), setting.red.getGreen(), setting.red.getBlue()), facecounter++, Facing.LEFT, this.setting);

    }

    /**
     * Rotates the faces of the cubie around the X axis.
     *
     * @param dir The direction to rotate the faces (-1 for counterclockwise, 1 for clockwise).
     */
    public void turnFacesX(int dir) {
        for (Face face : faces) {
            face.rotateFacingX(dir);
            face.turnX(dir * this.sketch.HALF_PI);
        }
    }

    /**
     * Rotates the faces of the cubie around the Y axis.
     *
     * @param dir The direction to rotate the faces (-1 for counterclockwise, 1 for clockwise).
     */
    public void turnFacesY(int dir) {
        for (Face face : faces) {
            face.rotateFacingY(dir);
            face.turnY(dir * this.sketch.HALF_PI);
        }
    }

    /**
     * Rotates the faces of the cubie around the Z axis.
     *
     * @param dir The direction to rotate the faces (-1 for counterclockwise, 1 for clockwise).
     */
    public void turnFacesZ(int dir) {
        for (Face face : faces) {
            face.rotateFacingZ(dir);
            face.turnZ(dir * this.sketch.HALF_PI);
        }
    }

    /**
     * Updates the position of the cubie.
     *
     * @param x The new x position of the cubie.
     * @param y The new y position of the cubie.
     * @param z The new z position of the cubie.
     */
    public void update(int x, int y, int z) {
        matrix.reset();
        matrix.translate(x, y, z);
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    /**
     * Displays the cubie on the PApplet sketch.
     */
    public void show() {
        this.sketch.noFill();
        this.sketch.stroke(setting.strokeColor.getRGB());
        this.sketch.strokeWeight(setting.strokeWeight);
        this.sketch.pushMatrix();
        this.sketch.applyMatrix(matrix);
        for (Face face : faces) {
            face.show();
        }
        this.sketch.popMatrix();
    }

    /**
     * Returns a string representation of the cubie.
     *
     * @return A string representing the cubie's position.
     */
    public String toString() {
        return "current:" + "x;" + position.x + " y:" + position.y + " :z" + position.z + "\n";
    }

    /**
     * Returns the position vector of the cubie.
     *
     * @return The position vector of the cubie.
     */
    public PVector getPosition() {
        return position;
    }

    /**
     * Returns the array of faces of the cubie.
     *
     * @return The array of faces of the cubie.
     */
    public Face[] getFaces() {
        return faces;
    }

    /**
     * Returns the solved position vector of the cubie.
     *
     * @return The solved position vector of the cubie.
     */
    public PVector getSolvedPosition() {
        return solvedPosition;
    }
}
