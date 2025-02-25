package apiCalls.eupaActions.external.permits;

import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.PermitTypeModel;
import apiCalls.Utils.eupaBuilders.external.permits.TypeModel;
import apiCalls.eupaActions.EupaBaseAPI;
import apiCalls.eupaActions.util.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class PermitTypeAPIEupa extends EupaBaseAPI {

    private static final String baseResource = "irhp-permit-type/";

    public static PermitTypeModel permitDetails(TypeModel type) {
        String path = type.getId()
                + "/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitType%5CById";

        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path);

        ValidatableResponse response = RestUtils.get(Utils.removeLastSlash(ApiUrl.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(PermitTypeModel.class);
    }

}
