package cubeAssets;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PMatrix3D;
import settings.Facing;
import settings.Settings;

import static org.junit.Assert.assertEquals;
import static processing.core.PApplet.runSketch;

public class CubieTest {
    FakeMain fm;
    String[] appletArgs;
    PMatrix3D matrix;


    @Before
    public void init() {
        this.fm = new FakeMain();
        this.appletArgs = new String[]{"--sketch-path= " + this.fm, "Gergos Cube"};
        runSketch(this.appletArgs, this.fm);
        this.matrix = new PMatrix3D();
    }

    @Test
    public void testTurnFacesX() {
        matrix.translate(0, 0, 0);
        Settings settings = new Settings();
        Cubie cubie = new Cubie(this.fm, matrix, 0, 0, 0, 0, settings);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.UP);
        cubie.turnFacesX(1);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.BACK);
    }

    @Test
    public void testTurnFacesY() {
        matrix.translate(0, 0, 0);
        Settings settings = new Settings();
        Cubie cubie = new Cubie(this.fm, matrix, 0, 0, 0, 0, settings);
        assertEquals(cubie.getFaces()[4].getFacing(), Facing.RIGHT);
        cubie.turnFacesY(1);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.UP);
    }

    @Test
    public void testTurnFacesZ() {
        matrix.translate(0, 0, 0);
        Settings settings = new Settings();
        Cubie cubie = new Cubie(this.fm, matrix, 0, 0, 0, 0, settings);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.UP);
        cubie.turnFacesZ(1);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.RIGHT);
    }
}