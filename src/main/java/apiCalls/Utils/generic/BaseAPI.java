package apiCalls.Utils.generic;

import activesupport.aws.s3.SecretsManager;
import activesupport.system.Properties;
import apiCalls.Utils.http.RestUtils;
import apiCalls.actions.Token;
import io.jsonwebtoken.Jwts;
import org.apache.hc.core5.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvsa.testing.lib.url.api.ApiUrl;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BaseAPI extends Token {
    private static final Logger LOGGER = LogManager.getLogger(BaseAPI.class);

    protected static final EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    private static final Headers headers = new Headers();

    public synchronized String adminJWT() throws HttpException {
        var adminUser = env == EnvironmentType.LOCAL
                ? SecretsManager.getSecretValue("adminLocalUser")
                : SecretsManager.getSecretValue("adminUser");
        var adminPassword = env == EnvironmentType.LOCAL
                ? SecretsManager.getSecretValue("adminLocalPassword")
                : SecretsManager.getSecretValue("adminPassword");

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

            String[] chunks = token.split("\\.");
            if (chunks.length != 3) {
                LOGGER.error("Invalid JWT format - expected 3 parts but got {}", chunks.length);
                return true;
            }
            
            String payload = new String(java.util.Base64.getUrlDecoder().decode(chunks[1]));
            
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode claims = mapper.readTree(payload);
            
            if (claims.has("exp")) {
                long expTime = claims.get("exp").asLong() * 1000; // Convert from seconds to milliseconds
                boolean expired = expTime < System.currentTimeMillis();
                LOGGER.debug("Token expiration check: exp={}, current={}, expired={}", 
                    new Date(expTime), new Date(), expired);
                return expired;
            } else {
                LOGGER.warn("Token has no expiration claim, treating as expired");
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("Error checking token expiration: {}", e.getMessage());
            return true;
        }
    }

    protected void logApiCall(String requestId, String methodName, String message) {
        LOGGER.info("RequestID: {}, Method: {}, Message: {}", requestId, methodName, message);
    }

    public synchronized ConcurrentHashMap<String, String> header(String requestId) throws HttpException {
        headers.getApiHeader().put("Authorization", "Bearer " + adminJWT());
        logApiCall(requestId, "header", "Authorization header set.");
        return new ConcurrentHashMap<>(headers.getApiHeader());
    }

    public synchronized String fetchApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        var requestId = UUID.randomUUID().toString();
        var url = ApiUrl.build(env, "application/%s/overview/".formatted(applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String fetchTMApplicationInformation(String applicationNumber, String jsonPath, String defaultReturn) throws HttpException {
        var requestId = UUID.randomUUID().toString();
        var url = ApiUrl.build(env, "transport-manager-application/%s".formatted(applicationNumber)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String fetchInternalUserInformation(String userId, String jsonPath, String defaultReturn) throws HttpException {
        var requestId = UUID.randomUUID().toString();
        var url = ApiUrl.build(env, "user/internal/%s".formatted(userId)).toString();
        return retrieveAPIData(url, jsonPath, defaultReturn, requestId);
    }

    public synchronized String retrieveAPIData(String url, String jsonPath, String defaultReturn, String requestId) throws HttpException {
        headers.getApiHeader().put("Authorization", "Bearer " + adminJWT());
        var response = RestUtils.get(url, headers.getApiHeader());

        try {
            return response.extract().response().jsonPath().getString(jsonPath);
        } catch (NullPointerException ne) {
            LOGGER.error("RequestID: {}, NullPointerException when extracting JSON path: {}", requestId, jsonPath, ne);
            return defaultReturn;
        }
    }
}