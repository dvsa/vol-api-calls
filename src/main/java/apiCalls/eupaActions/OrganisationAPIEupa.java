package apiCalls.eupaActions;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.organisation.OrganisationModel;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

public class OrganisationAPIEupa extends EupaBaseAPI {

    private static String baseResource = "organisation/";
    private static ValidatableResponse response;

    public static OrganisationModel get(@NotNull String organisationId){
        return getOrganisationModel(baseResource.concat(organisationId + "/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5COrganisation%5COrganisation"));
    }

    public static OrganisationModel dashboard(String organisationId) {
        return getOrganisationModel(baseResource.concat(organisationId + "/dashboard/?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5COrganisation%5CDashboard"));
    }

    private static OrganisationModel getOrganisationModel(String path, Class<OrganisationModel> modelClass, int expectedResponseCode) {
        String env = Properties.get("env", true);
        URL.build(EnvironmentType.getEnum(env), path);
        response = RestUtils.get(String.valueOf(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(expectedResponseCode);

        return response.extract().as(modelClass);
    }

    private static OrganisationModel getOrganisationModel(String path) {
        return getOrganisationModel(path, OrganisationModel.class, HttpStatus.SC_OK);
    }
}
