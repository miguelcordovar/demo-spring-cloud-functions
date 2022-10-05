package com.example.demo.domain.model.security.encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptRs {
    private String ciphertext;
    private String tag;
}
