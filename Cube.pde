class Cube {
  Settings setting = new Settings();
  int dimension = setting.dimension;
  Box[] data = new Box[dimension*dimension*dimension];
  boolean shuffled = false;
  String moveList = "";

  Cube() {
    int index = 0;
    for (int x=-1; x<=1; x++) {
      for (int y=-1; y<=1; y++) {
        for (int z=-1; z<=1; z++) {
          PMatrix3D matrix = new PMatrix3D();
          matrix.translate(x, y, z);
          data[index] = new Box(matrix, x, y, z);
          index++;
        }
      }
      data[2].high = true;
      data[0].high = true;
    }
  }

  void turnX(int index, int dir) {
    for (int i=0; i<data.length; i++) {
      if (data[i].x == index) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(dir*HALF_PI);
        matrix.translate(data[i].y, data[i].z);
        data[i].update(data[i].x, round(matrix.m02), round(matrix.m12));
        data[i].turnFacesX(dir);
      }
    }
  }
  void turnY(int index, int dir) {
    for (int i=0; i<data.length; i++) {
      if (data[i].y == index) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(-dir*HALF_PI);
        matrix.translate(data[i].x, data[i].z);
        data[i].update(round(matrix.m02), data[i].y, round(matrix.m12));
        data[i].turnFacesY(-dir);
      }
    }
  }

  void turnZ(int index, int dir) {
    for (int i=0; i<data.length; i++) {
      if (data[i].z == index) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(dir*HALF_PI);
        matrix.translate(data[i].x, data[i].y);
        data[i].update(round(matrix.m02), round(matrix.m12), data[i].z);
        data[i].turnFacesZ(dir);
      }
    }
  }
  
  void Yperm() {
  String[] yperm = {"r", "u", "R", "U", "R", "f", "r", "r", "U", "R", "U", "r","u","R", "F"};
  for(int i=0; i < yperm.length; i++) {
      print(yperm[i]);
      moveList += yperm[i];
    }
  }
  
  String[] mirrorArray(String[] array) {
    String[] returnarray = {};
    for(int i=array.length-1; i>=0 ; i--) {
      returnarray = append(returnarray, array[i]);
    }
    return returnarray;
  }
  
  void shuffleCube(){
  int rand = round(random(10, 20));
  println(rand);
  char moves[] = {'l','L','r','R','u','U','d','D','f','F','b','B'};
  for(int i=0; i<rand; i++) {
    int random = round(random(0, 11));
    moveList += moves[random];
  }
}
  

  void drawCube(Move move) {
    scale(50);
    for (int i=0; i<data.length; i++) {
      push();
      if (abs(data[i].z)>0 && data[i].z == move.z) {
        rotateZ(move.angle);
      } else if (abs(data[i].x)>0 && data[i].x == move.x) {
        rotateX(move.angle);
      } else if (abs(data[i].y)>0 && data[i].y == move.y) {
        rotateY(move.angle);
      }
      data[i].show();
      pop();
    }
  }
}
