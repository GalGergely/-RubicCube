package cube;

import processing.core.PApplet;

public class Move {
    private PApplet sketch;
    private float angle = 0;
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private int direction;
    private boolean animate = false;
    private Cube cube;

    public Move(PApplet sketch, int x, int y, int z, int direction, Cube cube) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = direction;
        this.cube = cube;
    }
    public void start() {
        this.animate = true;
    }
    public void update() {
        if (this.animate) {
            this.angle += direction * 0.3f; //animacioval
            //this.angle=HALF_PI*direction; //animvacio nelkul
            if (this.sketch.abs(this.angle) >= this.sketch.HALF_PI) {
                this.angle = 0;
                this.animate = false;
                if (this.sketch.abs(z)==1) {
                    cube.turnZ(z, direction);
                } else if(this.sketch.abs(z)==2) {
                    cube.turnZ(0, direction);
                }else if (this.sketch.abs(x)==1) {
                    cube.turnX(x, direction);
                }else if(this.sketch.abs(x)==2) { // middle
                    cube.turnX(0, direction);
                }else if (this.sketch.abs(y)==1) {
                    cube.turnY(y, direction);
                }else if(this.sketch.abs(y)==2) {
                    cube.turnY(0, direction);
                }
            }
        }
    }

    public PApplet getSketch() {
        return sketch;
    }

    public float getAngle() {
        return angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isAnimate() {
        return animate;
    }

    public Cube getCube() {
        return cube;
    }
}
