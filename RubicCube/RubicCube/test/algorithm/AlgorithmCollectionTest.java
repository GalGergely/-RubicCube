package algorithm;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.*;

public class AlgorithmCollectionTest {
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
    public void add() {
        AlgorithmCollection ac = new AlgorithmCollection(this.fm);
        String[] str = {"l", "l"} ;
        ac.add("test1", str);
        Algorithm newAlgo = ac.getAlgorithm("test1");
        assertEquals(newAlgo.getName(), "test1");
        assertEquals(newAlgo.getMoves(), str);
    }

    @Test
    public void delete() {
        AlgorithmCollection ac = new AlgorithmCollection(this.fm);
        String[] str = {"l", "l"} ;
        ac.add("test1", str);
        ac.delete("test1");
        Algorithm newAlgo = ac.getAlgorithm("test1");
        assertEquals(newAlgo.getName(), "does not exist");
    }
}