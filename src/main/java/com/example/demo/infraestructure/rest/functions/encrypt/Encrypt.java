package com.example.demo.infraestructure.rest.functions.encrypt;

import com.example.demo.application.command.EncryptMessageCommand;
import com.example.demo.application.commandBus.CommandBus;
import com.example.demo.domain.model.encrypt.Message;
import com.example.demo.domain.model.encrypt.EncryptedMessage;
import com.example.demo.infraestructure.rest.dto.encrypt.EncryptRequest;
import com.example.demo.infraestructure.rest.dto.encrypt.EncryptResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Encrypt implements Function<EncryptRequest, EncryptResponse> {

    private CommandBus commandBus;

    public Encrypt(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public EncryptResponse apply(EncryptRequest encryptRequest) {
        Message message = new Message();
        message.setApplication(encryptRequest.getApplication());
        message.setPlaintext(encryptRequest.getPlaintext());
        message.setAlg(encryptRequest.getAlg());
        message.setKid(encryptRequest.getKid());

        EncryptMessageCommand encryptMessageCommand = EncryptMessageCommand.Builder
            .getInstance()
            .message(message)
            .build();

        EncryptResponse encryptResponse = new EncryptResponse();

        try {
            EncryptedMessage encryptedMessage = commandBus.handle(encryptMessageCommand);
            encryptResponse.setCiphertext(encryptedMessage.getCiphertext());
            encryptedMessage.setTag(encryptedMessage.getTag());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encryptResponse;
    }
}
