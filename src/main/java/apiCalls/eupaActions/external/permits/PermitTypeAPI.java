package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypeModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class PermitTypeAPI {

    private static String baseResource = "irhp-permit-type/";
    private static ValidatableResponse apiResponse;

    public Headers apiHeaders = new Headers();

    public ValidatableResponse permitDetails(TypeModel type) {
        String path = type.getId()
                + "/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitType%5CById";

        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path);

        apiResponse = RestUtils.get(Utils.removeLastSlash(URL.getURL()), apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

}
