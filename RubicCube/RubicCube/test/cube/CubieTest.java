package cube;

import junit.framework.TestCase;
import org.junit.Before;
import processing.core.PApplet;
import processing.core.PMatrix3D;
import settings.Facing;

public class CubieTest extends TestCase {
    FakeMain fm;

    public class FakeMain extends PApplet {
        String path;

        public void setup() {
            path = sketchPath();
        }
    }
    @Before
    public void init(){
        this.fm = new FakeMain();
        this.fm.setup();
        this.fm.draw();
        PApplet.main("FakeMain");

    }

    public void testTurnFacesX() {
        PMatrix3D matrix = new PMatrix3D();
        matrix.translate(0,0,0);
        System.out.println(this.fm);
        Cubie cubie = new Cubie(this.fm, matrix, 0, 0, 0, 0);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.UP);
        cubie.turnFacesX(1);
        assertEquals(cubie.getFaces()[3].getFacing(), Facing.BACK);
    }

    public void testTurnFacesY() {
    }

    public void testTurnFacesZ() {
    }

    public void testUpdate() {
    }
}