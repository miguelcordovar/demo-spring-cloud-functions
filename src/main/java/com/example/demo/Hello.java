package com.example.demo;

import com.example.demo.model.Greeting;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
@Slf4j
public class Hello implements Function<Mono<User>, Mono<Greeting>> {

    @Autowired
    RestTemplate restTemplate;


    public Mono<Greeting> apply(Mono<User> mono) {

        Todo hello = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", Todo.class);

        log.info(hello.toString());

        return mono.map(user -> new Greeting("Hello, " + user.getName() + "!"));
    }
}
