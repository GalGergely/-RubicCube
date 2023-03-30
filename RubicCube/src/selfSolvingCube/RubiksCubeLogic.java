/**
 The RubiksCubeLogic class represents the core logic for solving a Rubik's Cube using the Old Pochmann method.
 It is responsible for the cube state, movements, shuffling, and solving algorithms.
 It also handles edge and corner buffer logic, solving one piece at a time, and breaking plugging if needed.
 This is the most important class in the project and provides the essential functionality for solving a Rubik's Cube.
 @author Gál Gergely
 */
package selfSolvingCube;

import algorithm.AlgorithmCollection;
import algorithm.OldPochmanCollection;
import algorithm.OldPochmanAlgorithm;
import cubeAssets.Cube;
import cubeAssets.Cubie;
import cubeAssets.Face;
import logger.LogWriter;
import processing.core.PApplet;
import settings.Facing;
import settings.Settings;

import java.util.ArrayList;

public class RubiksCubeLogic  {
    private final LogWriter logWriter;
    private final Settings setting;
    private final PApplet sketch;
    public String moveList = "";
    private boolean solving = false;
    private final Cube cube;
    private final AlgorithmCollection algorithmCollection;
    private final OldPochmanCollection op;
    private int animationTimer = 0;
    private int moveCounter = 0;
    private boolean edgesSolved = false;
    private boolean isParityNeeded = true;

    /**
     * Constructs a new RubiksCubeLogic object with the specified sketch, settings, and log writer.
     *
     * @param sketch    the PApplet instance for drawing the cube
     * @param setting   the settings for the Rubik's Cube
     * @param logWriter the log writer for logging moves and events
     */
    public RubiksCubeLogic(PApplet sketch, Settings setting, LogWriter logWriter) {
        this.logWriter = logWriter;
        this.setting = setting;
        this.sketch = sketch;
        this.algorithmCollection = new AlgorithmCollection();
        this.cube = new Cube(this.sketch, this.setting);
        this.op = new OldPochmanCollection();
    }


    /**
     * Shuffles the cube randomly with a number of moves between 10 and 20.
     */
    public void shuffleCube() {
        int rand = PApplet.round(this.sketch.random(10, 20));
        this.logWriter.log("Shuffle is " + rand + " moves long.");
        char[] moves = {'l','L','r','R','u','U','d','D','f','F','b','B'};
        for (int i = 0; i < rand; i++) {
            int random = PApplet.round(this.sketch.random(0, 11));
            moveList += moves[random];
        }
    }

    /**
     * Reverses a given algorithm by swapping the case of the characters.
     *
     * @param inputs the algorithm to reverse as a string array
     * @return the reversed algorithm as a string
     */
    private String reverseAlgorithm(String[] inputs) {
        String[] strings = new String[inputs.length];
        System.arraycopy(inputs, 0, strings, 0, inputs.length);
        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[strings.length - 1 - i];
            strings[strings.length - 1 - i] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            char c = s.charAt(0);
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Solves the cube using the Old Pochmann method.
     * This method manages the process of solving both edges and corners one piece at a time.
     * It also handles parity and breaking plugging if needed.
     */
    public void solve() {
        this.solving = true;
        if (this.edgesSolved) {
            if (moveCounter % 2 ==  1 && isParityNeeded) {
                this.moveList += "ruRFruuRuuRfruruuRU";
                this.logWriter.log("Parity is needed");
                isParityNeeded = false;
            }
            int id = this.lookForCornerBuffer();
            if (id != 3 && id != 5 && id != 0) {
                SolveOnePiece(id);
                if (this.setting.stepByStepSolving) {
                    this.solving = false;
                }
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllCornersAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    this.animationTimer = 0;
                    this.logWriter.log("corners are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.solving = false;
                }
                if (this.animationTimer >= 100) {
                    this.animationTimer = 0;
                    this.logWriter.log("corners are not done, breaking the plugging.");
                    boolean checkForFlippedBuffer = breakPlugging(notInPosition.get(0));
                    if (!checkForFlippedBuffer) {
                        breakPlugging(notInPosition.get(1));
                    }

                }
            }
        } else {
            int id = this.lookForEdgeBuffer();
            if (id != 117 && id != 118) {
                SolveOnePiece(id);
                if (this.setting.stepByStepSolving) {
                    this.solving = false;
                }
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllEdgesAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    this.logWriter.log("edges are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.edgesSolved = true;
                    this.animationTimer = 0;
                }
                if (this.animationTimer >= 100) {
                    this.logWriter.log("edges are not done, breaking the plugging.");
                    boolean checkForFlippedBuffer = breakPlugging(notInPosition.get(0));
                    if (!checkForFlippedBuffer) {
                        breakPlugging(notInPosition.get(1));
                    }
                    this.animationTimer = 0;
                }
            }
        }
    }

    /**
     * Breaks the plugging by executing an algorithm on a given Cubie.
     *
     * @param cubie the Cubie to break plugging on
     * @return true if the plugging
     * was broken successfully, false otherwise
     */
    public boolean breakPlugging(Cubie cubie) {
        for (Face face : cubie.getFaces()) {
            if (this.op.isItInAlgorithms(Integer.toString(face.getId()))) {
                if (face.getId() == 117 || face.getId() == 118 || face.getId() == 3 || face.getId() == 5 || face.getId() == 0) {
                    return false;
                }
                SolveOnePiece(face.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Solves one piece of the cube by executing an algorithm for the given piece ID.
     *
     * @param id the ID of the piece to solve
     */
    public void SolveOnePiece(int id) {
        this.moveCounter++;
        OldPochmanAlgorithm algo = this.moveToTarget(id);
        this.moveList += PApplet.join(algo.getSetup(),"");
        this.moveList += PApplet.join(algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getMoves(), "");
        try {
            Thread.sleep(100);
        } catch(Exception ignored) {}
        String reversedMoves = reverseAlgorithm(algo.getSetup());
        logMove(Integer.toString(id), PApplet.join(algo.getSetup(),""), algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getName(), reversedMoves);
        this.moveList += reversedMoves;
    }

    /**
     * Moves to the target position for the given piece ID using the Old Pochmann algorithm.
     *
     * @param id the ID of the piece to move to the target position
     * @return the Old Pochmann algorithm for the given piece ID
     */
    public OldPochmanAlgorithm moveToTarget(int id) {
        OldPochmanAlgorithm algo = null;
        for (OldPochmanAlgorithm algorithm : this.op.getAlgorithms()) {
            if (algorithm.getName().equals(Integer.toString(id))) {
                algo = algorithm;
            }
        }
        return algo;
    }

    /**
     * Searches for the edge buffer piece ID in the cube's current state.
     *
     * @return the edge buffer piece ID, or 1 if not found
     */
    public int lookForEdgeBuffer() {
        for (Cubie cubie : cube.getData()) {
            if(cubie.getPosition().x == 1 && cubie.getPosition().y == -1 && cubie.getPosition().z == 0) {
                for (Face face : cubie.getFaces()) {
                    if(face.getFacing() == Facing.UP) {
                        return face.getId();
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Searches for the corner buffer piece ID in the cube's current state.
     *
     * @return the corner buffer piece ID, or 1 if not found
     */
    public int lookForCornerBuffer() {
        for (Cubie cubie : cube.getData()) {
            if(cubie.getPosition().x == -1 && cubie.getPosition().y == -1 && cubie.getPosition().z == -1) {
                for (Face face : cubie.getFaces()) {
                    if(face.getFacing() == Facing.UP) {
                        return face.getId();
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Logs a move with the given piece ID, setup, algorithm, and redo setup.
     *
     * @param id       the piece ID
     * @param setup    the setup moves
     * @param algorithm the algorithm used
     * @param redo     the redo setup moves
     */
    private void logMove(String id, String setup, String algorithm, String redo) {
        this.logWriter.log("buffer id: " + id + " || setup: " + setup + " || algorithm: " + algorithm + " || redo setup: " + redo);
    }

    /**
     * Returns whether the cube is being solved or not.
     *
     * @return true if the cube is being solved, false otherwise
     */
    public boolean isSolving() {
        return solving;
    }

    /**
     * Sets the solving state of the cube.
     *
     * @param solving the new solving state to set
     */
    public void setSolving(boolean solving) {
        this.solving = solving;
    }

    /**
     * Returns the Cube object.
     *
     * @return the Cube object
     */
    public Cube getCube() {
        return this.cube;
    }
}