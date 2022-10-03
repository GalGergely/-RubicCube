Turns turn = new Turns();

void keyPressed () {
  if (!move.animate) {
    DoStuff(key);
  }
}
void DoStuff(char keys) {
  if (!move.animate) {
    switch (keys) {
    case ' ' :
      cube.shuffleCube();
      break;
    case 'y' :
      cube.doPerm(turn.yperm);
      break;
    case 't' :
      cube.doPerm(turn.tperm);
      break;
    case 'j' :
      cube.doPerm(turn.jperm);
      break;
   case 'p' :
      cube.doPerm(turn.lperm);
      break;
    case 'm' :
      move = new Move(2, 0, 0, 1, cube);
      move.start();
      break;
    case 'l' :
      move = new Move(-1, 0, 0, -1, cube);
      move.start();
      break;
    case 'L' :
      move = new Move(-1, 0, 0, 1, cube);
      move.start();
      break;
    case 'r' :
      move = new Move(1, 0, 0, 1, cube);
      move.start();
      break;
    case 'R' :
      move = new Move(1, 0, 0, -1, cube);
      move.start();
      break;
    case 'f' :
      move = new Move(0, 0, 1, 1, cube);
      move.start();
      break;
    case 'F' :
      move = new Move(0, 0, 1, -1, cube);
      move.start();
      break;
    case 'b' :
      move = new Move(0, 0, -1, -1, cube);
      move.start();
      break;
    case 'B' :
      move = new Move(0, 0, -1, 1, cube);
      move.start();
      break;
    case 'w' :
      move = new Move(0, 2, 0, 1, cube);
      move.start();
      break;  
    case 'u' :
      move = new Move(0, -1, 0, -1, cube);
      move.start();
      break;
    case 'U' :
      move = new Move(0, -1, 0, 1, cube);
      move.start();
      break;
    case 'd' :
      move = new Move(0, 1, 0, 1, cube);
      move.start();
      break;
    case 'D' :
      move = new Move(0, 1, 0, -1, cube);
      move.start();
      break;
    }
  }
}
