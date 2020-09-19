package br.com.brasilprev.api.model.enumerator;

public enum ModelStatus {

    DISABLED("DISABLED", 0),
    ENABLED("ENABLED", 1);

    public final String label;
    public final int atomicNumber;

    ModelStatus(String label, int atomicNumber) {
        this.label = label;
        this.atomicNumber = atomicNumber;
    }

    @Override
    public String toString() {
        return this.label;
    }


}
