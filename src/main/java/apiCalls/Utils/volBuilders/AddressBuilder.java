package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.platform.commons.util.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class AddressBuilder {

    @JsonProperty("version")
    private String version;
    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("addressLine3")
    private String addressLine3;
    @JsonProperty("addressLine4")
    private String addressLine4;
    @JsonProperty("town")
    private String town;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public AddressBuilder withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public AddressBuilder withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("addressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public AddressBuilder withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    @JsonProperty("addressLine3")
    public String getAddressLine3() {
        return addressLine3;
    }

    @JsonProperty("addressLine3")
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public AddressBuilder withAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    @JsonProperty("addressLine4")
    public String getAddressLine4() {
        return addressLine4;
    }

    @JsonProperty("addressLine4")
    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public AddressBuilder withAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
        return this;
    }

    @JsonProperty("town")
    public String getTown() {
        return town;
    }

    @JsonProperty("town")
    public void setTown(String town) {
        this.town = town;
    }

    public AddressBuilder withTown(String town) {
        this.town = town;
        return this;
    }

    @JsonProperty("postcode")
    public String getPostcode() {
        return postcode;
    }

    @JsonProperty("postcode")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public AddressBuilder withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public AddressBuilder withCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("version", getVersion())
                .append("addressLine1", getAddressLine1())
                .append("addressLine2", getAddressLine2())
                .append("addressLine3", getAddressLine3())
                .append("addressLine4", getAddressLine4())
                .append("town", getTown())
                .append("postcode", getPostcode())
                .append("countryCode", getCountryCode())
                .toString();
    }
}