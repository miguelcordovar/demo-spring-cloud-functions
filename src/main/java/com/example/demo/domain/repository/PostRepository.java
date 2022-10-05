package com.example.demo.domain.repository;

import com.example.demo.domain.model.post.Post;

import java.util.List;

public interface PostRepository {
    List<Post> listAll();
}
