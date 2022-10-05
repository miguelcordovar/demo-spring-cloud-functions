package com.example.demo.infraestructure.util;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ExceptionHandler {
    public static HttpResponseMessage handleException(HttpRequestMessage request, Exception exception) {

       if (exception instanceof IOException) {
           return request
               .createResponseBuilder(HttpStatus.BAD_REQUEST)
               .body(exception.getMessage())
               .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
               .build();
       } else if (exception instanceof RuntimeException) {
            return request
                .createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage())
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
       } else {
           return request
               .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(exception.getMessage())
               .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
               .build();
       }

    }
}
