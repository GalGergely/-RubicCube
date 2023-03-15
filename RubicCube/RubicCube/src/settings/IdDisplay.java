package settings;

import processing.core.PApplet;

public class IdDisplay {
    private final PApplet sketch;
    int id;

    public IdDisplay(PApplet sketch, int id) {
        this.sketch = sketch;
        this.id = id;
    }

    public void show() {
        this.sketch.fill(0);
        this.sketch.scale(0.02f);
        this.sketch.text(id, 0 , 0 , 1 );
        this.sketch.scale(1, -1);
        this.sketch.text(id, 0 , 0 , -1 );
        this.sketch.scale(1, -1);
    }
}
