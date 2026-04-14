package apiCalls.enums;

import java.util.Arrays;

public enum LicenceType {
    RESTRICTED("ltyp_r"),
    STANDARD_NATIONAL("ltyp_sn"),
    STANDARD_INTERNATIONAL("ltyp_si"),
    SPECIAL_RESTRICTED("ltyp_sr");

    private final String value;

    LicenceType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    public static LicenceType getEnum(String name) {
        return Arrays.stream(values())
                .filter(type -> type.asString().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to convert to enum, name: " + name));
    }
}