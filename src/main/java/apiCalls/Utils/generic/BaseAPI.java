package apiCalls.Utils.generic;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import activesupport.aws.s3.SecretsManager;
import apiCalls.actions.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class BaseAPI extends Token {
    protected static final Logger LOGGER = LogManager.getLogger(BaseAPI.class);

    protected static EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    static Headers headers = new Headers();

    public synchronized String adminJWT() throws HttpException {
        String adminUser = SecretsManager.getSecretValue("adminUser");
        String adminPassword = SecretsManager.getSecretValue("adminPassword");

        if (getAdminToken() == null || isTokenExpired(getAdminToken())) {
            LOGGER.info("Generating new admin token");
            generateAdminToken(adminUser, adminPassword);
        } else {
            LOGGER.info("Using existing admin token");
        }

        return getAdminToken();
    }

    private boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (JWTDecodeException e) {
            LOGGER.error("Error decoding token: " + e.getMessage());
            return true;
        }
    }

    protected void logApiCall(String requestId, String methodName, String message) {
        LOGGER.info("RequestID: {}, Method: {}, Message: {}", requestId, methodName, message);
    }

    public synchronized HashMap<String, String> header(String requestId) throws HttpException {
        headers.getApiHeader().put("Authorization", "Bearer " + adminJWT());
        logApiCall(requestId, "header", "Authorization header set.");
        return (HashMap<String, String>) headers.getApiHeader();
    }

    public synchronized String fetchApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        String requestId = UUID.randomUUID().toString();
        String url = URL.build(env, String.format("application/%s/overview/", applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String fetchTMApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        String requestId = UUID.randomUUID().toString();
        String url = URL.build(env, String.format("transport-manager-application/%s", applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String fetchInternalUserInformation(String userId, String jsonPath, String defaultReturn) throws HttpException {
        String requestId = UUID.randomUUID().toString();
        String url = URL.build(env, String.format("user/internal/%s", userId)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String retrieveAPIData(String url, String jsonPath, String defaultReturn, String requestId) throws HttpException {
        headers.getApiHeader().put("Authorization", "Bearer " + adminJWT());
        ValidatableResponse response = RestUtils.get(url, headers.getApiHeader());

        try {
            return response.extract().response().jsonPath().getString(jsonPath);
        } catch (NullPointerException ne) {
            LOGGER.error("RequestID: {}, NullPointerException when extracting JSON path: {}", requestId, jsonPath, ne);
            return defaultReturn;
        }
    }
}