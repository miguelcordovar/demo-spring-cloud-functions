package com.example.demo.application.usecase;

import com.example.demo.domain.model.post.Post;
import com.example.demo.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllPostsUseCase {
    private PostRepository postRepository;

    public GetAllPostsUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> handle() {
        return postRepository.listAll();
    }
}
