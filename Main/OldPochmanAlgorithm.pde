public class OldPochmanAlgorithm {
    private String name;
    private String[] setup;
    private String algorithm;

    public OldPochmanAlgorithm(String name, String[] setup, String algorithm) {
        this.name = name;
        this.setup = setup;
        this.algorithm = algorithm;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for setup
    public String[] getSetup() {
        return setup;
    }

    // Setter for setup
    public void setSetup(String[] setup) {
        this.setup = setup;
    }

    // Getter for algorithm
    public String getAlgorithm() {
        return algorithm;
    }

    // Setter for algorithm
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OldPochmanAlgorithm{name='").append(name).append('\'');
        sb.append(", setup=").append(setup.toString());
        sb.append(", algorithm='").append(algorithm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
