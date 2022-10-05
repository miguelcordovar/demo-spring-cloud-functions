package com.example.demo.domain.service;

import com.example.demo.domain.model.security.decrypt.DecryptRq;
import com.example.demo.domain.model.security.decrypt.DecryptRs;
import com.example.demo.domain.model.security.encrypt.EncryptRq;
import com.example.demo.domain.model.security.encrypt.EncryptRs;
import com.example.demo.domain.model.security.Credentials;

public interface SecurityService {
    EncryptRs encrypt(Credentials credentials, EncryptRq encryptRq);
    DecryptRs decrypt(Credentials credentials, DecryptRq decryptRq);
}
