/**
 * GergosCube class is the main class to run the Rubik's Cube simulation.
 * It extends PApplet from the Processing library and handles user input,
 * animation, and updates to the cube.
 */
package selfSolvingCube;

import logger.LogWriter;
import processing.core.*;

import peasy.*;

import java.util.Objects;

import algorithm.*;
import cubeAssets.*;
import settings.*;


public class GergosCube extends PApplet {
    PeasyCam cam;
    Settings setting = new Settings();
    AlgorithmCollection algorithm;
    Move move;
    RubiksCubeLogic cube;
    LogWriter logWriter;

    /**
     * Initializes the cube simulation, sets up the camera, and prepares necessary objects.
     */
    public void setup() {
        setting = new Settings();
        cam = new PeasyCam(this, setting.cameraZoomIn);
        algorithm = new AlgorithmCollection();
        logWriter = new LogWriter();
        logWriter.clearLogs();
        cube = new RubiksCubeLogic(this, setting, this.logWriter);
        move = new Move(this, new PVector(0,0,0), 0, cube.getCube());
    }

    /**
     * Closes the simulation when called.
     */
    @Override
    public void exit() {
        super.exit();
    }

    /**
     * Main drawing loop, updates the cube state and handles animations
     * and moves the cube if the moveList is not empty.
     */
    public void draw() {
        move.update();
        background(setting.backgroundColor.getRGB());
        cube.getCube().drawCube(move);
        if (!Objects.equals(cube.moveList, "")) {
            if (frameCount % 15 == 0) {
                specialKeyBindings(cube.moveList.charAt(0));
                cube.moveList = cube.moveList.substring(1);
            }
        } else if (cube.isSolving()) {
            cube.solve();
        }
    }

    /**
     * Handles key presses from the user to manipulate the cube.
     */
    public void keyPressed() {
        if (!move.isAnimate()) {
            if (key == 'm' || key == 'M' || key == 'w' || key == 'W') {
                println("if you want the ai solve this move is illegal");
            }
            this.cube.moveList += str(key);
        }
    }

    /**
     * Processes special key bindings for cube manipulation.
     * @param keys Character representing the key pressed.
     */
    public void specialKeyBindings(char keys) {
        if (!move.isAnimate()) {
            switch (keys) {
                case ' ' -> cube.shuffleCube();
                case 's' -> cube.setSolving(true);
                default -> {
                    move = KeyBindings.doMove(keys, move, cube.getCube(), this);
                    assert move != null;
                    move.start();
                }
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
