package apiCalls.enums;

public enum UserRoles {
    SYSTEM_ADMIN("system-admin"),
    INTERNAL_ADMIN("internal-admin"),
    INTERNAL_LIMITED_READ_ONLY("internal-limited-read-only"),
    INTERNAL_READ_ONLY("internal-read-only"),
    INTERNAL_CASE_WORKER("internal-case-worker"),
    INTERNAL("internal");

    private final String userRoles;

    private UserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String asString() {
        return userRoles;
    }
}