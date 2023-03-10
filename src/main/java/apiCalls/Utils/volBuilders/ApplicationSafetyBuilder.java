package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.platform.commons.util.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "version",
        "safetyConfirmation",
        "licence"
})
public class ApplicationSafetyBuilder {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("licence")
    private LicenceBuilder licence;
    @JsonProperty("safetyConfirmation")
    private String safetyConfirmation;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ApplicationSafetyBuilder withId(String id) {
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

    public ApplicationSafetyBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("safetyConfirmation")
    public String getSafetyConfirmation() {
        return safetyConfirmation;
    }

    @JsonProperty("safetyConfirmation")
    public void setSafetyConfirmation(String safetyConfirmation) {
        this.safetyConfirmation = safetyConfirmation;
    }

    public ApplicationSafetyBuilder withSafetyConfirmation(String safetyConfirmation) {
        this.safetyConfirmation = safetyConfirmation;
        return this;
    }

    @JsonProperty("licence")
    public LicenceBuilder getLicence() {
        return licence;
    }

    @JsonProperty("licence")
    public void setLicence(LicenceBuilder licence) {
        this.licence = licence;
    }

    public ApplicationSafetyBuilder withLicence(LicenceBuilder licence) {
        this.licence = licence;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("safetyConfirmation", getSafetyConfirmation())
                .append("licence", getLicence()).toString();
    }
}