package com.example.demo.domain.model.security.decrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecryptRq {
    private String ciphertext;
    private String alg;
    private String kid;
}
