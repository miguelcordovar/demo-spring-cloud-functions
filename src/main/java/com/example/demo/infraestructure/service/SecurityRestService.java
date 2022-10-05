package com.example.demo.infraestructure.service;

import com.example.demo.domain.model.security.Credentials;
import com.example.demo.domain.model.security.decrypt.DecryptRq;
import com.example.demo.domain.model.security.decrypt.DecryptRs;
import com.example.demo.domain.model.security.encrypt.EncryptRq;
import com.example.demo.domain.model.security.encrypt.EncryptRs;
import com.example.demo.domain.service.SecurityService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SecurityRestService implements SecurityService {
    private RestTemplate restTemplate;
    private String VTS_ENCRYPT_URI;
    private String VTS_DECRYPT_URI;

    public SecurityRestService(RestTemplate restTemplate) {
        this.VTS_ENCRYPT_URI = System.getenv("VTS_ENCRYPT_URI");
        this.VTS_DECRYPT_URI = System.getenv("VTS_DECRYPT_URI");
        this.restTemplate = restTemplate;
    }

    @Override
    public EncryptRs encrypt(Credentials credentials, EncryptRq encryptRq) {
        final String USER = credentials.getUsername();
        final String PASSWORD = credentials.getPassword();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(USER, PASSWORD);

        HttpEntity<EncryptRq> requestEntity = new HttpEntity<>(encryptRq, headers);

        ResponseEntity<EncryptRs> responseEntity = restTemplate.postForEntity(this.VTS_ENCRYPT_URI, requestEntity, EncryptRs.class);

        //TODO: Validate Exceptions
        //TODO: Validate Error Codes
        EncryptRs encryptRs = responseEntity.getBody();

        return encryptRs;
    }

    @Override
    public DecryptRs decrypt(Credentials credentials, DecryptRq decryptRq) {
        final String USER = credentials.getUsername();
        final String PASSWORD = credentials.getPassword();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(USER, PASSWORD);

        HttpEntity<DecryptRq> requestEntity = new HttpEntity<>(decryptRq, headers);

        ResponseEntity<DecryptRs> responseEntity = restTemplate.postForEntity(this.VTS_DECRYPT_URI, requestEntity, DecryptRs.class);

        //TODO: Validate Exceptions
        //TODO: Validate Error Codes
        DecryptRs decryptRs = responseEntity.getBody();

        return decryptRs;
    }
}
