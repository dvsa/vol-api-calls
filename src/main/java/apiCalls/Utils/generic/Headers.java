package apiCalls.Utils.generic;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    private static final ThreadLocal<Map<String, String>> apiHeaderThreadLocal = ThreadLocal.withInitial(HashMap::new);

    private static String API_HEADER;

    public Map<String, String> getApiHeader() {
        return apiHeaderThreadLocal.get();
    }

    public static void requireValidToken(String token) {
        if (token == null || !token.matches("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+")) {
            throw new IllegalStateException("Authentication did not return a valid JWT access token");
        }
    }

    public static Map<String, String> forRequest(Map<String, String> headers) {
        Map<String, String> snapshot = new HashMap<>(headers);
        snapshot.forEach((name, value) -> {
            if ("Authorization".equalsIgnoreCase(name)
                    && value != null && value.regionMatches(true, 0, "Bearer", 0, 6)) {
                if (!value.regionMatches(true, 0, "Bearer ", 0, 7)) {
                    throw new IllegalStateException("Cannot send a malformed bearer authorization header");
                }
                requireValidToken(value.substring(7));
            }
        });
        return snapshot;
    }

    public static String getAPI_HEADER() {
        return API_HEADER;
    }

    public static void setAPI_HEADER(String api_header) {
        API_HEADER = api_header;
    }

    public static void clear() {
        apiHeaderThreadLocal.remove();
    }
}