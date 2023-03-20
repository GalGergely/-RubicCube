package settings;

import processing.data.JSONObject;

import java.awt.Color;

public class Settings {
    public int cameraZoomIn = 500;
    public int backgroundColor = 200;

    //cube base settings
    public java.awt.Color strokeColor = new java.awt.Color(0,0,0); //fekete
    public float strokeWeight = 0.1f;

    //color settings
    public java.awt.Color blue = new java.awt.Color(118,145,172);
    public java.awt.Color green = new java.awt.Color(121,185,119);
    public java.awt.Color white = new java.awt.Color(255,255,255);
    public java.awt.Color yellow = new java.awt.Color(238,214,128);
    public java.awt.Color orange = new java.awt.Color(239,153,104);
    public java.awt.Color red = new java.awt.Color(211,87,87);
    public boolean stepByStepSolving = false;

}