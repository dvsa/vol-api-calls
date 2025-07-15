package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleSizeBuilder {
    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("vehicleSize")
    private String vehicleSize;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public VehicleSizeBuilder withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }
    public VehicleSizeBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("vehicleSize")
    public String getVehicleSize() {
        return vehicleSize;
    }

    @JsonProperty("vehicleSize")
    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public  VehicleSizeBuilder withVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("psvVehicleSize", getVehicleSize()).toString();
    }
}
