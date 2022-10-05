package com.example.demo.application.usecase;



import com.example.demo.domain.model.decrypt.DecryptedMessage;
import com.example.demo.domain.model.decrypt.Message;
import com.example.demo.domain.model.security.Credentials;
import com.example.demo.domain.model.security.decrypt.DecryptRq;
import com.example.demo.domain.model.security.decrypt.DecryptRs;
import com.example.demo.domain.repository.SecretRepository;
import com.example.demo.domain.service.SecurityService;
import org.springframework.stereotype.Component;

@Component
public class DecryptMessageUseCase {
    private SecurityService securityService;
    private SecretRepository secretRepository;

    public DecryptMessageUseCase(SecurityService securityService, SecretRepository secretRepository) {
        this.securityService = securityService;
        this.secretRepository = secretRepository;
    }

    public DecryptedMessage handle(Message message) {
        final String PREFIX_USERNAME = System.getenv("VTS_PREFIX_USERNAME");
        final String COD_APP = message.getApplication();
        final String ENVIRONMENT = System.getenv("VTS_ENVIRONMENT");

        final String USER = PREFIX_USERNAME.concat(COD_APP).concat(ENVIRONMENT);
        final String PASSWORD = secretRepository.get(USER);

        final Credentials credentials = new Credentials(USER, PASSWORD);

        DecryptRq decryptRq = new DecryptRq(message.getCiphertext(), message.getAlg(), message.getKid());

        DecryptRs decryptRs = securityService.decrypt(credentials, decryptRq);

        DecryptedMessage decryptedMessage = new DecryptedMessage(decryptRs.getPlaintext());

        return decryptedMessage;
    }
}
