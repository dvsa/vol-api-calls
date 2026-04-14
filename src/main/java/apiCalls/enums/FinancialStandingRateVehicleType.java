package apiCalls.enums;

import java.util.Arrays;

public enum FinancialStandingRateVehicleType {
    NA("fin_sta_veh_typ_na"),
    HGV("fin_sta_veh_typ_hgv"),
    LGV("fin_sta_veh_typ_lgv");

    private final String value;

    FinancialStandingRateVehicleType(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    public static FinancialStandingRateVehicleType getEnum(String name) {
        return Arrays.stream(values())
                .filter(type -> type.asString().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to convert to enum, name: " + name));
    }
}
