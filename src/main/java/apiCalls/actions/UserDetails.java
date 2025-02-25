package apiCalls.actions;

import apiCalls.Utils.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.enums.Realm;
import apiCalls.enums.UserType;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.concurrent.ConcurrentHashMap;

public class UserDetails extends BaseAPI {
    private final Headers apiHeaders = new Headers();
    private String jwtToken;

    private String organisationId;

    private ValidatableResponse apiResponse;

    private EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    private static final ConcurrentHashMap<String, String> userOrgMap = new ConcurrentHashMap<>();

    public synchronized ValidatableResponse getUserDetails(String userType, String userId, String username, String password) throws HttpException {
        apiHeaders.getApiHeader().put("Authorization", "Bearer " + adminJWT());

        if (userType.equals(UserType.EXTERNAL.asString())) {
            return handleExternalUser(userId, username, password);
        } else if (userType.equals(UserType.INTERNAL.asString())) {
            return handleInternalUser(userId, username, password);
        }

        throw new IllegalArgumentException("Invalid user type: " + userType);
    }

    private ValidatableResponse handleExternalUser(String userId, String username, String password) throws HttpException {
        var userDetailsResource = ApiUrl.build(env, "user/%s/%s".formatted(UserType.EXTERNAL.asString(), userId)).toString();
        apiResponse = RestUtils.get(userDetailsResource, apiHeaders.getApiHeader());

        setJwtToken(getToken(username, password, Realm.SELF_SERVE.asString()));

        var orgId = apiResponse.extract().jsonPath().prettyPeek().getString("organisationUsers.organisation.id");
        setOrganisationId(orgId);
        userOrgMap.put(userId, orgId);

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }

    private ValidatableResponse handleInternalUser(String userId, String username, String password) throws HttpException {
        var userDetailsResource = ApiUrl.build(env, "user/%s/%s".formatted(UserType.INTERNAL.asString(), userId)).toString();
        apiResponse = RestUtils.get(userDetailsResource, apiHeaders.getApiHeader());

        setJwtToken(getToken(username, password, Realm.INTERNAL.asString()));

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