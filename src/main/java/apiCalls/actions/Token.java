package apiCalls.actions;

import apiCalls.Utils.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Utils;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.volBuilders.TokenRequestBuilder;
import apiCalls.enums.UserType;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Token {
    private static final Logger LOGGER = Logger.getLogger(Token.class.getName());
    private String adminToken;
    private final EnvironmentType env;
    private final ConcurrentHashMap<String, String> header;
    private final TokenRequestBuilder tokenBody;

    public Token() {
        this.env = EnvironmentType.getEnum(Properties.get("env", true));
        this.header = new ConcurrentHashMap<>();
        this.tokenBody = new TokenRequestBuilder();
    }

    public synchronized String generateAdminToken(String adminUser, String adminPassword) throws HttpException {
        adminToken = null;
        adminToken = getToken(adminUser, adminPassword, UserType.INTERNAL.asString());
        return adminToken;
    }

    public synchronized String getToken(String username, String password, String realm) throws HttpException {
        var jwtTokenResource = ApiUrl.build(env).toString().concat("auth/login");
        tokenBody.withUsername(username).withPassword(password).withRealm(realm);

        LOGGER.info("Requesting token from URL: " + jwtTokenResource);

        int retryCount = 0;
        int maxRetries = 3;
        Exception lastFailure = null;
        while (retryCount < maxRetries) {
            ValidatableResponse tokenResponse;
            try {
                tokenResponse = requestToken(jwtTokenResource);
                Utils.checkHTTPStatusCode(tokenResponse, HttpStatus.SC_CREATED);
            } catch (Exception e) {
                lastFailure = e;
                retryCount++;
                if (retryCount < maxRetries) {
                    LOGGER.warning("Token request failed; retrying.");
                    try {
                        Thread.sleep((long) Math.pow(2, retryCount) * 1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new HttpException("Interrupted while requesting an authentication token", ie);
                    }
                }
                continue;
            }

            var response = tokenResponse.extract().body().jsonPath();
            if (!Boolean.TRUE.equals(response.get("flags.isValid"))) {
                throw new HttpException("Authentication failed for user '" + username
                        + "' in realm '" + realm + "' (code: " + response.get("flags.code") + ")");
            }

            String accessToken = response.getString("flags.identity.Token.access_token");
            try {
                Headers.requireValidToken(accessToken);
            } catch (IllegalStateException e) {
                throw new HttpException("Authentication returned no valid JWT access token for user '"
                        + username + "' in realm '" + realm + "'", e);
            }
            LOGGER.info("Token successfully created.");
            return accessToken;
        }
        throw new HttpException("Failed to create token after " + maxRetries + " attempts for user '"
                + username + "' in realm '" + realm + "'", lastFailure);
    }

    public String getAdminToken() {
        return adminToken;
    }

    protected ValidatableResponse requestToken(String resource) {
        return RestUtils.post(tokenBody, resource, header);
    }
}