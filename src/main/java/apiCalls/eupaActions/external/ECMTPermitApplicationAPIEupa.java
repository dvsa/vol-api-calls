package apiCalls.eupaActions.external;

import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.ECMTApplicationModel;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class ECMTPermitApplicationAPIEupa extends EupaBaseAPI {

    public static ECMTApplicationModel get(@NotNull String organisationId){
        String baseResource = "permits/ecmt-permit-application/";
        ApiUrl.build(
                EnvironmentType.getEnum(Properties.get("env")),
                baseResource.concat(
                        "?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CPermits" +
                        "%5CEcmtPermitApplication/&organisationId=" + organisationId + "&sort=id&order=DESC"
                )
        );
        String url = ApiUrl.getURL().toString().substring(0, ApiUrl.getURL().toString().length() - 1);
        ValidatableResponse response = RestUtils.get(url, getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(ECMTApplicationModel.class);
    }
}
