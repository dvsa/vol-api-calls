package apiCalls.actions;

import activesupport.http.RestUtils;
import activesupport.system.Properties;

import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.enums.Realm;
import apiCalls.enums.UserType;
import io.restassured.response.ValidatableResponse;

import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;


public class UserDetails extends BaseAPI {
    private Headers apiHeaders = new Headers();
    private String jwtToken;

    private String adminToken;
    private String organisationId;

    EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    private ValidatableResponse apiResponse;


    public synchronized ValidatableResponse getUserDetails(String userType, String userId, String username, String password) throws HttpException {
        String userDetailsResource;
            apiHeaders
                    .apiHeader
                    .put("Authorization", "Bearer " + adminJWT());

        if (userType.equals(UserType.EXTERNAL.asString())) {
            String orgId;
            userDetailsResource = URL.build(env, String.format("user/%s/%s", userType, userId)).toString();
            apiResponse = RestUtils.get(userDetailsResource, apiHeaders.getApiHeader());
            setJwtToken(getToken(username, password, Realm.SELF_SERVE.asString()));
            orgId = apiResponse.extract().jsonPath().prettyPeek().getString("organisationUsers.organisation.id");
            setOrganisationId(orgId);
        } else if (userType.equals(UserType.INTERNAL.asString())) {
            userDetailsResource = URL.build(env, String.format("user/%s/%s", userType, userId)).toString();
            apiResponse = RestUtils.get(userDetailsResource, apiHeaders.getApiHeader());
            setJwtToken(getToken(username, password, Realm.INTERNAL.asString()));
        }
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }
}