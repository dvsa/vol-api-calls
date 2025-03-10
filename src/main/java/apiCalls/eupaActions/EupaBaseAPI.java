package apiCalls.eupaActions;

import activesupport.system.Properties;
import activesupport.aws.s3.SecretsManager;
import apiCalls.actions.Token;
import apiCalls.enums.UserRoles;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.HttpException;
import org.dvsa.testing.lib.url.api.ApiUrl;

import org.dvsa.testing.lib.url.utils.EnvironmentType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class EupaBaseAPI {

    private static final Map<String, String> headers = new HashMap<>();
    protected static SecretsManager secrets;
    static {
         secrets = new SecretsManager();
        Token token = new Token();
        ApiUrl.build(EnvironmentType.getEnum(Properties.get("env", true)));
        try {
            setHeader( "Authorization", "Bearer " + token.getToken(SecretsManager.getSecretValue("adminUser"), SecretsManager.getSecretValue("adminPassword"), UserRoles.INTERNAL.asString()));
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getHeaders() {
        return headers;
    }

    public static void setHeader(@NotNull String header, @NotNull String value) {
        EupaBaseAPI.headers.put(header, value);
    }

    public static String getHeader(@NotNull String header) {
        return EupaBaseAPI.headers.get(header);
    }

    public static void updateHeader(@NotNull String header, @NotNull String value) {
        EupaBaseAPI.headers.replace(header, value);
    }

    protected static void prettyPrintJson(@NotNull String jsonString) {
        Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        try {
            System.out.println(gson.toJson(jp.parse(jsonString)));
        } catch (Exception ex) {}
    }
}
