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


    public Cubie(PApplet sketch, PMatrix3D matrix, int x, int y, int z, int facecounter, Settings setting) {
        this.sketch = sketch;
        this.setting = setting;
        position = new PVector(x,y,z);
        solvedPosition = new PVector(x,y,z);
        this.matrix=matrix;
        faces[0] = new Face(this.sketch, new PVector(0,0,-1), sketch.color(setting.blue.getRed(),  setting.blue.getGreen(),  setting.blue.getBlue()),   facecounter++, Facing.BACK);
        faces[1] = new Face(this.sketch, new PVector(0,0,1),  sketch.color(setting.green.getRed(), setting.green.getGreen(), setting.green.getBlue()),  facecounter++, Facing.FRONT);
        faces[2] = new Face(this.sketch, new PVector(0,1,0),  sketch.color(setting.white.getRed(), setting.white.getGreen(), setting.white.getBlue()),  facecounter++, Facing.DOWN);
        faces[3] = new Face(this.sketch, new PVector(0,-1,0), sketch.color(setting.yellow.getRed(),setting.yellow.getGreen(),setting.yellow.getBlue()), facecounter++, Facing.UP);
        faces[4] = new Face(this.sketch, new PVector(1,0,0),  sketch.color(setting.orange.getRed(),setting.orange.getGreen(),setting.orange.getBlue()) ,facecounter++, Facing.RIGHT);
        faces[5] = new Face(this.sketch ,new PVector(-1,0,0), sketch.color(setting.red.getRed(),   setting.red.getGreen(),   setting.red.getBlue()) ,   facecounter++, Facing.LEFT);

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
        this.sketch.stroke(setting.strokeColor.getRGB());
        this.sketch.strokeWeight(setting.strokeWeight);
        this.sketch.pushMatrix();
        this.sketch.applyMatrix(matrix);
        //if(this.position.x == -1 && this.position.y == -1 && this.position.z == -1) {
        //} else {
        for (Face face : faces) {
            face.show();
        //}
        }
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
