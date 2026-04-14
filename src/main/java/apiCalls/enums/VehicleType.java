package apiCalls.enums;

public enum VehicleType {
    MIXED_FLEET("app_veh_type_mixed"),
    LGV_ONLY_FLEET("app_veh_type_lgv");

    private final String value;

    VehicleType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}