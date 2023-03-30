/**
 * Represents a face of a cube with an associated color, id, and facing direction.
 * Provides methods for rotating the face and displaying it.
 */
package cubeAssets;

import processing.core.PApplet;
import processing.core.PVector;
import settings.Facing;
import settings.IdDisplay;
import settings.Settings;

public class Face {
    private final PApplet sketch;
    private PVector normal;
    private final int color;
    private final int id;
    private final IdDisplay display;
    private Facing facing;
    private final Settings setting;

    /**
     * Constructs a new Face with the specified attributes.
     *
     * @param sketch  The PApplet instance for rendering.
     * @param normal  The normal vector of the face.
     * @param color   The color of the face.
     * @param id      The id of the face.
     * @param facing  The initial facing direction of the face.
     * @param setting The Settings object containing configuration.
     */
    public Face(PApplet sketch, PVector normal, int color, int id, Facing facing, Settings setting) {
        this.setting = setting;
        this.sketch = sketch;
        this.normal = normal;
        this.color = color;
        this.id = id;
        this.facing = facing;
        this.display = new IdDisplay(this.sketch, this.id);
    }

    /**
     * Rotates the face around the X axis.
     *
     * @param angle The angle in radians to rotate the face.
     */
    public void turnX(float angle) {
        PVector v = new PVector();
        v.y = PApplet.round(normal.y * PApplet.cos(angle) - normal.z * PApplet.sin(angle));
        v.z = PApplet.round(normal.y * PApplet.sin(angle) - normal.z * PApplet.cos(angle));
        v.x = PApplet.round(normal.x);
        normal = v;
    }

    /**
     * Rotates the face around the Y axis.
     *
     * @param angle The angle in radians to rotate the face.
     */
    public void turnY(float angle) {
        PVector v = new PVector();
        v.x = PApplet.round(normal.x * PApplet.cos(angle) - normal.z * PApplet.sin(angle));
        v.z = PApplet.round(normal.x * PApplet.sin(angle) - normal.z * PApplet.cos(angle));
        v.y = PApplet.round(normal.y);
        normal = v;
    }

    /**
     * Rotates the face around the Z axis.
     *
     * @param angle The angle in radians to rotate the face.
     */
    public void turnZ(float angle) {
        PVector v = new PVector();
        v.x = PApplet.round(normal.x * PApplet.cos(angle) - normal.y * PApplet.sin(angle));
        v.y = PApplet.round(normal.x * PApplet.sin(angle) - normal.y * PApplet.cos(angle));
        v.z = PApplet.round(normal.z);
        normal = v;
    }

    /**
     * Rotates the facing direction of the face around the X axis.
     *
     * @param dir The direction to rotate the facing (-1 for counterclockwise, 1 for clockwise).
     */
    public void rotateFacingX(int dir) {
        if (dir < 0) {
            if (this.facing == Facing.UP) {
                this.facing = Facing.FRONT;
            } else if (this.facing == Facing.FRONT) {
                this.facing = Facing.DOWN;
            } else if (this.facing == Facing.DOWN) {
                this.facing = Facing.BACK;
            } else if (this.facing == Facing.BACK) {
                this.facing = Facing.UP;
            }
        } else {
            if (this.facing == Facing.UP) {
                this.facing = Facing.BACK;
            } else if (this.facing == Facing.BACK) {
                this.facing = Facing.DOWN;
            } else if (this.facing == Facing.DOWN) {
                this.facing = Facing.FRONT;
            } else if (this.facing == Facing.FRONT) {
                this.facing = Facing.UP;
            }
        }
    }

    /**
     * Rotates the facing direction of the face around the Y axis.
     *
     * @param dir The direction to rotate the facing (-1 for counterclockwise, 1 for clockwise).
     */
    public void rotateFacingY(int dir) {
        if (dir < 0) {
            if (this.facing == Facing.FRONT) {
                this.facing = Facing.RIGHT;
            } else if (this.facing == Facing.RIGHT) {
                this.facing = Facing.BACK;
            } else if (this.facing == Facing.BACK) {
                this.facing = Facing.LEFT;
            } else if (this.facing == Facing.LEFT) {
                this.facing = Facing.FRONT;
            }
        } else {
            if (this.facing == Facing.FRONT) {
                this.facing = Facing.LEFT;
            } else if (this.facing == Facing.LEFT) {
                this.facing = Facing.BACK;
            } else if (this.facing == Facing.BACK) {
                this.facing = Facing.RIGHT;
            } else if (this.facing == Facing.RIGHT) {
                this.facing = Facing.FRONT;
            }
        }
    }

    /**
     * Rotates the facing direction of the face around the Z axis.
     *
     * @param dir The direction to rotate the facing (-1 for counterclockwise, 1 for clockwise).
     */
    public void rotateFacingZ(int dir) {
        if (dir < 0) {
            if (this.facing == Facing.UP) {
                this.facing = Facing.LEFT;
            } else if (this.facing == Facing.LEFT) {
                this.facing = Facing.DOWN;
            } else if (this.facing == Facing.DOWN) {
                this.facing = Facing.RIGHT;
            } else if (this.facing == Facing.RIGHT) {
                this.facing = Facing.UP;
            }
        } else {
            if (this.facing == Facing.UP) {
                this.facing = Facing.RIGHT;
            } else if (this.facing == Facing.RIGHT) {
                this.facing = Facing.DOWN;
            } else if (this.facing == Facing.DOWN) {
                this.facing = Facing.LEFT;
            } else if (this.facing == Facing.LEFT) {
                this.facing = Facing.UP;
            }
        }
    }

    /**
     * Displays the face on the PApplet sketch.
     */
    public void show() {
        this.sketch.pushMatrix();
        this.sketch.fill(color);
        this.sketch.rectMode(this.sketch.CENTER);
        this.sketch.translate(0.5f * normal.x, 0.5f * normal.y, 0.5f * normal.z);
        if (PApplet.abs(normal.x) > 0) {
            this.sketch.rotateY(this.sketch.HALF_PI);
        } else if (PApplet.abs(normal.y) > 0) {
            this.sketch.rotateX(this.sketch.HALF_PI);
        }
        this.sketch.square(0, 0, 1);
        if (this.setting.faceIdsCheckBox) {
            this.display.show();
        }
        this.sketch.popMatrix();
    }

    /**
     * Returns the id of the face.
     *
     * @return The id of the face.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current facing direction of the face.
     *
     * @return The facing direction of the face.
     */
    public Facing getFacing() {
        return facing;
    }

    /**
     * Returns the normal vector of the face.
     *
     * @return The normal vector of the face.
     */
    public PVector getNormal() {
        return normal;
    }
}