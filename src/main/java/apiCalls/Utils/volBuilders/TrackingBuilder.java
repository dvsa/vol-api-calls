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
        "vehiclesPsvStatus",
        "vehiclesStatus",
        "taxiPhvStatus",
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
    private Object discsStatus;
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
    @JsonProperty("vehiclesPsvStatus")
    private Object vehiclesPsvStatus;
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


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public TrackingBuilder withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("version")
    public int getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(int version) {
        this.version = version;
    }

    public TrackingBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    @JsonProperty("addressesStatus")
    public String getAddressesStatus() {
        return addressesStatus;
    }

    @JsonProperty("addressesStatus")
    public void setAddressesStatus(String addressesStatus) {
        this.addressesStatus = addressesStatus;
    }

    public TrackingBuilder withAddressesStatus(String addressesStatus) {
        this.addressesStatus = addressesStatus;
        return this;
    }

    @JsonProperty("businessDetailsStatus")
    public String getBusinessDetailsStatus() {
        return businessDetailsStatus;
    }

    @JsonProperty("businessDetailsStatus")
    public void setBusinessDetailsStatus(String businessDetailsStatus) {
        this.businessDetailsStatus = businessDetailsStatus;
    }

    public TrackingBuilder withBusinessDetailsStatus(String businessDetailsStatus) {
        this.businessDetailsStatus = businessDetailsStatus;
        return this;
    }

    @JsonProperty("businessTypeStatus")
    public String getBusinessTypeStatus() {
        return businessTypeStatus;
    }

    @JsonProperty("businessTypeStatus")
    public void setBusinessTypeStatus(String businessTypeStatus) {
        this.businessTypeStatus = businessTypeStatus;
    }

    public TrackingBuilder withBusinessTypeStatus(String businessTypeStatus) {
        this.businessTypeStatus = businessTypeStatus;
        return this;
    }

    @JsonProperty("communityLicencesStatus")
    public String getCommunityLicencesStatus() {
        return communityLicencesStatus;
    }

    @JsonProperty("communityLicencesStatus")
    public void setCommunityLicencesStatus(String communityLicencesStatus) {
        this.communityLicencesStatus = communityLicencesStatus;
    }

    public TrackingBuilder withCommunityLicencesStatus(String communityLicencesStatus) {
        this.communityLicencesStatus = communityLicencesStatus;
        return this;
    }

    @JsonProperty("conditionsUndertakingsStatus")
    public String getConditionsUndertakingsStatus() {
        return conditionsUndertakingsStatus;
    }

    @JsonProperty("conditionsUndertakingsStatus")
    public void setConditionsUndertakingsStatus(String conditionsUndertakingsStatus) {
        this.conditionsUndertakingsStatus = conditionsUndertakingsStatus;
    }

    public TrackingBuilder withConditionsUndertakingsStatus(String conditionsUndertakingsStatus) {
        this.conditionsUndertakingsStatus = conditionsUndertakingsStatus;
        return this;
    }

    @JsonProperty("convictionsPenaltiesStatus")
    public String getConvictionsPenaltiesStatus() {
        return convictionsPenaltiesStatus;
    }

    @JsonProperty("convictionsPenaltiesStatus")
    public void setConvictionsPenaltiesStatus(String convictionsPenaltiesStatus) {
        this.convictionsPenaltiesStatus = convictionsPenaltiesStatus;
    }

    public TrackingBuilder withConvictionsPenaltiesStatus(String convictionsPenaltiesStatus) {
        this.convictionsPenaltiesStatus = convictionsPenaltiesStatus;
        return this;
    }

    @JsonProperty("discsStatus")
    public Object getDiscsStatus() {
        return discsStatus;
    }

    @JsonProperty("discsStatus")
    public void setDiscsStatus(Object discsStatus) {
        this.discsStatus = discsStatus;
    }

    public TrackingBuilder withDiscsStatus(Object discsStatus) {
        this.discsStatus = discsStatus;
        return this;
    }

    @JsonProperty("financialEvidenceStatus")
    public String getFinancialEvidenceStatus() {
        return financialEvidenceStatus;
    }

    @JsonProperty("financialEvidenceStatus")
    public void setFinancialEvidenceStatus(String financialEvidenceStatus) {
        this.financialEvidenceStatus = financialEvidenceStatus;
    }

    public TrackingBuilder withFinancialEvidenceStatus(String financialEvidenceStatus) {
        this.financialEvidenceStatus = financialEvidenceStatus;
        return this;
    }

    @JsonProperty("financialHistoryStatus")
    public String getFinancialHistoryStatus() {
        return financialHistoryStatus;
    }

    @JsonProperty("financialHistoryStatus")
    public void setFinancialHistoryStatus(String financialHistoryStatus) {
        this.financialHistoryStatus = financialHistoryStatus;
    }

    public TrackingBuilder withFinancialHistoryStatus(String financialHistoryStatus) {
        this.financialHistoryStatus = financialHistoryStatus;
        return this;
    }

    @JsonProperty("licenceHistoryStatus")
    public String getLicenceHistoryStatus() {
        return licenceHistoryStatus;
    }

    @JsonProperty("licenceHistoryStatus")
    public void setLicenceHistoryStatus(String licenceHistoryStatus) {
        this.licenceHistoryStatus = licenceHistoryStatus;
    }

    public TrackingBuilder withLicenceHistoryStatus(String licenceHistoryStatus) {
        this.licenceHistoryStatus = licenceHistoryStatus;
        return this;
    }

    @JsonProperty("operatingCentresStatus")
    public String getOperatingCentresStatus() {
        return operatingCentresStatus;
    }

    @JsonProperty("operatingCentresStatus")
    public void setOperatingCentresStatus(String operatingCentresStatus) {
        this.operatingCentresStatus = operatingCentresStatus;
    }

    public TrackingBuilder withOperatingCentresStatus(String operatingCentresStatus) {
        this.operatingCentresStatus = operatingCentresStatus;
        return this;
    }

    @JsonProperty("peopleStatus")
    public String getPeopleStatus() {
        return peopleStatus;
    }

    @JsonProperty("peopleStatus")
    public void setPeopleStatus(String peopleStatus) {
        this.peopleStatus = peopleStatus;
    }

    public TrackingBuilder withPeopleStatus(String peopleStatus) {
        this.peopleStatus = peopleStatus;
        return this;
    }

    @JsonProperty("safetyStatus")
    public String getSafetyStatus() {
        return safetyStatus;
    }

    @JsonProperty("safetyStatus")
    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public TrackingBuilder withSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
        return this;
    }

    @JsonProperty("taxiPhvStatus")
    public String getTaxiPhvStatus() {
        return taxiPhvStatus;
    }

    @JsonProperty("taxiPhvStatus")
    public void setTaxiPhvStatus(String taxiPhvStatus) {
        this.taxiPhvStatus = taxiPhvStatus;
    }

    public TrackingBuilder withTaxiPhvStatus(String taxiPhvStatus) {
        this.taxiPhvStatus = taxiPhvStatus;
        return this;
    }

    @JsonProperty("transportManagersStatus")
    public String getTransportManagersStatus() {
        return transportManagersStatus;
    }

    @JsonProperty("transportManagersStatus")
    public void setTransportManagersStatus(String transportManagersStatus) {
        this.transportManagersStatus = transportManagersStatus;
    }

    public TrackingBuilder withTransportManagersStatus(String transportManagersStatus) {
        this.transportManagersStatus = transportManagersStatus;
        return this;
    }

    @JsonProperty("typeOfLicenceStatus")
    public String getTypeOfLicenceStatus() {
        return typeOfLicenceStatus;
    }

    @JsonProperty("typeOfLicenceStatus")
    public void setTypeOfLicenceStatus(String typeOfLicenceStatus) {
        this.typeOfLicenceStatus = typeOfLicenceStatus;
    }

    public TrackingBuilder withTypeOfLicenceStatus(String typeOfLicenceStatus) {
        this.typeOfLicenceStatus = typeOfLicenceStatus;
        return this;
    }

    @JsonProperty("declarationsInternalStatus")
    public String getDeclarationsInternalStatus() {
        return declarationsInternalStatus;
    }

    @JsonProperty("declarationsInternalStatus")
    public void setDeclarationsInternalStatus(String declarationsInternalStatus) {
        this.declarationsInternalStatus = declarationsInternalStatus;
    }

    public TrackingBuilder withDeclarationsInternalStatus(String declarationsInternalStatus) {
        this.declarationsInternalStatus = declarationsInternalStatus;
        return this;
    }

    @JsonProperty("vehiclesPsvStatus")
    public Object getVehiclesPsvStatus() {
        return vehiclesPsvStatus;
    }

    @JsonProperty("vehiclesPsvStatus")
    public void setVehiclesPsvStatus(Object vehiclesPsvStatus) {
        this.vehiclesPsvStatus = vehiclesPsvStatus;
    }

    public TrackingBuilder withVehiclesPsvStatus(Object vehiclesPsvStatus) {
        this.vehiclesPsvStatus = vehiclesPsvStatus;
        return this;
    }

    @JsonProperty("vehiclesStatus")
    public String getVehiclesStatus() {
        return vehiclesStatus;
    }

    @JsonProperty("vehiclesStatus")
    public void setVehiclesStatus(String vehiclesStatus) {
        this.vehiclesStatus = vehiclesStatus;
    }

    public TrackingBuilder withVehiclesStatus(String vehiclesStatus) {
        this.vehiclesStatus = vehiclesStatus;
        return this;
    }

    @JsonProperty("vehiclesSizeStatus")
    public String getVehiclesSizeStatus() {
        return vehiclesSizeStatus;
    }

    @JsonProperty("vehiclesSizeStatus")
    public void setVehiclesSizeStatus(String vehiclesSizeStatus) {
        this.vehiclesSizeStatus = vehiclesSizeStatus;
    }

    public TrackingBuilder withVehiclesSizeStatus(String vehiclesSizeStatus) {
        this.vehiclesSizeStatus = vehiclesSizeStatus;
        return this;
    }

    @JsonProperty("psvOperateSmallStatus")
    public String getPsvOperateSmallStatus() {
        return psvOperateSmallStatus;
    }

    @JsonProperty("psvOperateSmallStatus")
    public void setPsvOperateSmallStatus(String psvOperateSmallStatus) {
        this.psvOperateSmallStatus = psvOperateSmallStatus;
    }

    public TrackingBuilder withPsvOperateSmallStatus(String psvOperateSmallStatus) {
        this.psvOperateSmallStatus = psvOperateSmallStatus;
        return this;
    }

    @JsonProperty("psvOperateLargeStatus")
    public String getPsvOperateLargeStatus() {
        return psvOperateLargeStatus;
    }

    @JsonProperty("psvOperateLargeStatus")
    public void setPsvOperateLargeStatus(String psvOperateLargeStatus) {
        this.psvOperateLargeStatus = psvOperateLargeStatus;
    }

    public TrackingBuilder withPsvOperateLargeStatus(String psvOperateLargeStatus) {
        this.psvOperateLargeStatus = psvOperateLargeStatus;
        return this;
    }

    @JsonProperty("psvSmallConditionsStatus")
    public String getPsvSmallConditionsStatus() {
        return psvSmallConditionsStatus;
    }

    @JsonProperty("psvSmallConditionsStatus")
    public void setPsvSmallConditionsStatus(String psvSmallConditionsStatus) {
        this.psvSmallConditionsStatus = psvSmallConditionsStatus;
    }

    public TrackingBuilder withPsvSmallConditionsStatus(String psvSmallConditionsStatus) {
        this.psvSmallConditionsStatus = psvSmallConditionsStatus;
        return this;
    }

    @JsonProperty("psvOperateNoveltyStatus")
    public String getPsvOperateNoveltyStatus() {
        return psvOperateNoveltyStatus;
    }

    @JsonProperty("psvOperateNoveltyStatus")
    public void setPsvOperateNoveltyStatus(String psvOperateNoveltyStatus) {
        this.psvOperateNoveltyStatus = psvOperateNoveltyStatus;
    }

    public TrackingBuilder withPsvOperateNoveltyStatus(String psvOperateNoveltyStatus) {
        this.psvOperateNoveltyStatus = psvOperateNoveltyStatus;
        return this;
    }

    @JsonProperty("psvSmallPartWrittenStatus")
    public String getPsvSmallPartWrittenStatus() {
        return psvSmallPartWrittenStatus;
    }

    @JsonProperty("psvSmallPartWrittenStatus")
    public void setPsvSmallPartWrittenStatus(String psvSmallPartWrittenStatus) {
        this.psvSmallPartWrittenStatus = psvSmallPartWrittenStatus;
    }

    public TrackingBuilder withPsvSmallPartWrittenStatus(String psvSmallPartWrittenStatus) {
        this.psvSmallPartWrittenStatus = psvSmallPartWrittenStatus;
        return this;
    }

    @JsonProperty("psvDocumentaryEvidenceSmallStatus")
    public String getPsvDocumentaryEvidenceSmallStatus() {
        return psvDocumentaryEvidenceSmallStatus;
    }

    @JsonProperty("psvDocumentaryEvidenceSmallStatus")
    public void setPsvDocumentaryEvidenceSmallStatus(String psvDocumentaryEvidenceSmallStatus) {
        this.psvDocumentaryEvidenceSmallStatus = psvDocumentaryEvidenceSmallStatus;
    }

    public TrackingBuilder withPsvDocumentaryEvidenceSmallStatus(String psvDocumentaryEvidenceSmallStatus) {
        this.psvDocumentaryEvidenceSmallStatus = psvDocumentaryEvidenceSmallStatus;
        return this;
    }

    @JsonProperty("psvDocumentaryEvidenceLargeStatus")
    public String getPsvDocumentaryEvidenceLargeStatus() {
        return psvDocumentaryEvidenceLargeStatus;
    }

    @JsonProperty("psvDocumentaryEvidenceLargeStatus")
    public void setPsvDocumentaryEvidenceLargeStatus(String psvDocumentaryEvidenceLargeStatus) {
        this.psvDocumentaryEvidenceLargeStatus = psvDocumentaryEvidenceLargeStatus;
    }

    public TrackingBuilder withPsvDocumentaryEvidenceLargeStatus(String psvDocumentaryEvidenceLargeStatus) {
        this.psvDocumentaryEvidenceLargeStatus = psvDocumentaryEvidenceLargeStatus;
        return this;
    }

    @JsonProperty("psvMainOccupationUndertakingsStatus")
    public String getPsvMainOccupationUndertakingsStatus() {
        return psvMainOccupationUndertakingsStatus;
    }

    @JsonProperty("psvMainOccupationUndertakingsStatus")
    public void setPsvMainOccupationUndertakingsStatus(String psvMainOccupationUndertakingsStatus) {
        this.psvMainOccupationUndertakingsStatus = psvMainOccupationUndertakingsStatus;
    }

    public TrackingBuilder withPsvMainOccupationUndertakingsStatus(String psvMainOccupationUndertakingsStatus) {
        this.psvMainOccupationUndertakingsStatus = psvMainOccupationUndertakingsStatus;
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
                ", discsStatus=" + discsStatus +
                ", financialEvidenceStatus='" + financialEvidenceStatus + '\'' +
                ", financialHistoryStatus='" + financialHistoryStatus + '\'' +
                ", licenceHistoryStatus='" + licenceHistoryStatus + '\'' +
                ", operatingCentresStatus='" + operatingCentresStatus + '\'' +
                ", peopleStatus='" + peopleStatus + '\'' +
                ", safetyStatus='" + safetyStatus + '\'' +
                ", transportManagersStatus='" + transportManagersStatus + '\'' +
                ", typeOfLicenceStatus='" + typeOfLicenceStatus + '\'' +
                ", declarationsInternalStatus='" + declarationsInternalStatus + '\'' +
                ", vehiclesPsvStatus=" + vehiclesPsvStatus +
                ", vehiclesStatus='" + vehiclesStatus + '\'' +
                ", taxiPhvStatus='" + taxiPhvStatus + '\'' +
                ", vehiclesSizeStatus=" + vehiclesSizeStatus +
                ", psvOperateSmallStatus=" + psvOperateSmallStatus +
                ", psvOperateLargeStatus=" + psvOperateLargeStatus +
                ", psvSmallConditionsStatus=" + psvSmallConditionsStatus +
                ", psvOperateNoveltyStatus=" + psvOperateNoveltyStatus +
                ", psvSmallPartWrittenStatus=" + psvSmallPartWrittenStatus +
                ", psvDocumentaryEvidenceSmallStatus=" + psvDocumentaryEvidenceSmallStatus +
                ", psvDocumentaryEvidenceLargeStatus=" + psvDocumentaryEvidenceLargeStatus +
                ", psvMainOccupationUndertakingsStatus=" + psvMainOccupationUndertakingsStatus +
                '}';
    }
}