package cube;

import processing.core.PApplet;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;

import java.util.ArrayList;
import settings.*;

public class Cube {
    private final PApplet sketch;
    Settings setting;
    public Cubie[] data = new Cubie[3 * 3 * 3];
    int facecounter = 0;
    int[] edges = {1,3,5,7,9,11,15,17,19,21,23,25};
    int[] corners = {0, 2, 6, 8, 18, 20, 24, 26};

    public Cube(PApplet sketch, Settings setting) {
        this.setting = setting;
        this.sketch = sketch;
        int index = 0;
        for (int x =-  1; x <=  1; x++) {
            for (int y =-  1; y <=  1; y++) {
                for (int z =-  1; z <=  1; z++) {
                    PMatrix3D matrix = new PMatrix3D();
                    matrix.translate(x, y, z);
                    data[index] = new Cubie(this.sketch, matrix, x, y, z, facecounter, this.setting);
                    facecounter += 6;
                    index++;
                }
            }
        }
    }

    public ArrayList<Cubie> checkIfAllEdgesAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<>();
        for (int edge : this.edges) {
            if (data[edge].getPosition().x == data[edge].getSolvedPosition().x && data[edge].getPosition().y == data[edge].getSolvedPosition().y && data[edge].getPosition().z == data[edge].getSolvedPosition().z) {
                if (data[edge].getFaces()[0].getFacing() != Facing.BACK || data[edge].getFaces()[2].getFacing() != Facing.DOWN) {
                    notInPosition.add(data[edge]);
                }
            } else {
                notInPosition.add(data[edge]);
            }
        }
        return notInPosition;
    }

    public ArrayList<Cubie> checkIfAllCornersAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<>();
        for (int corner : this.corners) {
            if (data[corner].getPosition().x == data[corner].getSolvedPosition().x && data[corner].getPosition().y == data[corner].getSolvedPosition().y && data[corner].getPosition().z == data[corner].getSolvedPosition().z) {
                if (data[corner].getFaces()[0].getFacing() != Facing.BACK || data[corner].getFaces()[2].getFacing() != Facing.DOWN) {
                    notInPosition.add(data[corner]);
                }
            } else {
                notInPosition.add(data[corner]);
            }
        }
        return notInPosition;
    }

    public void turnX(int index, int dir) {
        for (Cubie cubie : data) {
            if (cubie.getPosition().x == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * this.sketch.HALF_PI);
                matrix.translate(cubie.getPosition().y, cubie.getPosition().z);
                cubie.update((int) cubie.getPosition().x, PApplet.round(matrix.m02), PApplet.round(matrix.m12));
                cubie.turnFacesX(dir);
            }
        }
    }
    public void turnY(int index, int dir) {
        for (Cubie cubie : data) {
            if (cubie.getPosition().y == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(-dir * this.sketch.HALF_PI);
                matrix.translate(cubie.getPosition().x, cubie.getPosition().z);
                cubie.update(PApplet.round(matrix.m02), (int) cubie.getPosition().y, PApplet.round(matrix.m12));
                cubie.turnFacesY(-dir);
            }
        }
    }

    public void turnZ(int index, int dir) {
        for (Cubie cubie : data) {
            if (cubie.getPosition().z == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * this.sketch.HALF_PI);
                matrix.translate(cubie.getPosition().x, cubie.getPosition().y);
                cubie.update(PApplet.round(matrix.m02), PApplet.round(matrix.m12), (int) cubie.getPosition().z);
                cubie.turnFacesZ(dir);
            }
        }
    }

    public void drawCube(Move move) {
        this.sketch.scale(50);
        for (Cubie cubie : data) {
            this.sketch.push();
            if (PApplet.abs(cubie.getPosition().z) == 1 && cubie.getPosition().z == move.getZ()) {
                this.sketch.rotateZ(move.getAngle());
            } else if (PApplet.abs(cubie.getPosition().x) == 1 && cubie.getPosition().x == move.getX()) {
                this.sketch.rotateX(move.getAngle());
            } else if (PApplet.abs(cubie.getPosition().y) == 1 && cubie.getPosition().y == move.getY()) {
                this.sketch.rotateY(move.getAngle());
            } else if (PApplet.abs(cubie.getPosition().z) == 0 && cubie.getPosition().z == PApplet.abs(move.getZ()) - 2) {
                this.sketch.rotateZ(move.getAngle());
            } else if (PApplet.abs(cubie.getPosition().y) == 0 && cubie.getPosition().y == PApplet.abs(move.getY()) - 2) {
                this.sketch.rotateY(move.getAngle());
            } else if (PApplet.abs(cubie.getPosition().x) == 0 && cubie.getPosition().x == PApplet.abs(move.getX()) - 2) {
                this.sketch.rotateX(move.getAngle());
            }
            cubie.show();
            this.sketch.pop();
        }
    }
}