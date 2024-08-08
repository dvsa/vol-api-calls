package apiCalls.eupaActions.internal;

import activesupport.aws.s3.SecretsManager;
import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Utils;
import apiCalls.actions.Token;
import apiCalls.enums.UserRoles;
import apiCalls.eupaActions.BaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class LicenceAPI extends BaseAPI {

    private static final String baseResource = "licence/";

    public static String licenceNumber(@NotNull String licenceId) throws HttpException {
        Token accessToken = new Token();
        updateHeader( "Authorization", "Bearer " + accessToken.getToken(SecretsManager.getSecretValue("adminUser"), SecretsManager.getSecretValue("adminPassword"), UserRoles.INTERNAL.asString()));

        String env = Properties.get("env", true);
        URL.build(EnvironmentType.getEnum(env), baseResource.concat(licenceId));

        ValidatableResponse response = RestUtils.get(String.valueOf(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
        return response.extract().path("licNo");
    }
}