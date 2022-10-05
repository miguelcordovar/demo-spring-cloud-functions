package com.example.demo.application.commandBus;

import com.example.demo.application.command.Command;

public interface CommandBus {
    <T> T handle(Command<T> command) throws Exception;
}
