package com.example.demo.application.queryBus;

import com.example.demo.application.query.Query;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}
