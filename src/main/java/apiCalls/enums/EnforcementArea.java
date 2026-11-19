package apiCalls.enums;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

public enum EnforcementArea {
    NORTH_EAST("EA-B", "V048"),
    NORTH_WEST("EA-C", "V058"),
    MIDLANDS("EA-D", "V026"),
    EAST("EA-F", "N002"),
    WALES("EA-E", "N004"),
    WEST("EA-J", "V103"),
    LONDON("EA-H", "V001"),
    SCOTLAND("EA-A", "N003"),
    NORTHERN_IRELAND("EA-N", "NI01");

    private final String code;
    private final String localCode;

    EnforcementArea(String code, String localCode) {
        this.code = code.toUpperCase();
        this.localCode = localCode;
    }

    public String value() {
        return code;
    }

    public String value(EnvironmentType environment) {
        return environment == EnvironmentType.LOCAL ? localCode : code;
    }

    /**
     * @deprecated Use {@link #values()} instead.
     */
    @Deprecated(forRemoval = true)
    public static EnforcementArea[] enforcementAreaList() {
        return values();
    }
}