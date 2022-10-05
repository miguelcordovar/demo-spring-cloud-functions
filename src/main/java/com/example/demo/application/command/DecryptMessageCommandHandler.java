package com.example.demo.application.command;

import com.example.demo.application.commandBus.CommandHandler;
import com.example.demo.application.usecase.DecryptMessageUseCase;
import com.example.demo.domain.model.decrypt.DecryptedMessage;
import com.example.demo.domain.model.decrypt.Message;
import org.springframework.stereotype.Component;

@Component
public class DecryptMessageCommandHandler implements CommandHandler<DecryptedMessage, DecryptMessageCommand> {
    private DecryptMessageUseCase useCase;

    public DecryptMessageCommandHandler(DecryptMessageUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public DecryptedMessage handle(DecryptMessageCommand command) throws Exception {
        return useCase.handle(command.getMessage());
    }
}
