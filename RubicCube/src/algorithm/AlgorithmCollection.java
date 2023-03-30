/**
 * AlgorithmCollection class provides functionality to manage a collection of algorithms.
 * The algorithms are stored in a JSON file which is read upon object initialization.
 */
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

    /**
     * Constructor for the AlgorithmCollection class.
     * Initializes the algorithmMap from the JSON file specified by the filePath.
     */
    public AlgorithmCollection() {
        try (FileReader fileReader = new FileReader(filePath)) {
            algorithmMap = new Gson().fromJson(fileReader, new TypeToken<HashMap<String, ArrayList<String>>>() {
            }.getType());
            algorithmMap.forEach((key, value) -> algorithms.add(new Algorithm(key, value.toArray(new String[0]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an algorithm by its name from the algorithms list.
     *
     * @param name The name of the algorithm to retrieve.
     * @return The algorithm with the specified name, or an empty algorithm if not found.
     */
    public Algorithm getAlgorithm(String name) {
        return algorithms.stream().filter(a -> name.equals(a.getName())).findFirst().orElse(new Algorithm("does not exist", new String[0]));
    }

    /**
     * Returns the list of algorithms.
     *
     * @return An ArrayList of Algorithm objects.
     */
    public ArrayList<Algorithm> getAlgorithms() {
        return algorithms;
    }

    /**
     * Adds a new algorithm with the specified name and moves to the collection.
     *
     * @param name  The name of the algorithm.
     * @param moves An array of String containing the moves of the algorithm.
     */
    public void add(String name, String[] moves) {
        algorithms.add(new Algorithm(name, moves));
        algorithmMap.put(name, new ArrayList<>(List.of(moves)));
        saveAlgorithmMap();
    }

    /**
     * Deletes an algorithm with the specified name from the collection.
     *
     * @param name The name of the algorithm to delete.
     */
    public void delete(String name) {
        algorithms.removeIf(a -> a.getName().equals(name));
        algorithmMap.remove(name);
        saveAlgorithmMap();
    }

    /**
     * Prints the list of algorithms to the console.
     */
    public void printAlgorithms() {
        algorithms.forEach(a -> System.out.println(a.getName() + ": " + String.join(" ", a.getMoves())));
    }

    /**
     * Saves the current algorithmMap to the JSON file specified by filePath.
     */
    private void saveAlgorithmMap() {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            new Gson().toJson(algorithmMap, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}