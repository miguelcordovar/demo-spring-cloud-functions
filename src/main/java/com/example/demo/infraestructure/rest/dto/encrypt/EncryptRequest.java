package com.example.demo.infraestructure.rest.dto.encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptRequest {
    private String application;
    private String plaintext;
    private String alg;
    private String kid;
}
