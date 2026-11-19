package apiCalls.Utils.http;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RestUtilsTest {
    private HttpServer server;
    private String endpoint;
    private final AtomicInteger requests = new AtomicInteger();
    private final AtomicReference<String> authorization = new AtomicReference<>();

    @BeforeEach
    void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            requests.incrementAndGet();
            authorization.set(exchange.getRequestHeaders().getFirst("Authorization"));
            exchange.getRequestBody().close();
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        });
        server.start();
        endpoint = "http://127.0.0.1:" + server.getAddress().getPort() + "/";
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"POST", "PUT", "GET", "QUERY", "DELETE", "PROXY"})
    void malformedBearerTokenNeverReachesTheServer(String method) {
        Map<String, String> headers = Map.of("authorization", "Bearer null");

        assertThrows(IllegalStateException.class, () -> {
            switch (method) {
                case "POST" -> RestUtils.post(Map.of(), endpoint, headers);
                case "PUT" -> RestUtils.put(Map.of(), endpoint, headers);
                case "GET" -> RestUtils.get(endpoint, headers);
                case "QUERY" -> RestUtils.getWithQueryParams(endpoint, Map.of(), headers);
                case "DELETE" -> RestUtils.delete(Map.of(), endpoint, headers);
                case "PROXY" -> RestUtils.getThroughProxy("127.0.0.1", server.getAddress().getPort(), endpoint, headers);
                default -> throw new AssertionError(method);
            }
        });

        assertEquals(0, requests.get());
    }

    @Test
    void anonymousRegistrationRemainsUnauthenticated() {
        assertEquals(200, RestUtils.post(Map.of(), endpoint, Map.of()).extract().statusCode());
        assertEquals(1, requests.get());
        assertNull(authorization.get());
    }

    @Test
    void validBearerHeaderIsPreserved() {
        RestUtils.get(endpoint, Map.of("Authorization", "Bearer header.payload.signature"));

        assertEquals(1, requests.get());
        assertEquals("Bearer header.payload.signature", authorization.get());
    }

    @Test
    void basicAuthenticationIsNotTreatedAsAJwt() {
        RestUtils.get(endpoint, Map.of("Authorization", "Basic dXNlcjpwYXNz"));

        assertEquals(1, requests.get());
        assertEquals("Basic dXNlcjpwYXNz", authorization.get());
    }
}
