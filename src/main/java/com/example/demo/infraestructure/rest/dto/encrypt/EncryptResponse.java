package com.example.demo.infraestructure.rest.dto.encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptResponse {
    private String ciphertext;
    private String tag;
}
