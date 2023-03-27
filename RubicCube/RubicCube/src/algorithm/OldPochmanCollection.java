package algorithm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class OldPochmanCollection {
    private final ArrayList<OldPochmanAlgorithm> algorithms = new ArrayList<>();
    private final String filePath = "src/inputs/pochmanTargetPosition.json";

    public OldPochmanCollection() {
        try (FileReader fileReader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, ArrayList<Object>>>() {}.getType();
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

    public void printAlgorithms() {
        algorithms.forEach(a -> System.out.println(a.getName() + " " + String.join(" ", a.getSetup()) + " " + a.getAlgorithmName()));
    }

    public boolean isItInAlgorithms(String id) {
        return algorithms.stream().anyMatch(a -> a.getName().equals(id));
    }

    public ArrayList<OldPochmanAlgorithm> getAlgorithms() {
        return algorithms;
    }
}
