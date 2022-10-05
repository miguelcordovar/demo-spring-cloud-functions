package com.example.demo.infraestructure.commandBus;


import com.example.demo.application.command.Command;
import com.example.demo.application.commandBus.CommandBus;
import com.example.demo.application.commandBus.CommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class AppCommandBus implements CommandBus {

    private Map<Class, CommandHandler> handlers;

    public AppCommandBus(List<CommandHandler> commandHandlerImplementations) {
        this.handlers = new HashMap<>();

        commandHandlerImplementations.forEach(commandHandler -> {
            Class<?> queryClass = getCommandClass(commandHandler);
            handlers.put(queryClass, commandHandler);
        });
    }


    @Override
    public <T> T handle(Command<T> command) throws Exception {
        if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
        return  (T) handlers.get(command.getClass()).handle(command);
    }


    private Class<?> getCommandClass(CommandHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return getClass(commandInterface.getTypeName());
    }

    private Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }
}
