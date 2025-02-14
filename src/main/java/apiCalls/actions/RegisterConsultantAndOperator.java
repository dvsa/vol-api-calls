package apiCalls.actions;

import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.enums.BusinessType;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class RegisterConsultantAndOperator {
    private final RegisterUser operatorDetails;
    private final RegisterUser consultantDetails;
    private final Headers apiHeaders = new Headers();
    private final EnvironmentType env;

    public RegisterConsultantAndOperator() {
        this.env = EnvironmentType.getEnum(Properties.get("env", true));
        this.operatorDetails = new RegisterUser();
        this.consultantDetails = new RegisterUser();

        this.operatorDetails.setBusinessType(BusinessType.LIMITED_COMPANY.asString());
        this.consultantDetails.setBusinessType(BusinessType.LIMITED_COMPANY.asString());
    }

    public RegisterUser getOperatorDetails() {
        return operatorDetails;
    }

    public RegisterUser getConsultantDetails() {
        return consultantDetails;
    }

    @Nonnull
    public synchronized ValidatableResponse register() throws HttpException {
        var registerResource = URL.build(env, "user/selfserve/register/register-consultant-operator").toString();

        var consultantPayload = buildUserRegistrationDetails(consultantDetails);
        var operatorPayload = buildUserRegistrationDetails(operatorDetails);

        var payload = new HashMap<String, Object>();
        payload.put("consultantDetails", consultantPayload);
        payload.put("operatorDetails", operatorPayload);

        var apiResponse = RestUtils.post(payload, registerResource, apiHeaders.getApiHeader());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        consultantDetails.setUserId(apiResponse.extract().jsonPath().getString("id.user"));
        operatorDetails.setUserId(apiResponse.extract().jsonPath().getString("id.user"));

        return apiResponse;
    }

    private Map<String, Object> buildUserRegistrationDetails(RegisterUser user) {
        var person = new HashMap<String, Object>();
        person.put("title", user.getTitle());
        person.put("forename", user.getForeName());
        person.put("familyName", user.getFamilyName());
        person.put("birthDate", user.getBirthDate());

        var contactDetails = new HashMap<String, Object>();
        contactDetails.put("emailAddress", user.getEmailAddress());
        contactDetails.put("person", person);

        var registrationDetails = new HashMap<String, Object>();
        registrationDetails.put("loginId", user.getUserName());
        registrationDetails.put("contactDetails", contactDetails);
        registrationDetails.put("organisationName", user.getOrganisationName());
        registrationDetails.put("businessType", user.getBusinessType());

        return registrationDetails;
    }
}