package algorithm;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.*;

public class AlgorithmCollectionTest {
    AlgorithmCollection ac;
    String[] str;
    @Before
    public void init(){
        ac = new AlgorithmCollection();
        str = new String[]{"l", "l"};

    }

    @Test
    public void add() {
        ac.add("test1", str);
        Algorithm newAlgo = ac.getAlgorithm("test1");
        assertEquals(newAlgo.getName(), "test1");
        assertEquals(newAlgo.getMoves(), str);
    }

    @Test
    public void delete() {
        ac.add("test1", str);
        ac.delete("test1");
        Algorithm newAlgo = ac.getAlgorithm("test1");
        assertEquals(newAlgo.getName(), "does not exist");
    }
}