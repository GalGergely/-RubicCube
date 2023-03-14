package cube;

import processing.core.PApplet;
import processing.core.PVector;
import settings.Facing;
import settings.IdDisplay;

public class Face {
    private PApplet sketch;
    private PVector normal;
    private int c;
    private int id;
    private IdDisplay display;
    private Facing facing;

    public Face(PApplet sketch, PVector normal, int c, int id, Facing facing) {
        this.sketch = sketch;
        this.normal = normal;
        this.c=c;
        this.id = id;
        this.facing = facing;
        this.display = new IdDisplay(this.sketch, this.id);
    }
    public void turnX(float angle) {
        PVector v = new PVector();
        v.y = PApplet.round(normal.y * this.sketch.cos(angle) - normal.z * this.sketch.sin(angle));
        v.z = this.sketch.round(normal.y * this.sketch.sin(angle) - normal.z * this.sketch.cos(angle));
        v.x = this.sketch.round(normal.x);
        normal = v;
    }
    public void turnY(float angle) {
        PVector v = new PVector();
        v.x = this.sketch.round(normal.x * this.sketch.cos(angle) - normal.z *this.sketch. sin(angle));
        v.z = this.sketch.round(normal.x * this.sketch.sin(angle) - normal.z * this.sketch.cos(angle));
        v.y = this.sketch.round(normal.y);
        normal = v;
    }
    public void turnZ(float angle) {
        PVector v = new PVector();
        v.x = this.sketch.round(normal.x * this.sketch.cos(angle) - normal.y * this.sketch.sin(angle));
        v.y = this.sketch.round(normal.x * this.sketch.sin(angle) - normal.y * this.sketch.cos(angle));
        v.z = this.sketch.round(normal.z);
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
        this.sketch.fill(c);
        this.sketch.rectMode(this.sketch.CENTER);
        this.sketch.translate(0.5f*normal.x, 0.5f*normal.y, 0.5f*normal.z);
        if (this.sketch.abs(normal.x) > 0) {
            this.sketch.rotateY(this.sketch.HALF_PI);
        } else if (this.sketch.abs(normal.y) > 0) {
            this.sketch.rotateX(this.sketch.HALF_PI);
        }
        this.sketch.square(0, 0, 1);
        this.display.show();
        this.sketch.popMatrix();
    }

    public PApplet getSketch() {
        return sketch;
    }

    public PVector getNormal() {
        return normal;
    }

    public int getC() {
        return c;
    }

    public int getId() {
        return id;
    }

    public IdDisplay getDisplay() {
        return display;
    }

    public Facing getFacing() {
        return facing;
    }
}