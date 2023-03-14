package cube;

import processing.core.PApplet;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;

import java.util.ArrayList;
import settings.*;

public class Cube {
    private PApplet sketch;
    Settings setting;
    public Cubie[] data = new Cubie[3 * 3 * 3];
    int facecounter = 0;
    int[] edges = {1,3,5,7,9,11,15,17,19,21,23,25};
    int[] corners = {0, 2, 6, 8, 18, 20, 24, 26};

    public Cube(PApplet sketch) {
        this.sketch = sketch;
        this.setting = new Settings();
        int index = 0;
        for (int x =-  1; x <=  1; x++) {
            for (int y =-  1; y <=  1; y++) {
                for (int z =-  1; z <=  1; z++) {
                    PMatrix3D matrix = new PMatrix3D();
                    matrix.translate(x, y, z);
                    data[index] = new Cubie(this.sketch, matrix, x, y, z, facecounter);
                    facecounter += 6;
                    index++;
                }
            }
        }
    }

    public ArrayList<Cubie> checkIfAllEdgesAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<Cubie>();
        for (int edge : this.edges) {
            if (data[edge].getX() == data[edge].getSolvedPosition().x && data[edge].getY() == data[edge].getSolvedPosition().y && data[edge].getZ() == data[edge].getSolvedPosition().z) {
                if (data[edge].getFaces()[0].getFacing() == Facing.BACK && data[edge].getFaces()[2].getFacing() == Facing.DOWN) {
                    //its in his position
                } else {
                    notInPosition.add(data[edge]);
                }
            } else {
                notInPosition.add(data[edge]);
            }
        }
        return notInPosition;
    }

    public ArrayList<Cubie> checkIfAllCornersAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<Cubie>();
        for (int corner : this.corners) {
            if (data[corner].getX() == data[corner].getSolvedPosition().x && data[corner].getY() == data[corner].getSolvedPosition().y && data[corner].getZ() == data[corner].getSolvedPosition().z) {
                if (data[corner].getFaces()[0].getFacing() == Facing.BACK && data[corner].getFaces()[2].getFacing() == Facing.DOWN) {
                    //its in his position
                } else {
                    notInPosition.add(data[corner]);
                }
            } else {
                notInPosition.add(data[corner]);
            }
        }
        return notInPosition;
    }

    public void turnX(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].getX() == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * this.sketch.HALF_PI);
                matrix.translate(data[i].getY(), data[i].getZ());
                data[i].update(data[i].getX(), this.sketch.round(matrix.m02), this.sketch.round(matrix.m12));
                data[i].turnFacesX(dir);
            }
        }
    }
    public void turnY(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].getY() == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate( -dir * this.sketch.HALF_PI);
                matrix.translate(data[i].getX(), data[i].getZ());
                data[i].update(this.sketch.round(matrix.m02), data[i].getY(), this.sketch.round(matrix.m12));
                data[i].turnFacesY( -dir);
            }
        }
    }

    public void turnZ(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].getZ() == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * this.sketch.HALF_PI);
                matrix.translate(data[i].getX(), data[i].getY());
                data[i].update(this.sketch.round(matrix.m02), this.sketch.round(matrix.m12), data[i].getZ());
                data[i].turnFacesZ(dir);
            }
        }
    }

    public void drawCube(Move move) {
        this.sketch.scale(50);
        for (int i = 0; i < data.length; i++) {
            this.sketch.push();
            if (this.sketch.abs(data[i].getZ()) ==  1 && data[i].getZ() == move.getZ()) {
                this.sketch.rotateZ(move.getAngle());
            } else if (this.sketch.abs(data[i].getX()) ==  1 && data[i].getX() == move.getX()) {
                this.sketch.rotateX(move.getAngle());
            } else if (this.sketch.abs(data[i].getY()) ==  1 && data[i].getY() == move.getY()) {
                this.sketch.rotateY(move.getAngle());
            } else if (this.sketch.abs(data[i].getZ()) ==  0 && data[i].getZ() == this.sketch.abs(move.getZ()) - 2) {
                this.sketch.rotateZ(move.getAngle());
            } else if (this.sketch.abs(data[i].getY()) ==  0 && data[i].getY() == this.sketch.abs(move.getY()) - 2) {
                this.sketch.rotateY(move.getAngle());
            } else if (this.sketch.abs(data[i].getX()) ==  0 && data[i].getX() == this.sketch.abs(move.getX()) - 2) {
                this.sketch.rotateX(move.getAngle());
            }
            data[i].show();
            this.sketch.pop();
        }
    }
}