package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypesModel;
import apiCalls.eupaActions.BaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class AvailableTypesAPI extends BaseAPI {

    private static String baseResource = "permits/available-types/";
    private static ValidatableResponse response;

    public static TypesModel types() {
        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource);

        response = RestUtils.get(String.valueOf(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(TypesModel.class);
    }

}
