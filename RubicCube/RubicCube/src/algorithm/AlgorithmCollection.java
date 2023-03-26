package algorithm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmCollection {
    private final ArrayList<Algorithm> algorithms = new ArrayList<>();
    private final String filePath = "src/inputs/algorithms.json";

    private Map<String, ArrayList<String>> algorithmMap;

    public AlgorithmCollection() {
        try (FileReader fileReader = new FileReader(filePath)) {
            algorithmMap = new Gson().fromJson(fileReader, new TypeToken<HashMap<String, ArrayList<String>>>() {}.getType());
            algorithmMap.forEach((key, value) -> algorithms.add(new Algorithm(key, value.toArray(new String[0]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Algorithm getAlgorithm(String name) {
        return algorithms.stream().filter(a -> name.equals(a.getName())).findFirst().orElse(new Algorithm("does not exist", new String[0]));
    }

    public void printAlgorithms() {
        algorithms.forEach(a -> System.out.println(a.getName() + ": " + String.join(" ", a.getMoves())));
    }

    public void add(String name, String[] moves) {
        algorithms.add(new Algorithm(name, moves));
        algorithmMap.put(name, new ArrayList<>(List.of(moves)));
        saveAlgorithmMap();
    }

    public void delete(String name) {
        algorithms.removeIf(a -> a.getName().equals(name));
        algorithmMap.remove(name);
        saveAlgorithmMap();
    }

    private void saveAlgorithmMap() {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            new Gson().toJson(algorithmMap, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}