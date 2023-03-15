package algorithm;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OldPochmanCollectionTest{
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

    }
    @Test
    public void isItInAlgorithms() {
        OldPochmanCollection op = new OldPochmanCollection(this.fm);
        assertTrue(op.isItInAlgorithms("44"));
        assertFalse(op.isItInAlgorithms("fejenforgosultrarotacio"));
    }
}