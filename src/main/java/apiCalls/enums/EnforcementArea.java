package apiCalls.enums;

public enum EnforcementArea {
    NORTH_EAST("EA-B"),
    NORTH_WEST("EA-C"),
    MIDLANDS("EA-D"),
    EAST("EA-F"),
    WALES("EA-E"),
    WEST("EA-J"),
    LONDON("EA-H"),
    SCOTLAND("EA-A"),
    NORTHERN_IRELAND("EA-N");

    private final String code;

    EnforcementArea(String code) {
        this.code = code.toUpperCase();
    }

    public String value() {
        return code;
    }

    /**
     * @deprecated Use {@link #values()} instead.
     */
    @Deprecated(forRemoval = true)
    public static EnforcementArea[] enforcementAreaList() {
        return values();
    }
}