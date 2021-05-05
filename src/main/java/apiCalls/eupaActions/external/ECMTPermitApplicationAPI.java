package apiCalls.eupaActions.external;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.ECMTApplicationModel;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class ECMTPermitApplicationAPI {

    private static ValidatableResponse apiResponse;
    private static String baseResource = "permits/ecmt-permit-application/";

    private Headers apiHeaders = new Headers();

    public ValidatableResponse get(@NotNull String organisationId){
        String env = Properties.get("env", true);
        URL.build(
                EnvironmentType.getEnum(env), baseResource.concat(
                        "?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CPermits" +
                        "%5CEcmtPermitApplication/&organisationId=" + organisationId + "&sort=id&order=DESC"
                )
        );
        String url = URL.getURL().toString().substring(0, URL.getURL().toString().length() - 1);
        apiResponse = RestUtils.get(url, apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

}
