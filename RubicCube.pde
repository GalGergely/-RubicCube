import peasy.*;

Cube cube;
PeasyCam cam;
Settings setting;
Move move;

Ai ai;
boolean isTured = false;


void setup() {
size(600,600, P3D);
setting = new Settings();
cube = new Cube();
cam = new PeasyCam(this, setting.cameraZoomIn);
move = new Move(0,0,0,0, cube);
}

void draw () {
  
  move.update();
  background(setting.backgroundColor);
  cube.drawCube(move);
  //println(cube.data[7]);
  if(cube.moveList != "") {
    if(frameCount % 30 == 0) {
      isTured = true;
      DoStuff(cube.moveList.charAt(0));
      cube.moveList=cube.moveList.substring(1, cube.moveList.length());
    }
  }
  if(isTured && cube.moveList == ""){
    cube.shuffled=true;
    ai=new Ai(cube);
    ai.solve();
    isTured = false;
  }
  
}
