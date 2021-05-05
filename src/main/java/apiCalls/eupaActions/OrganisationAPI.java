package apiCalls.eupaActions;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class OrganisationAPI {

    private static String baseResource = "organisation/";
    private static ValidatableResponse apiResponse;

    public Headers apiHeaders = new Headers();

    public ValidatableResponse get(@NotNull String organisationId){
        return getOrganisationModel(baseResource.concat(organisationId + "/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5COrganisation%5COrganisation"));
    }

    public ValidatableResponse dashboard(String organisationId) {
        return getOrganisationModel(baseResource.concat(organisationId + "/dashboard/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5COrganisation%5CDashboard"));
    }

    private ValidatableResponse getOrganisationModel(String path, int expectedResponseCode) {
        String env = Properties.get("env", true);
        URL.build(EnvironmentType.getEnum(env), path);
        apiResponse = RestUtils.get(String.valueOf(URL.getURL()), apiHeaders.getHeaders());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    private ValidatableResponse getOrganisationModel(String path) {
        return getOrganisationModel(path, HttpStatus.SC_OK);
    }

}
