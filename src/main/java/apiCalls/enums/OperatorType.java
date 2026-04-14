package apiCalls.enums;

import java.util.Arrays;

public enum OperatorType {
    GOODS("lcat_gv"),
    PUBLIC("lcat_psv");

    private final String value;

    OperatorType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    public static OperatorType getEnum(String name) {
        return Arrays.stream(values())
                .filter(type -> type.asString().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to convert to enum, name: " + name));
    }
}