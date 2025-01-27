import java.util.Iterator;

public class AlgorithmCollection {
    private ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
    private JSONObject json;
    
    public AlgorithmCollection() {
        json = loadJSONObject("algorithms.json");
        for (String key : (String[]) json.keys().toArray(new String[json.size()])) {
            JSONArray array = json.getJSONArray(key);
            String[] moves = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                moves[i] = array.getString(i);
            }
            algorithms.add(new Algorithm(key, moves));
        }
    }
    
    public Algorithm getAlgorithm(String name) {
        for (Algorithm algorithm : algorithms) {
            if (name.equals(algorithm.getName())) {
                return algorithm;
            }
        }
        String[] bad_return  = {};
        Algorithm al = new Algorithm("does not exist", bad_return);
        return al;
    }
    
    public void printAlgorithms() {
        for (Algorithm algorithm : algorithms) {
            println(algorithm.getName() + ": " + join(algorithm.getMoves(), " "));
        }
    }
    
    public void add(String name, String[] moves) {
        algorithms.add(new Algorithm(name, moves));
        JSONArray array = new JSONArray();
        for (String move : moves) {
            array.append(move);
        }
        json.setJSONArray(name, array);
        saveJSONObject(json, "algorithms.json");
    }
    
    public void delete(String name) {
        for (int i = 0; i < algorithms.size(); i++) {
            if (algorithms.get(i).getName().equals(name)) {
                algorithms.remove(i);
                break;
            }
        }
        json.remove(name);
        saveJSONObject(json, "algorithms.json");
    }
}
