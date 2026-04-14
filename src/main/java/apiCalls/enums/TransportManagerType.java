package apiCalls.enums;

public enum TransportManagerType {
    INTERNAL("tm_t_e"),
    EXTERNAL("tm_t_i");

    private final String value;

    TransportManagerType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}