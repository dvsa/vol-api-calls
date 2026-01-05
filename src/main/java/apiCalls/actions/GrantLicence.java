package apiCalls.actions;

import activesupport.dates.Dates;
import activesupport.faker.FakerUtils;
import activesupport.system.Properties;
import apiCalls.Utils.http.RestUtils;
import apiCalls.Utils.volBuilders.*;
import apiCalls.Utils.generic.BaseAPI;
import apiCalls.Utils.generic.Headers;
import apiCalls.Utils.generic.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.joda.time.LocalDate;

import java.util.*;

public class GrantLicence extends BaseAPI {

    private static final Logger LOGGER = LogManager.getLogger(GrantLicence.class);
    private final EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    private final FakerUtils faker = new FakerUtils();
    private final Headers apiHeaders = new Headers();
    private ValidatableResponse apiResponse;
    private final CreateApplication application;
    private List<Integer> outstandingFeesIds;
    private final List<Double> feesToPay = new ArrayList<>();
    private int feeId;
    private String dateState;

    private void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public String getDateState() {
        return dateState;
    }

    public void setDateState(String dateState) {
        this.dateState = dateState;
    }

    public GrantLicence(CreateApplication application) {
        this.application = application;
        Dates date = new Dates(LocalDate::new);
        this.dateState = date.getFormattedDate(0, 0, 0, "yyyy-MM-dd");
    }

    public HashMap<String, String> header() throws HttpException {
        var header = new HashMap<>(apiHeaders.getApiHeader());
        header.put("Authorization", "Bearer " + adminJWT());
        return header;
    }

    public synchronized ValidatableResponse grantLicence() throws HttpException {
        createOverview();
        getOutstandingFees();
        payOutstandingFees();
        grant();
        return apiResponse;
    }

    /***
     * To use the following methods:
     * First Pass in a GetUserDetails object into a CreateApplication object and set the organisationId within GetUserDetails.
     * Second set applicationId within your CreateApplication object and then call the methods within GrantLicence.
     */

    public synchronized void createOverview() throws HttpException {
        var overviewResource = ApiUrl.build(env, "application/%s/overview/".formatted(application.getApplicationId())).toString();
        var status = "1";
        var overrideOption = "Y";
        var transportArea = "D";
        var trackingId = fetchApplicationInformation(application.getApplicationId(), "applicationTracking.id", null);
        var applicationVersion = Integer.parseInt(fetchApplicationInformation(application.getApplicationId(), "version", "1"));
        var applicationTrackingVersion = Integer.parseInt(fetchApplicationInformation(application.getApplicationId(), "applicationTracking.version", "1"));

        var applicationReferredToPi = fetchApplicationInformation(application.getApplicationId(), "applicationReferredToPi", null);
        applicationReferredToPi = (applicationReferredToPi == null || (!applicationReferredToPi.equals("Y") && !applicationReferredToPi.equals("1"))) ? "N" : applicationReferredToPi;

        var tracking = new TrackingBuilder()
                .withId(trackingId)
                .withVersion(applicationTrackingVersion)
                .withAddressesStatus(status)
                .withBusinessDetailsStatus(status)
                .withBusinessTypeStatus(status)
                .withCommunityLicencesStatus(status)
                .withConditionsUndertakingsStatus(status)
                .withConvictionsPenaltiesStatus(status)
                .withFinancialEvidenceStatus(status)
                .withFinancialHistoryStatus(status)
                .withLicenceHistoryStatus(status)
                .withOperatingCentresStatus(status)
                .withPeopleStatus(status)
                .withSafetyStatus(status)
                .withTransportManagersStatus(status)
                .withTypeOfLicenceStatus(status)
                .withDeclarationsInternalStatus(status)
                .withVehiclesStatus(status)
                .withVehiclesPsvStatus(status)
                .withTaxiPhvStatus(status)
                .withVehiclesSizeStatus(status)
                .withPsvOperateSmallStatus(status)
                .withPsvOperateLargeStatus(status)
                .withPsvSmallConditionsStatus(status)
                .withPsvOperateNoveltyStatus(status)
                .withPsvSmallPartWrittenStatus(status)
                .withPsvDocumentaryEvidenceSmallStatus(status)
                .withPsvDocumentaryEvidenceLargeStatus(status)
                .withPsvMainOccupationUndertakingsStatus(status);


        var overview = new OverviewBuilder()
                .withId(application.getApplicationId())
                .withVersion(applicationVersion)
                .withLeadTcArea(transportArea)
                .withTracking(tracking)
                .withOverrideOppositionDate(overrideOption)
                .withApplicationReferredToPi(applicationReferredToPi);

        apiResponse = RestUtils.put(overview, overviewResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
    }

    public synchronized void getOutstandingFees() throws HttpException {
        var getOutstandingFeesResource = ApiUrl.build(env, "application/%s/outstanding-fees/".formatted(application.getApplicationId())).toString();
        apiResponse = RestUtils.get(getOutstandingFeesResource, header());
        if (apiResponse.extract().statusCode() != HttpStatus.SC_OK) {
            LOGGER.info(apiResponse.extract().statusCode());
            LOGGER.info(apiResponse.extract().response().asString());
            throw new HttpException("Invalid response code: " + apiResponse.extract().statusCode());
        } else if (!apiResponse.extract().response().body().jsonPath().getList("outstandingFees.id").isEmpty()) {
            outstandingFeesIds = apiResponse.extract().response().body().jsonPath().getList("outstandingFees.id");
            var fees = apiResponse.extract().response().body().jsonPath().<String>getList("outstandingFees.grossAmount");
            fees.forEach(d -> {
                try {
                    feesToPay.add(Double.parseDouble(d));
                } catch (NumberFormatException e) {
                    e.fillInStackTrace();
                }
            });
        }
    }

    public synchronized void payOutstandingFees() throws HttpException {
        var payer = faker.generateFirstName() + faker.generateLastName();
        var paymentMethod = "fpm_cash";
        var slipNo = "123456";

        var payOutstandingFeesResource = ApiUrl.build(env, "transaction/pay-outstanding-fees/").toString();
        var feesBuilder = new FeesBuilder()
                .withFeeIds(outstandingFeesIds)
                .withOrganisationId(application.getUserDetails().getOrganisationId())
                .withApplicationId(application.getApplicationId())
                .withPaymentMethod(paymentMethod)
                .withReceived(feesToPay.stream().mapToDouble(Double::doubleValue).sum())
                .withReceiptDate(dateState)
                .withPayer(payer)
                .withSlipNo(slipNo);
        apiResponse = RestUtils.post(feesBuilder, payOutstandingFeesResource, header());
        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);
    }

    public synchronized void grant() throws HttpException {
        var grantApplicationResource = ApiUrl.build(env, "application/%s/grant/".formatted(application.getApplicationId())).toString();
        var grantApplication = new GrantApplicationBuilder()
                .withId(application.getApplicationId())
                .withDuePeriod("9")
                .withAuthority("grant_authority_dl")
                .withCaseworkerNotes("This notes are from the API");
        apiResponse = RestUtils.put(grantApplication, grantApplicationResource, header());

        if (apiResponse.extract().statusCode() != HttpStatus.SC_OK) {
            LOGGER.info(apiResponse.extract().statusCode());
            LOGGER.info(apiResponse.extract().response().asString());
            throw new HttpException("Invalid response code: " + apiResponse.extract().statusCode());
        } else if (apiResponse.extract().response().asString().contains("fee")) {
            this.feeId = apiResponse.extract().response().jsonPath().getInt("id.fee");
            var apiMessages = apiResponse.extract().jsonPath().get("messages").toString();
            if (!apiMessages.contains("Application status updated") && (!apiMessages.contains("Licence status updated"))) {
                throw new AssertionError("Licence failed to grant through the API.");
            }
        }
    }

    public synchronized ValidatableResponse payGrantFees(String NIFlag) throws HttpException {
        var payer = faker.generateFirstName() + faker.generateLastName();
        var grantFees = NIFlag.equals("Y") ? 449.00 : 401.00;
        var paymentMethod = "fpm_cash";
        var slipNo = "123456";

        var payOutstandingFeesResource = ApiUrl.build(env, "transaction/pay-outstanding-fees/").toString();

        var feesBuilder = new FeesBuilder()
                .withFeeIds(List.of(feeId))
                .withOrganisationId(application.getUserDetails().getOrganisationId())
                .withApplicationId(application.getApplicationId())
                .withPaymentMethod(paymentMethod)
                .withReceived(grantFees)
                .withReceiptDate(dateState)
                .withPayer(payer)
                .withSlipNo(slipNo);
        apiResponse = RestUtils.post(feesBuilder, payOutstandingFeesResource, header());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_CREATED);

        return apiResponse;
    }

    private synchronized void variationGrant() throws HttpException {
        var grantApplicationResource = ApiUrl.build(env, "variation/%s/grant/".formatted(application.getApplicationId())).toString();
        var grantVariationBuilder = new GenericBuilder().withId(application.getApplicationId());
        apiResponse = RestUtils.put(grantVariationBuilder, grantApplicationResource, header());

        Utils.checkHTTPStatusCode(apiResponse, HttpStatus.SC_OK);
    }

    public synchronized void refuse(String applicationId) throws HttpException {
        var grantApplicationResource = ApiUrl.build(env, "application/%s/refuse/".formatted(applicationId)).toString();
        var grantApplication = new GrantApplicationBuilder()
                .withId(applicationId)
                .withCaseworkerNotes("This notes are from the API");
        apiResponse = RestUtils.put(grantApplication, grantApplicationResource, header());

        if (apiResponse.extract().statusCode() != HttpStatus.SC_OK) {
            LOGGER.info(apiResponse.extract().statusCode());
            LOGGER.info(apiResponse.extract().response().asString());
            throw new HttpException("Invalid response code: " + apiResponse.extract().statusCode());
        } else if (apiResponse.extract().response().asString().contains("fee")) {
            this.feeId = apiResponse.extract().response().jsonPath().getInt("id.fee");
        }
    }

    public synchronized void withdraw(String applicationId) throws HttpException {
        var grantApplicationResource = ApiUrl.build(env, "application/%s/withdraw/".formatted(applicationId)).toString();
        var grantApplication = new GrantApplicationBuilder()
                .withId(applicationId)
                .withReason("reg_in_error");
        apiResponse = RestUtils.put(grantApplication, grantApplicationResource, header());

        if (apiResponse.extract().statusCode() != HttpStatus.SC_OK) {
            LOGGER.info(apiResponse.extract().statusCode());
            LOGGER.info(apiResponse.extract().response().asString());
            throw new HttpException("Invalid response code: " + apiResponse.extract().statusCode());
        } else if (apiResponse.extract().response().asString().contains("fee")) {
            this.feeId = apiResponse.extract().response().jsonPath().getInt("id.fee");
        }
    }
}