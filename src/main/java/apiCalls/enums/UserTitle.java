package apiCalls.enums;

public enum UserTitle {
    MR("title_mr"),
    MRS("title_mrs"),
    MS("title_ms");

    private final String value;

    UserTitle(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}