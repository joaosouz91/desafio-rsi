package br.com.brasilprev.api.model.enumerator;

public enum OrderStatus {

    WAITING_PAYMENT_APPROVAL("WAITING PAYMENT APPROVAL", 0),
    SEPARATING("SEPARATING", 1),
    ON_ITS_WAY("ON ITS WAY", 2),
    DELIVERED("DELIVERED", 3);

    public final String label;
    public final int atomicNumber;

    OrderStatus(String label, int atomicNumber) {
        this.label = label;
        this.atomicNumber = atomicNumber;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
