import peasy.*;

Cube cube;
PeasyCam cam;
Settings setting;

void setup() {
size(600,600, P3D);
setting = new Settings();
cube = new Cube();
cam = new PeasyCam(this, setting.cameraZoomIn);
}

void draw () {
  background(setting.backgroundColor);
  cube.drawCube();
}
