package apiCalls.actions;

import io.restassured.builder.ResponseBuilder;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    private String previousEnvironment;

    @BeforeEach
    void setEnvironment() {
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

    @Test
    void acceptsSuccessfulAuthentication() throws Exception {
        StubToken token = new StubToken(success("header.payload.signature"));

        assertEquals("header.payload.signature", token.getToken("operator", "password", "selfserve"));
        assertEquals(1, token.requests);
    }

    @Test
    void rejectsFailedAuthenticationEvenWhenHttpStatusIsCreated() {
        StubToken token = new StubToken("""
                {"flags":{"isValid":false,"code":0,"identity":[],"messages":["Invalid credentials"]}}
                """);

        HttpException failure = assertThrows(HttpException.class,
                () -> token.getToken("operator", "do-not-log-this-password", "selfserve"));

        assertTrue(failure.getMessage().contains("Authentication failed"));
        assertTrue(failure.getMessage().contains("operator"));
        assertTrue(failure.getMessage().contains("selfserve"));
        assertFalse(failure.getMessage().contains("do-not-log-this-password"));
        assertEquals(1, token.requests);
    }

    @Test
    void requiresAnExplicitSuccessfulAuthenticationFlag() {
        StubToken token = new StubToken("""
                {"flags":{"identity":{"Token":{"access_token":"header.payload.signature"}}}}
                """);

        assertThrows(HttpException.class, () -> token.getToken("operator", "password", "selfserve"));
        assertEquals(1, token.requests);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"null", "opaque-token", "header..signature"})
    void rejectsMissingOrMalformedAccessTokens(String accessToken) {
        StubToken token = new StubToken(success(accessToken));

        HttpException failure = assertThrows(HttpException.class,
                () -> token.getToken("operator", "password", "selfserve"));

        assertTrue(failure.getMessage().contains("no valid JWT"));
        assertEquals(1, token.requests);
    }

    @Test
    void replacesTheCachedTokenWhenAdminRefreshIsRequested() throws Exception {
        StubToken token = new StubToken(success("old.payload.signature"), success("new.payload.signature"));

        assertEquals("old.payload.signature", token.generateAdminToken("admin", "password"));
        assertEquals("new.payload.signature", token.generateAdminToken("admin", "password"));
        assertEquals("new.payload.signature", token.getAdminToken());
        assertEquals(2, token.requests);
    }

    @Test
    void failedAdminRefreshDoesNotLeaveTheOldTokenCached() throws Exception {
        StubToken token = new StubToken(success("old.payload.signature"), """
                {"flags":{"isValid":false,"code":0,"identity":[]}}
                """);
        token.generateAdminToken("admin", "password");

        assertThrows(HttpException.class, () -> token.generateAdminToken("admin", "password"));
        assertNull(token.getAdminToken());
    }

    private static String success(String accessToken) {
        return """
                {"flags":{"isValid":true,"identity":{"Token":{"access_token":%s}}}}
                """.formatted(accessToken == null ? "null" : "\"" + accessToken + "\"");
    }

    private static class StubToken extends Token {
        private final Queue<String> responses = new ArrayDeque<>();
        private int requests;

        StubToken(String... responses) {
            this.responses.addAll(java.util.List.of(responses));
        }

        @Override
        protected ValidatableResponse requestToken(String resource) {
            requests++;
            return new ResponseBuilder()
                    .setStatusCode(201)
                    .setContentType("application/json")
                    .setBody(responses.remove())
                    .build().then();
        }
    }
}
