package com.example.demo.infraestructure.rest.functions.encrypt;

import com.example.demo.infraestructure.rest.dto.encrypt.EncryptRequest;
import com.example.demo.infraestructure.rest.dto.encrypt.EncryptResponse;
import com.example.demo.infraestructure.util.HttpHeader;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.MediaType;

import java.util.Optional;

import static com.example.demo.infraestructure.util.ExceptionHandler.handleException;

public class EncryptHandler extends FunctionInvoker<EncryptRequest, EncryptResponse> {

    @FunctionName("encrypt")
    public HttpResponseMessage execute(
        @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<EncryptRequest>> request, ExecutionContext context) {

        //Validate Request
        EncryptRequest encryptRequest = request.getBody()
            .orElse(new EncryptRequest());

        //encryptRequest.setApplication(request.getHeaders().getOrDefault("X-APP-CODE",null));

        context.getLogger().info("Encrypt Message: " + encryptRequest.getPlaintext());

        //Handle Request
        try {
            EncryptResponse encryptResponse = handleRequest(encryptRequest, context);

            //Response
            return request
                .createResponseBuilder(HttpStatus.OK)
                .body(encryptResponse)
                .header(HttpHeader.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        } catch (Exception ex) {
            return handleException(request, ex);
        }

    }

}
