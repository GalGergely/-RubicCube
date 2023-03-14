public class RubiksCubeLogic  {
    private String moveList = "";
    boolean solving = false;
    private Cube cube;
    private AlgorithmCollection algorithmCollection = new AlgorithmCollection();
    OldPochman op;
    int animationTimer = 0;
    int moveCounter = 0;
    private boolean edgesSolved = false;
    boolean isParityNeeded = true;
    
    public RubiksCubeLogic() {
        this.cube = new Cube();
        this.op = new OldPochman(this.cube, this.moveList);
    }
    
    public Cube getCube() {
        return this.cube;
    }
    
    public void shuffleCube() {
        int rand = round(random(10, 20));
        println("Shuffle is " + rand + " moves long.");
        char[] moves = {'l','L','r','R','u','U','d','D','f','F','b','B'};
        for (int i = 0; i < rand; i++) {
            int random = round(random(0, 11));
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
        for (int i = 0; i < inputs.length; ++i) {
            strings[i] = inputs[i];
        }
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
    
    void solve() {
        this.solving = true;
        if (this.edgesSolved) {
            if (moveCounter % 2 ==  1 && isParityNeeded) {
                this.moveList += "ruRFruuRuuRfruruuRU";
                isParityNeeded = false;
            }
            int id = this.op.lookForCornerBuffer();
            if (id != 3 && id != 5 && id != 0) {
                SolveOnePiece(id);
                //this.solving = false;  //minden lepes utan megallas
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllCornersAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    this.animationTimer = 0;
                    println("corners are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.solving = false;
                }
                if (this.animationTimer >= 100) {
                    this.animationTimer = 0;
                    println("corners are not done, breaking the plugging.");
                    boolean chekForFlippedBuffer = breakPlugging(notInPosition.get(0));
                    if (!chekForFlippedBuffer) {
                        breakPlugging(notInPosition.get(1));
                    }
                    
                }
            }
        } else {
            int id = this.op.lookForEdgeBuffer();
            if (id != 117 && id != 118) {
                SolveOnePiece(id);
                //this.solving = false;  //minden lepes utan megallas
            } else {
                ArrayList<Cubie> notInPosition = this.cube.checkIfAllEdgesAreSolved();
                if (notInPosition.size() > 0) {
                    this.animationTimer++;
                } else {
                    println("edges are done, it took " + Integer.toString(moveCounter) + " moves.");
                    this.edgesSolved = true;
                    this.animationTimer = 0;
                }
                if (this.animationTimer >= 100) {
                    println("edges are not done, breaking the plugging.");
                    boolean chekForFlippedBuffer = breakPlugging(notInPosition.get(0));
                    if (!chekForFlippedBuffer) {
                        breakPlugging(notInPosition.get(1));
                    }
                    this.animationTimer = 0;
                }
            }
        }
    }
    
    boolean breakPlugging(Cubie cubie) {
        for (Face face : cubie.faces) {
            if (this.op.isItInAlgorithms(Integer.toString(face.id))) {
                // this is needed becouse the buffer can get stuck
                if (face.id == 117 || face.id == 118 || face.id == 3 || face.id == 5 || face.id == 0) {
                    return false;
                }
                SolveOnePiece(face.id);
                return true;
            }
        }
        return false;
    }
    
    void SolveOnePiece(int id) {
        this.moveCounter++;
        OldPochmanAlgorithm algo = this.op.moveToTarget(id);
        this.moveList += join(algo.getSetup(),"");
        this.moveList += join(algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getMoves(), "");
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            println(e);
        }
        String reversedMoves = reverseAlgorithm(algo.getSetup());
        logMove(Integer.toString(id), join(algo.getSetup(),""), algorithmCollection.getAlgorithm(algo.getAlgorithmName()).getName(), reversedMoves);
        this.moveList += reversedMoves;
    }
    
    private void logMove(String id, String setup, String algorithm, String redo) {
        println("buffer id: " + id + " || setup: " + setup + " || algorithm: " + algorithm + " || redo setup: " + redo);
    }
    
}