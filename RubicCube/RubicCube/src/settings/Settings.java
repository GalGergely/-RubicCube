/**
 * Settings is a class responsible for managing the application settings, including loading and saving them to a JSON file.
 */
package settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
    public int cameraZoomIn;
    public Color backgroundColor;
    public Color strokeColor;
    public float strokeWeight;
    public Color blue;
    public Color green;
    public Color white;
    public Color yellow;
    public Color orange;
    public Color red;
    public boolean stepByStepSolving;
    public boolean faceIdsCheckBox;

    /**
     * Constructor for the Settings class. Loads settings from a JSON file.
     */
    public Settings() {
        String filePath = "src/inputs/settings.json";
        try (FileReader fileReader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Map<String, Object> settingsMap = gson.fromJson(fileReader, Map.class);

            cameraZoomIn = ((Number) settingsMap.get("cameraZoomIn")).intValue();
            backgroundColor = getColorFromList((List<Number>) settingsMap.get("backgroundColor"));;
            strokeColor = getColorFromList((List<Number>) settingsMap.get("strokeColor"));
            strokeWeight = ((Number) settingsMap.get("strokeWeight")).floatValue();
            blue = getColorFromList((List<Number>) settingsMap.get("blue"));
            green = getColorFromList((List<Number>) settingsMap.get("green"));
            white = getColorFromList((List<Number>) settingsMap.get("white"));
            yellow = getColorFromList((List<Number>) settingsMap.get("yellow"));
            orange = getColorFromList((List<Number>) settingsMap.get("orange"));
            red = getColorFromList((List<Number>) settingsMap.get("red"));
            stepByStepSolving = (Boolean) settingsMap.get("stepByStepSolving");
            faceIdsCheckBox = (Boolean) settingsMap.get("faceIdsCheckBox");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current settings to a JSON file.
     */
    public void saveSettings() {
        String filePath = "src/inputs/settings.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Map<String, Object> settingsMap = new HashMap<>();
            settingsMap.put("cameraZoomIn", cameraZoomIn);
            settingsMap.put("backgroundColor", getColorList(backgroundColor));
            settingsMap.put("strokeColor", getColorList(strokeColor));
            settingsMap.put("strokeWeight", strokeWeight);
            settingsMap.put("blue", getColorList(blue));
            settingsMap.put("green", getColorList(green));
            settingsMap.put("white", getColorList(white));
            settingsMap.put("yellow", getColorList(yellow));
            settingsMap.put("orange", getColorList(orange));
            settingsMap.put("red", getColorList(red));
            settingsMap.put("stepByStepSolving", stepByStepSolving);
            settingsMap.put("faceIdsCheckBox", faceIdsCheckBox);

            gson.toJson(settingsMap, fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of integers representing the RGB values of the specified color.
     *
     * @param color Color object to convert to a list of integers.
     * @return List of integers representing the RGB values of the color.
     */
    private List<Integer> getColorList(Color color) {
        return List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Constructs a Color object from a list of numbers representing the RGB values.
     *
     * @param colorList List of numbers representing the RGB values.
     * @return Color object constructed from the specified list of numbers.
     */
    private Color getColorFromList(List<Number> colorList) {
        return new Color(colorList.get(0).intValue(), colorList.get(1).intValue(), colorList.get(2).intValue());
    }
}
