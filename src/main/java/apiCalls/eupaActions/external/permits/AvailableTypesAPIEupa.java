package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypesModel;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class AvailableTypesAPIEupa extends EupaBaseAPI {

    public static TypesModel types() {
        String baseResource = "permits/available-types/";
        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource);

        ValidatableResponse response = RestUtils.get(String.valueOf(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(TypesModel.class);
    }
}
