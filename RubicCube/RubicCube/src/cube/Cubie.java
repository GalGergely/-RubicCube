package cube;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import settings.Facing;
import settings.Settings;

public class Cubie {
    private PApplet sketch;
    private Settings setting;
    private PMatrix3D matrix;
    private int x;
    private int y;
    private int z;
    private Face[] faces = new Face[6];

    private PVector solvedPosition;


    public Cubie(PApplet sketch, PMatrix3D m, int x, int y, int z, int facecounter) {
        this.sketch = sketch;
        setting = new Settings();
        solvedPosition = new PVector(x,y,z);
        this.matrix=m;
        this.x=x;
        this.y=y;
        this.z=z;
        faces[0] = new Face(this.sketch, new PVector(0,0,-1), sketch.color(0,0,255), facecounter++, Facing.BACK);
        faces[1] = new Face(this.sketch, new PVector(0,0,1), sketch.color(0,255,0), facecounter++, Facing.FRONT);
        faces[2] = new Face(this.sketch, new PVector(0,1,0), sketch.color(255,255,255), facecounter++, Facing.DOWN);
        faces[3] = new Face(this.sketch, new PVector(0,-1,0), sketch.color(255,255,0), facecounter++, Facing.UP);
        faces[4] = new Face(this.sketch, new PVector(1,0,0), sketch.color(255,150,0) ,facecounter++, Facing.RIGHT);
        faces[5] = new Face(this.sketch ,new PVector(-1,0,0), sketch.color(255,0,0) ,facecounter++, Facing.LEFT);

    }

    public void turnFacesX(int dir) {
        for(int i=0; i<faces.length; i++) {
            faces[i].rotateFacingX(dir);
            faces[i].turnX(dir*this.sketch.HALF_PI);
        }
    }
    public void turnFacesY(int dir) {
        for(int i=0; i<faces.length; i++) {
            faces[i].rotateFacingY(dir);
            faces[i].turnY(dir*this.sketch.HALF_PI);
        }
    }
    public void turnFacesZ(int dir) {
        for(int i=0; i<faces.length; i++) {
            faces[i].rotateFacingZ(dir);
            faces[i].turnZ(dir*this.sketch.HALF_PI);
        }
    }
    public void update(int x, int y, int z) {
        matrix.reset();
        matrix.translate(x,y,z);
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void show(){
        this.sketch.noFill();
        this.sketch.stroke(setting.strokeColor);
        this.sketch.strokeWeight(setting.strokeWeight);
        this.sketch.pushMatrix();
        this.sketch.applyMatrix(matrix);
        //if(this.x == -1 && this.y == -1 && this.z == -1) {
        //} else {
        for(int i=0; i<faces.length; i++) {
            faces[i].show();
            //}
        }
        this.sketch.popMatrix();
    }

    public String toString() {
        return "current:" + "x;" + x + " y:" + y + " :z" + z + "    solved: " + "x;" + solvedPosition.x + " y:" + solvedPosition.y + " :z" + solvedPosition.z+"\n";
    }
    public String toStringWithId() {
        String returnString = "current:" + "x;" + x + " y:" + y + " :z" + z + " ids:";
        for(Face face : faces) {
            returnString += face.getId();
        }
        return returnString;
    }

    public PApplet getSketch() {
        return sketch;
    }

    public Settings getSetting() {
        return setting;
    }

    public PMatrix3D getMatrix() {
        return matrix;
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

    public Face[] getFaces() {
        return faces;
    }

    public PVector getSolvedPosition() {
        return solvedPosition;
    }
}
