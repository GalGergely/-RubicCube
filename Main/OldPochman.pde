public class OldPochman {
    Cube cube;
    int buffer;
    String moveList;
    private JSONObject json;
    private ArrayList<OldPochmanAlgorithm> algorithms = new ArrayList<OldPochmanAlgorithm>();


    public OldPochman (Cube cube, String moveList) {
        this.cube = cube;
        this.buffer = lookForEdgeBuffer();
        this.moveList = moveList;

        json = loadJSONObject("pochmanTargetPosition.json");
        for (String key : (String[]) json.keys().toArray(new String[json.size()])) {
            // Get the JSON array associated with the key
            JSONArray array = json.getJSONArray(key);

            // Convert the JSON array to a String array
            String[] moves = new String[array.getJSONArray(0).size()];
            for (int i = 0; i < array.getJSONArray(0).size(); i++) {
                moves[i] = array.getJSONArray(0).getString(i);
            }
            String algorithm = array.getString(1);

            // Create a new Algorithm object and add it to the list
            algorithms.add(new OldPochmanAlgorithm(key, moves, algorithm));
        }
    }

    public void printAlgorithms() {
        for (OldPochmanAlgorithm algorithm : algorithms) {
            println(algorithm.getName() + " " + join(algorithm.getSetup(), " ") + " " + algorithm.getAlgorithm());
        }
    }

    public int lookForEdgeBuffer() {
        for (Cubie cubie : cube.data) {
            if(cubie.x == 1 && cubie.y == -1 && cubie.z == 0) {
                for (Face face : cubie.faces) {
                  if(face.facing == Facing.UP) {
                    return face.id;
                  }
                }
            }
        }
        return 1;
    }

    public int lookForCornerBuffer() {
        for (Cubie cubie : cube.data) {
            if(cubie.x == -1 && cubie.y == -1 && cubie.z == -1) {
                for (Face face : cubie.faces) {
                  if(face.facing == Facing.UP) {
                    return face.id;
                  }
                }
            }
        }
        return 1;
    }

    public boolean isItInAlgorithms(String id) {
        for (OldPochmanAlgorithm algorithm : algorithms) {
            if (algorithm.getName().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public OldPochmanAlgorithm moveToTarget(int id) {
       OldPochmanAlgorithm algo = null;
        for (OldPochmanAlgorithm algorithm : algorithms) {
            if (algorithm.getName().equals(Integer.toString(id))) {
                algo = algorithm;
            }
        }
        return algo;
    }
}
