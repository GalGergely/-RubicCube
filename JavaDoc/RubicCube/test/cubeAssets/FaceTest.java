package cubeAssets;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PVector;
import settings.Facing;
import settings.Settings;

import static org.junit.Assert.*;
import static processing.core.PApplet.runSketch;

public class FaceTest {
    FakeMain fm;
    Settings setting = new Settings();
    String[] appletArgs = new String[]{"Gergos Cube"};

    @Before
    public void init(){
        this.fm = new FakeMain();
        runSketch(appletArgs, this.fm);
    }

    @Test
    public void turnX() {
        Face face = new Face(this.fm,new PVector(0,1,1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.turnX(this.fm.HALF_PI);
        System.out.println(face.getNormal().y);
        assertEquals(face.getNormal(),new PVector(0, -1, 1));
    }

    @Test
    public void turnY() {
        Face face = new Face(this.fm,new PVector(0,1,1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.turnY(this.fm.HALF_PI);
        assertEquals(face.getNormal(),new PVector(-1, 1, 0));
    }

    @Test
    public void turnZ() {
        Face face = new Face(this.fm,new PVector(1,1,1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.turnZ(this.fm.HALF_PI);
        assertEquals(face.getNormal(),new PVector(-1, 1, 1));
    }

    @Test
    public void rotateFacingX() {
        Face face = new Face(this.fm,new PVector(0,0,-1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.rotateFacingX(1);
        assertEquals(face.getFacing(), Facing.DOWN);

        Face face2 = new Face(this.fm,new PVector(0,-1,0), this.fm.color(0, 0, 255), 1, Facing.UP, this.setting);
        face2.rotateFacingX(1);
        assertEquals(face2.getFacing(), Facing.BACK);

        face.rotateFacingX(1);
        face.rotateFacingX(1);
        face.rotateFacingX(1);
        face2.rotateFacingX(1);
        face2.rotateFacingX(1);
        face2.rotateFacingX(1);
        assertEquals(face.getFacing(), Facing.BACK);
        assertEquals(face2.getFacing(), Facing.UP);

    }

    @Test
    public void rotateFacingY() {
        Face face = new Face(this.fm,new PVector(0,0,-1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.rotateFacingY(1);
        assertEquals(face.getFacing(), Facing.RIGHT);

        Face face2 = new Face(this.fm,new PVector(0,-1,0), this.fm.color(0, 0, 255), 1, Facing.UP, this.setting);
        face2.rotateFacingY(1);
        assertEquals(face2.getFacing(), Facing.UP);

        face.rotateFacingY(1);
        face.rotateFacingY(1);
        face.rotateFacingY(1);
        assertEquals(face.getFacing(), Facing.BACK);

    }

    @Test
    public void rotateFacingZ() {
        Face face = new Face(this.fm,new PVector(0,0,-1), this.fm.color(0, 0, 255), 1, Facing.BACK, this.setting);
        face.rotateFacingZ(1);
        assertEquals(face.getFacing(), Facing.BACK);

        Face face2 = new Face(this.fm,new PVector(0,-1,0), this.fm.color(0, 0, 255), 1, Facing.UP, this.setting);
        face2.rotateFacingZ(1);
        assertEquals(face2.getFacing(), Facing.RIGHT);
        face2.rotateFacingZ(1);
        face2.rotateFacingZ(1);
        face2.rotateFacingZ(1);
        assertEquals(face2.getFacing(), Facing.UP);
    }
}