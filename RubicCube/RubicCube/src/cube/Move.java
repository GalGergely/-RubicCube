package cube;

import processing.core.PApplet;

public class Move {
    private final PApplet sketch;
    private float angle = 0;
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private final int direction;
    private boolean animate = false;
    private final Cube cube;

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
            if (PApplet.abs(this.angle) >= this.sketch.HALF_PI) {
                this.angle = 0;
                this.animate = false;
                if (PApplet.abs(z)==1) {
                    cube.turnZ(z, direction);
                } else if(PApplet.abs(z)==2) {
                    cube.turnZ(0, direction);
                }else if (PApplet.abs(x)==1) {
                    cube.turnX(x, direction);
                }else if(PApplet.abs(x)==2) {
                    cube.turnX(0, direction);
                }else if (PApplet.abs(y)==1) {
                    cube.turnY(y, direction);
                }else if(PApplet.abs(y)==2) {
                    cube.turnY(0, direction);
                }
            }
        }
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
    public boolean isAnimate() { return animate; }
}
