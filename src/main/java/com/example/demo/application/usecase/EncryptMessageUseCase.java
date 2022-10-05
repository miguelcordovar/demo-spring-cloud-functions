package com.example.demo.application.usecase;

import com.example.demo.domain.model.encrypt.Message;
import com.example.demo.domain.model.encrypt.EncryptedMessage;
import com.example.demo.domain.model.security.Credentials;
import com.example.demo.domain.model.security.encrypt.EncryptRq;
import com.example.demo.domain.model.security.encrypt.EncryptRs;
import com.example.demo.domain.repository.SecretRepository;
import com.example.demo.domain.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EncryptMessageUseCase {
    private SecurityService securityService;
    private SecretRepository secretRepository;

    public EncryptMessageUseCase(SecurityService securityService, SecretRepository secretRepository) {
        this.securityService = securityService;
        this.secretRepository = secretRepository;
    }

    public EncryptedMessage handle(Message message) {
        final String PREFIX_USERNAME = System.getenv("VTS_PREFIX_USERNAME");
        final String COD_APP = message.getApplication();
        final String ENVIRONMENT = System.getenv("VTS_ENVIRONMENT");

        final String USER = PREFIX_USERNAME.concat(COD_APP).concat(ENVIRONMENT);
        final String PASSWORD = secretRepository.get(USER);

        log.info("USER: " + USER);

        final Credentials credentials = new Credentials(USER, PASSWORD);

        EncryptRq encryptRq = new EncryptRq(message.getPlaintext(), message.getAlg(), message.getKid());

        EncryptRs encryptRs = securityService.encrypt(credentials, encryptRq);

        EncryptedMessage encryptedMessage = new EncryptedMessage(encryptRs.getCiphertext(), encryptRs.getTag());

        return encryptedMessage;
    }
}
