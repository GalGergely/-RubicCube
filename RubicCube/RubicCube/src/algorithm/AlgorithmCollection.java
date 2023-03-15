package algorithm;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class AlgorithmCollection {
    private final PApplet sketch;
    private final ArrayList<Algorithm> algorithms = new ArrayList<>();
    private final JSONObject json;

    public AlgorithmCollection(PApplet sketch) {
        this.sketch = sketch;
        json = sketch.loadJSONObject("inputs/algorithms.json");
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
        return new Algorithm("does not exist", bad_return);
    }

    public void printAlgorithms() {
        for (Algorithm algorithm : algorithms) {
            System.out.println(algorithm.getName() + ": " + PApplet.join(algorithm.getMoves(), " "));
        }
    }

    public void add(String name, String[] moves) {
        algorithms.add(new Algorithm(name, moves));
        JSONArray array = new JSONArray();
        for (String move : moves) {
            array.append(move);
        }
        json.setJSONArray(name, array);
        sketch.saveJSONObject(json, "inputs/algorithms.json");
    }

    public void delete(String name) {
        for (int i = 0; i < algorithms.size(); i++) {
            if (algorithms.get(i).getName().equals(name)) {
                algorithms.remove(i);
                break;
            }
        }
        json.remove(name);
        sketch.saveJSONObject(json, "inputs/algorithms.json");
    }
}
