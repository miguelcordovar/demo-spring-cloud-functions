package com.example.demo.infraestructure.repository;

import com.azure.security.keyvault.secrets.SecretClient;
import com.example.demo.domain.repository.SecretRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SecretKeyVaultRepository implements SecretRepository {

    private SecretClient secretClient;

    public SecretKeyVaultRepository(SecretClient secretClient) {
        this.secretClient = secretClient;
    }

    @Override
    public String get(String key) {
        return secretClient.getSecret(key).getValue();
    }
}
