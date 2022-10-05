package com.example.demo.application.queryBus;

import com.example.demo.application.query.Query;

public interface QueryHandler<T, U extends Query<T>> {
    T handle(U query) throws Exception;
}
