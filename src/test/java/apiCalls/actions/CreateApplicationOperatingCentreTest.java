package apiCalls.actions;

import apiCalls.Utils.volBuilders.OperatingCentreBuilder;
import apiCalls.enums.TrafficArea;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateApplicationOperatingCentreTest {
    private String previousEnvironment;

    @BeforeEach
    void preserveEnvironment() {
        previousEnvironment = System.getProperty("env");
        System.setProperty("env", "local");
    }

    @AfterEach
    void restoreEnvironment() {
        if (previousEnvironment == null) {
            System.clearProperty("env");
        } else {
            System.setProperty("env", previousEnvironment);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"goods", "public"})
    void localNorthernIrelandSetsNi01BeforeCreatingOperatingCentre(String operatorType) throws Exception {
        var application = new StubApplication("local", "Y");
        application.setOperatorType(operatorType);

        application.addOperatingCentre();

        assertEquals(List.of("version", "defaults", "create"), application.calls);
        assertEquals(Map.of(
                "id", "123",
                "version", 7,
                "partial", true,
                "partialAction", "add",
                "trafficArea", "N",
                "enforcementArea", "NI01"), application.defaults);
        assertTrue(application.defaultsResource.endsWith("/application/123/operating-centres"));
        assertTrue(application.createResource.endsWith("/application/123/operating-centre/"));
    }

    @Test
    void localGreatBritainKeepsExistingRequestSequence() throws Exception {
        var application = new StubApplication("local", "N");

        application.addOperatingCentre();

        assertEquals(List.of("create"), application.calls);
        assertNull(application.defaults);
    }

    @ParameterizedTest
    @ValueSource(strings = {"dev", "qa"})
    void hostedNorthernIrelandKeepsExistingRequestSequence(String environment) throws Exception {
        var application = new StubApplication(environment, "Y");

        application.addOperatingCentre();

        assertEquals(List.of("create"), application.calls);
        assertNull(application.defaults);
    }

    @Test
    void failedLocalReferenceSetupDoesNotAttemptOperatingCentreCreation() {
        var application = new StubApplication("local", "Y");
        application.defaultsStatus = 400;

        assertThrows(HttpException.class, application::addOperatingCentre);

        assertEquals(List.of("version", "defaults"), application.calls);
    }

    private static class StubApplication extends CreateApplication {
        private final List<String> calls = new ArrayList<>();
        private Map<String, Object> defaults;
        private String defaultsResource;
        private String createResource;
        private int defaultsStatus = 200;

        private StubApplication(String environment, String niFlag) {
            super(user(environment), new UserDetails());
            setApplicationId("123");
            setNiFlag(niFlag);
            if ("Y".equals(niFlag)) {
                setTrafficArea(TrafficArea.NORTHERN_IRELAND);
            }
        }

        private static RegisterUser user(String environment) {
            System.setProperty("env", environment);
            return new RegisterUser();
        }

        @Override
        public synchronized String fetchApplicationInformation(String id, String jsonPath, String fallback) {
            assertEquals("123", id);
            assertEquals("version", jsonPath);
            calls.add("version");
            return "7";
        }

        @Override
        protected ValidatableResponse updateOperatingCentreDefaults(Map<String, Object> payload, String resource) {
            calls.add("defaults");
            defaults = payload;
            defaultsResource = resource;
            return response(defaultsStatus);
        }

        @Override
        protected ValidatableResponse createOperatingCentre(OperatingCentreBuilder operatingCentre, String resource) {
            calls.add("create");
            createResource = resource;
            return response(201);
        }

        private static ValidatableResponse response(int status) {
            return new ResponseBuilder().setStatusCode(status).setBody("{}").build().then();
        }
    }
}
