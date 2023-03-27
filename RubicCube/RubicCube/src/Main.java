import processing.core.*;

import peasy.*;

import java.util.Objects;

import algorithm.*;
import cube.*;
import processing.data.JSONObject;
import settings.*;

public class Main extends PApplet {
    PeasyCam cam;
    Settings setting = new Settings();
    AlgorithmCollection algorithm;
    Move move;
    RubiksCubeLogic cube;
    boolean isTured;

    public void setup() {
        setting = new Settings();
        cam = new PeasyCam(this, setting.cameraZoomIn);
        algorithm = new AlgorithmCollection();
        cube = new RubiksCubeLogic(this, setting);
        move = new Move(this,0,0,0,0, cube.getCube());

    }

    public void draw () {
        move.update();
        background(setting.backgroundColor);
        cube.getCube().drawCube(move);
        if(!Objects.equals(cube.moveList, "")) {
            if(frameCount % 15 == 0) {
                isTured = true;
                DoStuff(cube.moveList.charAt(0));
                cube.moveList = cube.moveList.substring(1);
            }
        } else if(cube.isSolving()) {
            cube.solve();
        }
    }

    public void keyPressed () {
        if (!move.isAnimate()) {
            if(key == 'm' || key == 'M' || key == 'w' || key == 'W') {
                println("if you want the ai solve this move is illegal");
                DoStuff(key);
            } else {
                DoStuff(key);
            }
        }
    }

    public void DoStuff(char keys) {
        if (!move.isAnimate()) {
            switch (keys) {
                case ' ' :
                    cube.shuffleCube();
                    break;
                case 's' :
                    //start solving the cube
                    cube.setSolving(true);
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
                    move = new Move(this,2, 0, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'M' :
                    move = new Move(this,2, 0, 0, -1, cube.getCube());
                    move.start();
                    break;
                case 'w' :
                    move = new Move(this,0, -2, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'W' :
                    move = new Move(this,0, 2, 0, -1, cube.getCube());
                    move.start();
                    break;
                case 'l' :
                    move = new Move(this,-1, 0, 0, -1, cube.getCube());
                    move.start();
                    break;
                case 'L' :
                    move = new Move(this,-1, 0, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'r' :
                    move = new Move(this,1, 0, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'R' :
                    move = new Move(this,1, 0, 0, -1, cube.getCube());
                    move.start();
                    break;
                case 'f' :
                    move = new Move(this,0, 0, 1, 1, cube.getCube());
                    move.start();
                    break;
                case 'F' :
                    move = new Move(this,0, 0, 1, -1, cube.getCube());
                    move.start();
                    break;
                case 'b' :
                    move = new Move(this,0, 0, -1, -1, cube.getCube());
                    move.start();
                    break;
                case 'B' :
                    move = new Move(this,0, 0, -1, 1, cube.getCube());
                    move.start();
                    break;
                case 'u' :
                    move = new Move(this,0, -1, 0, -1, cube.getCube());
                    move.start();
                    break;
                case 'U' :
                    move = new Move(this,0, -1, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'd' :
                    move = new Move(this,0, 1, 0, 1, cube.getCube());
                    move.start();
                    break;
                case 'D' :
                    move = new Move(this,0, 1, 0, -1, cube.getCube());
                    move.start();
                    break;
            }
        }
    }
    public void settings() { size(600, 600, P3D); }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Main" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
