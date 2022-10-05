package com.example.demo.domain.model.security.encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptRq {
    private String plaintext;
    private String alg;
    private String kid;
}
