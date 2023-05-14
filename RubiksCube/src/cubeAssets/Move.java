package cubeAssets;

import processing.core.PApplet;
import processing.core.PVector;
/**
 * Move class represents a single move of the Rubik's Cube.
 * It contains the necessary information for updating the cube state and executing the move.
 */
public class Move {
    private final PApplet sketch;
    private float angle;
    private final int direction;
    private boolean animate;
    private final PVector rotationAxis;
    private final Cube cube;

    /**
     * Constructs a Move instance with the specified parameters.
     * @param sketch PApplet object representing the sketch window.
     * @param rotationAxis X Y Z -axes of rotation direction.
     * @param direction The rotation direction (1 for clockwise, -1 for counterclockwise).
     * @param cube The cube to do the move on.
     */
    public Move(PApplet sketch, PVector rotationAxis, int direction, Cube cube) {
        this.sketch = sketch;
        this.rotationAxis = rotationAxis;
        this.direction = direction;
        this.cube = cube;
        this.angle = 0;
        this.animate = false;
    }

    /**
     * Initiates the move animation.
     */
    public void start() {
        this.animate = true;
    }

    /**
     * Updates the move's angle based on the animation and cube's state.
     * If the angle reaches or exceeds half of PI (90 degrees), the move animation stops, and the cube state is updated.
     */
    public void update() {
        if (this.animate) {
            this.angle += direction * 0.3f; // with animation
            //this.angle=this.sketch.HALF_PI*direction; // without animation
            if (PApplet.abs(this.angle) >= this.sketch.HALF_PI) {
                this.angle = 0;
                this.animate = false;
                if (PApplet.abs((int) this.rotationAxis.z) == 1) {
                    cube.turnZ((int) this.rotationAxis.z, direction);
                } else if (PApplet.abs((int) this.rotationAxis.z) == 2) {
                    cube.turnZ(0, direction);
                } else if (PApplet.abs((int) this.rotationAxis.x) == 1) {
                    cube.turnX((int) this.rotationAxis.x, direction);
                } else if (PApplet.abs((int) this.rotationAxis.x) == 2) {
                    cube.turnX(0, direction);
                } else if (PApplet.abs((int) this.rotationAxis.y) == 1) {
                    cube.turnY((int) this.rotationAxis.y, direction);
                } else if (PApplet.abs((int) this.rotationAxis.y) == 2) {
                    cube.turnY(0, direction);
                }
            }
        }
    }

    /**
     * Checks if the move is currently being animated.
     * @return true if the move is being animated, false otherwise.
     */
    public boolean isAnimate() {
        return animate;
    }

    // Getters for the Move class properties
    public float getAngle() {
        return angle;
    }

    public PVector getRotationAxis() {
        return rotationAxis;
    }
}
