package apiCalls.actions;

import activesupport.http.RestUtils;
import activesupport.system.Properties;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import javax.xml.ws.http.HTTPException;

public class GetApplicationDetails {

    private ValidatableResponse apiResponse;

    private CreateApplication application;
    private String applicationStatus;
    private String licenceId;
    private String licenceNumber;
    private String operatingCentreId;

    private EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    Headers apiHeaders = new Headers();

    public GetApplicationDetails(CreateApplication application) {
        this.application = application;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getOperatingCentreId() {
        return operatingCentreId;
    }

    public void setOperatingCentreId(String operatingCentreId) {
        this.operatingCentreId = operatingCentreId;
    }

    public ValidatableResponse getApplicationLicenceDetails() {
        String getApplicationResource = URL.build(env, String.format("application/%s", application.getApplicationId())).toString();
        apiHeaders.getHeaders().put("x-pid", Utils.config.getString("apiHeader"));
        apiResponse = RestUtils.get(getApplicationResource, apiHeaders.getHeaders());
        setLicenceId(apiResponse.extract().jsonPath().getString("licence.id"));
        setLicenceNumber(apiResponse.extract().jsonPath().getString("licence.licNo"));
        setApplicationStatus(apiResponse.extract().jsonPath().getString("licenceType.status.olbsKey"));
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }

    public ValidatableResponse getOperatingCentreDetails() {
        String getApplicationResource = URL.build(this.env, String.format("licence/%s/operating-centre", application.getLicenceId())).toString();
        apiResponse = RestUtils.get(getApplicationResource, this.apiHeaders.getHeaders());
        setOperatingCentreId(apiResponse.extract().jsonPath().getString("operatingCentres.operatingCentre.id").replaceAll("\\[|]", ""));
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);

        return apiResponse;
    }
}