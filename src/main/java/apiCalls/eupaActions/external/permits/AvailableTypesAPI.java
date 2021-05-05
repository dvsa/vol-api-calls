package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypesModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class AvailableTypesAPI {

    private static String baseResource = "permits/available-types/";
    private static ValidatableResponse apiResponse;

    public Headers apiHeaders = new Headers();

    public ValidatableResponse types() {
        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource);

        apiResponse = RestUtils.get(String.valueOf(URL.getURL()), apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

}
