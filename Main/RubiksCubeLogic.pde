public class RubiksCubeLogic  {
    private String moveList = "";
    boolean solving = false;
    private Cube cube;
    private AlgorithmCollection algorithmCollection = new AlgorithmCollection();
    OldPochman op;
    int animationTimer = 0;

    public RubiksCubeLogic() {
        this.cube = new Cube();
        this.op = new OldPochman(this.cube, this.moveList);
    }

    Cube getCube() {
        return this.cube;
    }

    void shuffleCube() {
        int rand = round(random(10, 20));
        println(rand);
        char[] moves = {'l','L','r','R','u','U','d','D','f','F','b','B'};
        for (int i = 0; i < rand; i++) {
            int random = round(random(0, 11));
            moveList += moves[random];
        }
    }

    void doPerm(Algorithm alg) {
        for (int i = 0; i < alg.getMoves().length; i++) {
            print(alg.getMoves()[i]);
            this.moveList += alg.getMoves()[i];
        }
    }

    public String reverseAlgorithm(String[] inputs) {
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
        int id = this.op.lookForBuffer();
        if (id != 117 && id != 118) {
            SolveOneCorner(id);
            //this.solving = false;  //minden lepes utan megallas
        } else {
            ArrayList<Cubie> notInPosition = this.cube.checkIfAllCorenrsAreSolved();
            if (notInPosition.size() > 0) {
                this.animationTimer++;
            } else {
                println("corners are done");
                this.solving = false;
                this.animationTimer = 0;
            }
            if(this.animationTimer >= 100) {
                println("corners are not done");
                println(notInPosition);
                // itt a not in position elso elemet kell felvinni valamelyik target cubieba. mindegy hogy merre nez, mindegy hogy melyikbe.
                this.animationTimer = 0;
                this.solving = false;
            }

        }
    }

    OldPochmanAlgorithm breakPlugging(Cubie cubie) {
        OldPochmanAlgorithm algo = null;
        if(cubie.x == 1 && cubie.y == 0 && cubie.z == 1) {
            this.moveList += "WL";
            doPerm(algorithm.getAlgorithm("yperm"));
            this.moveList += "LW";
        } else if (cubie.x == 1 && cubie.y == 1 && cubie.z == -1) {

        } else if (cubie.x == -1 && cubie.y == 0 && cubie.z == 1) {

        } else if (cubie.x == -1 && cubie.y == 1 && cubie.z == -1) {

        }
        return algo;
    }

    void SolveOneCorner(int id) {
        print("buffer id: " + id);
        OldPochmanAlgorithm algo = this.op.moveToTarget(id);
        print(" || setup: ");
        print(join(algo.getSetup(),""));
        this.moveList += join(algo.getSetup(),"");
        print(" || algorithm: ");
        print(algorithmCollection.getAlgorithm(algo.getAlgorithm()).getName());
        this.moveList += join(algorithmCollection.getAlgorithm(algo.getAlgorithm()).getMoves(), "");
        try {
            Thread.sleep(100);
        } catch(Exception e) {

        }
        String reversedMoves = reverseAlgorithm(algo.getSetup());
        print(" || redo setup: ");
        println(reversedMoves);
        this.moveList += reversedMoves;
    }

    String[] mirrorArray(String[] array) {
        String[] returnarray = {};
        for (int i = array.length - 1; i >=  0; i--) {
            returnarray = append(returnarray, array[i]);
        }
        return returnarray;
    }

}