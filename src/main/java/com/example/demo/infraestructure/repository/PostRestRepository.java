package com.example.demo.infraestructure.repository;

import com.example.demo.domain.model.post.Post;
import com.example.demo.domain.repository.PostRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class PostRestRepository implements PostRepository {
    private RestTemplate restTemplate;

    public PostRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Post> listAll() {

        final String URL_POST_SERVICE = "https://jsonplaceholder.typicode.com/posts";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(URL_POST_SERVICE,
                                                                        HttpMethod.GET,
                                                                        request,
                                                                        Post[].class
                                                                    );

        Post[] postsResponse = responseEntity.getBody();

        return List.of(postsResponse);
    }
}
