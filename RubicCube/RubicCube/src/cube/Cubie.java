package cube;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import settings.Facing;
import settings.Settings;

public class Cubie {
    private final PApplet sketch;
    private final Settings setting;
    private final PMatrix3D matrix;

    private final PVector position;

    private final Face[] faces = new Face[6];

    private final PVector solvedPosition;


    public Cubie(PApplet sketch, PMatrix3D m, int x, int y, int z, int facecounter) {
        this.sketch = sketch;
        setting = new Settings();
        position = new PVector(x,y,z);
        solvedPosition = new PVector(x,y,z);
        this.matrix=m;
        faces[0] = new Face(this.sketch, new PVector(0,0,-1), sketch.color(setting.blue.getR(),  setting.blue.getG(),  setting.blue.getB()),   facecounter++, Facing.BACK);
        faces[1] = new Face(this.sketch, new PVector(0,0,1),  sketch.color(setting.green.getR(), setting.green.getG(), setting.green.getB()),  facecounter++, Facing.FRONT);
        faces[2] = new Face(this.sketch, new PVector(0,1,0),  sketch.color(setting.white.getR(), setting.white.getG(), setting.white.getB()),  facecounter++, Facing.DOWN);
        faces[3] = new Face(this.sketch, new PVector(0,-1,0), sketch.color(setting.yellow.getR(),setting.yellow.getG(),setting.yellow.getB()), facecounter++, Facing.UP);
        faces[4] = new Face(this.sketch, new PVector(1,0,0),  sketch.color(setting.orange.getR(),setting.orange.getG(),setting.orange.getB()) ,facecounter++, Facing.RIGHT);
        faces[5] = new Face(this.sketch ,new PVector(-1,0,0), sketch.color(setting.red.getR(),   setting.red.getG(),   setting.red.getB()) ,   facecounter++, Facing.LEFT);

    }

    public void turnFacesX(int dir) {
        for (Face face : faces) {
            face.rotateFacingX(dir);
            face.turnX(dir * this.sketch.HALF_PI);
        }
    }
    public void turnFacesY(int dir) {
        for (Face face : faces) {
            face.rotateFacingY(dir);
            face.turnY(dir * this.sketch.HALF_PI);
        }
    }
    public void turnFacesZ(int dir) {
        for (Face face : faces) {
            face.rotateFacingZ(dir);
            face.turnZ(dir * this.sketch.HALF_PI);
        }
    }
    public void update(int x, int y, int z) {
        matrix.reset();
        matrix.translate(x,y,z);
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void show(){
        this.sketch.noFill();
        this.sketch.stroke(setting.strokeColor);
        this.sketch.strokeWeight(setting.strokeWeight);
        this.sketch.pushMatrix();
        this.sketch.applyMatrix(matrix);
        //if(this.x == -1 && this.y == -1 && this.z == -1) {
        //} else {
        for (Face face : faces) {
            face.show();
        }
        //}
        this.sketch.popMatrix();
    }

    public String toString() {
        return "current:" + "x;" + position.x + " y:" + position.y + " :z" + position.z + "    solved: " + "x;" + solvedPosition.x + " y:" + solvedPosition.y + " :z" + solvedPosition.z+"\n";
    }
    public String toStringWithId() {
        StringBuilder returnString = new StringBuilder("current:" + "x;" + position.x + " y:" + position.y + " :z" + position.z + " ids:");
        for(Face face : faces) {
            returnString.append(face.getId());
        }
        return returnString.toString();
    }

    public PVector getPosition() {
        return position;
    }

    public Face[] getFaces() {
        return faces;
    }

    public PVector getSolvedPosition() {
        return solvedPosition;
    }
}
