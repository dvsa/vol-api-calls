package apiCalls.eupaActions.external.permits;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.PermitApplicationModel;
import apiCalls.Utils.eupaBuilders.external.permits.TypeModel;
import apiCalls.eupaActions.BaseAPI;
import apiCalls.eupaActions.util.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class ApplicationAPI extends BaseAPI {

    private static final String baseResource = "irhp-application/";
    private static ValidatableResponse response;

    public static PermitApplicationModel active(String licenceID, TypeModel type) {
        String path = "licence/active/" +
                "?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5C" +
                "IrhpApplication%5CActiveApplication&licence=";
        path += String.format("%s&irhpPermitType=%s", licenceID, type.getId());

        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path);

        response = RestUtils.get(Utils.removeLastSlash(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(PermitApplicationModel.class);
    }

    public static Set<Integer> feePerPermit(PermitApplicationModel application) {
        return feePerPermit(application.getId());
    }

    public static Set<Integer> feePerPermit(Integer permitID) {
        String path = "fee-per-permit/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CIrhpApplication%5CFeePerPermit&id=" +
                permitID;

        response = RestUtils.get(Utils.removeLastSlash(URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path)), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
        HashMap<String, Integer> result = response.extract().body().jsonPath().get("$");
        return new HashSet<>(result.values());
    }
}