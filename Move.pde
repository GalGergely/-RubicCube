class Move {
  float angle = 0;
  int x = 0;
  int y = 0;
  int z = 0;
  int direction;
  boolean animate = false;

  Move(int x, int y, int z, int direction, Cube cube) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.direction = direction;
  }
  void start() {
    this.animate = true;
  }
  void update() {
    if (this.animate) {
      this.angle += direction * 0.1; //animacioval
      //this.angle=HALF_PI*direction; //animvacio nelkul
      if (abs(this.angle) >= HALF_PI) {
        this.angle = 0;
        this.animate = false;
        if (abs(z)==1) {
          cube.turnZ(z, direction);
        } else if(abs(z)>1) {
          cube.turnZ(0, direction);
        }else if (abs(x)==1) {
          cube.turnX(x, direction);
        }else if(abs(x)>1) { // middle
          cube.turnX(0, direction);
        }else if (abs(y)==1) {
          cube.turnY(y, direction);
        }else if(abs(y)>1) {
          cube.turnY(0, direction);
        }
      }
    }
  }
}
