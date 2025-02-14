package apiCalls.eupaActions.external.permits;

import apiCalls.Utils.http.RestUtils;

import activesupport.system.Properties;
import apiCalls.Utils.eupaBuilders.external.permits.TypeModel;
import apiCalls.Utils.eupaBuilders.external.permits.window.WindowsModel;
import apiCalls.eupaActions.EupaBaseAPI;
import apiCalls.eupaActions.util.Utils;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.dvsa.testing.lib.url.api.URL;
import org.dvsa.testing.lib.url.utils.EnvironmentType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OpenWindowsAPIEupa extends EupaBaseAPI {

    private static final String baseResource = "permits/open-windows/";
    private static ValidatableResponse response;

    public static WindowsModel openWindows(TypeModel type) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:mm:ss");
        String path = "?dto=Dvsa%5COlcs%5CTransfer%5CQuery%5CPermits%5COpenWindows&" + String.format(
                "permitType=%d&currentDateTime=%s",
                type.getId(),
                LocalDateTime.now().format(format)
        );
        URL.build(EnvironmentType.getEnum(Properties.get("env")), baseResource + path);

        response = RestUtils.get(Utils.removeLastSlash(URL.getURL()), getHeaders());

        prettyPrintJson(response.extract().asString());

        response.statusCode(HttpStatus.SC_OK);

        return response.extract().body().as(WindowsModel.class);
    }

}
