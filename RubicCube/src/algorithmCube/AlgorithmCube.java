/**
 * AlgorithmCube class allows the user to learn and practice a specific algorithm on the Rubik's Cube.
 * It extends PApplet from the Processing library and handles user input,
 * animation, and updates to the cube.
 */
package algorithmCube;

import algorithm.AlgorithmCollection;
import cubeAssets.*;
import logger.LogWriter;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;
import settings.Settings;

import java.util.Objects;


public class AlgorithmCube extends PApplet {

    /**
     * Constructs an AlgorithmCube instance with a desired algorithm.
     *
     * @param desiredAlgorithm A string representing the algorithm to practice.
     */
    public AlgorithmCube(String desiredAlgorithm) {
        this.desiredAlgorithm = desiredAlgorithm;
    }

    PeasyCam cam;
    Settings setting = new Settings();
    AlgorithmCollection algorithm;
    Move move;
    Cube cube;
    LogWriter logWriter;
    String moveList;
    String desiredAlgorithm;
    String toDoMoveList;

    /**
     * Initializes the cube simulation, sets up the camera, and prepares necessary objects.
     */
    public void setup() {
        setting = new Settings();
        cam = new PeasyCam(this, setting.cameraZoomIn);
        algorithm = new AlgorithmCollection();
        logWriter = new LogWriter();
        logWriter.clearLogs();
        this.cube = new Cube(this, setting);
        move = new Move(this, new PVector(0, 0, 0), 0, cube);
        moveList = "";
        toDoMoveList = String.join("", new AlgorithmCollection().getAlgorithm(desiredAlgorithm).getMoves());
        logWriter.log("You have to do the following:");
        logWriter.log(toDoMoveList);
    }

    /**
     * Closes the simulation when called.
     */
    @Override
    public void exit() {
        super.exit();
    }

    /**
     * Main drawing loop, updates the cube state and handles animations.
     * This method checks if the toDoMoveList and the moveList contains the same move.
     */
    public void draw() {
        move.update();
        background(setting.backgroundColor.getRGB());
        cube.drawCube(move);
        if (frameCount % 15 == 0) {
            if (!Objects.equals(moveList, "")) {
                if (moveList.charAt(0) == toDoMoveList.charAt(0)) {
                    move = KeyBindings.doMove(moveList.charAt(0), move, cube, this);
                    assert move != null;
                    move.start();
                    moveList = moveList.substring(1);
                    toDoMoveList = toDoMoveList.substring(1);
                } else {
                    logWriter.log("You messed it up!");
                    this.cube = new Cube(this, setting);
                    this.moveList = "";
                    this.toDoMoveList = String.join("", new AlgorithmCollection().getAlgorithm(desiredAlgorithm).getMoves());
                }
            } else if (Objects.equals(toDoMoveList, "")) {
                logWriter.log("You did it!");
                toDoMoveList = String.join("", new AlgorithmCollection().getAlgorithm(desiredAlgorithm).getMoves());
            }
        }
    }

    /**
     * Handles key presses from the user to manipulate the cube.
     */
    public void keyPressed() {
        if (!move.isAnimate()) {
            if (key == 'm' || key == 'M' || key == 'l' || key == 'L' || key == 'r' || key == 'R' || key == 'u' || key == 'U' || key == 'd' || key == 'D' || key == 'b' || key == 'B' || key == 'f' || key == 'F' || key == 'w' || key == 'W') {
                this.moveList += str(key);
            } else if (!(keyCode == SHIFT)) {
                logWriter.log("Invalid keypress");
            }

        }
    }

    /**
     * Sets the size of the sketch window.
     */
    public void settings() {
        size(600, 600, P3D);
    }
}
