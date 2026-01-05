package apiCalls.eupaActions.external.permits;

import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypesModel;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class AvailableTypesAPIEupa extends EupaBaseAPI {

    public static TypesModel types() {
        String baseResource = "permits/available-types/";
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), baseResource);

        ValidatableResponse response = RestUtils.get(String.valueOf(ApiUrl.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(TypesModel.class);
    }
}
