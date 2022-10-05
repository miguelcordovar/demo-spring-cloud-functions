package com.example.demo.domain.model.encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String application;
    private String plaintext;
    private String alg;
    private String kid;
}
