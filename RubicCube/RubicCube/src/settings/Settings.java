package settings;

import processing.core.PApplet;

public class Settings {
    public int dimension = 3;
    public int sideLength = 50;
    public int cameraZoomIn = 500;
    public int backgroundColor = 200;
    public float cubeOffstet = (dimension - 1) * sideLength * 0.5f;

    //cube base settings
    public int boxColor = 255; //fehér
    public int strokeColor = 0; //fekete
    public float strokeWeight = 0.1f; //fekete
}