package com.example.demo.application.command;


import com.example.demo.domain.model.decrypt.DecryptedMessage;
import com.example.demo.domain.model.decrypt.Message;
import com.example.demo.domain.model.security.Credentials;

public class DecryptMessageCommand implements Command<DecryptedMessage>{
    private final Message message;

    public DecryptMessageCommand(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public static class Builder {
        private Message message;

        public static DecryptMessageCommand.Builder getInstance() {
            return new Builder();
        }

        public DecryptMessageCommand.Builder message(Message message) {
            this.message = message;
            return this;
        }

        public DecryptMessageCommand build() {
            return new DecryptMessageCommand(this.message);
        }
    }
}
