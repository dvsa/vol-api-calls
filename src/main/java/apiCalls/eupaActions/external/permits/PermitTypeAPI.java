package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.PermitTypeModel;
import apiCalls.Utils.eupaBuilders.external.permits.TypeModel;
import apiCalls.eupaActions.BaseAPI;
import apiCalls.eupaActions.util.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class PermitTypeAPI extends BaseAPI {

    private static String baseResource = "irhp-permit-type/";
    private static ValidatableResponse response;

    public static PermitTypeModel permitDetails(TypeModel type) {
        String path = type.getId()
                + "/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpPermitType%5CById";

        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path);

        response = RestUtils.get(Utils.removeLastSlash(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(PermitTypeModel.class);
    }

}
