package apiCalls.Utils.generic;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class SecretsManager {

    public static String secretsId = "OLCS-DEVAPPCI-DEVCI-BATCHTESTRUNNER-MAIN-APPLICATION";

    private static final Logger LOGGER = LogManager.getLogger(SecretsManager.class);

    public static AWSSecretsManager awsClientSetup(){
        Regions region = Regions.EU_WEST_1;
        return AWSSecretsManagerClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(region)
                .build();
    }

    public static String getSecret(String secretKey) {
        String secret = null;

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretsId);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = awsClientSetup().getSecretValue(getSecretValueRequest);

        } catch (ResourceNotFoundException e) {
            LOGGER.info("The requested secret " + secretKey + " was not found");
        } catch (InvalidRequestException e) {
            LOGGER.info("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            LOGGER.info("The request had invalid params: " + e.getMessage());
        }

        assert getSecretValueResult != null;

        if (getSecretValueResult != null && getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            JSONObject jsonObject = new JSONObject(secret);
            secret = jsonObject.getString(secretKey);
        }
        return secret;
    }

}