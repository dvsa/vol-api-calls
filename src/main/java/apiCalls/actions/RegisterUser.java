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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getForeName() {
        return foreName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    private EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    private ValidatableResponse apiResponse;

    private Headers apiHeaders = new Headers();

    public RegisterUser() {
        FakerUtils faker = new FakerUtils();
        setForeName( faker.generateFirstName().concat(String.valueOf(Int.random(100, 999))) );
        setFamilyName( faker.generateLastName().concat(String.valueOf(Int.random(100, 999))) );
        setBirthDate( Int.random(1900, 2018) + "-" + Int.random(1, 12) + "-" + Int.random(1, 28) );
        setUserName( String.format("%s.%s%s", getForeName(), getFamilyName(), Int.random(1000,9999)) );
        setEmailAddress( String.format("%s_%s%s.tester@dvsa.com", getForeName(), getFamilyName(), Int.random(10000, 99999)) );
        setTitle( UserTitle.MR.asString() );
        setOrganisationName( faker.generateCompanyName() );
        setBusinessType( BusinessType.LIMITED_COMPANY.asString() );
    }




    public ValidatableResponse registerUser() {
        String registerResource = URL.build(env, "user/selfserve/register").toString();
        apiHeaders.getHeaders().put("api", "dvsa");

        PersonBuilder personBuilder = new PersonBuilder().withTitle(getTitle()).withForename(getForeName()).withFamilyName(getFamilyName()).withBirthDate(getBirthDate());
        ContactDetailsBuilder contactDetailsBuilder = new ContactDetailsBuilder().withEmailAddress(getEmailAddress()).withPerson(personBuilder);

        SelfServeUserRegistrationDetailsBuilder selfServeUserRegistrationDetailsBuilder = new SelfServeUserRegistrationDetailsBuilder().withLoginId(getUserName()).withContactDetails(contactDetailsBuilder)
                .withOrganisationName(getOrganisationName()).withBusinessType(getBusinessType());

        apiResponse = RestUtils.post(selfServeUserRegistrationDetailsBuilder, registerResource, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
        setUserId(apiResponse.extract().jsonPath().getString("id.user"));
        return apiResponse;
    }
}