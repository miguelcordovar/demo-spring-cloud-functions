package com.example.demo.infraestructure.util;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler {
    public static HttpResponseMessage handleException(HttpRequestMessage request, Exception exception) {

       if (exception instanceof IOException) {

           StringWriter sw = new StringWriter();
           PrintWriter pw = new PrintWriter(sw);
           exception.printStackTrace(pw);

           return request
               .createResponseBuilder(HttpStatus.BAD_REQUEST)
               .body("IOException --> " +  sw.toString())
               .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
               .build();
       } else if (exception instanceof RuntimeException) {

           StringWriter sw = new StringWriter();
           PrintWriter pw = new PrintWriter(sw);
           exception.printStackTrace(pw);

            return request
                .createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("RuntimeException --> " +  sw.toString())
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
       } else {
           StringWriter sw = new StringWriter();
           PrintWriter pw = new PrintWriter(sw);
           exception.printStackTrace(pw);

           return request
               .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(exception.getClass().getName() + "---> " + sw.toString())
               .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
               .build();
       }

    }
}
