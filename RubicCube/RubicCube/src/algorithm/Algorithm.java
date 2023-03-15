package algorithm;

public class Algorithm {
    private final String name;
    private final String[] moves;

    public Algorithm(String name, String[] moves) {
        this.name = name;
        this.moves = moves;
    }

    public String getName() { return name; }
    public String[] getMoves() { return moves; }
}
