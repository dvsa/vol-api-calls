package apiCalls.Utils.generic;

import activesupport.system.Properties;
import com.typesafe.config.Config;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvsa.testing.lib.url.utils.EnvironmentType;


public class Utils {
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    private static final EnvironmentType env = EnvironmentType.getEnum(Properties.get("env", true));
    public static Config config = new activesupport.config.Configuration(env.toString()).getConfig();

    public static void checkHTTPStatusCode(ValidatableResponse apiResponse, int scCreated) throws HttpException {
        if (apiResponse.extract().statusCode() != scCreated) {
            LOGGER.info("ERROR CODE: ".concat(Integer.toString(apiResponse.extract().statusCode())));
            LOGGER.info("RESPONSE MESSAGE: ".concat(apiResponse.extract().response().asString()));
            throw new HttpException("Invalid Response code: " + apiResponse.extract().statusCode());
        }
    }
}