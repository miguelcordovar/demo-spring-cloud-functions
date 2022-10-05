package com.example.demo.application.command;

import com.example.demo.domain.model.encrypt.Message;
import com.example.demo.domain.model.encrypt.EncryptedMessage;

public class EncryptMessageCommand implements Command<EncryptedMessage> {
    private Message message;

    public EncryptMessageCommand(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public static class Builder {
        private Message message;

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public EncryptMessageCommand build() {
            return new EncryptMessageCommand(this.message);
        }
    }
}
