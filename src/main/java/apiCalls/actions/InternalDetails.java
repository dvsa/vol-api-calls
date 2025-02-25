package apiCalls.actions;

import apiCalls.Utils.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.HashMap;

public class InternalDetails extends BaseAPI {
    private final Headers apiHeaders = new Headers();
    private final EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));

    public HashMap<String, String> header() throws HttpException {
        var header = new HashMap<>(apiHeaders.getApiHeader());
        header.put("Authorization", "Bearer " + adminJWT());
        return header;
    }

    public synchronized ValidatableResponse getFinancialStandingRates() throws HttpException {
        var financialStandingRateEndpoint = ApiUrl.build(env, "financial-standing-rate").toString();
        var apiResponse = RestUtils.get(financialStandingRateEndpoint, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }
}