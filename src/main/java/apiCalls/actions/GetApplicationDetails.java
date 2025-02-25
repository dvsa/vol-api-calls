package apiCalls.actions;

import apiCalls.Utils.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;

public class GetApplicationDetails extends BaseAPI {
    private final CreateApplication application;
    private String applicationStatus;
    private String licenceId;
    private String licenceNumber;
    private final EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    private final Headers apiHeaders = new Headers();

    public GetApplicationDetails(CreateApplication application) {
        this.application = application;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public synchronized ValidatableResponse getApplicationLicenceDetails() throws HttpException {
        var getApplicationResource = ApiUrl.build(env, "application/%s".formatted(application.getApplicationId())).toString();
        apiHeaders.getApiHeader().put("Authorization", "Bearer " + adminJWT());

        var apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getApiHeader());

        this.licenceId = apiResponse.extract().jsonPath().getString("licence.id");
        this.licenceNumber = apiResponse.extract().jsonPath().getString("licence.licNo");
        this.applicationStatus = apiResponse.extract().jsonPath().getString("licenceType.status.olbsKey");

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
        return apiResponse;
    }
}