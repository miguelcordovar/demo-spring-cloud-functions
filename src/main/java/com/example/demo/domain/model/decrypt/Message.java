package com.example.demo.domain.model.decrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String application;
    private String ciphertext;
    private String alg;
    private String kid;
}
