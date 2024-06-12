package apiCalls.eupaActions.internal;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.StandardResponseModel;
import apiCalls.Utils.eupaBuilders.internal.GrantApplicationModel;
import apiCalls.Utils.eupaBuilders.internal.OverviewModel;
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

public class CaseWorkerAPI extends BaseAPI {

    private static ValidatableResponse response;
    private static Token accessToken = new Token();

    public static void overview(@NotNull OverviewModel overview) throws HttpException {
        updateHeader("Authorization", "Bearer " + accessToken.getToken(secrets.getSecret("adminUser"), secrets.getSecret("adminPassword"), UserRoles.INTERNAL.asString()));
        URL.build(EnvironmentType.getEnum(Properties.get("env", true)), String.format("application/%s/overview/", overview.getApplicationId()));
        int version = 1;

        do {
            if (overview.getVersion() == null)
                overview.setVersion(version);

            response = RestUtils.put(overview, URL.getURL().toString(), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_CONFLICT)
                overview.setVersion(++version);

        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static StandardResponseModel grantApplication(@NotNull GrantApplicationModel grantApplication) throws HttpException {
        updateHeader( "Authorization", "Bearer " + accessToken.getToken(secrets.getSecret("adminUser"), secrets.getSecret("adminPassword"), UserRoles.INTERNAL.asString()));
        URL.build(EnvironmentType.getEnum(Properties.get("env", true)), String.format("application/%s/grant/", grantApplication.getId()));

        response = RestUtils.put(grantApplication, String.valueOf(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
        return response.extract().body().as(StandardResponseModel.class);
    }
}
