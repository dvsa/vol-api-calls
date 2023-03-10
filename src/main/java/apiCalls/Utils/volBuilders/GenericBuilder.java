package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class GenericBuilder {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("licenceType")
    private String licenceType;
    @JsonProperty("Address")
    private AddressBuilder address;
    @JsonProperty("paramValue")
    private String paramValue;
    @JsonProperty("description")
    private String description;
    @JsonProperty("licence")
    private String licence;

    @JsonProperty("licence")
    public String getLicence() {
        return licence;
    }

    @JsonProperty("licence")
    public void setLicence(String licence) {
        this.licence = licence;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public GenericBuilder withId(String id) {
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

    public GenericBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("licenceType")
    public String getLicenceType() {
        return licenceType;
    }

    @JsonProperty("licenceType")
    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public GenericBuilder withLicence(String licence) {
        this.licence = licence;
        return this;
    }


    public GenericBuilder withLicenceType(String licenceType) {
        this.licenceType = licenceType;
        return this;
    }

    @JsonProperty("Address")
    public AddressBuilder getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(AddressBuilder address) {
        this.address = address;
    }

    public GenericBuilder withAddress(AddressBuilder address) {
        this.address = address;
        return this;
    }

    @JsonProperty("paramValue")
    public String getParamValue() {
        return paramValue;
    }

    @JsonProperty("paramValue")
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public GenericBuilder withParamValue(String paramValue) {
        this.paramValue = paramValue;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public GenericBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("licenceType", getLicenceType())
                .append("registeredAddress", getAddress())
                .append("paramValue", getParamValue())
                .append("description", getDescription())
                .append("licence", getLicence()).toString();
    }
}