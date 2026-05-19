package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleSizeBuilder {

    @JsonProperty("id")
    private String id;

    @JsonProperty("psvVehicleSize")
    private String psvVehicleSize;

    @JsonProperty("version")
    private Integer version;

    public VehicleSizeBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public VehicleSizeBuilder withPsvVehicleSize(String psvVehicleSize) {
        this.psvVehicleSize = psvVehicleSize;
        return this;
    }

    public VehicleSizeBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }
}
