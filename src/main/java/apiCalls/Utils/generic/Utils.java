package apiCalls.Utils.generic;

import com.typesafe.config.Config;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.ws.http.HTTPException;
import java.net.URL;

public class Utils {
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    public static Config config = new activesupport.config.Configuration("qa").getConfig();

    public static void checkHTTPStatusCode(ValidatableResponse apiResponse, int scCreated) {
        if (apiResponse.extract().statusCode() != scCreated) {
            LOGGER.info("ERROR CODE: ".concat(Integer.toString(apiResponse.extract().statusCode())));
            LOGGER.info("RESPONSE MESSAGE: ".concat(apiResponse.extract().response().asString()));
            throw new HTTPException(apiResponse.extract().statusCode());
        }
    }

    public static String removeLastSlash(URL url) {
        return removeLastSlash(url.toString());
    }

    public static String removeLastSlash(String url) {
        if (url.endsWith("/")) {
            int size = url.length();
            return url.substring(0, size - 1);
        }

        return url;
    }
}
