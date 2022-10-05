package com.example.demo.infraestructure.configuration;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;

@Component
@Slf4j
public class AppConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {


        

        return builder
            .requestFactory(() -> new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                log.info("prepareConnection");
                log.info(connection.getClass().getSuperclass().getName());
                log.info(HttpsURLConnection.class.getName());
                if (connection.getClass().getSuperclass().getName().equalsIgnoreCase(HttpsURLConnection.class.getName())) {
                    log.info("connection");
                    ((HttpsURLConnection) connection).setHostnameVerifier((hostname, session) -> {
                        log.info("hostname: " +  hostname);
                        return true;
                    });
                }
                super.prepareConnection(connection, httpMethod);
            }
        }).build();
    }

    @Bean
    public SecretClient secretClient() {
        final String KEY_VAULT_URI = System.getenv("VTS_KEY_VAULT_URI");

        return new SecretClientBuilder()
            .vaultUrl(KEY_VAULT_URI)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }
}
