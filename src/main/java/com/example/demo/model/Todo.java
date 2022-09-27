package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
    private String userId;
    private String id;
    private String title;
    private boolean completed;
}
