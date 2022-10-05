package com.example.demo.application.commandBus;

import com.example.demo.application.command.Command;

public interface CommandHandler<T, U extends Command<T>> {
    T handle(U command) throws Exception;
}
