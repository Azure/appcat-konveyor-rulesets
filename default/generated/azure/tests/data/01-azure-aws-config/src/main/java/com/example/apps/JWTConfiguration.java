package com.example.apps;

import com.example.AprendeFacilBack.Web.Config.AWSSecretManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.util.Map;

@Configuration
public class JWTConfiguration {

    private final AWSSecretManager awsSecretManager;

    public JWTConfiguration(AWSSecretManager awsSecretManager) {
        this.awsSecretManager = awsSecretManager;
    }

    @Bean
    public Map<String, String> jwtKeys() {
        // Obtener las claves del AWS Secrets Manager
        String secretName = "my-jwt-keys"; // Reemplaza con el nombre de tu secreto
        return awsSecretManager.getSecret(secretName);
    }

    @Bean
    @Primary
    public JWTServiceImp customJWTServiceImp(Map<String, String> jwtKeys) {
        return new JWTServiceImp(jwtKeys);
    }
}
