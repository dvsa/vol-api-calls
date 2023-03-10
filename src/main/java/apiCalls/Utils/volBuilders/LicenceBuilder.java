package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class LicenceBuilder {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("safetyInsVehicles")
    private String safetyInsVehicles;
    @JsonProperty("safetyInsTrailers")
    private String safetyInsTrailers;
    @JsonProperty("safetyInsVaries")
    private String safetyInsVaries;
    @JsonProperty("tachographIns")
    private String tachographIns;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public LicenceBuilder withId(String id) {
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

    public LicenceBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("safetyInsVehicles")
    public String getSafetyInsVehicles() {
        return safetyInsVehicles;
    }

    @JsonProperty("safetyInsVehicles")
    public void setSafetyInsVehicles(String safetyInsVehicles) {
        this.safetyInsVehicles = safetyInsVehicles;
    }

    public LicenceBuilder withSafetyInsVehicles(String safetyInsVehicles) {
        this.safetyInsVehicles = safetyInsVehicles;
        return this;
    }

    @JsonProperty("safetyInsTrailers")
    public String getSafetyInsTrailers() {
        return safetyInsTrailers;
    }

    @JsonProperty("safetyInsTrailers")
    public void setSafetyInsTrailers(String safetyInsTrailers) {
        this.safetyInsTrailers = safetyInsTrailers;
    }

    public LicenceBuilder withSafetyInsTrailers(String safetyInsTrailers) {
        this.safetyInsTrailers = safetyInsTrailers;
        return this;
    }

    @JsonProperty("safetyInsVaries")
    public String getSafetyInsVaries() {
        return safetyInsVaries;
    }

    @JsonProperty("safetyInsVaries")
    public void setSafetyInsVaries(String safetyInsVaries) {
        this.safetyInsVaries = safetyInsVaries;
    }

    public LicenceBuilder withSafetyInsVaries(String safetyInsVaries) {
        this.safetyInsVaries = safetyInsVaries;
        return this;
    }

    @JsonProperty("tachographIns")
    public String getTachographIns() {
        return tachographIns;
    }

    @JsonProperty("tachographIns")
    public void setTachographIns(String tachographIns) {
        this.tachographIns = tachographIns;
    }

    public LicenceBuilder withTachographIns(String tachographIns) {
        this.tachographIns = tachographIns;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("safetyInsVehicles", getSafetyInsVehicles())
                .append("safetyInsVaries", getSafetyInsVaries())
                .append("safetyInsTrailers", getSafetyInsTrailers())
                .append("tachographIns", getTachographIns()).toString();
    }
}