package apiCalls.eupaActions.external;

import apiCalls.Utils.http.RestUtils;

import activesupport.number.Int;
import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.GenericModel;
import apiCalls.Utils.eupaBuilders.external.*;
import apiCalls.Utils.eupaBuilders.internal.ApplicationFeesModel;
import apiCalls.Utils.eupaBuilders.internal.FeesModel;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ApplicationAPIEupa extends EupaBaseAPI {
    private static ValidatableResponse response;

    public static StandardResponseModel create(@NotNull ApplicationModel applicationModel) {
        String baseResource = "application";
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), baseResource);

        response = RestUtils.post(applicationModel, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_CREATED);

        return response.extract().body().as(StandardResponseModel.class);
    }

    public static void businessType(@NotNull BusinessTypeModel businessType) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("organisation/%s/business-type/", businessType.getOrganisationId()));

        do {
            response = RestUtils.put(businessType, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            businessType.setVersion(businessType.getVersion() + 1);
        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && businessType.getVersion() < 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static StandardResponseModel businessDetails(@NotNull BusinessDetailsModel businessDetails) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("organisation/business-details/application/%s", businessDetails.getLicenceId()));

        do {
            response = RestUtils.put(businessDetails, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            businessDetails.setVersion(businessDetails.getVersion() + 1);
        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && businessDetails.getVersion() < 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(StandardResponseModel.class);
    }

    public static StandardResponseModel address(@NotNull ApplicationContactDetailsModel applicationContactDetails){
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/addresses/", applicationContactDetails.getApplicationNumber()));

        response = RestUtils.put(applicationContactDetails, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(StandardResponseModel.class);
    }

    public static void partner(PersonModel person) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/people/", person.getApplicationId()));

        response = RestUtils.post(person, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_CREATED);
    }

    public static void operatingCentre(OperatingCentreModel operatingCentre) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/operating-centre/", operatingCentre.getApplicationId()));
        int maxTries = 2;
        boolean firstEval = true;

        do {
            // Added wait as backend fails and returns with ERR_TA_GOODS intermittently due to a race condition with what we suspect is the address lookup service
            if (maxTries > 0 && !firstEval) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                maxTries--;
            }

            response = RestUtils.post(operatingCentre, String.valueOf(ApiUrl.getURL()), getHeaders());

            System.out.print("\n\nRESPONSE:\n\n");
            prettyPrintJson(response.extract().asString());

            firstEval = false;

        } while (response.extract().statusCode() == 400 && maxTries > 0);

        response.statusCode(HttpStatus.SC_CREATED);
    }

    public static void operatingCentreDetails(OperatingCentreDetailsModel operatingCentreDetailsModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/operating-centres", operatingCentreDetailsModel.getApplicationNumber()));
        int version = 1;

        do {
            if (operatingCentreDetailsModel.getVersion() == null)
                operatingCentreDetailsModel.setVersion(version);

            response = RestUtils.put(
                    operatingCentreDetailsModel,
                    String.valueOf(ApiUrl.getURL()),
                    getHeaders()
            );

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            operatingCentreDetailsModel.setVersion(++version);
        }
        while (response.extract().statusCode() == HttpStatus.SC_CONFLICT);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void financialEvidence(@NotNull FinancialEvidenceModel financialEvidence) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/financial-evidence", financialEvidence.getApplicationNumber()));
        int version = 1;

        do {
            if (financialEvidence.getVersion() == null)
                    financialEvidence.setVersion(version);

            response = RestUtils.put(financialEvidence, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            financialEvidence.setVersion(++version);
        } while(response.extract().statusCode() == HttpStatus.SC_CONFLICT);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static StandardResponseModel transportManager(@NotNull TransportManagerModel transportManager) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), "transport-manager/create-new-user/");

        response = RestUtils.post(transportManager, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_CREATED);

        return response.extract().as(StandardResponseModel.class);
    }

    public static void submitTransport(int applicationNumber, int transportManagerApplicationId){
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("transport-manager-application/%d/submit", applicationNumber));

        GenericModel genericModel = new GenericModel().withId(transportManagerApplicationId).withVersion(1);

        response = RestUtils.put(genericModel, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void psvVehicles(@NotNull VehiclesModel vehiclesModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/psv-vehicles", vehiclesModel.getApplicationNumber()));
        vehicle(ApiUrl.getURL(), vehiclesModel);
    }

    public static void goodsVehicles(@NotNull VehiclesModel vehiclesModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/vehicles", vehiclesModel.getApplicationNumber()));
        vehicle(ApiUrl.getURL(), vehiclesModel);
    }

    private static void vehicle(@NotNull java.net.URL url, @NotNull VehiclesModel vehiclesModel) {
        for(int version = 1; version <= 20; version++){
            if(vehiclesModel.getVersion() == null)
                vehiclesModel.setVersion(version);

            response = RestUtils.put(vehiclesModel, String.valueOf(url), getHeaders());

            vehiclesModel.setVersion(version);

            if (response.extract().statusCode() == HttpStatus.SC_OK) {
                break;
            }
        }

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void vehicleDeclaration(@NotNull VehicleDeclarationModel vehicleDeclarationModel){
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/vehicle-declaration", vehicleDeclarationModel.getId()));

        int version = 1;

        do {
            if (vehicleDeclarationModel.getVersion() == null) {
                vehicleDeclarationModel.setVersion(version);
            }

            response = RestUtils.put(vehicleDeclarationModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                return;

            vehicleDeclarationModel.setVersion(++version);

        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void financialHistory(@NotNull FinancialHistoryModel financialHistoryModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/financial-history", financialHistoryModel.getApplicationNumber()));
        int version = 1;

        do {
            if (financialHistoryModel.getVersion() == null) {
                financialHistoryModel.setVersion(version);
            }

            response = RestUtils.put(financialHistoryModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if(response.extract().statusCode() == HttpStatus.SC_OK)
                return;

            financialHistoryModel.setVersion(++version);

        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20 );

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void safetyAndCompliance(@NotNull SafetyModel safetyModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/safety", safetyModel.getApplicationNumber()));
        int version = 1;

        do {
            if (safetyModel.getVersion() == null) {
                safetyModel.setVersion(version);
            }

            if (safetyModel.getLicenceModel().getVersion() == null) {
                safetyModel.getLicenceModel().setVersion(Int.random(0, 20));
            }

            response = RestUtils.put(safetyModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                return;

            safetyModel.setVersion(++version);

        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void safetyInspector(@NotNull SafetyInspectorModel safetyInspectorModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/workshop", safetyInspectorModel.getApplicationNumber()));

        response = RestUtils.post(safetyInspectorModel, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_CREATED);
    }

    public static void convictions(@NotNull ConvictionsModel convictionsModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/previous-convictions", convictionsModel.getApplicationNumber()));
        int version = 1;

        do {
            if (convictionsModel.getVersion() == null)
                convictionsModel.setVersion(version);

            response = RestUtils.put(convictionsModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                return;

            convictionsModel.setVersion(++version);
        } while(response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static void licenceHistory(@NotNull LicenceHistoryModel licenceHistoryModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/licence-history", licenceHistoryModel.getApplicationNumber()));
        int version = 1;

        do {
            if (licenceHistoryModel.getVersion() == null)
                licenceHistoryModel.setVersion(version);

            response = RestUtils.put(licenceHistoryModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            licenceHistoryModel.setVersion(++version);
        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);
    }

    public static StandardResponseModel declaration(@NotNull DeclarationModel declarationModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/declaration/", declarationModel.getApplicationNumber()));
        int version = 1;

        do {
            if (declarationModel.getVersion() == null)
                declarationModel.setVersion(version);

            response = RestUtils.put(declarationModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            declarationModel.setVersion(++version);
        } while (response.extract().statusCode() != HttpStatus.SC_OK && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(StandardResponseModel.class);
    }

    public static StandardResponseModel submit(@NotNull GenericModel genericModel) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/submit", genericModel.getId()));
        int version = 1;

        do {
            if (genericModel.getVersion() == null)
                genericModel.setVersion(version);

            response = RestUtils.put(genericModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            if (response.extract().statusCode() == HttpStatus.SC_OK)
                break;

            genericModel.setVersion(++version);
        } while (response.extract().statusCode() == HttpStatus.SC_CONFLICT && version <= 20);

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(StandardResponseModel.class);
    }

    public static ApplicationFeesModel outstandingFees(@NotNull String applicationId) {
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), String.format("application/%s/outstanding-fees/", applicationId));

        response = RestUtils.get(String.valueOf(ApiUrl.getURL()),
 getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().as(ApplicationFeesModel.class);
    }

    public static void payOutstandingFee(@NotNull FeesModel fees){
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env")), "transaction/pay-outstanding-fees/");

        response = RestUtils.post(fees, String.valueOf(ApiUrl.getURL()), getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_CREATED);
    }
}