package algorithm;

public class OldPochmanAlgorithm {
    private String name;
    private String[] setup;
    private String algorithmName;

    public OldPochmanAlgorithm(String name, String[] setup, String algorithmName) {
        this.name = name;
        this.setup = setup;
        this.algorithmName = algorithmName;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String[] getSetup() { return setup; }
    public void setSetup(String[] setup) { this.setup = setup; }
    public String getAlgorithmName() { return algorithmName; }
    public void setAlgorithmName(String moves) { this.algorithmName = algorithmName; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OldPochmanAlgorithm{name='").append(name).append('\'');
        sb.append(", setup=").append(setup.toString());
        sb.append(", algorithm='").append(algorithmName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}