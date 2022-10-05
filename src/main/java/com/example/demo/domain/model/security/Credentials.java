package com.example.demo.domain.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    @Getter
    private String username;
    @Getter
    private String password;
}
