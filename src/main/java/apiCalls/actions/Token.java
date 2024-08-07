package apiCalls.actions;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Utils;
import apiCalls.Utils.volBuilders.TokenRequestBuilder;
import apiCalls.enums.UserType;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
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
        if (adminToken == null) {
            LOGGER.info("Admin token is null, generating a new token.");
            adminToken = getToken(adminUser, adminPassword, UserType.INTERNAL.asString());
            setToken(adminToken);
        } else {
            LOGGER.info("Admin token already exists, returning the existing token.");
        }
        return adminToken;
    }

    public synchronized String getToken(String username, String password, String realm) throws HttpException {
        String jwtTokenResource = URL.build(env).toString().concat("auth/login");
        tokenBody.withUsername(username).withPassword(password).withRealm(realm);

        LOGGER.info("Requesting token from URL: " + jwtTokenResource);

        int retryCount = 0;
        int maxRetries = 3;
        while (retryCount < maxRetries) {
            try {
                ValidatableResponse tokenResponse = RestUtils.post(tokenBody, jwtTokenResource, header);
                Utils.checkHTTPStatusCode(tokenResponse, HttpStatus.SC_CREATED);
                LOGGER.info("Token successfully created.");
                return tokenResponse.extract().body().jsonPath().getString("flags.identity.Token.access_token");
            } catch (Exception e) {
                LOGGER.warning("Token creation failed, retrying... " + e.getMessage());
                retryCount++;
                try {
                    Thread.sleep((long) Math.pow(2, retryCount) * 1000); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new HttpException("Failed to create token after " + maxRetries + " retries.");
    }

    public String getAdminToken() {
        return adminToken;
    }

    private synchronized void setToken(String adminToken) {
        this.adminToken = adminToken;
        LOGGER.info("Admin token set.");
    }
}