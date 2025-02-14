package apiCalls.eupaActions.internal;

import activesupport.aws.s3.SecretsManager;
import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.internal.irhp.permit.stock.AvailableCountriesModel;
import apiCalls.actions.Token;
import apiCalls.enums.UserRoles;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class IrhpPermitStockAPIEupa extends EupaBaseAPI {

    private static final String baseResource = "irhp-permit-stock/";

    public static AvailableCountriesModel availableCountries() throws HttpException {
        Token accessToken = new Token();
        updateHeader( "Authorization", "Bearer " + accessToken.getToken(SecretsManager.getSecretValue("adminUser"), SecretsManager.getSecretValue("adminPassword"), UserRoles.INTERNAL.asString()));

        URL.build(EnvironmentType.getEnum(Properties.get("env", true)), baseResource.concat("available-countries/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitStock%5CAvailableCountries"));

        ValidatableResponse response = RestUtils.get(String.valueOf(URL.getURL()), getHeaders());

        response.statusCode(HttpStatus.SC_OK);
        prettyPrintJson(response.extract().asString());

        return response.extract().as(AvailableCountriesModel.class);
    }
}
