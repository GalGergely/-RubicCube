import algorithm.Algorithm;
import algorithm.AlgorithmCollection;
import algorithm.OldPochmanCollection;
import algorithm.OldPochmanAlgorithm;
import cube.Cube;
import cube.Cubie;
import cube.Face;
import processing.core.PApplet;
import settings.Facing;

import java.util.ArrayList;

public class RubiksCubeLogic  {
    private PApplet sketch;
    public String moveList = "";
    private boolean solving = false;
    private Cube cube;
    private AlgorithmCollection algorithmCollection;
    OldPochmanCollection op;
    int animationTimer = 0;
    int moveCounter = 0;
    private boolean edgesSolved = false;
    boolean isParityNeeded = true;

    public RubiksCubeLogic(PApplet sketch) {
        this.sketch = sketch;
        this.algorithmCollection = new AlgorithmCollection(sketch);
        this.cube = new Cube(this.sketch);
        this.op = new OldPochmanCollection(this.sketch);
    }

    public Cube getCube() {
        return this.cube;
    }

    public void shuffleCube() {
        int rand = PApplet.round(this.sketch.random(10, 20));
        System.out.println("Shuffle is " + rand + " moves long.");
        char[] moves = {'l','L','r','R','u','U','d','D','f','F','b','B'};
        for (int i = 0; i < rand; i++) {
            int random = PApplet.round(this.sketch.random(0, 11));
            moveList += moves[random];
        }
    }

    public void doPerm(Algorithm alg) {
        for (int i = 0; i < alg.getMoves().length; i++) {
            this.moveList += alg.getMoves()[i];
        }
    }

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

    public void solve() {
        this.solving = true;
        if (this.edgesSolved) {
            if (moveCounter % 2 ==  1 && isParityNeeded) {
                this.moveList += "ruRFruuRuuRfruruuRU";
                isParityNeeded = false;
            }
            int id = this.lookForCornerBuffer();
            if (id != 3 && id != 5 && id != 0) {
                SolveOnePiece(id);
                //this.solving = false;  //minden lepes utan megallas
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllCornersAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    this.animationTimer = 0;
                    System.out.println("corners are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.solving = false;
                }
                if (this.animationTimer >= 100) {
                    this.animationTimer = 0;
                    System.out.println("corners are not done, breaking the plugging.");
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
                //this.solving = false;  //minden lepes utan megallas
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllEdgesAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    System.out.println("edges are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.edgesSolved = true;
                    this.animationTimer = 0;
                }
                if (this.animationTimer >= 100) {
                    System.out.println("edges are not done, breaking the plugging.");
                    boolean checkForFlippedBuffer = breakPlugging(notInPosition.get(0));
                    if (!checkForFlippedBuffer) {
                        breakPlugging(notInPosition.get(1));
                    }
                    this.animationTimer = 0;
                }
            }
        }
    }

    public boolean breakPlugging(Cubie cubie) {
        for (Face face : cubie.getFaces()) {
            if (this.op.isItInAlgorithms(Integer.toString(face.getId()))) {
                // this is needed because the buffer can get stuck
                if (face.getId() == 117 || face.getId() == 118 || face.getId() == 3 || face.getId() == 5 || face.getId() == 0) {
                    return false;
                }
                SolveOnePiece(face.getId());
                return true;
            }
        }
        return false;
    }

    public void SolveOnePiece(int id) {
        this.moveCounter++;
        OldPochmanAlgorithm algo = this.moveToTarget(id);
        this.moveList += PApplet.join(algo.getSetup(),"");
        this.moveList += PApplet.join(algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getMoves(), "");
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            System.out.println(e);
        }
        String reversedMoves = reverseAlgorithm(algo.getSetup());
        logMove(Integer.toString(id), PApplet.join(algo.getSetup(),""), algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getName(), reversedMoves);
        this.moveList += reversedMoves;
    }

    public OldPochmanAlgorithm moveToTarget(int id) {
        OldPochmanAlgorithm algo = null;
        for (OldPochmanAlgorithm algorithm : this.op.getAlgorithms()) {
            if (algorithm.getName().equals(Integer.toString(id))) {
                algo = algorithm;
            }
        }
        return algo;
    }

    public int lookForEdgeBuffer() {
        for (Cubie cubie : cube.data) {
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

    public int lookForCornerBuffer() {
        for (Cubie cubie : cube.data) {
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

    private void logMove(String id, String setup, String algorithm, String redo) {
        System.out.println("buffer id: " + id + " || setup: " + setup + " || algorithm: " + algorithm + " || redo setup: " + redo);
    }

    public boolean isSolving() {
        return solving;
    }

    public void setSolving(boolean solving) {
        this.solving = solving;
    }
}