package com.example.demo.application.query;

import com.example.demo.domain.model.post.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllPostsQuery extends Query<List<Post>> {

    public GetAllPostsQuery() {
    }

    public static class Builder {
        public static GetAllPostsQuery.Builder getInstance() {
            return new GetAllPostsQuery.Builder();
        }

        public GetAllPostsQuery build() {
            return new GetAllPostsQuery();
        }
    }
}
