class Cube {
  Settings setting = new Settings();
  int dimension = setting.dimension;
  Box[][][] data = new Box[dimension][dimension][dimension];
  Cube() {
    for (int i=0; i<setting.dimension; i++) {
      for (int j=0; j<setting.dimension; j++) {
        for (int k=0; k<setting.dimension; k++) {
          data[i][j][k] = new Box(setting.sideLength * i - setting.cubeOffstet, setting.sideLength * j - setting.cubeOffstet, setting.sideLength * k - setting.cubeOffstet, setting.sideLength);
        }
      }
    }
  }
  
  void drawCube() {
     for (int i=0; i<setting.dimension; i++) {
      for (int j=0; j<setting.dimension; j++) {
        for (int k=0; k<setting.dimension; k++) {
          data[i][j][k].show();
        }
      }
    }
    
  }
}
