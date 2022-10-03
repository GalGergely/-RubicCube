class Ai {
  Cube cube;
  Settings setting = new Settings();
  int dimension = setting.dimension;
  Box buffer;

  Ai(Cube cube) {
    this.cube = cube;
  }
  void solve() {
    buffer = whatInBuffer();
    println(buffer);
    if (buffer.y==-1) { // sárga oldal
      for (Face face : buffer.faces) {
        if(face.normal.y==-1){
          println(face.id);
        }
      }
    }
  }
  Box whatInBuffer() {
    for (int i=0; i<dimension*dimension*dimension; i++) {
      if (cube.data[i].x==1 && cube.data[i].y==-1 && cube.data[i].z==0) {
        return cube.data[i];
      }
    }
    return cube.data[0];
  }
}
