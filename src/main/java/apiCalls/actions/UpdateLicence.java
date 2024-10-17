package apiCalls.actions;

import activesupport.MissingRequiredArgument;
import activesupport.dates.Dates;
import activesupport.dates.LocalDateCalendar;
import activesupport.faker.FakerUtils;
import activesupport.http.RestUtils;
import activesupport.number.Int;
import activesupport.system.Properties;
import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.Utils.volBuilders.*;
import apiCalls.enums.UserRoles;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.exceptions.MalformedURLException;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.joda.time.LocalDate;


import java.util.*;


public class UpdateLicence extends BaseAPI {
    private final CreateApplication application;
    private ValidatableResponse apiResponse;
    private final Dates date = new Dates(new LocalDateCalendar());
    private final FakerUtils faker = new FakerUtils();
    private final Headers apiHeaders = new Headers();
    private String adminAPIHeader;
    private String variationType;
    private String variationApplicationId;
    private String caseType;
    private String caseDescription;
    private String defendantType;
    private String defendantFirstname;
    private String defendantLastname;
    private String defendantBirthDate;
    private String offenceDate;
    private String convictionDate;
    private String convictionCategory;
    private String categoryText;
    private String court;
    private String penalty;
    private String costs;
    private String convictionNotes;
    private String complainantForename;
    private String complainantFamilyName;
    private String complaintType;
    private String complainantStatus;
    private String isCompliance;
    private String complaintDate;
    private String infringementDate;
    private String complainantDescription;
    private String driverForename;
    private String driverFamilyName;
    private String conditionUndertakingType;
    private String conditionUndertakingCategory;
    private String conditionsUndertakingDescription;
    private String fulfilled;
    private String attachedTo;
    private String submissionType;
    private String caseNoteComment;
    private String caseNotePriority;
    private String internalUserId;
    private String internalUserTeam;
    private String internalUserForeName;
    private String internalUserFamilyName;
    private String internalUserLogin;
    private String internalUserEmailAddress;
    private String internalUserDOB;
    private String internalUserAddressLine1;
    private String internalUserAddressLine2;
    private String internalUserAddressLine3;
    private String internalUserAddressLine4;
    private String internalUserTown;
    private String internalUserPostCode;
    private String countryCode;
    private String discsStolen;
    private String discSequence;
    private String startNumber;
    private String endNumber;
    private String queueId;
    private String interimReason;
    private String interimStartDate;
    private String interimEndDate;
    private String goodOrPsv;
    private String trafficAreaName;
    private String businessType;
    private String licenceType;
    private String licenceStatus;
    private final List<String> caseCategories = new ArrayList<>();
    private final List<String> caseOutcomes = new ArrayList<>();
    private int caseId;
    private int caseNoteId;
    private int complaintId;
    private int convictionId;
    private int conditionUndertaking;
    private int submissionsId;
    private final int version = 1;
    private static EnvironmentType env;

    public String getVariationType() {
        return variationType;
    }

    public void setVariationType(String variationType) {
        this.variationType = variationType;
    }

    public String getVariationApplicationId() {
        return variationApplicationId;
    }

    public void setVariationApplicationId(String variationApplicationId) {
        this.variationApplicationId = variationApplicationId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getDefendantType() {
        return defendantType;
    }

    public void setDefendantType(String defendantType) {
        this.defendantType = defendantType;
    }

    public String getDefendantFirstname() {
        return defendantFirstname;
    }

    public void setDefendantFirstname(String defendantFirstname) {
        this.defendantFirstname = defendantFirstname;
    }

    public String getDefendantLastname() {
        return defendantLastname;
    }

    public void setDefendantLastname(String defendantLastname) {
        this.defendantLastname = defendantLastname;
    }

    public String getDefendantBirthDate() {
        return defendantBirthDate;
    }

    public void setDefendantBirthDate(String defendantBirthDate) {
        this.defendantBirthDate = defendantBirthDate;
    }

    public String getOffenceDate() {
        return offenceDate;
    }

    public void setOffenceDate(String offenceDate) {
        this.offenceDate = offenceDate;
    }

    public String getConvictionDate() {
        return convictionDate;
    }

    public void setConvictionDate(String convictionDate) {
        this.convictionDate = convictionDate;
    }

    public String getConvictionCategory() {
        return convictionCategory;
    }

    public void setConvictionCategory(String convictionCategory) {
        this.convictionCategory = convictionCategory;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getConvictionNotes() {
        return convictionNotes;
    }

    public void setConvictionNotes(String convictionNotes) {
        this.convictionNotes = convictionNotes;
    }

    public String getComplainantForename() {
        return complainantForename;
    }

    public void setComplainantForename(String complainantForename) {
        this.complainantForename = complainantForename;
    }

    public String getComplainantFamilyName() {
        return complainantFamilyName;
    }

    public void setComplainantFamilyName(String complainantFamilyName) {
        this.complainantFamilyName = complainantFamilyName;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplainantStatus() {
        return complainantStatus;
    }

    public void setComplainantStatus(String complainantStatus) {
        this.complainantStatus = complainantStatus;
    }

    public String getIsCompliance() {
        return isCompliance;
    }

    public void setIsCompliance(String isCompliance) {
        this.isCompliance = isCompliance;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getInfringementDate() {
        return infringementDate;
    }

    public void setInfringementDate(String infringementDate) {
        this.infringementDate = infringementDate;
    }

    public String getComplainantDescription() {
        return complainantDescription;
    }

    public void setComplainantDescription(String complainantDescription) {
        this.complainantDescription = complainantDescription;
    }

    public String getDriverForename() {
        return driverForename;
    }

    public void setDriverForename(String driverForename) {
        this.driverForename = driverForename;
    }

    public String getDriverFamilyName() {
        return driverFamilyName;
    }

    public void setDriverFamilyName(String driverFamilyName) {
        this.driverFamilyName = driverFamilyName;
    }

    public String getConditionUndertakingType() {
        return conditionUndertakingType;
    }

    public void setConditionUndertakingType(String conditionUndertakingType) {
        this.conditionUndertakingType = conditionUndertakingType;
    }

    public String getConditionUndertakingCategory() {
        return conditionUndertakingCategory;
    }

    public void setConditionUndertakingCategory(String conditionUndertakingCategory) {
        this.conditionUndertakingCategory = conditionUndertakingCategory;
    }

    public String getConditionsUndertakingDescription() {
        return conditionsUndertakingDescription;
    }

    public void setConditionsUndertakingDescription(String conditionsUndertakingDescription) {
        this.conditionsUndertakingDescription = conditionsUndertakingDescription;
    }

    public String getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(String fulfilled) {
        this.fulfilled = fulfilled;
    }

    public String getAttachedTo() {
        return attachedTo;
    }

    public void setAttachedTo(String attachedTo) {
        this.attachedTo = attachedTo;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public String getCaseNoteComment() {
        return caseNoteComment;
    }

    public void setCaseNoteComment(String caseNoteComment) {
        this.caseNoteComment = caseNoteComment;
    }

    public String getCaseNotePriority() {
        return caseNotePriority;
    }

    public void setCaseNotePriority(String caseNotePriority) {
        this.caseNotePriority = caseNotePriority;
    }

    public String getInternalUserId() {
        return internalUserId;
    }

    public void setInternalUserId(String internalUserId) {
        this.internalUserId = internalUserId;
    }

    public String getInternalUserTeam() {
        return internalUserTeam;
    }

    public void setInternalUserTeam(String internalUserTeam) {
        this.internalUserTeam = internalUserTeam;
    }

    public String getInternalUserForeName() {
        return internalUserForeName;
    }

    public void setInternalUserForeName(String internalUserForeName) {
        this.internalUserForeName = internalUserForeName;
    }

    public String getInternalUserFamilyName() {
        return internalUserFamilyName;
    }

    public void setInternalUserFamilyName(String internalUserFamilyName) {
        this.internalUserFamilyName = internalUserFamilyName;
    }

    public String getInternalUserLogin() {
        return internalUserLogin;
    }

    public void setInternalUserLogin(String internalUserLogin) {
        this.internalUserLogin = internalUserLogin;
    }

    public String getInternalUserEmailAddress() {
        return internalUserEmailAddress;
    }

    public void setInternalUserEmailAddress(String internalUserEmailAddress) {
        this.internalUserEmailAddress = internalUserEmailAddress;
    }

    public String getInternalUserDOB() {
        return internalUserDOB;
    }

    public void setInternalUserDOB(String internalUserDOB) {
        this.internalUserDOB = internalUserDOB;
    }

    public String getInternalUserAddressLine1() {
        return internalUserAddressLine1;
    }

    public void setInternalUserAddressLine1(String internalUserAddressLine1) {
        this.internalUserAddressLine1 = internalUserAddressLine1;
    }

    public String getInternalUserAddressLine2() {
        return internalUserAddressLine2;
    }

    public void setInternalUserAddressLine2(String internalUserAddressLine2) {
        this.internalUserAddressLine2 = internalUserAddressLine2;
    }

    public String getInternalUserAddressLine3() {
        return internalUserAddressLine3;
    }

    public void setInternalUserAddressLine3(String internalUserAddressLine3) {
        this.internalUserAddressLine3 = internalUserAddressLine3;
    }

    public String getInternalUserAddressLine4() {
        return internalUserAddressLine4;
    }

    public void setInternalUserAddressLine4(String internalUserAddressLine4) {
        this.internalUserAddressLine4 = internalUserAddressLine4;
    }

    public String getInternalUserTown() {
        return internalUserTown;
    }

    public void setInternalUserTown(String internalUserTown) {
        this.internalUserTown = internalUserTown;
    }

    public String getInternalUserPostCode() {
        return internalUserPostCode;
    }

    public void setInternalUserPostCode(String internalUserPostCode) {
        this.internalUserPostCode = internalUserPostCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDiscsStolen() {
        return discsStolen;
    }

    public void setDiscsStolen(String discsStolen) {
        this.discsStolen = discsStolen;
    }

    public String getDiscSequence() {
        return discSequence;
    }

    public void setDiscSequence(String discSequence) {
        this.discSequence = discSequence;
    }

    public String getStartNumber() {
        return startNumber;
    }

    private void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(String endNumber) {
        this.endNumber = endNumber;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getInterimReason() {
        return interimReason;
    }

    public void setInterimReason(String interimReason) {
        this.interimReason = interimReason;
    }

    public String getInterimStartDate() {
        return interimStartDate;
    }

    public void setInterimStartDate(String interimStartDate) {
        this.interimStartDate = interimStartDate;
    }

    public String getInterimEndDate() {
        return interimEndDate;
    }

    public void setInterimEndDate(String interimEndDate) {
        this.interimEndDate = interimEndDate;
    }

    public String getGoodOrPsv() {
        return goodOrPsv;
    }

    private void setGoodOrPsv(String goodOrPsv) {
        this.goodOrPsv = goodOrPsv;
    }

    public String getTrafficAreaName() {
        return trafficAreaName;
    }

    private void setTrafficAreaName(String trafficAreaName) {
        this.trafficAreaName = trafficAreaName;
    }

    private void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public String getLicenceStatus() {
        return licenceStatus;
    }

    private void setLicenceStatus(String licenceStatus) {
        this.licenceStatus = licenceStatus;
    }

    public int getCaseId() {
        return caseId;
    }

    private void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getCaseNoteId() {
        return caseNoteId;
    }

    private void setCaseNoteId(int caseNoteId) {
        this.caseNoteId = caseNoteId;
    }

    public int getComplaintId() {
        return complaintId;
    }

    private void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getConvictionId() {
        return convictionId;
    }

    private void setConvictionId(int convictionId) {
        this.convictionId = convictionId;
    }

    public int getConditionUndertaking() {
        return conditionUndertaking;
    }

    private void setConditionUndertaking(int conditionUndertaking) {
        this.conditionUndertaking = conditionUndertaking;
    }

    public int getSubmissionsId() {
        return submissionsId;
    }

    private void setSubmissionsId(int submissionsId) {
        this.submissionsId = submissionsId;
    }

    static {
        try {
            env = EnvironmentType.getEnum(Properties.get("env", false));
        } catch (MissingRequiredArgument missingRequiredArgument) {
            missingRequiredArgument.printStackTrace();
        }
    }

    public UpdateLicence(CreateApplication application) {
        this.application = application;
        setVariationType(null);
        setCaseType("case_t_lic");
        setCaseDescription("Sent through the API");
        caseCategories.add("case_cat_compl_conv");
        caseCategories.add("case_cat_compl_proh");
        caseOutcomes.add("case_o_other");
        caseOutcomes.add("case_o_cur");
        setDefendantType("def_t_dir");
        setDefendantFirstname(faker.generateFirstName());
        setDefendantLastname(faker.generateLastName());
        setDefendantBirthDate("99-6-10");
        setOffenceDate("18-4-1");
        setConvictionDate("18-6-10");
        setConvictionCategory("conv_c_cat_1065");
        setCategoryText("Driver correcting entry in driver's record book in wrong fashion");
        setCourt("CourtAPI");
        setPenalty("Heavy");
        setCosts("1000");
        setConvictionNotes("This has been submitted");
        setComplainantForename(faker.generateFirstName());
        setComplainantFamilyName(faker.generateLastName());
        setComplaintType("ct_cov");
        setComplainantStatus("cs_yst");
        setIsCompliance("true");
        Dates dates = new Dates(LocalDate::new);
        setComplaintDate(dates.getFormattedDate(0, 0, -4, "yyyy-MM-dd"));
        setInfringementDate(dates.getFormattedDate(0, 0, -5, "yyyy-MM-dd"));
        setComplainantDescription("Driver correcting entry in driver's record book in wrong fashion");
        setDriverForename(faker.generateFirstName());
        setDriverFamilyName(faker.generateLastName());
        setConditionUndertakingType("cdt_con");
        setConditionUndertakingCategory("cu_cat_fin");
        setConditionsUndertakingDescription("This undertaken has not been fulfilled");
        setFulfilled("N");
        setAttachedTo("cat_lic");
        setSubmissionType("submission_type_o_env");
        setCaseNoteComment("case note submitted through the API");
        setCaseNotePriority("Y");
        setInternalUserTeam("1");
        setInternalUserForeName(faker.generateFirstName());
        setInternalUserFamilyName(faker.generateLastName());
        setInternalUserLogin(String.format("%s%s%s", getInternalUserForeName(), getInternalUserFamilyName(), Int.random(10000, 99999)));
        setInternalUserEmailAddress(getInternalUserLogin().concat("AsTheAdminUser@dvsavol.org"));
        setInternalUserDOB(date.getFormattedDate(0, 0, -30, "yyyy-MM-dd"));
        var internalUserAddress = faker.generateAddress();
        setInternalUserAddressLine1(internalUserAddress.get("addressLine1"));
        setInternalUserAddressLine2(internalUserAddress.get("addressLine2"));
        setInternalUserAddressLine3(internalUserAddress.get("addressLine3"));
        setInternalUserAddressLine4(internalUserAddress.get("addressLine4"));
        setInternalUserTown(internalUserAddress.get("town"));
        setInternalUserPostCode("LS28 5LY");
        setCountryCode("GB");
        setDiscsStolen("2");
        setDiscSequence("6");
        setInterimReason("Interim granted through the API");
        setInterimStartDate(date.getFormattedDate(0, 0, 0, "yyyy-MM-dd"));
        setInterimEndDate(date.getFormattedDate(0, 5, 0, "yyyy-MM-dd"));
    }

    public synchronized HashMap<String, String> header() throws HttpException {
        apiHeaders.getApiHeader().put("Authorization", "Bearer " + adminJWT());
        return new HashMap<>(apiHeaders.getApiHeader());
    }

    public synchronized void createVariation() throws HttpException {
        var licenceHistoryResource = URL.build(env, "licence/%s/variation".formatted(application.getLicenceId())).toString();
        var variation = new VariationBuilder()
                .withId(application.getLicenceId())
                .withFeeRequired("N")
                .withAppliedVia("applied_via_phone")
                .withVariationType(getVariationType());
        apiResponse = RestUtils.post(variation, licenceHistoryResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setVariationApplicationId(String.valueOf(apiResponse.extract().jsonPath().getInt("id.application")));
    }

    public synchronized void updateLicenceType() throws HttpException {
        var typeOfLicenceResource = URL.build(env, "variation/%s/type-of-licence".formatted(application.getLicenceId())).toString();
        var variationApplicationVersion = Integer.parseInt(fetchApplicationInformation(getVariationApplicationId(), "version", "1"));
        var genericBuilder = new GenericBuilder()
                .withId(getVariationApplicationId())
                .withVersion(variationApplicationVersion)
                .withLicenceType(application.getLicenceType());
        apiResponse = RestUtils.put(genericBuilder, typeOfLicenceResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
    }

    public synchronized void createCase() throws MalformedURLException, HttpException {
        var caseResource = URL.build(env, "cases").toString();
        var caseBuilder = new CaseBuilder()
                .withId(application.getLicenceId())
                .withCaseType(getCaseType())
                .withCategorys(caseCategories)
                .withDescription(getCaseDescription())
                .withOutcomes(caseOutcomes)
                .withApplication(application.getApplicationId());
        apiResponse = RestUtils.post(caseBuilder, caseResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setCaseId(apiResponse.extract().body().jsonPath().get("id.case"));
    }

    public synchronized void addConviction() throws MalformedURLException, HttpException {
        var convictionResource = URL.build(env, "conviction").toString();
        var caseConvictionBuilder = new CaseConvictionBuilder()
                .withCase(getCaseId())
                .withConvictionCategory(getConvictionCategory())
                .withConvictionDate(getConvictionDate())
                .withBirthDate(getDefendantBirthDate())
                .withCategoryText(getCategoryText())
                .withCosts(getCosts())
                .withCourt(getCourt())
                .withMsi("Y")
                .withPenalty(getPenalty())
                .withNotes(getConvictionNotes())
                .withTakenIntoConsideration("Y")
                .withIsDeclared("Y")
                .withIsDealtWith("Y")
                .withDefendantType(getDefendantType())
                .withPersonFirstname(getDefendantFirstname())
                .withPersonLastname(getDefendantLastname())
                .withOffenceDate(getOffenceDate());
        apiResponse = RestUtils.post(caseConvictionBuilder, convictionResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setConvictionId(apiResponse.extract().jsonPath().getInt("id.conviction"));
    }

    public synchronized void addComplaint() throws HttpException {
        var complaintResource = URL.build(env, "complaint").toString();
        var complaintBuilder = new CaseComplaintBuilder()
                .withCase(getCaseId())
                .withComplainantForename(getComplainantForename())
                .withComplainantFamilyName(getComplainantFamilyName())
                .withComplaintType(getComplaintType())
                .withStatus(getComplainantStatus())
                .withIsCompliance(getIsCompliance())
                .withComplaintDate(getComplaintDate())
                .withInfringementDate(getInfringementDate())
                .withDescription(getComplainantDescription())
                .withDriverForename(getDriverForename())
                .withDriverFamilyName(getDriverFamilyName());
        apiResponse = RestUtils.post(complaintBuilder, complaintResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setComplaintId(apiResponse.extract().jsonPath().getInt("id.complaint"));
    }

    public synchronized void addConditionsUndertakings() throws MalformedURLException, HttpException {
        var conditionsUndertakingResource = URL.build(env, "condition-undertaking").toString();
        var conditionsBuilder = new CaseConditionsBuilder()
                .withLicence(application.getLicenceId())
                .withApplication(application.getApplicationId())
                .withCase(Integer.toString(getCaseId()))
                .withType(getConditionUndertakingType())
                .withConditionCategory(getConditionUndertakingCategory())
                .withFulfilled(getFulfilled())
                .withAttachedTo(getAttachedTo())
                .withNotes(getConditionsUndertakingDescription());
        apiResponse = RestUtils.post(conditionsBuilder, conditionsUndertakingResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setConditionUndertaking(apiResponse.extract().jsonPath().getInt("id.conditionUndertaking"));
    }

    public synchronized void createSubmission() throws MalformedURLException, HttpException {
        var submissionResource = URL.build(env, "submission").toString();
        var submissionBuilder = new CaseSubmissionBuilder()
                .withCase(Integer.toString(getCaseId()))
                .withSubmissionType(getSubmissionType());
        apiResponse = RestUtils.post(submissionBuilder, submissionResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setSubmissionsId(apiResponse.extract().jsonPath().getInt("id.submission"));
    }

    public synchronized void createCaseNote() throws MalformedURLException, HttpException {
        var caseNoteResource = URL.build(env, "processing/note").toString();
        var caseNotesBuilder = new CaseNotesBuilder()
                .withCase(Integer.toString(getCaseId()))
                .withLicence(application.getLicenceId())
                .withApplication(application.getApplicationId())
                .withComment(getCaseNoteComment())
                .withPriority(getCaseNotePriority());
        apiResponse = RestUtils.post(caseNotesBuilder, caseNoteResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setCaseNoteId(apiResponse.extract().jsonPath().getInt("id.note"));
    }

    public synchronized ValidatableResponse getCaseDetails(String resource, int id) throws HttpException {
        var caseDetailsResource = URL.build(env, "%s/%s".formatted(resource, id)).toString();
        apiResponse = RestUtils.get(caseDetailsResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public synchronized ValidatableResponse variationUpdateOperatingCentre() throws HttpException {
        if (application.getLicenceType().equals("special_restricted")) {
            throw new IllegalArgumentException("Cannot update operating centre for special_restricted licence");
        }
        var updateOperatingCentreResource = URL.build(env, "application/%s/variation-operating-centre/%s".formatted(application.getLicenceId(), getVariationApplicationId())).toString();
        var updateOperatingCentre = new OperatingCentreVariationBuilder()
                .withId(getVariationApplicationId())
                .withApplication(getVariationApplicationId())
                .withNoOfVehiclesRequired(String.valueOf(application.getNoOfAddedHgvVehicles()))
                .withVersion(version);
        apiResponse = RestUtils.put(updateOperatingCentre, updateOperatingCentreResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public synchronized String createInternalUser(String userRole, String userType) throws HttpException {
        var roles = new ArrayList<String>();
        roles.add(userRole);
        var internalAdminUserResource = URL.build(env, "user/internal").toString();

        var addressBuilder = new AddressBuilder()
                .withAddressLine1(getInternalUserAddressLine1())
                .withAddressLine2(getInternalUserAddressLine2())
                .withAddressLine3(getInternalUserAddressLine3())
                .withAddressLine4(getInternalUserAddressLine4())
                .withTown(getInternalUserTown())
                .withPostcode(getInternalUserPostCode())
                .withCountryCode(getCountryCode());
        var personBuilder = new PersonBuilder()
                .withForename(getInternalUserForeName())
                .withFamilyName(getInternalUserFamilyName())
                .withBirthDate(getInternalUserDOB());

        var contactDetails = new ContactDetailsBuilder()
                .withEmailAddress(getInternalUserEmailAddress())
                .withAddress(addressBuilder)
                .withPerson(personBuilder);
        var internalAdminUser = new CreateInternalAdminUser()
                .withContactDetails(contactDetails)
                .withLoginId(getInternalUserLogin())
                .withRoles(roles)
                .withTeam(getInternalUserTeam())
                .withUserType(userType)
                .withOSType("windows_10");
        apiResponse = RestUtils.post(internalAdminUser, internalAdminUserResource, header());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setInternalUserId(apiResponse.extract().response().jsonPath().getString("id.user"));
        return getInternalUserId();
    }

    public synchronized ValidatableResponse updateInternalUserDetails(String userId, String osType) throws HttpException {
        var version = fetchInternalUserInformation(userId, "version", "1");
        var internalAdminUserResource = URL.build(env, "user/internal/%s".formatted(userId)).toString();

        var addressBuilder = new AddressBuilder()
                .withAddressLine1(getInternalUserAddressLine1())
                .withAddressLine2(getInternalUserAddressLine2())
                .withAddressLine3(getInternalUserAddressLine3())
                .withAddressLine4(getInternalUserAddressLine4())
                .withTown(getInternalUserTown())
                .withPostcode(getInternalUserPostCode())
                .withCountryCode(getCountryCode());
        var personBuilder = new PersonBuilder()
                .withForename(getInternalUserForeName())
                .withFamilyName(getInternalUserFamilyName())
                .withBirthDate(getInternalUserDOB());

        var contactDetails = new ContactDetailsBuilder()
                .withEmailAddress(getInternalUserEmailAddress())
                .withAddress(addressBuilder)
                .withPerson(personBuilder);
        var internalAdminUser = new CreateInternalAdminUser()
                .withContactDetails(contactDetails)
                .withLoginId(getInternalUserLogin())
                .withTeam(getInternalUserTeam())
                .withUserType(UserRoles.INTERNAL.asString())
                .withVersion(version)
                .withOSType(osType)
                .withId(userId);
        apiResponse = RestUtils.put(internalAdminUser, internalAdminUserResource, header());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public synchronized ValidatableResponse grantVariation(String resource) throws MalformedURLException, HttpException {
        var grantVariation = URL.build(env, "variation/%s/%s".formatted(getVariationApplicationId(), resource)).toString();
        var genericBuilder = new GenericBuilder().withId(getVariationApplicationId());
        apiResponse = RestUtils.put(genericBuilder, grantVariation, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public synchronized String getLicenceTrafficArea() throws HttpException {
        var getApplicationResource = URL.build(env, "licence/%s".formatted(application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setTrafficAreaName(apiResponse.extract().jsonPath().getString("trafficArea.name"));
        return getTrafficAreaName();
    }

    public synchronized String getLicenceStatusDetails() throws HttpException {
        var getApplicationResource = URL.build(env, "licence/%s".formatted(application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setLicenceStatus(apiResponse.extract().jsonPath().getString("status.description"));
        return getLicenceStatus();
    }

    public synchronized String getOperatorTypeDetails() throws HttpException {
        var getApplicationResource = URL.build(env, "licence/%s".formatted(application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setGoodOrPsv(apiResponse.extract().jsonPath().getString("goodsOrPsv.description"));
        return getGoodOrPsv();
    }

    public synchronized String getBusinessTypeDetails() throws HttpException {
        var getApplicationResource = URL.build(env, "licence/%s".formatted(application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setBusinessType(apiResponse.extract().jsonPath().getString("organisation.type.description"));
        return getBusinessType();
    }

    public synchronized String getLicenceTypeDetails() throws HttpException {
        var getApplicationResource = URL.build(env, "licence/%s".formatted(application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setLicenceType(apiResponse.extract().jsonPath().getString("licenceType.description"));
        return getLicenceType();
    }

    public synchronized void updateLicenceStatus(String status) throws HttpException {
        var typeOfLicenceResource = URL.build(env, "licence/%s/decisions/%s".formatted(application.getLicenceId(), status)).toString();
        var genericBuilder = new GenericBuilder().withId(application.getLicenceId());
        apiResponse = RestUtils.post(genericBuilder, typeOfLicenceResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
    }

}