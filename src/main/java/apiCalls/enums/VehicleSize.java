package apiCalls.enums;


public enum VehicleSize {
    SMALL_VEHICLES_LESS_THAN_NINE_SEATS("app_veh_type_mixed"),
    NINE_SEATS_AND_ABOVE("app_veh_type_lgv");

    private final String VehicleSize;

    private VehicleSize(String vehicleTypes) {
        this.VehicleSize = vehicleTypes;
    }

    public String asString() {
        return VehicleSize;
    }
}