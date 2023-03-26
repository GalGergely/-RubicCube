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
    public int backgroundColor;
    public Color strokeColor;
    public float strokeWeight;
    public Color blue;
    public Color green;
    public Color white;
    public Color yellow;
    public Color orange;
    public Color red;
    public boolean stepByStepSolving;

    public Settings() {
        String filePath = "src/inputs/settings.json";
        try (FileReader fileReader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Map<String, Object> settingsMap = gson.fromJson(fileReader, Map.class);

            cameraZoomIn = ((Number) settingsMap.get("cameraZoomIn")).intValue();
            backgroundColor = ((Number) settingsMap.get("backgroundColor")).intValue();
            strokeColor = getColorFromList((List<Number>) settingsMap.get("strokeColor"));
            strokeWeight = ((Number) settingsMap.get("strokeWeight")).floatValue();
            blue = getColorFromList((List<Number>) settingsMap.get("blue"));
            green = getColorFromList((List<Number>) settingsMap.get("green"));
            white = getColorFromList((List<Number>) settingsMap.get("white"));
            yellow = getColorFromList((List<Number>) settingsMap.get("yellow"));
            orange = getColorFromList((List<Number>) settingsMap.get("orange"));
            red = getColorFromList((List<Number>) settingsMap.get("red"));
            stepByStepSolving = (Boolean) settingsMap.get("stepByStepSolving");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        String filePath = "src/inputs/settings.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Map<String, Object> settingsMap = new HashMap<>();
            settingsMap.put("cameraZoomIn", cameraZoomIn);
            settingsMap.put("backgroundColor", backgroundColor);
            settingsMap.put("strokeColor", getColorList(strokeColor));
            settingsMap.put("strokeWeight", strokeWeight);
            settingsMap.put("blue", getColorList(blue));
            settingsMap.put("green", getColorList(green));
            settingsMap.put("white", getColorList(white));
            settingsMap.put("yellow", getColorList(yellow));
            settingsMap.put("orange", getColorList(orange));
            settingsMap.put("red", getColorList(red));
            settingsMap.put("stepByStepSolving", stepByStepSolving);

            gson.toJson(settingsMap, fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getColorList(Color color) {
        return List.of(color.getRed(), color.getGreen(), color.getBlue());
    }

    private Color getColorFromList(List<Number> colorList) {
        return new Color(colorList.get(0).intValue(), colorList.get(1).intValue(), colorList.get(2).intValue());
    }
}
