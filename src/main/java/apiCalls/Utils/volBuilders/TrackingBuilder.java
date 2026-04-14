package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "version",
        "addressesStatus",
        "businessDetailsStatus",
        "businessTypeStatus",
        "communityLicencesStatus",
        "conditionsUndertakingsStatus",
        "convictionsPenaltiesStatus",
        "discsStatus",
        "financialEvidenceStatus",
        "financialHistoryStatus",
        "licenceHistoryStatus",
        "operatingCentresStatus",
        "peopleStatus",
        "safetyStatus",
        "taxiPhvStatus",
        "transportManagersStatus",
        "typeOfLicenceStatus",
        "declarationsInternalStatus",
        "vehiclesDeclarationsStatus",
        "vehiclesPsvStatus",
        "vehiclesStatus",
        "vehiclesSizeStatus",
        "psvOperateSmallStatus",
        "psvOperateLargeStatus",
        "psvSmallConditionsStatus",
        "psvOperateNoveltyStatus",
        "psvSmallPartWrittenStatus",
        "psvDocumentaryEvidenceSmallStatus",
        "psvDocumentaryEvidenceLargeStatus",
        "psvMainOccupationUndertakingsStatus"
})
public class TrackingBuilder {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private int version;
    @JsonProperty("addressesStatus")
    private String addressesStatus;
    @JsonProperty("businessDetailsStatus")
    private String businessDetailsStatus;
    @JsonProperty("businessTypeStatus")
    private String businessTypeStatus;
    @JsonProperty("communityLicencesStatus")
    private String communityLicencesStatus;
    @JsonProperty("conditionsUndertakingsStatus")
    private String conditionsUndertakingsStatus;
    @JsonProperty("convictionsPenaltiesStatus")
    private String convictionsPenaltiesStatus;
    @JsonProperty("discsStatus")
    private String discsStatus;
    @JsonProperty("financialEvidenceStatus")
    private String financialEvidenceStatus;
    @JsonProperty("financialHistoryStatus")
    private String financialHistoryStatus;
    @JsonProperty("licenceHistoryStatus")
    private String licenceHistoryStatus;
    @JsonProperty("operatingCentresStatus")
    private String operatingCentresStatus;
    @JsonProperty("peopleStatus")
    private String peopleStatus;
    @JsonProperty("safetyStatus")
    private String safetyStatus;
    @JsonProperty("transportManagersStatus")
    private String transportManagersStatus;
    @JsonProperty("typeOfLicenceStatus")
    private String typeOfLicenceStatus;
    @JsonProperty("declarationsInternalStatus")
    private String declarationsInternalStatus;
    @JsonProperty("vehiclesDeclarationsStatus")
    private String vehiclesDeclarationsStatus;
    @JsonProperty("vehiclesPsvStatus")
    private String vehiclesPsvStatus;
    @JsonProperty("vehiclesStatus")
    private String vehiclesStatus;
    @JsonProperty("taxiPhvStatus")
    private String taxiPhvStatus;
    @JsonProperty("vehiclesSizeStatus")
    private String vehiclesSizeStatus;
    @JsonProperty("psvOperateSmallStatus")
    private String psvOperateSmallStatus;
    @JsonProperty("psvOperateLargeStatus")
    private String psvOperateLargeStatus;
    @JsonProperty("psvSmallConditionsStatus")
    private String psvSmallConditionsStatus;
    @JsonProperty("psvOperateNoveltyStatus")
    private String psvOperateNoveltyStatus;
    @JsonProperty("psvSmallPartWrittenStatus")
    private String psvSmallPartWrittenStatus;
    @JsonProperty("psvDocumentaryEvidenceSmallStatus")
    private String psvDocumentaryEvidenceSmallStatus;
    @JsonProperty("psvDocumentaryEvidenceLargeStatus")
    private String psvDocumentaryEvidenceLargeStatus;
    @JsonProperty("psvMainOccupationUndertakingsStatus")
    private String psvMainOccupationUndertakingsStatus;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public TrackingBuilder withId(String id) { this.id = id; return this; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    public TrackingBuilder withVersion(int version) { this.version = version; return this; }

    public String getAddressesStatus() { return addressesStatus; }
    public void setAddressesStatus(String addressesStatus) { this.addressesStatus = addressesStatus; }
    public TrackingBuilder withAddressesStatus(String addressesStatus) { this.addressesStatus = addressesStatus; return this; }

    public String getBusinessDetailsStatus() { return businessDetailsStatus; }
    public void setBusinessDetailsStatus(String businessDetailsStatus) { this.businessDetailsStatus = businessDetailsStatus; }
    public TrackingBuilder withBusinessDetailsStatus(String businessDetailsStatus) { this.businessDetailsStatus = businessDetailsStatus; return this; }

    public String getBusinessTypeStatus() { return businessTypeStatus; }
    public void setBusinessTypeStatus(String businessTypeStatus) { this.businessTypeStatus = businessTypeStatus; }
    public TrackingBuilder withBusinessTypeStatus(String businessTypeStatus) { this.businessTypeStatus = businessTypeStatus; return this; }

    public String getCommunityLicencesStatus() { return communityLicencesStatus; }
    public void setCommunityLicencesStatus(String communityLicencesStatus) { this.communityLicencesStatus = communityLicencesStatus; }
    public TrackingBuilder withCommunityLicencesStatus(String communityLicencesStatus) { this.communityLicencesStatus = communityLicencesStatus; return this; }

    public String getConditionsUndertakingsStatus() { return conditionsUndertakingsStatus; }
    public void setConditionsUndertakingsStatus(String conditionsUndertakingsStatus) { this.conditionsUndertakingsStatus = conditionsUndertakingsStatus; }
    public TrackingBuilder withConditionsUndertakingsStatus(String conditionsUndertakingsStatus) { this.conditionsUndertakingsStatus = conditionsUndertakingsStatus; return this; }

    public String getConvictionsPenaltiesStatus() { return convictionsPenaltiesStatus; }
    public void setConvictionsPenaltiesStatus(String convictionsPenaltiesStatus) { this.convictionsPenaltiesStatus = convictionsPenaltiesStatus; }
    public TrackingBuilder withConvictionsPenaltiesStatus(String convictionsPenaltiesStatus) { this.convictionsPenaltiesStatus = convictionsPenaltiesStatus; return this; }

    public String getDiscsStatus() { return discsStatus; }
    public void setDiscsStatus(String discsStatus) { this.discsStatus = discsStatus; }
    public TrackingBuilder withDiscsStatus(String discsStatus) { this.discsStatus = discsStatus; return this; }

    public String getFinancialEvidenceStatus() { return financialEvidenceStatus; }
    public void setFinancialEvidenceStatus(String financialEvidenceStatus) { this.financialEvidenceStatus = financialEvidenceStatus; }
    public TrackingBuilder withFinancialEvidenceStatus(String financialEvidenceStatus) { this.financialEvidenceStatus = financialEvidenceStatus; return this; }

    public String getFinancialHistoryStatus() { return financialHistoryStatus; }
    public void setFinancialHistoryStatus(String financialHistoryStatus) { this.financialHistoryStatus = financialHistoryStatus; }
    public TrackingBuilder withFinancialHistoryStatus(String financialHistoryStatus) { this.financialHistoryStatus = financialHistoryStatus; return this; }

    public String getLicenceHistoryStatus() { return licenceHistoryStatus; }
    public void setLicenceHistoryStatus(String licenceHistoryStatus) { this.licenceHistoryStatus = licenceHistoryStatus; }
    public TrackingBuilder withLicenceHistoryStatus(String licenceHistoryStatus) { this.licenceHistoryStatus = licenceHistoryStatus; return this; }

    public String getOperatingCentresStatus() { return operatingCentresStatus; }
    public void setOperatingCentresStatus(String operatingCentresStatus) { this.operatingCentresStatus = operatingCentresStatus; }
    public TrackingBuilder withOperatingCentresStatus(String operatingCentresStatus) { this.operatingCentresStatus = operatingCentresStatus; return this; }

    public String getPeopleStatus() { return peopleStatus; }
    public void setPeopleStatus(String peopleStatus) { this.peopleStatus = peopleStatus; }
    public TrackingBuilder withPeopleStatus(String peopleStatus) { this.peopleStatus = peopleStatus; return this; }

    public String getSafetyStatus() { return safetyStatus; }
    public void setSafetyStatus(String safetyStatus) { this.safetyStatus = safetyStatus; }
    public TrackingBuilder withSafetyStatus(String safetyStatus) { this.safetyStatus = safetyStatus; return this; }

    public String getTaxiPhvStatus() { return taxiPhvStatus; }
    public void setTaxiPhvStatus(String taxiPhvStatus) { this.taxiPhvStatus = taxiPhvStatus; }
    public TrackingBuilder withTaxiPhvStatus(String taxiPhvStatus) { this.taxiPhvStatus = taxiPhvStatus; return this; }

    public String getTransportManagersStatus() { return transportManagersStatus; }
    public void setTransportManagersStatus(String transportManagersStatus) { this.transportManagersStatus = transportManagersStatus; }
    public TrackingBuilder withTransportManagersStatus(String transportManagersStatus) { this.transportManagersStatus = transportManagersStatus; return this; }

    public String getTypeOfLicenceStatus() { return typeOfLicenceStatus; }
    public void setTypeOfLicenceStatus(String typeOfLicenceStatus) { this.typeOfLicenceStatus = typeOfLicenceStatus; }
    public TrackingBuilder withTypeOfLicenceStatus(String typeOfLicenceStatus) { this.typeOfLicenceStatus = typeOfLicenceStatus; return this; }

    public String getDeclarationsInternalStatus() { return declarationsInternalStatus; }
    public void setDeclarationsInternalStatus(String declarationsInternalStatus) { this.declarationsInternalStatus = declarationsInternalStatus; }
    public TrackingBuilder withDeclarationsInternalStatus(String declarationsInternalStatus) { this.declarationsInternalStatus = declarationsInternalStatus; return this; }

    public String getVehiclesDeclarationsStatus() { return vehiclesDeclarationsStatus; }
    public void setVehiclesDeclarationsStatus(String vehiclesDeclarationsStatus) { this.vehiclesDeclarationsStatus = vehiclesDeclarationsStatus; }
    public TrackingBuilder withVehiclesDeclarationsStatus(String vehiclesDeclarationsStatus) { this.vehiclesDeclarationsStatus = vehiclesDeclarationsStatus; return this; }

    public String getVehiclesPsvStatus() { return vehiclesPsvStatus; }
    public void setVehiclesPsvStatus(String vehiclesPsvStatus) { this.vehiclesPsvStatus = vehiclesPsvStatus; }
    public TrackingBuilder withVehiclesPsvStatus(String vehiclesPsvStatus) { this.vehiclesPsvStatus = vehiclesPsvStatus; return this; }

    public String getVehiclesStatus() { return vehiclesStatus; }
    public void setVehiclesStatus(String vehiclesStatus) { this.vehiclesStatus = vehiclesStatus; }
    public TrackingBuilder withVehiclesStatus(String vehiclesStatus) { this.vehiclesStatus = vehiclesStatus; return this; }

    public String getVehiclesSizeStatus() { return vehiclesSizeStatus; }
    public void setVehiclesSizeStatus(String vehiclesSizeStatus) { this.vehiclesSizeStatus = vehiclesSizeStatus; }
    public TrackingBuilder withVehiclesSizeStatus(String vehiclesSizeStatus) { this.vehiclesSizeStatus = vehiclesSizeStatus; return this; }

    public String getPsvOperateSmallStatus() { return psvOperateSmallStatus; }
    public void setPsvOperateSmallStatus(String psvOperateSmallStatus) { this.psvOperateSmallStatus = psvOperateSmallStatus; }
    public TrackingBuilder withPsvOperateSmallStatus(String psvOperateSmallStatus) { this.psvOperateSmallStatus = psvOperateSmallStatus; return this; }

    public String getPsvOperateLargeStatus() { return psvOperateLargeStatus; }
    public void setPsvOperateLargeStatus(String psvOperateLargeStatus) { this.psvOperateLargeStatus = psvOperateLargeStatus; }
    public TrackingBuilder withPsvOperateLargeStatus(String psvOperateLargeStatus) { this.psvOperateLargeStatus = psvOperateLargeStatus; return this; }

    public String getPsvSmallConditionsStatus() { return psvSmallConditionsStatus; }
    public void setPsvSmallConditionsStatus(String psvSmallConditionsStatus) { this.psvSmallConditionsStatus = psvSmallConditionsStatus; }
    public TrackingBuilder withPsvSmallConditionsStatus(String psvSmallConditionsStatus) { this.psvSmallConditionsStatus = psvSmallConditionsStatus; return this; }

    public String getPsvOperateNoveltyStatus() { return psvOperateNoveltyStatus; }
    public void setPsvOperateNoveltyStatus(String psvOperateNoveltyStatus) { this.psvOperateNoveltyStatus = psvOperateNoveltyStatus; }
    public TrackingBuilder withPsvOperateNoveltyStatus(String psvOperateNoveltyStatus) { this.psvOperateNoveltyStatus = psvOperateNoveltyStatus; return this; }

    public String getPsvSmallPartWrittenStatus() { return psvSmallPartWrittenStatus; }
    public void setPsvSmallPartWrittenStatus(String psvSmallPartWrittenStatus) { this.psvSmallPartWrittenStatus = psvSmallPartWrittenStatus; }
    public TrackingBuilder withPsvSmallPartWrittenStatus(String psvSmallPartWrittenStatus) { this.psvSmallPartWrittenStatus = psvSmallPartWrittenStatus; return this; }

    public String getPsvDocumentaryEvidenceSmallStatus() { return psvDocumentaryEvidenceSmallStatus; }
    public void setPsvDocumentaryEvidenceSmallStatus(String psvDocumentaryEvidenceSmallStatus) { this.psvDocumentaryEvidenceSmallStatus = psvDocumentaryEvidenceSmallStatus; }
    public TrackingBuilder withPsvDocumentaryEvidenceSmallStatus(String psvDocumentaryEvidenceSmallStatus) { this.psvDocumentaryEvidenceSmallStatus = psvDocumentaryEvidenceSmallStatus; return this; }

    public String getPsvDocumentaryEvidenceLargeStatus() { return psvDocumentaryEvidenceLargeStatus; }
    public void setPsvDocumentaryEvidenceLargeStatus(String psvDocumentaryEvidenceLargeStatus) { this.psvDocumentaryEvidenceLargeStatus = psvDocumentaryEvidenceLargeStatus; }
    public TrackingBuilder withPsvDocumentaryEvidenceLargeStatus(String psvDocumentaryEvidenceLargeStatus) { this.psvDocumentaryEvidenceLargeStatus = psvDocumentaryEvidenceLargeStatus; return this; }

    public String getPsvMainOccupationUndertakingsStatus() { return psvMainOccupationUndertakingsStatus; }
    public void setPsvMainOccupationUndertakingsStatus(String psvMainOccupationUndertakingsStatus) { this.psvMainOccupationUndertakingsStatus = psvMainOccupationUndertakingsStatus; }
    public TrackingBuilder withPsvMainOccupationUndertakingsStatus(String psvMainOccupationUndertakingsStatus) { this.psvMainOccupationUndertakingsStatus = psvMainOccupationUndertakingsStatus; return this; }

    /**
     * Sets all tracking statuses to the given value.
     * Useful for granting where all sections need to be marked as accepted.
     */
    public TrackingBuilder withAllStatuses(String status) {
        this.addressesStatus = status;
        this.businessDetailsStatus = status;
        this.businessTypeStatus = status;
        this.communityLicencesStatus = status;
        this.conditionsUndertakingsStatus = status;
        this.convictionsPenaltiesStatus = status;
        this.discsStatus = status;
        this.financialEvidenceStatus = status;
        this.financialHistoryStatus = status;
        this.licenceHistoryStatus = status;
        this.operatingCentresStatus = status;
        this.peopleStatus = status;
        this.safetyStatus = status;
        this.taxiPhvStatus = status;
        this.transportManagersStatus = status;
        this.typeOfLicenceStatus = status;
        this.declarationsInternalStatus = status;
        this.vehiclesDeclarationsStatus = status;
        this.vehiclesPsvStatus = status;
        this.vehiclesStatus = status;
        this.vehiclesSizeStatus = status;
        this.psvOperateSmallStatus = status;
        this.psvOperateLargeStatus = status;
        this.psvSmallConditionsStatus = status;
        this.psvOperateNoveltyStatus = status;
        this.psvSmallPartWrittenStatus = status;
        this.psvDocumentaryEvidenceSmallStatus = status;
        this.psvDocumentaryEvidenceLargeStatus = status;
        this.psvMainOccupationUndertakingsStatus = status;
        return this;
    }

    @Override
    public String toString() {
        return "TrackingBuilder{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", addressesStatus='" + addressesStatus + '\'' +
                ", businessDetailsStatus='" + businessDetailsStatus + '\'' +
                ", businessTypeStatus='" + businessTypeStatus + '\'' +
                ", communityLicencesStatus='" + communityLicencesStatus + '\'' +
                ", conditionsUndertakingsStatus='" + conditionsUndertakingsStatus + '\'' +
                ", convictionsPenaltiesStatus='" + convictionsPenaltiesStatus + '\'' +
                ", discsStatus='" + discsStatus + '\'' +
                ", financialEvidenceStatus='" + financialEvidenceStatus + '\'' +
                ", financialHistoryStatus='" + financialHistoryStatus + '\'' +
                ", licenceHistoryStatus='" + licenceHistoryStatus + '\'' +
                ", operatingCentresStatus='" + operatingCentresStatus + '\'' +
                ", peopleStatus='" + peopleStatus + '\'' +
                ", safetyStatus='" + safetyStatus + '\'' +
                ", taxiPhvStatus='" + taxiPhvStatus + '\'' +
                ", transportManagersStatus='" + transportManagersStatus + '\'' +
                ", typeOfLicenceStatus='" + typeOfLicenceStatus + '\'' +
                ", declarationsInternalStatus='" + declarationsInternalStatus + '\'' +
                ", vehiclesDeclarationsStatus='" + vehiclesDeclarationsStatus + '\'' +
                ", vehiclesPsvStatus='" + vehiclesPsvStatus + '\'' +
                ", vehiclesStatus='" + vehiclesStatus + '\'' +
                ", vehiclesSizeStatus='" + vehiclesSizeStatus + '\'' +
                ", psvOperateSmallStatus='" + psvOperateSmallStatus + '\'' +
                ", psvOperateLargeStatus='" + psvOperateLargeStatus + '\'' +
                ", psvSmallConditionsStatus='" + psvSmallConditionsStatus + '\'' +
                ", psvOperateNoveltyStatus='" + psvOperateNoveltyStatus + '\'' +
                ", psvSmallPartWrittenStatus='" + psvSmallPartWrittenStatus + '\'' +
                ", psvDocumentaryEvidenceSmallStatus='" + psvDocumentaryEvidenceSmallStatus + '\'' +
                ", psvDocumentaryEvidenceLargeStatus='" + psvDocumentaryEvidenceLargeStatus + '\'' +
                ", psvMainOccupationUndertakingsStatus='" + psvMainOccupationUndertakingsStatus + '\'' +
                '}';
    }
}