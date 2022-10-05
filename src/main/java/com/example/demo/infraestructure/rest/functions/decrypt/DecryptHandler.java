package com.example.demo.infraestructure.rest.functions.decrypt;

import com.example.demo.infraestructure.rest.dto.decrypt.DecryptRequest;
import com.example.demo.infraestructure.rest.dto.decrypt.DecryptResponse;
import com.example.demo.infraestructure.util.HttpHeader;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.MediaType;

import java.util.Optional;

import static com.example.demo.infraestructure.util.ExceptionHandler.handleException;

public class DecryptHandler extends FunctionInvoker<DecryptRequest, DecryptResponse> {
    @FunctionName("decrypt")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<DecryptRequest>> request, ExecutionContext context) {

        //Validate Request
        DecryptRequest decryptRequest = request.getBody()
            .filter((r -> r.getCiphertext() != null))
            .orElse(new DecryptRequest());

        //decryptRequest.setApplication(request.getHeaders().getOrDefault("X-APP-CODE",null));

        context.getLogger().info("Decrypt Message: " + decryptRequest.getCiphertext());

        //Handle Request
        try {
            DecryptResponse decryptResponse = handleRequest(decryptRequest, context);

            //Response
            return request
                .createResponseBuilder(HttpStatus.OK)
                .body(decryptResponse)
                .header(HttpHeader.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        } catch (Exception ex) {
            return handleException(request, ex);
        }

    }
}
