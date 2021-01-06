package apiCalls.actions;

import activesupport.faker.FakerUtils;
import apiCalls.Utils.builders.*;
import apiCalls.Utils.generic.*;
import activesupport.http.RestUtils;
import activesupport.number.Int;
import activesupport.string.Str;
import activesupport.system.Properties;
import apiCalls.enums.*;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.LinkedHashMap;

public class CreateApplication extends BaseAPI {

    FakerUtils faker = new FakerUtils();

    private static int version = 1;

    private RegisterUser user;
    private GetUserDetails userDetails;
    private String applicationId;
    private String licenceId;
    private String operatorType;
    private String licenceType;
    private String niFlag = System.getProperty("ni");
    private String isInterim;
    private String organisationName;
    private String organisationEmailAddress;
    private String phoneNumber;
    private String companyNumber;
    private String natureOfBusiness;
    private TrafficArea trafficArea;
    private EnforcementArea enforcementArea;
    private String postCodeByTrafficArea;
    private String countryCode;
    private String psvVehicleSize;
    private String psvNoSmallVhlConfirmation;
    private String psvOperateSmallVhl;
    private String psvSmallVhlNotes;
    private String psvLimousines;
    private String psvNoLimousineConfirmation;
    private String psvOnlyLimousinesConfirmation;
    private String directorTitle;
    private String directorForeName;
    private String directorFamilyName;
    private String directorDOB;
    private String registeredAddressLine1;
    private String registeredAddressLine2;
    private String registeredAddressLine3;
    private String registeredAddressLine4;
    private String registeredTown;
    private String registeredPostCode;
    private String correspondenceAddressLine1;
    private String correspondenceAddressLine2;
    private String correspondenceAddressLine3;
    private String correspondenceAddressLine4;
    private String correspondenceTown;
    private String correspondencePostCode;
    private String establishmentAddressLine1;
    private String establishmentAddressLine2;
    private String establishmentAddressLine3;
    private String establishmentAddressLine4;
    private String establishmentTown;
    private String establishmentPostCode;
    private String transportManagerType;
    private String transportManagerApplicationId;
    private String isOwner;
    private String transportManagerFirstName;
    private String transportManagerLastName;
    private String transportManagerDOB;
    private String transportManagerEmailAddress;
    private String transportManagerUserName;
    private String transportManagerAddressLine1;
    private String transportManagerAddressLine2;
    private String transportManagerAddressLine3;
    private String transportManagerAddressLine4;
    private String transportManagerTown;
    private String transportManagerPostCode;
    private String operatingCentreAddressLine1;
    private String operatingCentreAddressLine2;
    private String operatingCentreAddressLine3;
    private String operatingCentreAddressLine4;
    private String operatingCentreTown;
    private String operatingCentrePostCode;
    private String transportConsultantName;
    private String transportConsultantPhone;
    private String transportConsultantEmailAddress;
    private String transportConsultantAddressLine1;
    private String transportConsultantAddressLine2;
    private String transportConsultantAddressLine3;
    private String transportConsultantAddressLine4;
    private String transportConsultantTown;
    private String transportConsultantPostCode;
    private String safetyInspectorForeName;
    private String safetyInspectorFamilyName;
    private String safetyInspectorDOB;
    private String safetyInspectorAddressLine1;
    private String safetyInspectorAddressLine2;
    private String safetyInspectorAddressLine3;
    private String safetyInspectorAddressLine4;
    private String safetyInspectorTown;
    private String safetyInspectorPostCode;
    private String taxiPhvAddressLine1;
    private String taxiPhvAddressLine2;
    private String taxiPhvAddressLine3;
    private String taxiPhvAddressLine4;
    private String taxiPhvTown;
    private String taxiPhvPostCode;

    private int noOfVehiclesRequested;
    private int operatingCentreVehicleCap;
    private int operatingCentreTrailerCap;
    private int restrictedVehicles;

    private double hours;

    public Headers apiHeaders = new Headers();

    private EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    private ValidatableResponse apiResponse;

    public RegisterUser getUser() { return user; }

    public void setUser(RegisterUser user) { this.user = user; }

    public GetUserDetails getUserDetails() { return userDetails; }

    public void setUserDetails(GetUserDetails userDetails) { this.userDetails = userDetails; }

    public String getApplicationId() { return applicationId; }

    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getLicenceId() { return licenceId; }

    public void setLicenceId(String licenceId) { this.licenceId = licenceId; }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = OperatorType.valueOf(operatorType.toUpperCase()).asString(); }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = LicenceType.valueOf(licenceType.toUpperCase()).asString();
    }

    public String getNiFlag() {
        return niFlag;
    }

    public void setNiFlag(String niFlag) {
        this.niFlag = niFlag;
    }

    public String getIsInterim() { return isInterim; }

    public void setIsInterim(String isInterim) {
        this.isInterim = isInterim;
    }

    public String getOrganisationName() { return user.getOrganisationName(); }

    public String getOrganisationEmailAddress() {
        return organisationEmailAddress;
    }

    public void setOrganisationEmailAddress(String organisationEmailAddress) { this.organisationEmailAddress = organisationEmailAddress; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public TrafficArea getTrafficArea() { return trafficArea; }

    public void setTrafficArea(TrafficArea trafficArea) { this.trafficArea = trafficArea; }

    public EnforcementArea getEnforcementArea() { return enforcementArea; }

    public void setEnforcementArea(EnforcementArea enforcementArea) { this.enforcementArea = enforcementArea; }

    public String getPostCodeByTrafficArea() { return postCodeByTrafficArea; }

    public void setPostCodeByTrafficArea(String postCodeByTrafficArea) { this.postCodeByTrafficArea = postCodeByTrafficArea; }

    public String getCountryCode() { return countryCode; }

    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getPsvVehicleSize() {
        return psvVehicleSize;
    }

    public void setPsvVehicleSize(String psvVehicleSize) {
        this.psvVehicleSize = psvVehicleSize;
    }

    public String getPsvNoSmallVhlConfirmation() {
        return psvNoSmallVhlConfirmation;
    }

    public void setPsvNoSmallVhlConfirmation(String psvNoSmallVhlConfirmation) { this.psvNoSmallVhlConfirmation = psvNoSmallVhlConfirmation; }

    public String getPsvOperateSmallVhl() {
        return psvOperateSmallVhl;
    }

    public void setPsvOperateSmallVhl(String psvOperateSmallVhl) {
        this.psvOperateSmallVhl = psvOperateSmallVhl;
    }

    public String getPsvSmallVhlNotes() {
        return psvSmallVhlNotes;
    }

    public void setPsvSmallVhlNotes(String psvSmallVhlNotes) {
        this.psvSmallVhlNotes = psvSmallVhlNotes;
    }

    public String getPsvLimousines() {
        return psvLimousines;
    }

    public void setPsvLimousines(String psvLimousines) {
        this.psvLimousines = psvLimousines;
    }

    public String getPsvNoLimousineConfirmation() {
        return psvNoLimousineConfirmation;
    }

    public void setPsvNoLimousineConfirmation(String psvNoLimousineConfirmation) { this.psvNoLimousineConfirmation = psvNoLimousineConfirmation; }

    public String getPsvOnlyLimousinesConfirmation() {
        return psvOnlyLimousinesConfirmation;
    }

    public void setPsvOnlyLimousinesConfirmation(String psvOnlyLimousinesConfirmation) { this.psvOnlyLimousinesConfirmation = psvOnlyLimousinesConfirmation; }

    public String getDirectorTitle() { return directorTitle; }

    public void setDirectorTitle(String directorTitle) { this.directorTitle = directorTitle; }

    public String getDirectorForeName() { return directorForeName; }

    public void setDirectorForeName(String directorForeName) { this.directorForeName = directorForeName; }

    public String getDirectorFamilyName() { return directorFamilyName; }

    public void setDirectorFamilyName(String directorFamilyName) { this.directorFamilyName = directorFamilyName; }

    public String getDirectorDOB() { return directorDOB; }

    public void setDirectorDOB(String directorDOB) { this.directorDOB = directorDOB; }

    public String getRegisteredAddressLine1() { return registeredAddressLine1; }

    public void setRegisteredAddressLine1(String registeredAddressLine1) { this.registeredAddressLine1 = registeredAddressLine1; }

    public String getRegisteredAddressLine2() { return registeredAddressLine2; }

    public void setRegisteredAddressLine2(String registeredAddressLine2) { this.registeredAddressLine2 = registeredAddressLine2; }

    public String getRegisteredAddressLine3() { return registeredAddressLine3; }

    public void setRegisteredAddressLine3(String registeredAddressLine3) { this.registeredAddressLine3 = registeredAddressLine3; }

    public String getRegisteredAddressLine4() { return registeredAddressLine4; }

    public void setRegisteredAddressLine4(String registeredAddressLine4) { this.registeredAddressLine4 = registeredAddressLine4; }

    public String getRegisteredTown() { return registeredTown; }

    public void setRegisteredTown(String registeredTown) { this.registeredTown = registeredTown; }

    public String getRegisteredPostCode() { return registeredPostCode; }

    public void setRegisteredPostCode(String registeredPostCode) { this.registeredPostCode = registeredPostCode; }

    public String getCorrespondenceAddressLine1() { return correspondenceAddressLine1; }

    public void setCorrespondenceAddressLine1(String correspondenceAddressLine1) { this.correspondenceAddressLine1 = correspondenceAddressLine1; }

    public String getCorrespondenceAddressLine2() { return correspondenceAddressLine2; }

    public void setCorrespondenceAddressLine2(String correspondenceAddressLine2) { this.correspondenceAddressLine2 = correspondenceAddressLine2; }

    public String getCorrespondenceAddressLine3() { return correspondenceAddressLine3; }

    public void setCorrespondenceAddressLine3(String correspondenceAddressLine3) { this.correspondenceAddressLine3 = correspondenceAddressLine3; }

    public String getCorrespondenceAddressLine4() { return correspondenceAddressLine4; }

    public void setCorrespondenceAddressLine4(String correspondenceAddressLine4) { this.correspondenceAddressLine4 = correspondenceAddressLine4; }

    public String getCorrespondenceTown() { return correspondenceTown; }

    public void setCorrespondenceTown(String correspondenceTown) { this.correspondenceTown = correspondenceTown; }

    public String getCorrespondencePostCode() { return correspondencePostCode; }

    public void setCorrespondencePostCode(String correspondencePostCode) { this.correspondencePostCode = correspondencePostCode; }

    public String getEstablishmentAddressLine1() { return establishmentAddressLine1; }

    public void setEstablishmentAddressLine1(String establishmentAddressLine1) { this.establishmentAddressLine1 = establishmentAddressLine1; }

    public String getEstablishmentAddressLine2() { return establishmentAddressLine2; }

    public void setEstablishmentAddressLine2(String establishmentAddressLine2) { this.establishmentAddressLine2 = establishmentAddressLine2; }

    public String getEstablishmentAddressLine3() { return establishmentAddressLine3; }

    public void setEstablishmentAddressLine3(String establishmentAddressLine3) { this.establishmentAddressLine3 = establishmentAddressLine3; }

    public String getEstablishmentAddressLine4() { return establishmentAddressLine4; }

    public void setEstablishmentAddressLine4(String establishmentAddressLine4) { this.establishmentAddressLine4 = establishmentAddressLine4; }

    public String getEstablishmentTown() { return establishmentTown; }

    public void setEstablishmentTown(String establishmentTown) { this.establishmentTown = establishmentTown; }

    public String getEstablishmentPostCode() { return establishmentPostCode; }

    public void setEstablishmentPostCode(String establishmentPostCode) { this.establishmentPostCode = establishmentPostCode; }

    public String getTransportManagerType() { return transportManagerType; }

    public void setTransportManagerTypeType(String transportManagerType) { this.transportManagerType = transportManagerType; }

    public String getTransportManagerApplicationId() { return transportManagerApplicationId; }

    public void setTransportManagerApplicationId(String transportManagerApplicationId) { this.transportManagerApplicationId = transportManagerApplicationId; }

    public String getIsOwner() { return isOwner; }

    public void setIsOwner(String isOwner) { this.isOwner = isOwner; }

    public String getTransportManagerFirstName() { return transportManagerFirstName; }

    public void setTransportManagerFirstName(String transportManagerFirstName) { this.transportManagerFirstName = transportManagerFirstName; }

    public String getTransportManagerLastName() { return transportManagerLastName; }

    public void setTransportManagerLastName(String transportManagerLastName) { this.transportManagerLastName = transportManagerLastName; }

    public String getTransportManagerDOB() { return transportManagerDOB; }

    public void setTransportManagerDOB(String transportManagerDOB) { this.transportManagerDOB = transportManagerDOB; }

    public String getTransportManagerEmailAddress() { return transportManagerEmailAddress; }

    public void setTransportManagerEmailAddress(String transportManagerEmailAddress) { this.transportManagerEmailAddress = transportManagerEmailAddress; }

    public String getTransportManagerUserName() { return transportManagerUserName; }

    public void setTransportManagerUserName(String transportManagerUserName) { this.transportManagerUserName = transportManagerUserName; }

    public String getTransportManagerAddressLine1() { return transportManagerAddressLine1; }

    public void setTransportManagerAddressLine1(String transportManagerAddressLine1) { this.transportManagerAddressLine1 = transportManagerAddressLine1; }

    public String getTransportManagerAddressLine2() { return transportManagerAddressLine2; }

    public void setTransportManagerAddressLine2(String transportManagerAddressLine2) { this.transportManagerAddressLine2 = transportManagerAddressLine2; }

    public String getTransportManagerAddressLine3() { return transportManagerAddressLine3; }

    public void setTransportManagerAddressLine3(String transportManagerAddressLine3) { this.transportManagerAddressLine3 = transportManagerAddressLine3; }

    public String getTransportManagerAddressLine4() { return transportManagerAddressLine4; }

    public void setTransportManagerAddressLine4(String transportManagerAddressLine4) { this.transportManagerAddressLine4 = transportManagerAddressLine4; }

    public String getTransportManagerTown() { return transportManagerTown; }

    public void setTransportManagerTown(String transportManagerTown) { this.transportManagerTown = transportManagerTown; }

    public String getTransportManagerPostCode() { return transportManagerPostCode; }

    public void setTransportManagerPostCode(String transportManagerPostCode) { this.transportManagerPostCode = transportManagerPostCode; }

    public String getOperatingCentreAddressLine1() { return operatingCentreAddressLine1; }

    public void setOperatingCentreAddressLine1(String operatingCentreAddressLine1) { this.operatingCentreAddressLine1 = operatingCentreAddressLine1; }

    public String getOperatingCentreAddressLine2() { return operatingCentreAddressLine2; }

    public void setOperatingCentreAddressLine2(String operatingCentreAddressLine2) { this.operatingCentreAddressLine2 = operatingCentreAddressLine2; }

    public String getOperatingCentreAddressLine3() { return operatingCentreAddressLine3; }

    public void setOperatingCentreAddressLine3(String operatingCentreAddressLine3) { this.operatingCentreAddressLine3 = operatingCentreAddressLine3; }

    public String getOperatingCentreAddressLine4() { return operatingCentreAddressLine4; }

    public void setOperatingCentreAddressLine4(String operatingCentreAddressLine4) { this.operatingCentreAddressLine4 = operatingCentreAddressLine4; }

    public String getOperatingCentreTown() { return operatingCentreTown; }

    public void setOperatingCentreTown(String operatingCentreTown) { this.operatingCentreTown = operatingCentreTown; }

    public String getOperatingCentrePostCode() { return operatingCentrePostCode; }

    public void setOperatingCentrePostCode(String operatingCentrePostCode) { this.operatingCentrePostCode = operatingCentrePostCode; }

    public String getTransportConsultantName() { return transportConsultantEmailAddress; }

    public void setTransportConsultantName(String transportConsultantName) { this.transportConsultantName = transportConsultantName; }

    public String getTransportConsultantPhone() { return transportConsultantPhone; }

    public void setTransportConsultantPhone(String transportConsultantPhone) { this.transportConsultantPhone = transportConsultantPhone; }

    public String getTransportConsultantEmailAddress() { return transportConsultantEmailAddress; }

    public void setTransportConsultantEmailAddress(String transportConsultantEmailAddress) { this.transportConsultantEmailAddress = transportConsultantEmailAddress; }

    public String getTransportConsultantAddressLine1() { return transportConsultantAddressLine1; }

    public void setTransportConsultantAddressLine1(String transportConsultantAddressLine1) { this.transportConsultantAddressLine1 = transportConsultantAddressLine1; }

    public String getTransportConsultantAddressLine2() { return transportConsultantAddressLine2; }

    public void setTransportConsultantAddressLine2(String transportConsultantAddressLine2) { this.transportConsultantAddressLine2 = transportConsultantAddressLine2; }

    public String getTransportConsultantAddressLine3() { return transportConsultantAddressLine3; }

    public void setTransportConsultantAddressLine3(String transportConsultantAddressLine3) { this.transportConsultantAddressLine3 = transportConsultantAddressLine3; }

    public String getTransportConsultantAddressLine4() { return transportConsultantAddressLine4; }

    public void setTransportConsultantAddressLine4(String transportConsultantAddressLine4) { this.transportConsultantAddressLine4 = transportConsultantAddressLine4; }

    public String getTransportConsultantTown() { return transportConsultantTown; }

    public void setTransportConsultantTown(String transportConsultantTown) { this.transportConsultantTown = transportConsultantTown; }

    public String getTransportConsultantPostCode() { return transportConsultantPostCode; }

    public void setTransportConsultantPostCode(String transportConsultantPostCode) { this.transportConsultantPostCode = transportConsultantPostCode; }

    public String getSafetyInspectorForeName() { return safetyInspectorForeName; }

    public void setSafetyInspectorForeName(String safetyInspectorForeName) { this.safetyInspectorForeName = safetyInspectorForeName; }

    public String getSafetyInspectorFamilyName() { return safetyInspectorFamilyName; }

    public void setSafetyInspectorFamilyName(String safetyInspectorFamilyName) { this.safetyInspectorFamilyName = safetyInspectorFamilyName; }

    public String getSafetyInspectorDOB() { return safetyInspectorDOB; }

    public void setSafetyInspectorDOB(String safetyInspectorDOB) { this.safetyInspectorDOB = safetyInspectorDOB; }

    public String getSafetyInspectorAddressLine1() { return safetyInspectorAddressLine1; }

    public void setSafetyInspectorAddressLine1(String safetyInspectorAddressLine1) { this.safetyInspectorAddressLine1 = safetyInspectorAddressLine1; }

    public String getSafetyInspectorAddressLine2() { return safetyInspectorAddressLine2; }

    public void setSafetyInspectorAddressLine2(String safetyInspectorAddressLine2) { this.safetyInspectorAddressLine2 = safetyInspectorAddressLine2; }

    public String getSafetyInspectorAddressLine3() { return safetyInspectorAddressLine3; }

    public void setSafetyInspectorAddressLine3(String safetyInspectorAddressLine3) { this.safetyInspectorAddressLine3 = safetyInspectorAddressLine3; }

    public String getSafetyInspectorAddressLine4() { return safetyInspectorAddressLine4; }

    public void setSafetyInspectorAddressLine4(String safetyInspectorAddressLine4) { this.safetyInspectorAddressLine4 = safetyInspectorAddressLine4; }

    public String getSafetyInspectorTown() { return safetyInspectorTown; }

    public void setSafetyInspectorTown(String safetyInspectorTown) { this.safetyInspectorTown = safetyInspectorTown; }

    public String getSafetyInspectorPostCode() { return safetyInspectorPostCode; }

    public void setSafetyInspectorPostCode(String safetyInspectorPostCode) { this.safetyInspectorPostCode = safetyInspectorPostCode; }

    public String getTaxiPhvAddressLine1() { return taxiPhvAddressLine1; }

    public void setTaxiPhvAddressLine1(String taxiPhvAddressLine1) { this.taxiPhvAddressLine1 = taxiPhvAddressLine1; }

    public String getTaxiPhvAddressLine2() { return taxiPhvAddressLine2; }

    public void setTaxiPhvAddressLine2(String taxiPhvAddressLine2) { this.taxiPhvAddressLine2 = taxiPhvAddressLine2; }

    public String getTaxiPhvAddressLine3() { return taxiPhvAddressLine3; }

    public void setTaxiPhvAddressLine3(String taxiPhvAddressLine3) { this.taxiPhvAddressLine3 = taxiPhvAddressLine3; }

    public String getTaxiPhvAddressLine4() { return taxiPhvAddressLine4; }

    public void setTaxiPhvAddressLine4(String taxiPhvAddressLine4) { this.taxiPhvAddressLine4 = taxiPhvAddressLine4; }

    public String getTaxiPhvPostCode() { return taxiPhvPostCode; }

    public void setTaxiPhvPostCode(String taxiPhvPostCode) { this.taxiPhvPostCode = taxiPhvPostCode; }

    public String getTaxiPhvTown() { return taxiPhvTown; }

    public void setTaxiPhvTown(String taxiPhvTown) { this.taxiPhvTown = taxiPhvTown; }

    public int getNoOfVehiclesRequested() { return noOfVehiclesRequested; }

    public void setNoOfVehiclesRequested(int noOfVehiclesRequested) { this.noOfVehiclesRequested = noOfVehiclesRequested; }

    public int getOperatingCentreVehicleCap() { return operatingCentreVehicleCap; }

    public void setOperatingCentreVehicleCap(int operatingCentreVehicleCap) { this.operatingCentreVehicleCap = operatingCentreVehicleCap; }

    public int getOperatingCentreTrailerCap() { return operatingCentreTrailerCap; }

    public void setOperatingCentreTrailerCap(int operatingCentreTrailerCap) { this.operatingCentreTrailerCap = operatingCentreTrailerCap; }

    public int getRestrictedVehicles() { return restrictedVehicles; }

    public void setRestrictedVehicles(int restrictedVehicles) { this.restrictedVehicles = restrictedVehicles; }

    public double getHours() { return hours; }

    public void setHours(double hours) { this.hours = hours; }

    public CreateApplication(RegisterUser registerUser, GetUserDetails getUserDetails) {

        // Classes
        setUser(registerUser);
        setUserDetails(getUserDetails);

        // Application details
        setOperatorType( "goods" );
        setLicenceType( "standard_national" );
        setNiFlag( "N" );
        setIsInterim( "N" );
        setNoOfVehiclesRequested( 5 );
        setTrafficArea( TrafficArea.NORTH_EAST );
        setPostCodeByTrafficArea( TrafficArea.getPostCode(getTrafficArea()));
        setCountryCode( "GB" );

        // PSV details
        setPsvVehicleSize( "psvvs_both" );
        setPsvNoSmallVhlConfirmation( "Y" );
        setPsvOperateSmallVhl( "Y" );
        setPsvSmallVhlNotes( "submitted through the API" );
        setPsvLimousines( "Y" );
        setPsvNoLimousineConfirmation( "Y" );
        setPsvOnlyLimousinesConfirmation( "Y" );

        // Taxi Details
        LinkedHashMap<String, String> generatedTaxiPhvAddress = faker.generateAddress();
        setTaxiPhvAddressLine1( generatedTaxiPhvAddress.get("addressLine1") );
        setTaxiPhvAddressLine2( generatedTaxiPhvAddress.get("addressLine2") );
        setTaxiPhvAddressLine3( generatedTaxiPhvAddress.get("addressLine3") );
        setTaxiPhvAddressLine4( generatedTaxiPhvAddress.get("addressLine4") );
        setTaxiPhvTown( generatedTaxiPhvAddress.get("town") );
        setTaxiPhvPostCode( getPostCodeByTrafficArea() );

        // Business details

        // // Business general details
        setCompanyNumber( String.valueOf(Int.random(00000000, 99999999)) );
        setNatureOfBusiness( faker.generateNatureOfBusiness() );
        setOrganisationEmailAddress( getOrganisationName().replace(" ", "_").replace(",", "").concat(".volBusiness@dvsa.com") );
        setPhoneNumber( "0712345678" );

        // // Registered Business Address details
        LinkedHashMap<String, String> generatedRegisteredAddress = faker.generateAddress();
        setRegisteredAddressLine1( generatedRegisteredAddress.get("addressLine1") );
        setRegisteredAddressLine2( generatedRegisteredAddress.get("addressLine2") );
        setRegisteredAddressLine3( generatedRegisteredAddress.get("addressLine3") );
        setRegisteredAddressLine4( generatedRegisteredAddress.get("addressLine4") );
        setRegisteredTown( generatedRegisteredAddress.get("town") );
        setRegisteredPostCode( getPostCodeByTrafficArea() );

        // // Correspondence Address details
        LinkedHashMap<String, String> generatedCorrespondenceAddress = faker.generateAddress();
        setCorrespondenceAddressLine1( generatedCorrespondenceAddress.get("addressLine1") );
        setCorrespondenceAddressLine2( generatedCorrespondenceAddress.get("addressLine2") );
        setCorrespondenceAddressLine3( generatedCorrespondenceAddress.get("addressLine3") );
        setCorrespondenceAddressLine4( generatedCorrespondenceAddress.get("addressLine4") );
        setCorrespondenceTown( generatedCorrespondenceAddress.get("town") );
        setCorrespondencePostCode( getPostCodeByTrafficArea() );

        // // Establishment Address details
        LinkedHashMap<String, String> generatedEstablishmentAddress = faker.generateAddress();
        setEstablishmentAddressLine1( generatedEstablishmentAddress.get("addressLine1") );
        setEstablishmentAddressLine2( generatedEstablishmentAddress.get("addressLine2") );
        setEstablishmentAddressLine3( generatedEstablishmentAddress.get("addressLine3") );
        setEstablishmentAddressLine4( generatedEstablishmentAddress.get("addressLine4") );
        setEstablishmentTown( generatedEstablishmentAddress.get("town") );
        setEstablishmentPostCode( getPostCodeByTrafficArea() );

        // Director details
        setDirectorTitle( UserTitle.MR.asString() );
        setDirectorForeName( faker.generateFirstName().concat(String.valueOf(Int.random(100, 999))) );
        setDirectorFamilyName( faker.generateLastName().concat(String.valueOf(Int.random(100, 999))) );
        setDirectorDOB( Int.random(1900, 2018) + "-" + Int.random(1, 12) + "-" + Int.random(1, 28) );

        // Operating Centre details
        LinkedHashMap<String, String> generatedOperatingCentreAddress = faker.generateAddress();
        setOperatingCentreAddressLine1( generatedOperatingCentreAddress.get("addressLine1") );
        setOperatingCentreAddressLine2( generatedOperatingCentreAddress.get("addressLine2") );
        setOperatingCentreAddressLine3( generatedOperatingCentreAddress.get("addressLine3") );
        setOperatingCentreAddressLine4( generatedOperatingCentreAddress.get("addressLine4") );
        setOperatingCentreTown( generatedOperatingCentreAddress.get("town") );
        setOperatingCentrePostCode( getPostCodeByTrafficArea() );
        setOperatingCentreVehicleCap( 5 );
        setOperatingCentreTrailerCap( 5 );
        setRestrictedVehicles( 2 );
        setEnforcementArea( EnforcementArea.NORTH_EAST );

        // Transport Manager details
        LinkedHashMap<String, String> generatedTransportManagerAddress = faker.generateAddress();
        setTransportManagerAddressLine1( generatedTransportManagerAddress.get("AddressLine1") );
        setTransportManagerAddressLine2( generatedTransportManagerAddress.get("AddressLine2") );
        setTransportManagerAddressLine3( generatedTransportManagerAddress.get("AddressLine3") );
        setTransportManagerAddressLine4( generatedTransportManagerAddress.get("AddressLine4") );
        setTransportManagerTown( generatedTransportManagerAddress.get("town") );
        setTransportManagerPostCode( getPostCodeByTrafficArea() );

        // Safety Inspector Address details
        LinkedHashMap<String, String> generatedSafetyInspectorAddress = faker.generateAddress();
        setSafetyInspectorForeName( faker.generateFirstName().concat(String.valueOf(Int.random(100, 999))) );
        setSafetyInspectorFamilyName( faker.generateFirstName().concat(String.valueOf(Int.random(100, 999))) );
        setSafetyInspectorAddressLine1( generatedSafetyInspectorAddress.get("addressLine1") );
        setSafetyInspectorAddressLine2( generatedSafetyInspectorAddress.get("addressLine2") );
        setSafetyInspectorAddressLine3( generatedSafetyInspectorAddress.get("addressLine3") );
        setSafetyInspectorAddressLine4( generatedSafetyInspectorAddress.get("addressLine4") );
        setSafetyInspectorTown( generatedSafetyInspectorAddress.get("addressLine4") );
        setSafetyInspectorPostCode( getPostCodeByTrafficArea() );

        // Transport Consultant details
        String generatedTransportConsultantName = String.format("%s %s", faker.generateFirstName(), faker.generateLastName());
        setTransportConsultantName( generatedTransportConsultantName );
        setTransportConsultantEmailAddress( generatedTransportConsultantName.replace(" ", "_").replace(",", "")
                .concat(".volTransportConsultant@dvsa.com") );

        // Transport Consultant Address details
        LinkedHashMap<String, String> transportConsultantAddress = faker.generateAddress();
        setTransportConsultantAddressLine1( transportConsultantAddress.get("addressLine1") );
        setTransportConsultantAddressLine2( transportConsultantAddress.get("addressLine2") );
        setTransportConsultantAddressLine3( transportConsultantAddress.get("addressLine3") );
        setTransportConsultantAddressLine4( transportConsultantAddress.get("addressLine4") );
        setTransportConsultantTown( faker.generateAddress().get("town") );
        setTransportConsultantPostCode( getPostCodeByTrafficArea() );
    }


    public ValidatableResponse startApplication() {
        String createApplicationResource = URL.build(env, "application").toString();
        apiHeaders.headers.put("x-pid", getUserDetails().getPid());

        ApplicationBuilder applicationBuilder = new ApplicationBuilder().withOperatorType(getOperatorType())
                .withLicenceType(getLicenceType()).withNiFlag(getNiFlag()).withOrganisation(getUserDetails().getOrganisationId());
        apiResponse = RestUtils.post(applicationBuilder, createApplicationResource, apiHeaders.getHeaders());
        setApplicationId(apiResponse.extract().jsonPath().getString("id.application"));
        setLicenceId(apiResponse.extract().jsonPath().getString("id.licence"));

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    public ValidatableResponse addBusinessType() {
        String organisationVersion = fetchApplicationInformation(getApplicationId(), "licence.organisation.version", "1");
        String updateBusinessTypeResource = URL.build(env, String.format("organisation/%s/business-type/", getUserDetails().getOrganisationId())).toString();

        BusinessTypeBuilder businessTypeBuilder = new BusinessTypeBuilder().withBusinessType(getUser().getBusinessType()).withVersion(organisationVersion)
                .withId(getUserDetails().getOrganisationId()).withApplication(getApplicationId());
        apiResponse = RestUtils.put(businessTypeBuilder, updateBusinessTypeResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addBusinessDetails() {
        String organisationVersion = fetchApplicationInformation(getApplicationId(), "licence.organisation.version", "1");
        String updateBusinessDetailsResource = URL.build(env, String.format("organisation/business-details/application/%s", getApplicationId())).toString();

        AddressBuilder address = new AddressBuilder().withAddressLine1(registeredAddressLine1).withAddressLine2(registeredAddressLine2).withAddressLine3(registeredAddressLine3)
                .withAddressLine4(registeredAddressLine4).withTown(registeredTown).withPostcode(getPostCodeByTrafficArea());
        UpdateBusinessDetailsBuilder businessDetails = new UpdateBusinessDetailsBuilder()
                .withId(getApplicationId()).withCompanyNumber(getCompanyNumber()).withNatureOfBusiness(getNatureOfBusiness()).withLicence(getLicenceId())
                .withVersion(organisationVersion).withName(getOrganisationName()).withAddress(address);

        apiResponse = RestUtils.put(businessDetails, updateBusinessDetailsResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addAddressDetails() {
        String applicationAddressResource = URL.build(env, String.format("application/%s/addresses/", getApplicationId())).toString();

        ContactDetailsBuilder contactDetailsBuilder = new ContactDetailsBuilder().withPhoneNumber(getPhoneNumber()).withEmailAddress(getOrganisationEmailAddress());

        AddressBuilder correspondenceAddress = new AddressBuilder().withAddressLine1(getCorrespondenceAddressLine1()).withAddressLine2(getCorrespondenceAddressLine2()).withAddressLine3(getCorrespondenceAddressLine3())
                .withAddressLine4(getCorrespondenceAddressLine4()).withTown(getCorrespondenceTown()).withPostcode(getPostCodeByTrafficArea()).withCountryCode(getCountryCode());

        AddressBuilder establishmentAddress = new AddressBuilder().withAddressLine1(establishmentAddressLine1).withAddressLine2(establishmentAddressLine2).withAddressLine3(establishmentAddressLine3)
                .withAddressLine4(establishmentAddressLine4).withTown(establishmentTown).withPostcode(getPostCodeByTrafficArea()).withCountryCode(getCountryCode());

        AddressBuilder transportConsultantAddress = new AddressBuilder().withAddressLine1(transportConsultantAddressLine1).withAddressLine2(transportConsultantAddressLine2).withAddressLine3(transportConsultantAddressLine3)
                .withAddressLine4(transportConsultantAddressLine4).withTown(transportConsultantTown).withPostcode(transportConsultantPostCode).withCountryCode(getCountryCode());

        ContactDetailsBuilder transportConsultantContact = new ContactDetailsBuilder().withPhoneNumber(transportConsultantPhone).withEmailAddress(transportConsultantEmailAddress);

        TransportConsultantBuilder transportConsultant = new TransportConsultantBuilder().withConfirmation("Y").withAddress(transportConsultantAddress).withName(transportConsultantName)
                .withWrittenPermissionToEngage("Y").withContact(transportConsultantContact);

        ApplicationAddressBuilder addressBuilder = new ApplicationAddressBuilder().withId(applicationId).withContact(contactDetailsBuilder)
                .withCorrespondenceAddress(correspondenceAddress).withEstablishmentAddress(establishmentAddress).withConsultant(transportConsultant);

        apiResponse = RestUtils.put(addressBuilder, applicationAddressResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addDirectors() {
        String addPersonResource = URL.build(env, String.format("application/%s/people/", getApplicationId())).toString();
        PersonBuilder addPerson = new PersonBuilder().withId(getApplicationId()).withTitle(getDirectorTitle())
                .withForename(getDirectorForeName()).withFamilyName(getDirectorFamilyName()).withBirthDate(getDirectorDOB());
        apiResponse = RestUtils.post(addPerson, addPersonResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    public ValidatableResponse addOperatingCentre() {
        if (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        }
        String operatingCentreResource = URL.build(env, String.format("application/%s/operating-centre/", getApplicationId())).toString();
        String permissionOption = "Y";

        AddressBuilder address = new AddressBuilder().withAddressLine1(getOperatingCentreAddressLine1()).withAddressLine2(getOperatingCentreAddressLine2()).
                withAddressLine3(getOperatingCentreAddressLine3()).withAddressLine4(getOperatingCentreAddressLine4()).withTown(getOperatingCentreTown())
                .withPostcode(postCodeByTrafficArea).withCountryCode(getCountryCode());

        OperatingCentreBuilder operatingCentreBuilder = new OperatingCentreBuilder().withApplication(getApplicationId()).withPermission(permissionOption).withAddress(address);

        if (operatorType.equals(OperatorType.GOODS.asString())) {
            operatingCentreBuilder.withNoOfVehiclesRequired(String.valueOf(getOperatingCentreVehicleCap()))
                    .withNoOfTrailersRequired(String.valueOf(getOperatingCentreTrailerCap()));
        }
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (!licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            operatingCentreBuilder.withNoOfVehiclesRequired(String.valueOf(getOperatingCentreVehicleCap()));
        }
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            operatingCentreBuilder.withNoOfVehiclesRequired(String.valueOf(getRestrictedVehicles()));
        }
        apiResponse = RestUtils.post(operatingCentreBuilder, operatingCentreResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);


        return apiResponse;
    }

    public ValidatableResponse updateOperatingCentre() {
        if (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        }
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));
        String updateOperatingCentreResource = URL.build(env, String.format("application/%s/operating-centres", getApplicationId())).toString();
        OperatingCentreUpdater updateOperatingCentre = new OperatingCentreUpdater().withId(getApplicationId())
                .withTrafficArea(getTrafficArea().asString()).withEnforcementArea(getEnforcementArea().asString()).withVersion(applicationVersion);

        if (operatorType.equals(OperatorType.GOODS.asString())) {
            updateOperatingCentre.withTotAuthVehicles(getOperatingCentreVehicleCap())
                    .withTotCommunityLicences(1)
                    .withTAuthTrailers(getOperatingCentreVehicleCap());
        }
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (!licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            updateOperatingCentre.withTotAuthVehicles(getOperatingCentreVehicleCap())
                    .withTotCommunityLicences(1);
        }

        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            updateOperatingCentre.withTotAuthVehicles(getRestrictedVehicles());
        }

        apiResponse = RestUtils.put(updateOperatingCentre, updateOperatingCentreResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addFinancialEvidence() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            return null;
        }

        String financialEvidenceResource = URL.build(env, String.format("application/%s/financial-evidence", getApplicationId())).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));

        FinancialEvidenceBuilder financialEvidenceBuilder = new FinancialEvidenceBuilder().withId(getApplicationId())
                .withVersion(applicationVersion).withFinancialEvidenceUploaded(0);
        apiResponse = RestUtils.put(financialEvidenceBuilder, financialEvidenceResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addTransportManager() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        setTransportManagerFirstName( faker.generateFirstName().concat(String.valueOf(Int.random(100, 999))) );
        setTransportManagerLastName( faker.generateLastName().concat(String.valueOf(Int.random(100, 999))) );
        setTransportManagerDOB( Int.random(1900, 2018) + "-" + Int.random(1, 12) + "-" + Int.random(1, 28) );
        setTransportManagerUserName(String.format("apiTM%s%s%s", getTransportManagerFirstName(), getTransportManagerLastName(), Int.random(0, 2000)) );
        setTransportManagerEmailAddress( String.format("%s %s", getTransportManagerFirstName(), getTransportManagerLastName())
                .replace(" ", "_").replace(",", "").concat(".volTransportManager@dvsa.com") );
        setHours( 2.0 );
        setIsOwner( getIsOwner() == null ? "Y" : getIsOwner() );
        String hasEmail = "Y";
        String addTransportManager = URL.build(env, "transport-manager/create-new-user/").toString();
        TransportManagerBuilder transportManagerBuilder = new TransportManagerBuilder().withApplication(getApplicationId()).withFirstName(getTransportManagerFirstName())
                .withFamilyName(getTransportManagerLastName()).withHasEmail(hasEmail).withUsername(getTransportManagerUserName()).withEmailAddress(getTransportManagerEmailAddress())
                .withBirthDate(getTransportManagerDOB());
        apiResponse = RestUtils.post(transportManagerBuilder, addTransportManager, apiHeaders.getHeaders());
        setTransportManagerApplicationId(apiResponse.extract().jsonPath().getString("id.transportManagerApplicationId"));

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    public ValidatableResponse submitTransport() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        int TMApplicationVersion = Integer.parseInt(fetchTMApplicationInformation(getTransportManagerApplicationId(), "version", "1"));
        String submitTransportManager = URL.build(env, String.format("transport-manager-application/%s/submit", getTransportManagerApplicationId())).toString();
        GenericBuilder genericBuilder = new GenericBuilder().withId(transportManagerApplicationId).withVersion(TMApplicationVersion);
        apiResponse = RestUtils.put(genericBuilder, submitTransportManager, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addTmResponsibilities() {
        if (getOperatorType().equals(OperatorType.GOODS.asString()) && (getLicenceType().equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String addTMresp = URL.build(env, String.format("transport-manager-application/%s/update-details/", getTransportManagerApplicationId())).toString();
        int TMApplicationVersion = Integer.parseInt(fetchTMApplicationInformation(getTransportManagerApplicationId(), "version", "1"));
        AddressBuilder Address = new AddressBuilder().withAddressLine1(getTransportManagerAddressLine1()).withAddressLine2(getTransportManagerAddressLine2()).withAddressLine3(getTransportManagerAddressLine3())
                .withAddressLine4(getTransportManagerAddressLine4()).withTown(getTransportManagerTown()).withPostcode(postCodeByTrafficArea).withCountryCode(getCountryCode());
        TmRespBuilder tmRespBuilder = new TmRespBuilder().withEmail(getTransportManagerEmailAddress()).withPlaceOfBirth(getTransportConsultantTown()).withHomeAddress(Address).withWorkAddress(Address).withTmType(getTransportManagerType()).withIsOwner(isOwner)
                .withHoursMon(hours).withHoursTue(hours).withHoursWed(hours).withHoursThu(hours).withHoursThu(hours).withHoursFri(hours).withHoursSat(hours).withHoursSun(hours).withDob(getTransportManagerDOB())
                .withId(getTransportManagerApplicationId()).withVersion(TMApplicationVersion);

        apiResponse = RestUtils.put(tmRespBuilder, addTMresp, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse submitTmResponsibilities() {
        if (getOperatorType().equals(OperatorType.PUBLIC.asString()) && (getLicenceType().equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String submitTmResp = URL.build(env, String.format("transport-manager-application/%s/submit", getTransportManagerApplicationId())).toString();
        int TMApplicationVersion = Integer.parseInt(fetchTMApplicationInformation(getTransportManagerApplicationId(), "version", "1"));

        GenericBuilder genericBuilder = new GenericBuilder().withId(transportManagerApplicationId).withVersion(TMApplicationVersion);
        apiResponse = RestUtils.put(genericBuilder, submitTmResp, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addVehicleDetails() {
        if (getOperatorType().equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        }
        if (getNoOfVehiclesRequested() > getOperatingCentreVehicleCap()) {
            throw new ValueException("Cannot have more than the specified amount of vehicles on an operating centre.");
        }
        String vehiclesResource = null;
        String vrm;

        String operatorType = getOperatorType().equals(OperatorType.GOODS.asString()) ? "goods" : "psv";
        vehiclesResource = URL.build(env, String.format("application/%s/%s-vehicles", getApplicationId(), operatorType)).toString();

        // TODO: Might need to add 'make' to the API builder because DVLA pulls this as well
        do {
            for (int i = 0; i < noOfVehiclesRequested; i++) {
                vrm = Str.randomWord(2).concat(String.valueOf(Int.random(10, 99)).concat(Str.randomWord(3)))
                        .toLowerCase();
                VehiclesBuilder vehiclesDetails = new VehiclesBuilder().withId(getApplicationId()).withApplication(getApplicationId()).withHasEnteredReg("Y").withVrm(vrm)
                        .withPlatedWeight(String.valueOf(Int.random(1, 9999))).withVersion(version);
                assert vehiclesResource != null;
                apiResponse = RestUtils.post(vehiclesDetails, vehiclesResource, apiHeaders.getHeaders());
            }
        }
        while ((apiResponse.extract().statusCode() == HttpStatus.SC_CONFLICT) || (apiResponse.extract().statusCode() == HttpStatus.SC_BAD_REQUEST)
                || (apiResponse.extract().statusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY));

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    public ValidatableResponse submitVehicleDeclaration() {
        if (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        }

        String vehicleDeclarationResource = URL.build(env, String.format(String.format("application/%s/vehicle-declaration", applicationId))).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));

        VehicleDeclarationBuilder vehicleDeclarationBuilder = new VehicleDeclarationBuilder().withId(getApplicationId()
        ).withPsvVehicleSize(psvVehicleSize)
                .withPsvLimousines(psvLimousines).withPsvNoSmallVhlConfirmation(psvNoSmallVhlConfirmation).withPsvOperateSmallVhl(psvOperateSmallVhl).withPsvSmallVhlNotes(psvSmallVhlNotes)
                .withPsvNoLimousineConfirmation(psvNoLimousineConfirmation).withPsvOnlyLimousinesConfirmation(psvOnlyLimousinesConfirmation).withVersion(applicationVersion);
        apiResponse = RestUtils.put(vehicleDeclarationBuilder, vehicleDeclarationResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addFinancialHistory() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String financialHistoryAnswer = "N";
        String insolvencyAnswer = "false";
        String financialHistoryResource = URL.build(env, String.format("application/%s/financial-history", getApplicationId())).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));

        FinancialHistoryBuilder financialHistoryBuilder = new FinancialHistoryBuilder().withId(getApplicationId()).withVersion(String.valueOf(applicationVersion)).withBankrupt(financialHistoryAnswer)
                .withLiquidation(financialHistoryAnswer).withReceivership(financialHistoryAnswer).withAdministration(financialHistoryAnswer).withAdministration(financialHistoryAnswer)
                .withDisqualified(financialHistoryAnswer).withInsolvencyDetails(insolvencyAnswer).withInsolvencyConfirmation(insolvencyAnswer);
        apiResponse = RestUtils.put(financialHistoryBuilder, financialHistoryResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addApplicationSafetyAndComplianceDetails() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String tachographIns = "tach_na";
        String safetyInsVaries = "N";
        String safetyConfirmationOption = "Y";
        String applicationSafetyResource = URL.build(env, String.format("application/%s/safety", getApplicationId())).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));

        LicenceBuilder licence = new LicenceBuilder().withId(getLicenceId()).withVersion(version).withSafetyInsVaries(safetyInsVaries).withSafetyInsVehicles(String.valueOf(operatingCentreVehicleCap))
                .withSafetyInsTrailers(String.valueOf(operatingCentreVehicleCap)).withTachographIns(tachographIns);
        ApplicationSafetyBuilder applicationSafetyBuilder = new ApplicationSafetyBuilder().withId(getApplicationId()).withVersion(applicationVersion)
                .withSafetyConfirmation(safetyConfirmationOption).withLicence(licence);
        apiResponse = RestUtils.put(applicationSafetyBuilder, applicationSafetyResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addSafetyInspector() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String safetyInspectorResource = URL.build(env, String.format("application/%s/workshop", getApplicationId())).toString();
        AddressBuilder addressBuilder = new AddressBuilder().withAddressLine1(safetyInspectorAddressLine1).withAddressLine2(safetyInspectorAddressLine2).withAddressLine3(safetyInspectorAddressLine3)
                .withAddressLine4(safetyInspectorAddressLine4).withTown(safetyInspectorTown).withPostcode(safetyInspectorPostCode).withCountryCode(getCountryCode());
        ContactDetailsBuilder contactDetailsBuilder = new ContactDetailsBuilder().withFullName(String.format("%s %s", safetyInspectorForeName, safetyInspectorFamilyName)).withAddress(addressBuilder);
        SafetyInspectorBuilder safetyInspectorBuilder = new SafetyInspectorBuilder().withApplication(applicationId).withLicence(getLicenceId()).withIsExternal("N")
                .withContactDetails(contactDetailsBuilder);
        apiResponse = RestUtils.post(safetyInspectorBuilder, safetyInspectorResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    public ValidatableResponse addConvictionsDetails() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String previousConvictionsResource = URL.build(env, String.format("application/%s/previous-convictions", applicationId)).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(applicationId, "version", "1"));

        CaseConvictionsPenaltiesBuilder convictionsPenaltiesBuilder = new CaseConvictionsPenaltiesBuilder().withId(applicationId).withConvictionsConfirmation("Y")
                .withPrevConviction("N").withVersion(applicationVersion);
        apiResponse = RestUtils.put(convictionsPenaltiesBuilder, previousConvictionsResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse addLicenceHistory() {
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            return null;
        }
        String optionResponse = "N";
        String licenceHistoryResource = URL.build(env, String.format("application/%s/licence-history", applicationId)).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(applicationId, "version", "1"));

        LicenceHistoryBuilder licenceHistoryBuilder = new LicenceHistoryBuilder().withId(applicationId).withPrevHadLicence(optionResponse).withPrevHasLicence(optionResponse)
                .withPrevBeenAtPi(optionResponse).withPrevBeenDisqualifiedTc(optionResponse).withPrevBeenRefused(optionResponse).withPrevBeenRevoked(optionResponse).withPrevPurchasedAssets(optionResponse)
                .withVersion(applicationVersion);
        apiResponse = RestUtils.put(licenceHistoryBuilder, licenceHistoryResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse submitTaxiPhv() {
        String phLicenceNumber = "phv".concat(String.valueOf(Int.random(100000,999999)));
        String councilName = "Volhampton";
        if (operatorType.equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString()))) {
            String submitResource = URL.build(env, String.format("application/%s/taxi-phv", getApplicationId())).toString();
            AddressBuilder addressBuilder = new AddressBuilder().withAddressLine1(getTaxiPhvAddressLine1()).withAddressLine2(getTaxiPhvAddressLine2())
                    .withAddressLine3(getTaxiPhvAddressLine3()).withAddressLine4(getTaxiPhvAddressLine4()).withTown(getTaxiPhvTown()).withPostcode(postCodeByTrafficArea).withCountryCode(getCountryCode());
            PhvTaxiBuilder taxiBuilder = new PhvTaxiBuilder().withId(applicationId).withPrivateHireLicenceNo(phLicenceNumber).withCouncilName(councilName).withLicence(getLicenceId()).withAddress(addressBuilder);
            apiResponse = RestUtils.post(taxiBuilder, submitResource, apiHeaders.getHeaders());
            Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        }
        return apiResponse;
    }

    public ValidatableResponse applicationReviewAndDeclare() {

        int applicationVersion = Integer.parseInt(fetchApplicationInformation(getApplicationId(), "version", "1"));
        String reviewResource = URL.build(env, String.format("application/%s/declaration/", getApplicationId())).toString();
        DeclarationsAndUndertakings undertakings = new DeclarationsAndUndertakings().withId(getApplicationId()).withVersion(String.valueOf(applicationVersion))
                .withSignatureType("sig_physical_signature").withDeclarationConfirmation("Y");

        if (getOperatorType().equals(OperatorType.GOODS.asString()) && (getIsInterim().equals("Y"))) {
            undertakings.withInterimRequested("Y").withInterimReason("Testing through the API");
        }

        apiResponse = RestUtils.put(undertakings, reviewResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse submitApplication() {
        String submitResource = URL.build(env, String.format("application/%s/submit", applicationId)).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(applicationId, "version", "1"));

        GenericBuilder genericBuilder = new GenericBuilder().withId(applicationId).withVersion(applicationVersion);
        apiResponse = RestUtils.put(genericBuilder, submitResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }
}