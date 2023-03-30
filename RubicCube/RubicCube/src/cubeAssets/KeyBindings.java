/**
 * KeyBindings class provides a static method to execute cube moves
 * based on the key pressed by the user.
 */
package cubeAssets;

import processing.core.PApplet;
import processing.core.PVector;

public class KeyBindings {
    /**
     * Executes a cube move based on the key pressed.
     * @param keys Character representing the key pressed.
     * @param move Move object representing the current move state.
     * @param cube Cube object representing the current cube state.
     * @param sketch PApplet object representing the sketch window.
     * @return Move object representing the updated move state.
     */
    public static Move doMove(char keys, Move move, Cube cube, PApplet sketch) {
        if (!move.isAnimate()) {
            switch (keys) {
                case 'm' -> {
                    return new Move(sketch, new PVector(2,0,0), 1, cube);
                }
                case 'M' -> {
                    return new Move(sketch, new PVector(2,0,0), -1, cube);
                }
                case 'w' -> {
                    return new Move(sketch, new PVector(0,-2,0), 1, cube);
                }
                case 'W' -> {
                    return new Move(sketch, new PVector(0,2,0), -1, cube);
                }
                case 'l' -> {
                    return new Move(sketch, new PVector(-1,0,0), -1, cube);
                }
                case 'L' -> {
                    return new Move(sketch, new PVector(-1,0,0), 1, cube);
                }
                case 'r' -> {
                    return new Move(sketch, new PVector(1,0,0), 1, cube);
                }
                case 'R' -> {
                    return new Move(sketch, new PVector(1,0,0), -1, cube);
                }
                case 'f' -> {
                    return new Move(sketch, new PVector(0,0,1), 1, cube);
                }
                case 'F' -> {
                    return new Move(sketch, new PVector(0,0,1), -1, cube);
                }
                case 'b' -> {
                    return new Move(sketch, new PVector(0,0,-1), -1, cube);
                }
                case 'B' -> {
                    return new Move(sketch, new PVector(0,0,-1), 1, cube);
                }
                case 'u' -> {
                    return new Move(sketch, new PVector(0,-1, 0), -1, cube);
                }
                case 'U' -> {
                    return new Move(sketch, new PVector(0,-1,0), 1, cube);
                }
                case 'd' -> {
                    return new Move(sketch, new PVector(0,1,0), 1, cube);
                }
                case 'D' -> {
                    return new Move(sketch, new PVector(0,1,0), -1, cube);
                }
            }
        }
        return null;
    }
}
