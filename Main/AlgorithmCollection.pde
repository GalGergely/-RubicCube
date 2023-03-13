import java.util.Iterator;

public class AlgorithmCollection {
    private ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
    private JSONObject json;
    
    public AlgorithmCollection () {
        json = loadJSONObject("algorithms.json");
        // Iterate through the keys of the JSON object
        for (String key : (String[]) json.keys().toArray(new String[json.size()])) {
            // Get the JSON array associated with the key
            JSONArray array = json.getJSONArray(key);

            // Convert the JSON array to a String array
            String[] moves = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                moves[i] = array.getString(i);
            }

            // Create a new Algorithm object and add it to the list
            algorithms.add(new Algorithm(key, moves));
        }
    }
    
    public Algorithm getAlgorithm(String name) {
      for (Algorithm algorithm : algorithms) {
        if(name.equals(algorithm.getName())) {
          return algorithm;
        }
      }
      String[] bad_return  = {};
      Algorithm al = new Algorithm("does not exist", bad_return);
      return al;
    }

    public void printAlgorithms() {
        // Iterate through the algorithms list and print each algorithm
        for (Algorithm algorithm : algorithms) {
            println(algorithm.getName() + ": " + join(algorithm.getMoves(), " "));
        }
    }

    public void add(String name, String[] moves) {
        // Create a new Algorithm object and add it to the list
        algorithms.add(new Algorithm(name, moves));

        // Create a new JSON array and add the moves to it
        JSONArray array = new JSONArray();
        for (String move : moves) {
            array.append(move);
        }

        // Add the new key-value pair to the JSON object and save it to the file
        json.setJSONArray(name, array);
        saveJSONObject(json, "algorithms.json");
    }

    public void delete(String name) {
        // Remove the Algorithm object with the given name from the list
        for (int i = 0; i < algorithms.size(); i++) {
            if (algorithms.get(i).getName().equals(name)) {
                algorithms.remove(i);
                break;
            }
        }

        // Remove the key-value pair with the given name from the JSON object and save it to the file
        json.remove(name);
        saveJSONObject(json, "algorithms.json");
    }
}
