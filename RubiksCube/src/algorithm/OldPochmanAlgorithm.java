package algorithm;

import java.util.Arrays;

public class OldPochmanAlgorithm {
    private final String name;
    private final String[] setup;
    private final String algorithmName;

    public OldPochmanAlgorithm(String name, String[] setup, String algorithmName) {
        this.name = name;
        this.setup = setup;
        this.algorithmName = algorithmName;
    }

    public String getName() {
        return name;
    }

    public String[] getSetup() {
        return setup;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    @Override
    public String toString() {
        return "OldPochmanAlgorithm{name='" + name + '\'' + ", setup=" + Arrays.toString(setup) + ", algorithm='" + algorithmName + '\'' + '}';
    }
}