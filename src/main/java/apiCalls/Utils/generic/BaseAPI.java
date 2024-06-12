package apiCalls.Utils.generic;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.actions.Token;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;


public class BaseAPI extends Token {
    protected static EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    static Headers headers = new Headers();
    protected SecretsManagerClient secretsManagerClient = new SecretsManagerClient();
    public synchronized String adminJWT() throws HttpException {
        String adminUser =secretsManagerClient.getSecret("adminUser");
        String adminPassword = secretsManagerClient.getSecret("adminPassword");
        if(getAdminToken() == null){
            generateAdminToken(adminUser, adminPassword);
        }
        return getAdminToken();
    }

    public synchronized String fetchApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        String url = URL.build(env, String.format("application/%s/overview/", applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn);
    }

    public synchronized String fetchTMApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        String url = URL.build(env, String.format("transport-manager-application/%s", applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn);
    }

    public synchronized String fetchInternalUserInformation(String userId, String jsonPath, String defaultReturn) throws HttpException {
        String url = URL.build(env, String.format("user/internal/%s", userId)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn);
    }

    public synchronized String retrieveAPIData(String url, String jsonPath, String defaultReturn) throws HttpException {
        headers.apiHeader.put("Authorization", "Bearer " + adminJWT());
        ValidatableResponse response = RestUtils.get(url, headers.getApiHeader());
        try {
            return response.extract().response().jsonPath().getString(jsonPath);
        } catch (NullPointerException ne) {
            return defaultReturn;
        }
    }
}