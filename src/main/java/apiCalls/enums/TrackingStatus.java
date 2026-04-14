package apiCalls.enums;

/**
 * Tracking status values for application section completion.
 * Matches the valid values defined in olcs-transfer ApplicationTracking partial
 * (InArray validator with haystack: "0", "1", "2", "3").
 */
public enum TrackingStatus {
    NOT_STARTED("0"),
    ACCEPTED("1"),
    IN_PROGRESS("2"),
    UPDATED("3");

    private final String value;

    TrackingStatus(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}
