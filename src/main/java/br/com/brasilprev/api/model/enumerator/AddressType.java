package br.com.brasilprev.api.model.enumerator;

public enum AddressType {

    RESIDENTIAL("RESIDENTIAL", 0),
    COMMERCIAL("COMMERCIAL", 1),
    OTHER("OTHER", 2);

    public final String label;
    public final int atomicNumber;

    AddressType(String label, int atomicNumber) {
        this.label = label;
        this.atomicNumber = atomicNumber;
    }

    @Override
    public String toString() {
        return this.label;
    }

}
