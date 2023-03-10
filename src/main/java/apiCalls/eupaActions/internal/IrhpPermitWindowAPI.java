package apiCalls.eupaActions.internal;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.internal.irhp.permit.stock.OpenByCountryModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.actions.AccessToken;
import apiCalls.enums.UserRoles;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrhpPermitWindowAPI {

    private static EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    private static ValidatableResponse apiResponse;
    private static Headers apiHeaders = new Headers();

    public static OpenByCountryModel openByCountry(String[] countryIds) throws HttpException {
        AccessToken accessToken = new AccessToken();
        apiHeaders.headers.put( "Authorization", "Bearer " + accessToken.getToken(Utils.config.getString("adminUser"), Utils.config.getString("adminPassword"), UserRoles.INTERNAL.asString()));
        String openCountries = URL.build(env,"irhp-permit-window/open-by-country").toString();

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < countryIds.length; i++) {
            map.put(String.format("countries[%s]", i), String.format("%s", countryIds[i]));
        }

        apiResponse = RestUtils.getWithQueryParams(openCountries, map, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse.extract().as(OpenByCountryModel.class);
    }

    public static OpenByCountryModel openByCountry() throws HttpException {
        List<String> countryIds = IrhpPermitStockAPI.availableCountries().getAllCountryIds();
        return openByCountry(countryIds.toArray(new String[0]));
    }
}