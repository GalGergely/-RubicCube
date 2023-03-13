class Face {
  PVector normal;
  color c;
  int id;
  IdDisplay display;
  Facing facing;

  Face(PVector normal, color c, int id, Facing facing) {
    this.normal = normal;
    this.c=c;
    this.id = id;
    this.facing = facing;
    this.display = new IdDisplay(this.id);
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
  void rotateFacingX(int dir) {
    if(dir < 0) {
      if(this.facing==Facing.UP){
        this.facing=Facing.FRONT;
      } else if (this.facing==Facing.FRONT) {
        this.facing=Facing.DOWN;
      } else if (this.facing==Facing.DOWN) { 
        this.facing=Facing.BACK;
      } else if (this.facing==Facing.BACK) {
        this.facing=Facing.UP;
      }
    } else {
      if(this.facing==Facing.UP){
        this.facing=Facing.BACK;
      } else if (this.facing==Facing.BACK) {
        this.facing=Facing.DOWN;
      } else if (this.facing==Facing.DOWN) { 
        this.facing=Facing.FRONT;
      } else if (this.facing==Facing.FRONT) {
        this.facing=Facing.UP;
      }
    }
  }

  void rotateFacingY(int dir) {
    if(dir < 0) {
      if(this.facing==Facing.FRONT){
        this.facing=Facing.RIGHT;
      } else if (this.facing==Facing.RIGHT) {
        this.facing=Facing.BACK;
      } else if (this.facing==Facing.BACK) { 
        this.facing=Facing.LEFT;
      } else if (this.facing==Facing.LEFT) {
        this.facing=Facing.FRONT;
      }
    } else {
      if(this.facing==Facing.FRONT){
        this.facing=Facing.LEFT;
      } else if (this.facing==Facing.LEFT) {
        this.facing=Facing.BACK;
      } else if (this.facing==Facing.BACK) { 
        this.facing=Facing.RIGHT;
      } else if (this.facing==Facing.RIGHT) {
        this.facing=Facing.FRONT;
      }
    }
  }
  
  void rotateFacingZ(int dir) {
    if(dir < 0) {
      if(this.facing==Facing.UP){
        this.facing=Facing.LEFT;
      } else if (this.facing==Facing.LEFT) {
        this.facing=Facing.DOWN;
      } else if (this.facing==Facing.DOWN) { 
        this.facing=Facing.RIGHT;
      } else if (this.facing==Facing.RIGHT) {
        this.facing=Facing.UP;
      }
    } else {
      if(this.facing==Facing.UP){
        this.facing=Facing.RIGHT;
      } else if (this.facing==Facing.RIGHT) {
        this.facing=Facing.DOWN;
      } else if (this.facing==Facing.DOWN) { 
        this.facing=Facing.LEFT;
      } else if (this.facing==Facing.LEFT) {
        this.facing=Facing.UP;
      }
    }
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
    //this.display.show();
    popMatrix();
  }
}
