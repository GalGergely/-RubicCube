package algorithm;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OldPochmanCollectionTest {
    OldPochmanCollection op;

    @Before
    public void init() {
        op = new OldPochmanCollection();
    }

    @Test
    public void isItInAlgorithms() {
        assertTrue(op.isItInAlgorithms("44"));
        assertFalse(op.isItInAlgorithms("fejenforgosultrarotacio"));
    }

}