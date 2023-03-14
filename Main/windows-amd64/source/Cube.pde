class Cube {
    Settings setting = new Settings();
    int dimension = setting.dimension;
    public Cubie[] data = new Cubie[dimension * dimension * dimension];
    int facecounter = 0;
    int[] edges = {1,3,5,7,9,11,15,17,19,21,23,25};
    int[] corners = {0, 2, 6, 8, 18, 20, 24, 26};
    
    Cube() {
        int index = 0;
        for (int x =-  1; x <=  1; x++) {
            for (int y =-  1; y <=  1; y++) {
                for (int z =-  1; z <=  1; z++) {
                    PMatrix3D matrix = new PMatrix3D();
                    matrix.translate(x, y, z);
                    data[index] = new Cubie(matrix, x, y, z, facecounter);
                    facecounter += 6;
                    index++;
                }
            }
        }
    }
    
    ArrayList<Cubie> checkIfAllEdgesAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<Cubie>();
        for (int edge : this.edges) {
            if (data[edge].x == data[edge].solvedPosition.x && data[edge].y == data[edge].solvedPosition.y && data[edge].z == data[edge].solvedPosition.z) {
                if (data[edge].faces[0].facing == Facing.BACK && data[edge].faces[2].facing == Facing.DOWN) {
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
    
    ArrayList<Cubie> checkIfAllCornersAreSolved() {
        ArrayList<Cubie> notInPosition = new ArrayList<Cubie>();
        for (int corner : this.corners) {
            if (data[corner].x == data[corner].solvedPosition.x && data[corner].y == data[corner].solvedPosition.y && data[corner].z == data[corner].solvedPosition.z) {
                if (data[corner].faces[0].facing == Facing.BACK && data[corner].faces[2].facing == Facing.DOWN) {
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
    
    void turnX(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].x == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * HALF_PI);
                matrix.translate(data[i].y, data[i].z);
                data[i].update(data[i].x, round(matrix.m02), round(matrix.m12));
                data[i].turnFacesX(dir);
            }
        }
    }
    void turnY(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].y == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate( -dir * HALF_PI);
                matrix.translate(data[i].x, data[i].z);
                data[i].update(round(matrix.m02), data[i].y, round(matrix.m12));
                data[i].turnFacesY( -dir);
            }
        }
    }
    
    void turnZ(int index, int dir) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].z == index) {
                PMatrix2D matrix = new PMatrix2D();
                matrix.rotate(dir * HALF_PI);
                matrix.translate(data[i].x, data[i].y);
                data[i].update(round(matrix.m02), round(matrix.m12), data[i].z);
                data[i].turnFacesZ(dir);
            }
        }
    } 
    
    void drawCube(Move move) {
        scale(50);
        for (int i = 0; i < data.length; i++) {
            push();
            if (abs(data[i].z) ==  1 && data[i].z == move.z) {
                rotateZ(move.angle);
            } else if (abs(data[i].x) ==  1 && data[i].x == move.x) {
                rotateX(move.angle);
            } else if (abs(data[i].y) ==  1 && data[i].y == move.y) {
                rotateY(move.angle);
            } else if (abs(data[i].z) ==  0 && data[i].z == abs(move.z) - 2) {
                rotateZ(move.angle);
            } else if (abs(data[i].y) ==  0 && data[i].y == abs(move.y) - 2) {
                rotateY(move.angle);
            } else if (abs(data[i].x) ==  0 && data[i].x == abs(move.x) - 2) {
                rotateX(move.angle);
            }
            data[i].show();
            pop();
        }
    }
}
