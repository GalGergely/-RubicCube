import peasy.*;

PeasyCam cam;
Settings setting;
AlgorithmCollection algorithm;
Move move;
RubiksCubeLogic cube;
boolean isTured;
boolean animating = true;

void setup() {
size(600, 600, P3D);
setting = new Settings();
cam = new PeasyCam(this, setting.cameraZoomIn);
algorithm = new AlgorithmCollection();
cube = new RubiksCubeLogic();
move = new Move(0,0,0,0, cube.getCube());

}

void draw () {
  move.update();
  background(setting.backgroundColor);
  cube.getCube().drawCube(move);
  if(cube.moveList != "") {
    if(frameCount % 15 == 0) {
      isTured = true;
      DoStuff(cube.moveList.charAt(0));
      cube.moveList=cube.moveList.substring(1, cube.moveList.length());
    }
  } else if(cube.solving) {
    cube.solve();
  }
}
