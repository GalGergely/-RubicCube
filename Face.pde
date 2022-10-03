class Face {
  PVector normal;
  color c;
  PVector pos;
  int id;

  Face(PVector normal, color c, PVector pos, int id) {
    this.normal = normal;
    this.c=c;
    this.pos = pos;
    this.id = id;
  }
    void turnX(float angle) {
    PVector v = new PVector();
    v.y = round(normal.y * cos(angle) - normal.z * sin(angle));
    v.z = round(normal.y * sin(angle) - normal.z * cos(angle));
    v.x = round(normal.x);
    normal = v;
  }
  void turnY(float angle) {
    PVector v = new PVector();
    v.x = round(normal.x * cos(angle) - normal.z * sin(angle));
    v.z = round(normal.x * sin(angle) - normal.z * cos(angle));
    v.y = round(normal.y);
    normal = v;
  }
  void turnZ(float angle) {
    PVector v = new PVector();
    v.x = round(normal.x * cos(angle) - normal.y * sin(angle));
    v.y = round(normal.x * sin(angle) - normal.y * cos(angle));
    v.z = round(normal.z);
    normal = v;
  }
  
  void show() {
    pushMatrix();
    fill(c);
    rectMode(CENTER);
    translate(0.5*normal.x, 0.5*normal.y, 0.5*normal.z);
    if (abs(normal.x) > 0) {
      rotateY(HALF_PI);
    } else if (abs(normal.y) > 0) {
      rotateX(HALF_PI);
    }
    square(0, 0, 1);
    fill(0);
    scale(0.01);
    textSize(10);
    text(id, 1, 1, 1);
    text(id, -1, -1, -1);
    popMatrix();
  }
}
