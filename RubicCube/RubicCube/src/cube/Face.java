package cube;

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
    private Settings setting;

    public Face(PApplet sketch, PVector normal, int color, int id, Facing facing, Settings setting) {
        this.setting = setting;
        this.sketch = sketch;
        this.normal = normal;
        this.color = color;
        this.id = id;
        this.facing = facing;
        this.display = new IdDisplay(this.sketch, this.id);
    }
    public void turnX(float angle) {
        PVector v = new PVector();
        v.y = PApplet.round(normal.y * PApplet.cos(angle) - normal.z * PApplet.sin(angle));
        v.z = PApplet.round(normal.y * PApplet.sin(angle) - normal.z * PApplet.cos(angle));
        v.x = PApplet.round(normal.x);
        normal = v;
    }
    public void turnY(float angle) {
        PVector v = new PVector();
        v.x = PApplet.round(normal.x * PApplet.cos(angle) - normal.z * PApplet.sin(angle));
        v.z = PApplet.round(normal.x * PApplet.sin(angle) - normal.z * PApplet.cos(angle));
        v.y = PApplet.round(normal.y);
        normal = v;
    }
    public void turnZ(float angle) {
        PVector v = new PVector();
        v.x = PApplet.round(normal.x * PApplet.cos(angle) - normal.y * PApplet.sin(angle));
        v.y = PApplet.round(normal.x * PApplet.sin(angle) - normal.y * PApplet.cos(angle));
        v.z = PApplet.round(normal.z);
        normal = v;
    }
    public void rotateFacingX(int dir) {
        if(dir < 0) {
            if(this.facing==Facing.UP){
                this.facing=Facing.FRONT;
            } else if (this.facing==Facing.FRONT) {
                this.facing=Facing.DOWN;
            } else if (this.facing==Facing.DOWN) {
                this.facing=Facing.BACK;
            } else if (this.facing==Facing.BACK) {
                this.facing=Facing.UP;
            }
        } else {
            if(this.facing==Facing.UP){
                this.facing=Facing.BACK;
            } else if (this.facing==Facing.BACK) {
                this.facing=Facing.DOWN;
            } else if (this.facing==Facing.DOWN) {
                this.facing=Facing.FRONT;
            } else if (this.facing==Facing.FRONT) {
                this.facing=Facing.UP;
            }
        }
    }

    public void rotateFacingY(int dir) {
        if(dir < 0) {
            if(this.facing==Facing.FRONT){
                this.facing=Facing.RIGHT;
            } else if (this.facing==Facing.RIGHT) {
                this.facing=Facing.BACK;
            } else if (this.facing==Facing.BACK) {
                this.facing=Facing.LEFT;
            } else if (this.facing==Facing.LEFT) {
                this.facing=Facing.FRONT;
            }
        } else {
            if(this.facing==Facing.FRONT){
                this.facing=Facing.LEFT;
            } else if (this.facing==Facing.LEFT) {
                this.facing=Facing.BACK;
            } else if (this.facing==Facing.BACK) {
                this.facing=Facing.RIGHT;
            } else if (this.facing==Facing.RIGHT) {
                this.facing=Facing.FRONT;
            }
        }
    }

    public void rotateFacingZ(int dir) {
        if(dir < 0) {
            if(this.facing==Facing.UP){
                this.facing=Facing.LEFT;
            } else if (this.facing==Facing.LEFT) {
                this.facing=Facing.DOWN;
            } else if (this.facing==Facing.DOWN) {
                this.facing=Facing.RIGHT;
            } else if (this.facing==Facing.RIGHT) {
                this.facing=Facing.UP;
            }
        } else {
            if(this.facing==Facing.UP){
                this.facing=Facing.RIGHT;
            } else if (this.facing==Facing.RIGHT) {
                this.facing=Facing.DOWN;
            } else if (this.facing==Facing.DOWN) {
                this.facing=Facing.LEFT;
            } else if (this.facing==Facing.LEFT) {
                this.facing=Facing.UP;
            }
        }
    }

    public void show() {
        this.sketch.pushMatrix();
        this.sketch.fill(color);
        this.sketch.rectMode(this.sketch.CENTER);
        this.sketch.translate(0.5f*normal.x, 0.5f*normal.y, 0.5f*normal.z);
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

    public int getId() {
        return id;
    }

    public Facing getFacing() {
        return facing;
    }

    public PVector getNormal() {
        return normal;
    }
}