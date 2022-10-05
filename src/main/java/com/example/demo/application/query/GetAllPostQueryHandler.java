package com.example.demo.application.query;

import com.example.demo.application.queryBus.QueryHandler;
import com.example.demo.application.usecase.GetAllPostsUseCase;
import com.example.demo.domain.model.post.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllPostQueryHandler implements QueryHandler<List<Post>, GetAllPostsQuery> {
    private GetAllPostsUseCase getAllPostsUseCase;

    public GetAllPostQueryHandler(GetAllPostsUseCase useCase) {
        this.getAllPostsUseCase = useCase;
    }

    @Override
    public List<Post> handle(GetAllPostsQuery query) throws Exception {
        return getAllPostsUseCase.handle();
    }
}
