package algorithm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
/**
 * OldPochmanCollection class provides functionality to manage a collection of Old Pochman algorithms.
 * The algorithms are stored in a JSON file which is read upon object initialization.
 */
public class OldPochmanCollection {
    private final ArrayList<OldPochmanAlgorithm> algorithms = new ArrayList<>();

    /**
     * Constructor for the OldPochmanCollection class.
     * Initializes the collection of OldPochmanAlgorithm objects from the JSON file specified by the filePath.
     */
    public OldPochmanCollection() {
        String filePath = "src/inputs/pochmanTargetPosition.json";
        try (FileReader fileReader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, ArrayList<Object>>>() {
            }.getType();
            Map<String, ArrayList<Object>> pochmanMap = new Gson().fromJson(fileReader, type);

            pochmanMap.forEach((key, value) -> {
                ArrayList<String> moves = (ArrayList<String>) value.get(0);
                String algorithm = (String) value.get(1);
                algorithms.add(new OldPochmanAlgorithm(key, moves.toArray(new String[0]), algorithm));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if an algorithm with the specified ID exists in the collection.
     *
     * @param id The ID of the algorithm to check.
     * @return true if the algorithm exists, false otherwise.
     */
    public boolean isItInAlgorithms(String id) {
        return algorithms.stream().anyMatch(a -> a.getName().equals(id));
    }

    /**
     * Returns the list of Old Pochman algorithms.
     *
     * @return An ArrayList of OldPochmanAlgorithm objects.
     */
    public ArrayList<OldPochmanAlgorithm> getAlgorithms() {
        return algorithms;
    }
}
