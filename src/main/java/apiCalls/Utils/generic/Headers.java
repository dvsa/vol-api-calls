package apiCalls.Utils.generic;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    private static final ThreadLocal<Map<String, String>> apiHeaderThreadLocal = ThreadLocal.withInitial(HashMap::new);

    private static String API_HEADER;

    public Map<String, String> getApiHeader() {
        return apiHeaderThreadLocal.get();
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