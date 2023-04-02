package cubeAssets;

import org.junit.Before;
import org.junit.Test;
import processing.core.PMatrix3D;
import processing.core.PVector;
import settings.Facing;
import settings.Settings;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static processing.core.PApplet.runSketch;

public class CubeTest {

    FakeMain fm;
    String[] appletArgs;
    Settings settings;
    Cube cube;


    @Before
    public void init() {
        this.fm = new FakeMain();
        this.appletArgs = new String[]{"--sketch-path= " + this.fm, "Gergos Cube"};
        runSketch(this.appletArgs, this.fm);
        settings = new Settings();
        this.cube = new Cube(this.fm, settings);
    }

    @Test
    public void checkIfAllEdgesAreSolved() {
        ArrayList<Cubie> expected = new ArrayList<>();
        cube.turnX(1, 1);
        cube.turnX(1, 1);
        cube.turnX(1, 1);
        assertNotEquals(cube.checkIfAllCornersAreSolved(), expected);
        cube.turnX(1, 1);
        assertEquals(cube.checkIfAllCornersAreSolved(), expected);
    }

    @Test
    public void checkIfAllCornersAreSolved() {
        ArrayList<Cubie> expected = new ArrayList<>();
        cube.turnX(1, 1);
        cube.turnX(1, 1);
        cube.turnX(1, 1);
        assertNotEquals(cube.checkIfAllCornersAreSolved(), expected);
        cube.turnX(1, 1);
        assertEquals(cube.checkIfAllCornersAreSolved(), expected);
    }

    @Test
    public void turnX() {
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
        cube.turnX(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, 1, -1));
        cube.turnX(-1,1);
        cube.turnX(-1,1);
        cube.turnX(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
    }

    @Test
    public void turnY() {
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
        cube.turnY(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, 1));
        cube.turnY(-1,1);
        cube.turnY(-1,1);
        cube.turnY(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
    }

    @Test
    public void turnZ() {
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
        cube.turnZ(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(1, -1, -1));
        cube.turnZ(-1,1);
        cube.turnZ(-1,1);
        cube.turnZ(-1,1);
        assertEquals(cube.getData()[0].getPosition(), new PVector(-1, -1, -1));
    }
}