package apiCalls.eupaActions.external;

import activesupport.aws.s3.SecretsManager;
import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.PersonModel;
import apiCalls.Utils.eupaBuilders.external.UserModel;
import apiCalls.Utils.eupaBuilders.external.UserRegistrationDetailsModel;
import apiCalls.actions.Token;
import apiCalls.enums.UserRoles;
import apiCalls.eupaActions.EupaBaseAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class UserAPIEupa extends EupaBaseAPI {

    private static String baseResource = "user/selfserve/";
    private static ValidatableResponse response;

    /**
     * This method registers a new user using the information specified in the userRegistrationDetailsModel
     * object that's passed in as an argument
     * @param userRegistrationDetailsModel This is a Model of the account details.
     * @return The user that was registered.
     */
    public static PersonModel register(@NotNull UserRegistrationDetailsModel userRegistrationDetailsModel) throws HttpException {
        Token accessToken = new Token();
        EupaBaseAPI.setHeader("Authorization", "Bearer " + accessToken.getToken(SecretsManager.getSecretValue("adminUser"), SecretsManager.getSecretValue("adminPassword"), UserRoles.INTERNAL.asString()));
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env", true)), baseResource + "register");
        int maxTries = 5;

        do {
            response = RestUtils.post(userRegistrationDetailsModel, String.valueOf(ApiUrl.getURL()), getHeaders());

            System.out.print("\n\nRESPONSE:\n\n");
            prettyPrintJson(response.extract().asString());

            if (response.extract().statusCode() == HttpStatus.SC_NOT_FOUND) {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            maxTries--;
        } while (response.extract().statusCode() == HttpStatus.SC_NOT_FOUND && maxTries > 0);

        response.statusCode(HttpStatus.SC_CREATED);

        String userID = response
                .extract()
                .jsonPath()
                .getString("id.user");

        userRegistrationDetailsModel.getContactDetailsModel().getPersonModel().setUserId(userID);

        return userRegistrationDetailsModel.getContactDetailsModel().getPersonModel();
    }

    /**
     * This retrieves VOL user information of the person that created the account along with other information
     * associated with that account.
     * @param personModel This is the person that created the account.
     * @return the information associated with the person passed in as an argument.
     */
    public static UserModel get(@NotNull PersonModel personModel) {
        EupaBaseAPI.getHeaders().put("x-pid", SecretsManager.getSecretValue("apiHeader"));
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env", true)), baseResource + personModel.getUserId());

        response = RestUtils.get(String.valueOf(ApiUrl.getURL()),
 getHeaders());

        System.out.print("\n\nRESPONSE:\n\n");
        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        UserModel user = response.extract().body().as(UserModel.class);
        updateHeader("x-pid", user.getPid());

        return user;
    }
}