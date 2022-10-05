package com.example.demo.infraestructure.rest.dto.posts;

import com.example.demo.domain.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {
    private List<Post> posts;
}
