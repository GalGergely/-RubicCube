class Cubie {
  Settings setting = new Settings();
  PMatrix3D matrix;
  int x;
  int y;
  int z;
  Face[] faces = new Face[6];

  PVector solvedPosition;


  Cubie(PMatrix3D m, int x, int y, int z, int facecounter) {
      solvedPosition = new PVector(x,y,z);
      this.matrix=m;
      this.x=x;
      this.y=y;
      this.z=z;
      faces[0] = new Face(new PVector(0,0,-1), setting.blue, facecounter++, Facing.BACK);
      faces[1] = new Face(new PVector(0,0,1), setting.green, facecounter++, Facing.FRONT);
      faces[2] = new Face(new PVector(0,1,0), setting.white, facecounter++, Facing.DOWN);
      faces[3] = new Face(new PVector(0,-1,0), setting.yellow, facecounter++, Facing.UP);
      faces[4] = new Face(new PVector(1,0,0), setting.orange ,facecounter++, Facing.RIGHT);
      faces[5] = new Face(new PVector(-1,0,0), setting.red ,facecounter++, Facing.LEFT);

  }

  void turnFacesX(int dir) {
    for(int i=0; i<faces.length; i++) {
      faces[i].rotateFacingX(dir);
      faces[i].turnX(dir*HALF_PI);
    }
  }
    void turnFacesY(int dir) {
    for(int i=0; i<faces.length; i++) {
      faces[i].rotateFacingY(dir);
      faces[i].turnY(dir*HALF_PI);
    }
  }
    void turnFacesZ(int dir) {
    for(int i=0; i<faces.length; i++) {
      faces[i].rotateFacingZ(dir);
      faces[i].turnZ(dir*HALF_PI);
    }
  }
   void update(int x, int y, int z) {
     matrix.reset();
     matrix.translate(x,y,z);
      this.x=x;
      this.y=y;
      this.z=z;
   }

   void show(){
   noFill();
   stroke(setting.strokeColor);
   strokeWeight(setting.strokeWeight);
   pushMatrix();
   applyMatrix(matrix);
   //if(this.x == 1 && this.y == 0 && this.z == 1) {
   //} else {
   for(int i=0; i<faces.length; i++) {
     faces[i].show();
   //}
   }
   popMatrix();
  }

  String toString() {
    return "current:" + "x;" + x + " y:" + y + " :z" + z + "    solved: " + "x;" + solvedPosition.x + " y:" + solvedPosition.y + " :z" + solvedPosition.z+"\n";
  }
  String toStringWithId() {
    String returnString = "current:" + "x;" + x + " y:" + y + " :z" + z + " ids:";
    for(Face face : faces) {
        returnString += face.id;
    }
    return returnString;
  }
}
