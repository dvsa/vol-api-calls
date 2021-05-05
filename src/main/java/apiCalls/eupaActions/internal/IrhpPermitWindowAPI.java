package apiCalls.eupaActions.internal;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.internal.irhp.permit.stock.OpenByCountryModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import apiCalls.eupaActions.BaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.List;
import java.util.stream.IntStream;

public class IrhpPermitWindowAPI {

    private static String baseResource = "irhp-permit-window/";
    private static ValidatableResponse apiResponse;
    public Headers apiHeaders = new Headers();

    public ValidatableResponse openByCountry(String... countryIds) {
        String path = "open-by-country/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitWindow%5COpenByCountry";

        String countries = IntStream.range(0, countryIds.length)
                .mapToObj(String::valueOf)
                .reduce("", (String accumulator, String idx) -> accumulator.concat("&countries%5B" + idx + "%5D=" + countryIds[Integer.parseInt(idx)]));

        URL.build(EnvironmentType.getEnum(Properties.get("env", true)), baseResource + path.concat(countries));

        apiResponse = RestUtils.get(URL.getURL().toString().substring(0, URL.getURL().toString().length() - 1), apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public static OpenByCountryModel openByCountry() {
        List<String> countryIds = IrhpPermitStockAPI.availableCountries().getAllCountryIds();
        return openByCountry(countryIds.toArray(new String[0]));
    }

}
