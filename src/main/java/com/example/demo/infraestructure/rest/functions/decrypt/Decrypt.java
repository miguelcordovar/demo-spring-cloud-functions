package com.example.demo.infraestructure.rest.functions.decrypt;

import com.example.demo.application.command.DecryptMessageCommand;
import com.example.demo.application.commandBus.CommandBus;
import com.example.demo.domain.model.decrypt.DecryptedMessage;
import com.example.demo.domain.model.decrypt.Message;
import com.example.demo.infraestructure.rest.dto.decrypt.DecryptRequest;
import com.example.demo.infraestructure.rest.dto.decrypt.DecryptResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Decrypt implements Function<DecryptRequest, DecryptResponse> {
    public CommandBus commandBus;

    public Decrypt(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public DecryptResponse apply(DecryptRequest decryptRequest) {
        Message message = new Message();
        message.setApplication(decryptRequest.getApplication());
        message.setCiphertext(decryptRequest.getCiphertext());
        message.setAlg(decryptRequest.getAlg());
        message.setKid(decryptRequest.getKid());

        DecryptMessageCommand decryptMessageCommand = DecryptMessageCommand.Builder
            .getInstance()
            .message(message)
            .build();

        DecryptResponse decryptResponse = new DecryptResponse();

        try {
            DecryptedMessage decryptedMessage = commandBus.handle(decryptMessageCommand);
            decryptResponse.setPlaintext(decryptedMessage.getPlaintext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return decryptResponse;
    }
}
