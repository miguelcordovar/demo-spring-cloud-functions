package com.example.demo.application.command;

import com.example.demo.application.commandBus.CommandHandler;
import com.example.demo.application.usecase.EncryptMessageUseCase;
import com.example.demo.domain.model.encrypt.EncryptedMessage;
import org.springframework.stereotype.Component;

@Component
public class EncryptMessageCommandHandler implements CommandHandler<EncryptedMessage, EncryptMessageCommand> {
    private EncryptMessageUseCase useCase;

    public EncryptMessageCommandHandler(EncryptMessageUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public EncryptedMessage handle(EncryptMessageCommand command) throws Exception {
        return useCase.handle(command.getMessage());
    }
}
