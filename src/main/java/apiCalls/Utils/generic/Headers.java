package apiCalls.Utils.generic;

import java.util.HashMap;

public class Headers {

    private static String API_HEADER;

    public HashMap<String, String> apiHeader = new HashMap<>();

    public HashMap<String, String> getApiHeader() {
        return apiHeader;
    }

    public static String getAPI_HEADER() {
        return API_HEADER;
    }

    public static void setAPI_HEADER(String api_header) {
        API_HEADER = api_header;
    }
}