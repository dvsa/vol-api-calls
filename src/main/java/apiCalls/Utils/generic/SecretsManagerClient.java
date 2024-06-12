package apiCalls.Utils.generic;

import com.amazonaws.services.secretsmanager.AmazonSecretsManager;
import com.amazonaws.services.secretsmanager.AmazonSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResponse;
import com.amazonaws.services.secretsmanager.caching.SecretsManagerCache;
import com.amazonaws.services.secretsmanager.caching.SecretCacheConfiguration;
import com.amazonaws.services.secretsmanager.caching.SecretCacheItem;
import org.json.JSONObject;
import java.time.Duration;
import software.amazon.awssdk.regions.Region;

public class SecretsManagerClient {
    
    private SecretsManagerCache cache;

    private final String region = "eu-west-1";

    private final String secretId = "OLCS-DEVAPPCI-DEVCI-BATCHTESTRUNNER-MAIN-APPLICATION";

    private AmazonSecretsManager secretsManager;

    public SecretsManagerClient() {
        this.secretsManager = AmazonSecretsManagerClientBuilder.defaultClient();
        this.secretsManager.setRegion(Region.of(region));
        
        // Create a SecretsManagerCache configuration
        SecretCacheConfiguration cacheConfig = SecretCacheConfiguration.builder()
            .maxCacheSize(100) // Set the maximum cache size
            .cacheItemTTL(Duration.ofMinutes(30)) // Set the cache item TTL
            .build();
            }

        // Create the SecretsManagerCache
        SecretsManagerCache cache = new SecretsManagerCache(secretsManager, cacheConfig);

    }

    public String getSecret(String secretName) throws IllegalArgumentException {
        if (secretName == null || secretName.equals("")) {
            throw new IllegalArgumentException("Secret name cannot be null or empty");
        }

        SecretCacheItem secretCacheItem = cache.GetCachedSecret();

        String secret = null;
        if (secretCacheItem != null && secretCacheItem.getSecretString() != null) {
            secret = secretCacheItem.getSecretString();
        }
        else {
            GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretId);
            GetSecretValueResponse getSecretValueResponse = secretsManager.getSecretValue(getSecretValueRequest);
            secret = getSecretValueResponse.getSecretString();
            if (secret != null) {
                cache.PutSecret(secretId, secret);
            }
        }

        if (secret == null) {
            throw new IllegalStateException("Secret is null");
        }

        JSONObject jsonObject = new JSONObject(secret);
        secretValue = jsonObject.optString(secretName, null);

        if (secretValue == null) {
            throw new IllegalStateException("Secret name not found in the secret JSON object");
        }

        return secretValue;
    }

}


