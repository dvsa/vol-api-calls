package apiCalls.eupaActions.internal;

import activesupport.aws.s3.SecretsManager;
import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.actions.Token;
import apiCalls.enums.UserRoles;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class LicenceAPIEupa extends EupaBaseAPI {

    private static final String baseResource = "licence/";

    public static String licenceNumber(@NotNull String licenceId) throws HttpException {
        Token accessToken = new Token();
        updateHeader( "Authorization", "Bearer " + accessToken.getToken(SecretsManager.getSecretValue("adminUser"), SecretsManager.getSecretValue("adminPassword"), UserRoles.INTERNAL.asString()));

        String env = Properties.get("env", true);
        ApiUrl.build(EnvironmentType.getEnum(env), baseResource.concat(licenceId));

        ValidatableResponse response = RestUtils.get(String.valueOf(ApiUrl.getURL()),
 getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
        return response.extract().path("licNo");
    }
}