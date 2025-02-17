package apiCalls.actions;

import activesupport.faker.FakerUtils;
import activesupport.number.Int;
import apiCalls.Utils.volBuilders.ContactDetailsBuilder;
import apiCalls.Utils.volBuilders.PersonBuilder;
import apiCalls.Utils.volBuilders.SelfServeUserRegistrationDetailsBuilder;
import apiCalls.Utils.generic.Headers;
import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Utils;
import apiCalls.enums.BusinessType;
import apiCalls.enums.UserTitle;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class RegisterUser {

    private String title;
    private String foreName;
    private String familyName;
    private String userName;
    private String birthDate;
    private String emailAddress;
    private String organisationName;
    private String businessType;
    private String userId;

    private final EnvironmentType env;
    private final Headers apiHeaders = new Headers();

    public RegisterUser() {
        env = EnvironmentType.getEnum(Properties.get("env", true));
        FakerUtils faker = new FakerUtils();

        synchronized (this) {
            this.foreName = faker.generateFirstName().concat(String.valueOf(Int.random(100, 999)));
            this.familyName = faker.generateLastName().concat(String.valueOf(Int.random(100, 999)));
            this.birthDate = Int.random(1900, 2018) + "-" + Int.random(1, 12) + "-" + Int.random(1, 28);
            this.userName = String.format("%s%s%s", foreName, familyName, Int.random(1000,9999));
            this.emailAddress = String.format("%s_%s%s.tester@dvsa.com", foreName, familyName, Int.random(10000, 99999));
            this.title = UserTitle.MR.asString();
            this.organisationName = faker.generateCompanyName();
            this.businessType = BusinessType.LIMITED_COMPANY.asString();
        }
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public synchronized String getTitle() {
        return title;
    }

    public synchronized void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public synchronized String getForeName() {
        return foreName;
    }

    public synchronized void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public synchronized String getFamilyName() {
        return familyName;
    }

    public synchronized void setUserName(String userName) {
        this.userName = userName;
    }

    public synchronized String getUserName() {
        return userName;
    }

    public synchronized String getBirthDate() {
        return birthDate;
    }

    public synchronized void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public synchronized String getEmailAddress() {
        return emailAddress;
    }

    public synchronized void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public synchronized String getOrganisationName() {
        return organisationName;
    }

    public synchronized void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public synchronized String getBusinessType() {
        return businessType;
    }

    public synchronized void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public synchronized void setUserId(String userId) {
        this.userId = userId;
    }

    public synchronized String getUserId() {
        return userId;
    }

    public synchronized ValidatableResponse registerUser() throws HttpException {
        String registerResource = URL.build(env, "user/selfserve/register").toString();

        PersonBuilder personBuilder = new PersonBuilder()
                .withTitle(getTitle())
                .withForename(getForeName())
                .withFamilyName(getFamilyName())
                .withBirthDate(getBirthDate());

        ContactDetailsBuilder contactDetailsBuilder = new ContactDetailsBuilder()
                .withEmailAddress(getEmailAddress())
                .withPerson(personBuilder);

        SelfServeUserRegistrationDetailsBuilder selfServeUserRegistrationDetailsBuilder = new SelfServeUserRegistrationDetailsBuilder()
                .withLoginId(getUserName())
                .withContactDetails(contactDetailsBuilder)
                .withOrganisationName(getOrganisationName())
                .withBusinessType(getBusinessType());

        ValidatableResponse apiResponse = RestUtils.post(selfServeUserRegistrationDetailsBuilder, registerResource, apiHeaders.getApiHeader());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setUserId(apiResponse.extract().jsonPath().getString("id.user"));
        return apiResponse;
    }
}