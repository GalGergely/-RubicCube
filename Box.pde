class Box {
  Settings setting = new Settings();
  PMatrix3D matrix;
  int x;
  int y;
  int z;
  boolean high = false;
  Face[] faces = new Face[6];
  PVector solvedPosition;
  
  
  Box(PMatrix3D m, int x, int y, int z) {
      solvedPosition = new PVector(x,y,z);
      this.matrix=m;
      this.x=x;
      this.y=y;
      this.z=z;
      faces[0] = new Face(new PVector(0,0,-1), setting.blue, new PVector(x,y,z));
      faces[1] = new Face(new PVector(0,0,1), setting.green,  new PVector(x,y,z));
      faces[2] = new Face(new PVector(0,1,0), setting.white,  new PVector(x,y,z));
      faces[3] = new Face(new PVector(0,-1,0), setting.yellow,  new PVector(x,y,z));
      faces[4] = new Face(new PVector(1,0,0), setting.orange,  new PVector(x,y,z));
      faces[5] = new Face(new PVector(-1,0,0), setting.red,  new PVector(x,y,z));
      
  }
  
  void turnFacesX(int dir) {
    for(int i=0; i<faces.length; i++) {
      faces[i].turnX(dir*HALF_PI);
    }
  }
    void turnFacesY(int dir) {
    for(int i=0; i<faces.length; i++) {
      faces[i].turnY(dir*HALF_PI);
    }
  }
    void turnFacesZ(int dir) {
    for(int i=0; i<faces.length; i++) {
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
   for(int i=0; i<faces.length; i++) {
     faces[i].show();
   }
   popMatrix();
  }
  
  String toString() {
    return "current:" + "x;" + x + " y:" + y + " :z" + z + "    solved: " + "x;" + solvedPosition.x + " y:" + solvedPosition.y + " :z" + solvedPosition.z;
  }
}
