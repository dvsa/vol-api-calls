package apiCalls.actions;


import activesupport.MissingRequiredArgument;
import activesupport.dates.Dates;
import activesupport.dates.LocalDateCalendar;
import activesupport.faker.FakerUtils;
import activesupport.http.RestUtils;
import activesupport.number.Int;
import apiCalls.Utils.volBuilders.*;
import apiCalls.Utils.generic.*;
import apiCalls.enums.LicenceType;
import apiCalls.enums.OperatorType;
import apiCalls.enums.UserRoles;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.exceptions.MalformedURLException;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import activesupport.system.Properties;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;

import static org.junit.Assert.assertThat;

import java.util.*;


public class UpdateLicence extends BaseAPI {
    private final CreateApplication application;

    private ValidatableResponse apiResponse;

    private Dates date = new Dates(new LocalDateCalendar());

    private FakerUtils faker = new FakerUtils();

    private Headers apiHeaders = new Headers();

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

    public String getAdminAPIHeader() { return adminAPIHeader; }

    public void setAdminAPIHeader(String adminAPIHeader) { this.adminAPIHeader = adminAPIHeader; }

    public String getVariationType() { return variationType; }

    public void setVariationType(String variationType) {
        this.variationType = variationType;
    }

    public String getVariationApplicationId() { return variationApplicationId; }

    public void setVariationApplicationId(String variationApplicationId) { this.variationApplicationId = variationApplicationId; }

    public String getCaseType() { return caseType; }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) { this.caseDescription = caseDescription; }

    public String getDefendantType() { return defendantType; }

    public void setDefendantType(String defendantType) { this.defendantType = defendantType; }

    public String getDefendantFirstname() { return defendantFirstname; }

    public void setDefendantFirstname(String defendantFirstname) { this.defendantFirstname = defendantFirstname; }

    public String getDefendantLastname() { return defendantLastname; }

    public void setDefendantLastname(String defendantLastname) { this.defendantLastname = defendantLastname; }

    public String getDefendantBirthDate() { return defendantBirthDate; }

    public void setDefendantBirthDate(String defendantBirthDate) { this.defendantBirthDate = defendantBirthDate; }

    public String getOffenceDate() { return offenceDate; }

    public void setOffenceDate(String offenceDate) { this.offenceDate = offenceDate; }

    public String getConvictionDate() { return convictionDate; }

    public void setConvictionDate(String convictionDate) { this.convictionDate = convictionDate; }

    public String getConvictionCategory() { return convictionCategory; }

    public void setConvictionCategory(String convictionCategory) { this.convictionCategory = convictionCategory; }

    public String getCategoryText() { return categoryText; }

    public void setCategoryText(String categoryText) { this.categoryText = categoryText; }

    public String getCourt() { return court; }

    public void setCourt(String court) { this.court = court; }

    public String getPenalty() { return penalty; }

    public void setPenalty(String penalty) { this.penalty = penalty; }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getConvictionNotes() { return convictionNotes; }

    public void setConvictionNotes(String convictionNotes) { this.convictionNotes = convictionNotes; }

    public String getComplainantForename() { return complainantForename; }

    public void setComplainantForename(String complainantForename) { this.complainantForename = complainantForename; }

    public String getComplainantFamilyName() { return complainantFamilyName; }

    public void setComplainantFamilyName(String complainantFamilyName) { this.complainantFamilyName = complainantFamilyName; }

    public String getComplaintType() { return complaintType; }

    public void setComplaintType(String complaintType) { this.complaintType = complaintType; }

    public String getComplainantStatus() { return complainantStatus; }

    public void setComplainantStatus(String complainantStatus) { this.complainantStatus = complainantStatus; }

    public String getIsCompliance() { return isCompliance; }

    public void setIsCompliance(String isCompliance) { this.isCompliance = isCompliance; }

    public String getComplaintDate() { return complaintDate; }

    public void setComplaintDate(String complaintDate) { this.complaintDate = complaintDate; }

    public String getInfringementDate() { return infringementDate; }

    public void setInfringementDate(String infringementDate) { this.infringementDate = infringementDate; }

    public String getComplainantDescription() { return complainantDescription; }

    public void setComplainantDescription(String complainantDescription) { this.complainantDescription = complainantDescription; }

    public String getDriverForename() { return driverForename; }

    public void setDriverForename(String driverForename) { this.driverForename = driverForename; }

    public String getDriverFamilyName() { return driverFamilyName; }

    public void setDriverFamilyName(String driverFamilyName) { this.driverFamilyName = driverFamilyName; }

    public String getConditionUndertakingType() { return conditionUndertakingType; }

    public void setConditionUndertakingType(String conditionUndertakingType) { this.conditionUndertakingType = conditionUndertakingType; }

    public String getConditionUndertakingCategory() { return conditionUndertakingCategory; }

    public void setConditionUndertakingCategory(String conditionUndertakingCategory) { this.conditionUndertakingCategory = conditionUndertakingCategory; }

    public String getConditionsUndertakingDescription() { return conditionsUndertakingDescription; }

    public void setConditionsUndertakingDescription(String conditionsUndertakingDescription) { this.conditionsUndertakingDescription = conditionsUndertakingDescription; }

    public String getFulfilled() { return fulfilled; }

    public void setFulfilled(String fulfilled) { this.fulfilled = fulfilled; }

    public String getAttachedTo() { return attachedTo; }

    public void setAttachedTo(String attachedTo) { this.attachedTo = attachedTo; }

    public String getSubmissionType() { return submissionType; }

    public void setSubmissionType(String submissionType) { this.submissionType = submissionType; }

    public String getCaseNoteComment() { return caseNoteComment; }

    public void setCaseNoteComment(String caseNoteComment) { this.caseNoteComment = caseNoteComment; }

    public String getCaseNotePriority() { return caseNotePriority; }

    public void setCaseNotePriority(String caseNotePriority) { this.caseNotePriority = caseNotePriority; }

    public String getInternalUserId() { return internalUserId; }

    public void setInternalUserId(String internalUserId) { this.internalUserId = internalUserId; }

    public String getInternalUserTeam() { return internalUserTeam; }

    public void setInternalUserTeam(String internalUserTeam) { this.internalUserTeam = internalUserTeam; }

    public String getInternalUserForeName(){ return internalUserForeName; }

    public void setInternalUserForeName(String internalUserForeName){ this.internalUserForeName = internalUserForeName; }

    public String getInternalUserFamilyName() { return internalUserFamilyName; }

    public void setInternalUserFamilyName(String internalUserFamilyName) { this.internalUserFamilyName = internalUserFamilyName; }

    public String getInternalUserLogin() { return internalUserLogin; }

    public void setInternalUserLogin(String internalUserLogin) { this.internalUserLogin = internalUserLogin; }

    public String getInternalUserEmailAddress() { return internalUserEmailAddress; }

    public void setInternalUserEmailAddress(String internalUserEmailAddress) { this.internalUserEmailAddress = internalUserEmailAddress; }

    public String getInternalUserDOB() { return internalUserDOB; }

    public void setInternalUserDOB(String internalUserDOB) { this.internalUserDOB = internalUserDOB; }

    public String getInternalUserAddressLine1() { return internalUserAddressLine1; }

    public void setInternalUserAddressLine1(String internalUserAddressLine1) { this.internalUserAddressLine1 = internalUserAddressLine1; }

    public String getInternalUserAddressLine2() { return internalUserAddressLine2; }

    public void setInternalUserAddressLine2(String internalUserAddressLine2) { this.internalUserAddressLine2 = internalUserAddressLine2; }

    public String getInternalUserAddressLine3() { return internalUserAddressLine3; }

    public void setInternalUserAddressLine3(String internalUserAddressLine3) { this.internalUserAddressLine3 = internalUserAddressLine3; }

    public String getInternalUserAddressLine4() { return internalUserAddressLine4; }

    public void setInternalUserAddressLine4(String internalUserAddressLine4) { this.internalUserAddressLine4 = internalUserAddressLine4; }

    public String getInternalUserTown() { return internalUserTown; }

    public void setInternalUserTown(String internalUserTown) { this.internalUserTown = internalUserTown; }

    public String getInternalUserPostCode() { return internalUserPostCode; }

    public void setInternalUserPostCode(String internalUserPostCode) { this.internalUserPostCode = internalUserPostCode; }

    public String getCountryCode() { return countryCode; }

    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getDiscsStolen() { return discsStolen; }

    public void setDiscsStolen(String discsStolen) { this.discsStolen = discsStolen; }

    public String getDiscSequence() { return discSequence; }

    public void setDiscSequence(String discSequence) { this.discSequence = discSequence; }

    public String getStartNumber() { return startNumber; }

    private void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(String endNumber) {
        this.endNumber = endNumber;
    }

    public String getQueueId() { return queueId; }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getInterimReason() { return interimReason; }

    public void setInterimReason(String interimReason) { this.interimReason = interimReason; }

    public String getInterimStartDate() { return interimStartDate; }

    public void setInterimStartDate(String interimStartDate) { this.interimStartDate = interimStartDate; }

    public String getInterimEndDate() { return interimEndDate; }

    public void setInterimEndDate(String interimEndDate) { this.interimEndDate = interimEndDate; }

    public String getGoodOrPsv() { return goodOrPsv; }

    private void setGoodOrPsv(String goodOrPsv) {
        this.goodOrPsv = goodOrPsv;
    }

    public String getTrafficAreaName() { return trafficAreaName; }

    private void setTrafficAreaName(String trafficAreaName) {
        this.trafficAreaName = trafficAreaName;
    }

    private void setBusinessType(String businessType) { this.businessType = businessType; }

    public String getBusinessType() {
        return businessType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicenceType() { return licenceType; }

    public String getLicenceStatus() { return licenceStatus; }

    private void setLicenceStatus(String licenceStatus) { this.licenceStatus = licenceStatus; }

    public int getCaseId() { return caseId; }

    private void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getCaseNoteId() {
        return caseNoteId;
    }

    private void setCaseNoteId(int caseNoteId) {
        this.caseNoteId = caseNoteId;
    }

    public int getComplaintId() { return complaintId; }

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

    public int getSubmissionsId() { return submissionsId; }

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
        apiHeaders.headers.put("x-pid", Utils.config.getString("apiHeader"));
        setVariationType(null);
        setAdminAPIHeader(Utils.config.getString("apiHeader"));

        // Case Details
        setCaseType("case_t_lic");
        setCaseDescription("Sent through the API");
        caseCategories.add("case_cat_compl_conv");
        caseCategories.add("case_cat_compl_proh");
        caseOutcomes.add("case_o_other");
        caseOutcomes.add("case_o_cur");

        // Conviction Details
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

        // Complainant Details
        setComplainantForename(faker.generateFirstName());
        setComplainantFamilyName(faker.generateLastName());
        setComplaintType("ct_cov");
        setComplainantStatus("cs_yst");
        setIsCompliance("true");
        Dates dates = new Dates(LocalDate::new);
        setComplaintDate(dates.getFormattedDate(0,0,-4,"yyyy-MM-dd"));
        setInfringementDate(dates.getFormattedDate(0,0,-5,"yyyy-MM-dd"));
        setComplainantDescription("Driver correcting entry in driver's record book in wrong fashion");
        setDriverForename(faker.generateFirstName());
        setDriverFamilyName(faker.generateLastName());

        // Conditions and Undertaking Details
        setConditionUndertakingType("cdt_con");
        setConditionUndertakingCategory("cu_cat_fin");
        setConditionsUndertakingDescription("This undertaken has not been fulfilled");
        setFulfilled("N");
        setAttachedTo("cat_lic");

        // Submission Details
        setSubmissionType("submission_type_o_env");

        // Case Note Details
        setCaseNoteComment("case note submitted through the API");
        setCaseNotePriority("Y");

        // Internal User Details
        setInternalUserTeam("1");
        setInternalUserForeName(faker.generateFirstName());
        setInternalUserFamilyName(faker.generateLastName());
        setInternalUserLogin( String.format("%s%s%s", getInternalUserForeName(), getInternalUserFamilyName(), Int.random(10000, 99999)) );
        setInternalUserEmailAddress( getInternalUserLogin().concat("AsTheAdminUser@dvsavol.org") );
        setInternalUserDOB(date.getFormattedDate(0, 0, -30, "yyyy-MM-dd"));
        LinkedHashMap<String, String> internalUserAddress = faker.generateAddress();
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

    public void createVariation() {
        String licenceHistoryResource = URL.build(env, String.format("licence/%s/variation", application.getLicenceId())).toString();

        VariationBuilder variation = new VariationBuilder().withId(application.getLicenceId()).withFeeRequired("N").withAppliedVia("applied_via_phone").withVariationType(getVariationType());
        apiResponse = RestUtils.post(variation, licenceHistoryResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setVariationApplicationId(String.valueOf(apiResponse.extract().jsonPath().getInt("id.application")));
    }

    public void createVariationViaSelfServe(String licenceId) {
        String licenceHistoryResource = URL.build(env, String.format("licence/%s/variation", licenceId)).toString();

        VariationBuilder variation = new VariationBuilder().withId(application.getLicenceId()).withFeeRequired("N").withAppliedVia("applied_via_phone").withVariationType(getVariationType());
        apiResponse = RestUtils.post(variation, licenceHistoryResource, application.apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setVariationApplicationId(String.valueOf(apiResponse.extract().jsonPath().getInt("id.application")));
    }

    public void updateLicenceType() {
        String typeOfLicenceResource = URL.build(env, String.format("variation/%s/type-of-licence", application.getLicenceId())).toString();
        Integer variationApplicationVersion = Integer.parseInt(fetchApplicationInformation(getVariationApplicationId(), "version", "1"));

        GenericBuilder genericBuilder = new GenericBuilder().withId(getVariationApplicationId()).withVersion(variationApplicationVersion).withLicenceType(application.getLicenceType());
        apiResponse = RestUtils.put(genericBuilder, typeOfLicenceResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
    }

    public void createCase() throws MalformedURLException {
        String caseResource = URL.build(env, "cases").toString();

        CaseBuilder caseBuilder = new CaseBuilder().withId(application.getLicenceId()).withCaseType(getCaseType()).withCategorys(caseCategories)
                .withDescription(getCaseDescription()).withOutcomes(caseOutcomes).withApplication(application.getApplicationId());
        apiResponse = RestUtils.post(caseBuilder, caseResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setCaseId(apiResponse.extract().body().jsonPath().get("id.case"));
    }

    public void addConviction() throws MalformedURLException {

        String convictionResource = URL.build(env, "conviction").toString();

        CaseConvictionBuilder caseConvictionBuilder = new CaseConvictionBuilder().withCase(getCaseId()).withConvictionCategory(getConvictionCategory())
                .withConvictionDate(getConvictionDate()).withBirthDate(getDefendantBirthDate()).withCategoryText(getCategoryText()).withCosts(getCosts())
                .withCourt(getCourt()).withMsi("Y").withPenalty(getPenalty()).withNotes(getConvictionNotes()).withTakenIntoConsideration("Y")
                .withIsDeclared("Y").withIsDealtWith("Y").withDefendantType(getDefendantType()).withPersonFirstname(getDefendantFirstname())
                .withPersonLastname(getDefendantLastname()).withOffenceDate(getOffenceDate());
        apiResponse = RestUtils.post(caseConvictionBuilder, convictionResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setConvictionId(apiResponse.extract().jsonPath().getInt("id.conviction"));
    }

    public void addComplaint() {

        String complaintResource = URL.build(env, "complaint").toString();
        CaseComplaintBuilder complaintBuilder = new CaseComplaintBuilder().withCase(getCaseId()).withComplainantForename(getComplainantForename())
                .withComplainantFamilyName(getComplainantFamilyName()).withComplaintType(getComplaintType()).withStatus(getComplainantStatus())
                .withIsCompliance(getIsCompliance()).withComplaintDate(getComplaintDate()).withInfringementDate(getInfringementDate())
                .withDescription(getComplainantDescription()).withDriverForename(getDriverForename()).withDriverFamilyName(getDriverFamilyName());
        apiResponse = RestUtils.post(complaintBuilder, complaintResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setComplaintId(apiResponse.extract().jsonPath().getInt("id.complaint"));
    }

    public void addConditionsUndertakings() throws MalformedURLException {

        String conditionsUndertakingResource = URL.build(env, "condition-undertaking").toString();
        CaseConditionsBuilder conditionsBuilder = new CaseConditionsBuilder().withLicence(application.getLicenceId())
                .withApplication(application.getApplicationId()).withCase(Integer.toString(getCaseId())).withType(getConditionUndertakingType())
                .withConditionCategory(getConditionUndertakingCategory()).withFulfilled(getFulfilled()).withAttachedTo(getAttachedTo())
                .withNotes(getConditionsUndertakingDescription());
        apiResponse = RestUtils.post(conditionsBuilder, conditionsUndertakingResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setConditionUndertaking(apiResponse.extract().jsonPath().getInt("id.conditionUndertaking"));
    }

    public void createSubmission() throws MalformedURLException {
        String submissionResource = URL.build(env, "submission").toString();
        CaseSubmissionBuilder submissionBuilder = new CaseSubmissionBuilder().withCase(Integer.toString(getCaseId())).withSubmissionType(getSubmissionType());
        apiResponse = RestUtils.post(submissionBuilder, submissionResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setSubmissionsId(apiResponse.extract().jsonPath().getInt("id.submission"));
    }

    public void createCaseNote() throws MalformedURLException {

        String caseNoteResource = URL.build(env, "processing/note").toString();
        CaseNotesBuilder caseNotesBuilder = new CaseNotesBuilder().withCase(Integer.toString(getCaseId())).withLicence(application.getLicenceId())
                .withApplication(application.getApplicationId()).withComment(getCaseNoteComment()).withPriority(getCaseNotePriority());
        apiResponse = RestUtils.post(caseNotesBuilder, caseNoteResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        setCaseNoteId(apiResponse.extract().jsonPath().getInt("id.note"));
    }

    public ValidatableResponse getCaseDetails(String resource, int id) {
        String caseDetailsResource = URL.build(env, String.format("%s/%s", resource, id)).toString();
        apiResponse = RestUtils.get(caseDetailsResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }


    public String createInternalUser(String userRole, String userType) {
        List<String> roles = new ArrayList<>();
        roles.add(userRole);
        String internalAdminUserResource = URL.build(env, "user/internal").toString();

        AddressBuilder addressBuilder = new AddressBuilder().withAddressLine1(getInternalUserAddressLine1())
                .withAddressLine2(getInternalUserAddressLine2()).withAddressLine3(getInternalUserAddressLine3())
                .withAddressLine4(getInternalUserAddressLine4()).withTown(getInternalUserTown())
                .withPostcode(getInternalUserPostCode()).withCountryCode(getCountryCode());
        PersonBuilder personBuilder = new PersonBuilder().withForename(getInternalUserForeName())
                .withFamilyName(getInternalUserFamilyName()).withBirthDate(getInternalUserDOB());

        ContactDetailsBuilder contactDetails = new ContactDetailsBuilder().withEmailAddress(getInternalUserEmailAddress()).withAddress(addressBuilder).withPerson(personBuilder);
        CreateInternalAdminUser internalAdminUser = new CreateInternalAdminUser().withContactDetails(contactDetails)
                .withLoginId(getInternalUserLogin()).withRoles(roles).withTeam(getInternalUserTeam()).withUserType(userType).withOSType("windows_10");
        apiResponse = RestUtils.post(internalAdminUser, internalAdminUserResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setInternalUserId(apiResponse.extract().response().jsonPath().getString("id.user"));
        return getInternalUserId();
    }

    public ValidatableResponse updateInternalUserDetails(String userId, String osType) {
        String version = fetchInternalUserInformation(userId, "version", "1");

        String internalAdminUserResource = URL.build(env, String.format("user/internal/%s", userId)).toString();

        AddressBuilder addressBuilder = new AddressBuilder().withAddressLine1(getInternalUserAddressLine1())
                .withAddressLine2(getInternalUserAddressLine2()).withAddressLine3(getInternalUserAddressLine3())
                .withAddressLine4(getInternalUserAddressLine4()).withTown(getInternalUserTown())
                .withPostcode(getInternalUserPostCode()).withCountryCode(getCountryCode());
        PersonBuilder personBuilder = new PersonBuilder().withForename(getInternalUserForeName())
                .withFamilyName(getInternalUserFamilyName()).withBirthDate(getInternalUserDOB());

        ContactDetailsBuilder contactDetails = new ContactDetailsBuilder().withEmailAddress(getInternalUserEmailAddress())
                .withAddress(addressBuilder).withPerson(personBuilder);
        CreateInternalAdminUser internalAdminUser = new CreateInternalAdminUser().withContactDetails(contactDetails)
                .withLoginId(getInternalUserLogin()).withTeam(getInternalUserTeam())
                .withUserType(UserRoles.INTERNAL.asString()).withVersion(version).withOSType(osType).withId(userId);
        apiResponse = RestUtils.put(internalAdminUser, internalAdminUserResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse grantVariation(String resource) throws MalformedURLException {
        String grantVariation = URL.build(env, String.format("variation/%s/%s", getVariationApplicationId(), resource)).toString();

        GenericBuilder genericBuilder = new GenericBuilder().withId(getVariationApplicationId());
        apiResponse = RestUtils.put(genericBuilder, grantVariation, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public String getLicenceTrafficArea() {
        String getApplicationResource = URL.build(env, String.format("licence/%s", application.getLicenceId())).toString();

        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setTrafficAreaName(apiResponse.extract().jsonPath().getString("trafficArea.name"));
        return getTrafficAreaName();
    }

    public String getLicenceStatusDetails() {
        String getApplicationResource = URL.build(env, String.format("licence/%s", application.getLicenceId())).toString();

        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setLicenceStatus(apiResponse.extract().jsonPath().getString("status.description"));
        return getLicenceStatus();
    }

    public String getOperatorTypeDetails() {
        String getApplicationResource = URL.build(env, String.format("licence/%s", application.getLicenceId())).toString();

        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setGoodOrPsv(apiResponse.extract().jsonPath().getString("goodsOrPsv.description"));
        return getGoodOrPsv();
    }

    public String getBusinessTypeDetails() {
        String getApplicationResource = URL.build(env, String.format("licence/%s", application.getLicenceId())).toString();

        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setBusinessType(apiResponse.extract().jsonPath().getString("organisation.type.description"));

        return getBusinessType();
    }

    public String getLicenceTypeDetails() {
        String getApplicationResource = URL.build(env, String.format("licence/%s", application.getLicenceId())).toString();

        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setLicenceType(apiResponse.extract().jsonPath().getString("licenceType.description"));
        return getLicenceType();
    }

    public void updateLicenceStatus(String status) {
        String typeOfLicenceResource = URL.build(env, String.format("licence/%s/decisions/%s", application.getLicenceId(), status)).toString();

        GenericBuilder genericBuilder = new GenericBuilder().withId(application.getLicenceId());
        apiResponse = RestUtils.post(genericBuilder, typeOfLicenceResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
    }

    public ValidatableResponse surrenderLicence(String licenceId) {
        String surrenderLicenceResource = URL.build(env, String.format("licence/%s/surrender", licenceId)).toString();

        SurrendersBuilder surrendersBuilder = new SurrendersBuilder().withLicence(licenceId);
        apiResponse = RestUtils.post(surrendersBuilder, surrenderLicenceResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        return apiResponse;
    }

    public ValidatableResponse updateSurrender(Integer surrenderId) {
        String updateSurrender = URL.build(env, String.format("licence/%s/surrender", application.getLicenceId())).toString();

        SurrendersBuilder surrendersBuilder = new SurrendersBuilder().withLicence(application.getLicenceId())
                .withId(surrenderId.toString()).withDiscsStolen(getDiscsStolen()).withVersion(version);
        apiResponse = RestUtils.put(surrendersBuilder, updateSurrender, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public ValidatableResponse deleteSurrender(Integer surrenderId) {
        String deleteSurrender = URL.build(env, String.format("licence/%s/surrender", application.getLicenceId())).toString();

        GenericBuilder genericBuilder = new GenericBuilder().withLicence(application.getLicenceId()).withId(surrenderId.toString());

        apiResponse = RestUtils.delete(genericBuilder, deleteSurrender, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public void updateFeatureToggle(String toggleId, String friendlyName, String configName, String status) {
        String updateFeatureToggleResource = URL.build(env, String.format("feature-toggle/%s/", toggleId)).toString();

        FeatureToggleBuilder featureToggleBuilder = new FeatureToggleBuilder().withId(toggleId).withFriendlyName(friendlyName).withConfigName(configName)
                .withStatus(status);

        apiResponse = RestUtils.put(featureToggleBuilder, updateFeatureToggleResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        apiResponse.statusCode(HttpStatus.SC_OK);
    }

    private void getDiscInformation() {
        Map<String, String> queryParams = new HashMap<>();
        {
            queryParams.put("niFlag", "N");
            queryParams.put("licenceType", String.valueOf(application.getLicenceType()));
            queryParams.put("operatorType", String.valueOf(application.getOperatorType()));
            queryParams.put("discSequence", getDiscSequence());
        }
        String discNumberingResource = URL.build(env, "disc-sequence/discs-numbering").toString();
        apiResponse = RestUtils.getWithQueryParams(discNumberingResource, queryParams, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        setStartNumber(apiResponse.extract().jsonPath().get("results.startNumber").toString());
        setEndNumber(apiResponse.extract().jsonPath().get("results.endNumber").toString());
    }

    public void printLicenceDiscs() {
        String operator;
        getDiscInformation();
        if (application.getOperatorType().equals(OperatorType.GOODS.asString())) {
            operator = "goods";
        } else {
            operator = "psv";
        }
        String discPrintResource = URL.build(env, String.format("%s-disc/print-discs/", operator)).toString();
        PrintDiscBuilder printDiscBuilder = new PrintDiscBuilder().withDiscSequence(getDiscSequence())
                .withLicenceType(application.getLicenceType()).withNiFlag(application.getNiFlag()).withStartNumber(getStartNumber());
        apiResponse = RestUtils.post(printDiscBuilder, discPrintResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        assertThat(apiResponse.extract().body().jsonPath().get("id.queue"), Matchers.notNullValue());
        setQueueId(apiResponse.extract().jsonPath().get("id.queue").toString());
        confirmDiscPrint();
    }

    private void confirmDiscPrint() {
        String operator;
        if (application.getOperatorType().equals(OperatorType.GOODS.asString())) {
            operator = "goods";
        } else {
            operator = "psv";
        }
        String discConfirmResource = URL.build(env, String.format("%s-disc/confirm-printing/", operator)).toString();
        ConfirmPrintBuilder confirmPrintBuilder = new ConfirmPrintBuilder().withDiscSequence(getDiscSequence())
                .withEndNumber(getEndNumber()).withStartNumber(getStartNumber()).withIsSuccessfull(true)
                .withLicenceType(application.getLicenceType()).withNiFlag(application.getNiFlag()).withQueueId(getQueueId());
        apiResponse = RestUtils.post(confirmPrintBuilder, discConfirmResource, apiHeaders.getHeaders());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
    }

    public void submitInterimApplication(String applicationId) {

        String interimApplicationResource = URL.build(env, String.format("application/%s/interim/", applicationId)).toString();
        int applicationVersion = Integer.parseInt(fetchApplicationInformation(applicationId, "version", "1"));

        InterimApplicationBuilder interimApplicationBuilder = new InterimApplicationBuilder().withAuthHgvVehicles(String.valueOf(application.getNoOfAddedHgvVehicles())).withAuthTrailers(String.valueOf(application.getNoOfAddedHgvVehicles()))
                .withRequested("Y").withReason(getInterimReason()).withStartDate(getInterimStartDate()).withEndDate(getInterimEndDate())
                .withAction("grant").withId(applicationId).withVersion(applicationVersion);
        apiResponse = RestUtils.put(interimApplicationBuilder, interimApplicationResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
    }

    public void grantInterimApplication(String applicationId) {
        submitInterimApplication(applicationId);
        String interimApplicationResource = URL.build(env, String.format("application/%s/interim/grant/", applicationId)).toString();

        InterimApplicationBuilder interimApplicationBuilder = new InterimApplicationBuilder().withId(applicationId);
        apiResponse = RestUtils.post(interimApplicationBuilder, interimApplicationResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
    }

    public ValidatableResponse addOperatingCentreToVariation(String vehicles, String trailers) {
        if (licenceType.equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        }
        String operatingCentreResource = URL.build(env, String.format("application/%s/operating-centre/", variationApplicationId)).toString();
        String permissionOption = "Y";
        AddressBuilder address = new AddressBuilder().withAddressLine1("test").withAddressLine2("test").
                withAddressLine3("test").withAddressLine4("test").withTown("test")
                .withPostcode(application.getPostCodeByTrafficArea()).withCountryCode(getCountryCode());
        OperatingCentreBuilder operatingCentreBuilder = new OperatingCentreBuilder().withApplication(variationApplicationId).withPermission(permissionOption).withAddress(address);
        if (application.getOperatorType().equals(OperatorType.GOODS.asString())) {
            operatingCentreBuilder.withNoOfHgvVehiclesRequired(String.valueOf(vehicles))
                    .withNoOfTrailersRequired(String.valueOf(trailers));
        }
        if (application.getOperatorType().equals(OperatorType.PUBLIC.asString()) && (!licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            operatingCentreBuilder.withNoOfHgvVehiclesRequired(String.valueOf(application.getTotalOperatingCentreHgvAuthority()));
            //NOTE: Because of timing constraints, psvs now store their vehicles in the hgv column and are updated via the hgv api call.
            // This is even though the vehicles aren't hgvs. It may be fixed later but at the moment this is the new norm.
        }
        if (application.getOperatorType().equals(OperatorType.PUBLIC.asString()) && (licenceType.equals(LicenceType.RESTRICTED.asString()))) {
            operatingCentreBuilder.withNoOfHgvVehiclesRequired(String.valueOf(application.getRestrictedVehicles()));
        }
        apiResponse = RestUtils.post(operatingCentreBuilder, operatingCentreResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);


        return apiResponse;
    }

    public ValidatableResponse editOperatingCentreOnVariation(String operatingCentreId, int vehicle) {
        if (application.getLicenceType().equals("special_restricted")) {
            throw new IllegalArgumentException("Cannot update operating centre for special_restricted licence");
        }
        String updateOperatingCentreResource = URL.build(env, String.format("application/%s/variation-operating-centre/L%s/", getVariationApplicationId(), operatingCentreId)).toString();
        OperatingCentreVariationBuilder updateOperatingCentre = new OperatingCentreVariationBuilder().withId(getVariationApplicationId())
                .withApplication(getVariationApplicationId()).withNoOfVehiclesRequired(String.valueOf(vehicle))
                .withVersion(version);
        apiResponse = RestUtils.put(updateOperatingCentre, updateOperatingCentreResource, application.apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse updateTotalAuthorisationsOnVariation(int hgvAuthorisation, int lgvAuthorisation) {
        if (this.application.getLicenceType().equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        } else {
            int applicationVersion = Integer.parseInt(this.fetchApplicationInformation(this.variationApplicationId, "version", "1"));
            String updateOperatingCentreResource = URL.build(env, String.format("application/%s/operating-centres", this.variationApplicationId)).toString();
            OperatingCentreUpdater updateOperatingCentre = (new OperatingCentreUpdater()).withId(this.variationApplicationId)
                    .withVersion(applicationVersion).withTotAuthHgvVehicles(hgvAuthorisation).withTotAuthLgvVehicles(lgvAuthorisation)
                    .withTotAuthTrailers(this.application.getNoOfOperatingCentreTrailerAuthorised()).withEnforcementArea(application.getEnforcementArea().value());
            this.apiResponse = RestUtils.put(updateOperatingCentre, updateOperatingCentreResource, application.apiHeaders.getHeaders());
            Utils.checkHTTPStatusCode(this.apiResponse, 200);
            return this.apiResponse;
        }
    }

    public ValidatableResponse updateLgvAuthorisationOnVariation(int lgvAuthorisation) {
        if (this.application.getLicenceType().equals(LicenceType.SPECIAL_RESTRICTED.asString())) {
            return null;
        } else {
            int applicationVersion = Integer.parseInt(this.fetchApplicationInformation(this.variationApplicationId, "version", "1"));
            String updateOperatingCentreResource = URL.build(env, String.format("application/%s/operating-centres", this.variationApplicationId)).toString();
            OperatingCentreUpdater updateOperatingCentre = (new OperatingCentreUpdater()).withId(this.variationApplicationId)
                    .withVersion(applicationVersion).withTotAuthHgvVehicles(application.getTotalOperatingCentreHgvAuthority()).withTotAuthLgvVehicles(lgvAuthorisation)
                    .withTotAuthTrailers(this.application.getNoOfOperatingCentreTrailerAuthorised()).withEnforcementArea(application.getEnforcementArea().value());
            this.apiResponse = RestUtils.put(updateOperatingCentre, updateOperatingCentreResource, application.apiHeaders.getHeaders());
            Utils.checkHTTPStatusCode(this.apiResponse, 200);
            return this.apiResponse;
        }
    }

}
