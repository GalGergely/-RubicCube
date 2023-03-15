package algorithm;


import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class OldPochmanCollection {
    private final ArrayList<OldPochmanAlgorithm> algorithms = new ArrayList<>();


    public OldPochmanCollection(PApplet sketch) {

        JSONObject json = sketch.loadJSONObject("inputs/pochmanTargetPosition.json");
        for (String key : (String[]) json.keys().toArray(new String[json.size()])) {
            JSONArray array = json.getJSONArray(key);
            String[] moves = new String[array.getJSONArray(0).size()];
            for (int i = 0; i < array.getJSONArray(0).size(); i++) {
                moves[i] = array.getJSONArray(0).getString(i);
            }
            String algorithm = array.getString(1);
            algorithms.add(new OldPochmanAlgorithm(key, moves, algorithm));
        }
    }

    public void printAlgorithms() {
        for (OldPochmanAlgorithm algorithm : algorithms) {
            System.out.println(algorithm.getName() + " " + PApplet.join(algorithm.getSetup(), " ") + " " + algorithm.getAlgorithmName());
        }
    }

    public boolean isItInAlgorithms(String id) {
        for (OldPochmanAlgorithm algorithm : algorithms) {
            if (algorithm.getName().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<OldPochmanAlgorithm> getAlgorithms() {
        return algorithms;
    }
}
