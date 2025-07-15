package apiCalls.Utils.eupaBuilders.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "psvVehicleSize",
        "psvNoSmallVhlConfirmation",
        "psvSmallVhlConfirmation",
        "evidenceUploadType",
        "psvOperateSmallVhl",
        "psvSmallVhlNotes",
        "psvLimousines",
        "psvNoLimousineConfirmation",
        "psvOnlyLimousinesConfirmation",
        "version"
})
public class VehicleDeclarationModel {

    @JsonProperty("id")
    private String apllicationNumber;
    @JsonProperty("psvVehicleSize")
    private String psvVehicleSize;
    @JsonProperty("psvNoSmallVhlConfirmation")
    private String psvNoSmallVhlConfirmation;
    @JsonProperty("eidenceUploadType")
    private String evidenceUploadType;
    @JsonProperty("psvSmallVhlConfirmation")
    private String psvSmallVhlConfirmation;
    @JsonProperty("psvOperateSmallVhl")
    private String psvOperateSmallVhl;
    @JsonProperty("psvSmallVhlNotes")
    private String psvSmallVhlNotes;
    @JsonProperty("psvLimousines")
    private String psvLimousines;
    @JsonProperty("psvNoLimousineConfirmation")
    private String psvNoLimousineConfirmation;
    @JsonProperty("psvOnlyLimousinesConfirmation")
    private String psvOnlyLimousinesConfirmation;
    @JsonProperty("version")
    private Integer version;

    @JsonProperty("id")
    public String getId() {
        return apllicationNumber;
    }

    @JsonProperty("id")
    public void setId(String apllicationNumber) {
        this.apllicationNumber = apllicationNumber;
    }

    public VehicleDeclarationModel withId(String apllicationNumber) {
        this.apllicationNumber = apllicationNumber;
        return this;
    }

    @JsonProperty("psvVehicleSize")
    public String getPsvVehicleSize() {
        return psvVehicleSize;
    }

    @JsonProperty("psvVehicleSize")
    public void setPsvVehicleSize(String psvVehicleSize) {
        this.psvVehicleSize = psvVehicleSize;
    }

    public VehicleDeclarationModel withPsvVehicleSize(String psvVehicleSize) {
        this.psvVehicleSize = psvVehicleSize;
        return this;
    }

    @JsonProperty("psvNoSmallVhlConfirmation")
    public String getPsvNoSmallVhlConfirmation() {
        return psvNoSmallVhlConfirmation;
    }

    @JsonProperty("psvNoSmallVhlConfirmation")
    public void setPsvNoSmallVhlConfirmation(String psvNoSmallVhlConfirmation) {
        this.psvNoSmallVhlConfirmation = psvNoSmallVhlConfirmation;
    }

    public VehicleDeclarationModel withPsvNoSmallVhlConfirmation(String psvNoSmallVhlConfirmation) {
        this.psvNoSmallVhlConfirmation = psvNoSmallVhlConfirmation;
        return this;
    }

    @JsonProperty("psvSmallVhlConfirmation")
    public String getPsvSmallVhlConfirmation() {
        return psvSmallVhlConfirmation;
    }

    @JsonProperty("psvSmallVhlConfirmation")
    public void setPsvSmallVhlConfirmation(String psvSmallVhlConfirmation) {
        this.psvSmallVhlConfirmation = psvSmallVhlConfirmation;
    }

    public VehicleDeclarationModel withPsvSmallVhlConfirmation(String psvSmallVhlConfirmation) {
        this.psvSmallVhlConfirmation = psvSmallVhlConfirmation;
        return this;
    }

    @JsonProperty("evidenceUploadType")
    public String getEvidenceUploadType() {
        return evidenceUploadType;
    }

    @JsonProperty("evidenceUploadType")
    public void setEvidenceUploadType(String evidenceUploadType) {
        this.evidenceUploadType = evidenceUploadType;
    }

    public VehicleDeclarationModel withEvidenceUploadType(String evidenceUploadType) {
        this.evidenceUploadType = evidenceUploadType;
        return this;
    }

    @JsonProperty("psvOperateSmallVhl")
    public String getPsvOperateSmallVhl() {
        return psvOperateSmallVhl;
    }

    @JsonProperty("psvOperateSmallVhl")
    public void setPsvOperateSmallVhl(String psvOperateSmallVhl) {
        this.psvOperateSmallVhl = psvOperateSmallVhl;
    }

    public VehicleDeclarationModel withPsvOperateSmallVhl(String psvOperateSmallVhl) {
        this.psvOperateSmallVhl = psvOperateSmallVhl;
        return this;
    }

    @JsonProperty("psvSmallVhlNotes")
    public String getPsvSmallVhlNotes() {
        return psvSmallVhlNotes;
    }

    @JsonProperty("psvSmallVhlNotes")
    public void setPsvSmallVhlNotes(String psvSmallVhlNotes) {
        this.psvSmallVhlNotes = psvSmallVhlNotes;
    }

    public VehicleDeclarationModel withPsvSmallVhlNotes(String psvSmallVhlNotes) {
        this.psvSmallVhlNotes = psvSmallVhlNotes;
        return this;
    }

    @JsonProperty("psvLimousines")
    public String getPsvLimousines() {
        return psvLimousines;
    }

    @JsonProperty("psvLimousines")
    public void setPsvLimousines(String psvLimousines) {
        this.psvLimousines = psvLimousines;
    }

    public VehicleDeclarationModel withPsvLimousines(String psvLimousines) {
        this.psvLimousines = psvLimousines;
        return this;
    }

    @JsonProperty("psvNoLimousineConfirmation")
    public String getPsvNoLimousineConfirmation() {
        return psvNoLimousineConfirmation;
    }

    @JsonProperty("psvNoLimousineConfirmation")
    public void setPsvNoLimousineConfirmation(String psvNoLimousineConfirmation) {
        this.psvNoLimousineConfirmation = psvNoLimousineConfirmation;
    }

    public VehicleDeclarationModel withPsvNoLimousineConfirmation(String psvNoLimousineConfirmation) {
        this.psvNoLimousineConfirmation = psvNoLimousineConfirmation;
        return this;
    }

    @JsonProperty("psvOnlyLimousinesConfirmation")
    public String getPsvOnlyLimousinesConfirmation() {
        return psvOnlyLimousinesConfirmation;
    }

    @JsonProperty("psvOnlyLimousinesConfirmation")
    public void setPsvOnlyLimousinesConfirmation(String psvOnlyLimousinesConfirmation) {
        this.psvOnlyLimousinesConfirmation = psvOnlyLimousinesConfirmation;
    }

    public VehicleDeclarationModel withPsvOnlyLimousinesConfirmation(String psvOnlyLimousinesConfirmation) {
        this.psvOnlyLimousinesConfirmation = psvOnlyLimousinesConfirmation;
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

    public VehicleDeclarationModel withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", getId()) //
                .append("psvVehicleSize", getPsvVehicleSize())
                .append("psvNoSmallVhlConfirmation", getPsvNoSmallVhlConfirmation())
                .append("psvSmallVhlConfirmation()", getPsvSmallVhlConfirmation())
                .append("evidenceUploadType()", getEvidenceUploadType())
                .append("psvOperateSmallVhl", getPsvOperateSmallVhl())
                .append("psvSmallVhlNotes", getPsvSmallVhlNotes())
                .append("psvLimousines:", getPsvLimousines())
                .append("psvNoLimousineConfirmation", getPsvNoLimousineConfirmation())
                .append("psvOnlyLimousinesConfirmation", getPsvOnlyLimousinesConfirmation())
                .append("version", getVersion())
                .toString();
    }

}
