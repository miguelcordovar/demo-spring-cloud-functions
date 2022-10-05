package com.example.demo.infraestructure.rest.dto.decrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecryptRequest {
    private String application;
    private String ciphertext;
    private String alg;
    private String kid;
}
