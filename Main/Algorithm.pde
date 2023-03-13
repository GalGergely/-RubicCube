public class Algorithm {
    private String name;
    private String[] moves;

    public Algorithm(String name, String[] moves) {
        this.name = name;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public String[] getMoves() {
        return moves;
    }
}
