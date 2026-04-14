package apiCalls.enums;

public enum UserType {
    INTERNAL("internal"),
    EXTERNAL("selfserve");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}