package apiCalls.enums;

public enum Realm {
    SELF_SERVE("selfserve"),
    INTERNAL("internal");

    private final String value;

    Realm(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}