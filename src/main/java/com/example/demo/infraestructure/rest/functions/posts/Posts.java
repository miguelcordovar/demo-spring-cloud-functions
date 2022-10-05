package com.example.demo.infraestructure.rest.functions.posts;

import com.example.demo.application.query.GetAllPostsQuery;
import com.example.demo.application.queryBus.QueryBus;
import com.example.demo.infraestructure.rest.dto.posts.PostsRequest;
import com.example.demo.infraestructure.rest.dto.posts.PostsResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Posts implements Function<PostsRequest, PostsResponse> {

    private QueryBus queryBus;

    public Posts(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @Override
    public PostsResponse apply(PostsRequest postsRequest) {

        GetAllPostsQuery getAllPostsQuery = GetAllPostsQuery.Builder
            .getInstance()
            .build();

        PostsResponse postsResponse = new PostsResponse();

        try {
            postsResponse.setPosts(queryBus.handle(getAllPostsQuery));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return postsResponse;
    }

}
