package apiCalls.Utils.http;

import apiCalls.Utils.generic.Headers;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtils {

    public static ValidatableResponse post(@NotNull Object requestBody, @NotNull String serviceEndPoint, @NotNull Map<String, String> headers) {
        return given()
                .urlEncodingEnabled(true)
                .filters(new ResponseLoggingFilter(), new ErrorLoggingFilter())
                .contentType(ContentType.JSON)
                .headers(Headers.forRequest(headers))
                .body(requestBody)
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .post(serviceEndPoint)
                .then();
    }

    public static ValidatableResponse put(@NotNull Object requestBody, @NotNull String serviceEndPoint, @NotNull Map<String, String> headers) {
        return given()
                .urlEncodingEnabled(true)
                .filter(new ErrorLoggingFilter())
                .contentType(ContentType.JSON)
                .headers(Headers.forRequest(headers))
                .body(requestBody)
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .put(serviceEndPoint)
                .then();
    }

    public static ValidatableResponse getWithQueryParams(@NotNull String serviceEndPoint, @Nullable Map<String, String> queryParam, @NotNull Map<String, String> headers) {
        return given().queryParams(queryParam)
                .urlEncodingEnabled(true)
                .filter(new ErrorLoggingFilter())
                .headers(Headers.forRequest(headers))
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .get(serviceEndPoint)
                .then();
    }

    public static ValidatableResponse get(@NotNull URL serviceEndPoint, @NotNull Map<String, String> headers) {
        return get(serviceEndPoint.toString(), headers);
    }

    public static ValidatableResponse get(@NotNull String serviceEndPoint, @NotNull Map<String, String> headers) {
        return given()
                .urlEncodingEnabled(true)
                .filter(new ErrorLoggingFilter())
                .headers(Headers.forRequest(headers))
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .get(serviceEndPoint)
                .then();
    }

    public static ValidatableResponse delete(@NotNull Object requestBody, @NotNull String serviceEndPoint, @NotNull Map<String, String> headers) {
        return given()
                .urlEncodingEnabled(true)
                .filter(new ErrorLoggingFilter())
                .contentType(ContentType.JSON)
                .headers(Headers.forRequest(headers))
                .body(requestBody)
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .delete(serviceEndPoint)
                .then();
    }

    public static ValidatableResponse getThroughProxy(@NotNull String proxyURI, @NotNull int proxyPort, @NotNull String serviceEndPoint, @NotNull Map<String, String> headers) {
        return given()
                .urlEncodingEnabled(true)
                .filter(new ErrorLoggingFilter())
                .headers(Headers.forRequest(headers))
                .proxy(proxyURI, proxyPort)
                .when()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation().allowAllHostnames()))
                .get(serviceEndPoint)
                .then();
    }
}