package com.example.demo.domain.model.encrypt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptedMessage {
    private String ciphertext;
    private String tag;
}
