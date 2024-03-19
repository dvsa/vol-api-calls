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

import java.util.HashMap;

public class Token {

    EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    HashMap<String, String> header = new HashMap<>();
    TokenRequestBuilder tokenBody = new TokenRequestBuilder();

    synchronized String generateAdminToken() throws HttpException {
        return getToken(Utils.config.getString("adminUser"), Utils.config.getString("adminPassword"), UserType.INTERNAL.asString());

    }

    public synchronized String getToken(String username, String password, String realm) throws HttpException {
        String jwtTokenResource;
        jwtTokenResource = URL.build(env).toString().concat("auth/login");
        tokenBody.withUsername(username).withPassword(password).withRealm(realm);
        ValidatableResponse tokenResponse = RestUtils.post(tokenBody, jwtTokenResource, header);

        Utils.checkHTTPStatusCode(tokenResponse, HttpStatus.SC_CREATED);

        return tokenResponse.extract().body().jsonPath().getString("flags.identity.Token.access_token");
    }
}
