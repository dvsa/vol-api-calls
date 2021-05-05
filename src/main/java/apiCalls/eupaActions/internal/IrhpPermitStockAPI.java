package apiCalls.eupaActions.internal;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.internal.irhp.permit.stock.AvailableCountriesModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.eupaActions.BaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class IrhpPermitStockAPI {

    private static final String baseResource = "irhp-permit-stock/";
    private static ValidatableResponse apiResponse;
    public Headers apiHeaders = new Headers();


    public ValidatableResponse availableCountries() {

        URL.build(EnvironmentType.getEnum(Properties.get("env", true)), baseResource.concat("available-countries/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitStock%5CAvailableCountries"));

        apiResponse = RestUtils.get(String.valueOf(URL.getURL()), apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

}
