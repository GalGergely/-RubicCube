class Ai {
  Cube cube;
  Settings setting = new Settings();
  int dimension = setting.dimension;

  Ai(Cube cube) {
    this.cube = cube;
  }
  void solve() {
    if (cube.shuffled) {
      for (int i=0; i<dimension*dimension*dimension; i++) {
        println(cube.data[i]);
      }
      println();
    }
    //megnezni hol vannak a sarga oldalra valo elemek
    for (int i=0; i<dimension*dimension*dimension; i++) {
      if (cube.data[i].solvedPosition.y==-1) {
      }
    }
  }
}
