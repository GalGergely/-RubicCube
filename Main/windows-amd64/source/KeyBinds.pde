void keyPressed () {
  if (!move.animate) {
    if(key == 'm' || key == 'M' || key == 'w' || key == 'W') {
      println("if you want the ai solve this move is illegal");
      DoStuff(key);
    } else {
      DoStuff(key);
    }
  }
}
void DoStuff(char keys) {
  if (!move.animate) {
    switch (keys) {
    case ' ' :
      cube.shuffleCube();
      break;
    case 's' :
      //start solving the cube
      cube.solving = true;
      break;
    case 'y' :
      cube.doPerm(algorithm.getAlgorithm("yperm"));
      break;
    case 't' :
      cube.doPerm(algorithm.getAlgorithm("tperm"));
      break;
    case 'j' :
      cube.doPerm(algorithm.getAlgorithm("jperm"));
      break;
   case 'p' :
      cube.doPerm(algorithm.getAlgorithm("lperm"));
      break;
    case 'm' :
      move = new Move(2, 0, 0, 1, cube.getCube());
      move.start();
      break;
    case 'M' :
      move = new Move(2, 0, 0, -1, cube.getCube());
      move.start();
      break;
    case 'w' :
      move = new Move(0, -2, 0, 1, cube.getCube());
      move.start();
      break;
    case 'W' :
      move = new Move(0, 2, 0, -1, cube.getCube());
      move.start();
      break;
    case 'l' :
      move = new Move(-1, 0, 0, -1, cube.getCube());
      move.start();
      break;
    case 'L' :
      move = new Move(-1, 0, 0, 1, cube.getCube());
      move.start();
      break;
    case 'r' :
      move = new Move(1, 0, 0, 1, cube.getCube());
      move.start();
      break;
    case 'R' :
      move = new Move(1, 0, 0, -1, cube.getCube());
      move.start();
      break;
    case 'f' :
      move = new Move(0, 0, 1, 1, cube.getCube());
      move.start();
      break;
    case 'F' :
      move = new Move(0, 0, 1, -1, cube.getCube());
      move.start();
      break;
    case 'b' :
      move = new Move(0, 0, -1, -1, cube.getCube());
      move.start();
      break;
    case 'B' :
      move = new Move(0, 0, -1, 1, cube.getCube());
      move.start();
      break;
    case 'u' :
      move = new Move(0, -1, 0, -1, cube.getCube());
      move.start();
      break;
    case 'U' :
      move = new Move(0, -1, 0, 1, cube.getCube());
      move.start();
      break;
    case 'd' :
      move = new Move(0, 1, 0, 1, cube.getCube());
      move.start();
      break;
    case 'D' :
      move = new Move(0, 1, 0, -1, cube.getCube());
      move.start();
      break;
    }
  }
}
