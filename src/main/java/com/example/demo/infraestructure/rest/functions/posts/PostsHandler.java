package com.example.demo.infraestructure.rest.functions.posts;

import com.example.demo.infraestructure.rest.dto.posts.PostsRequest;
import com.example.demo.infraestructure.rest.dto.posts.PostsResponse;
import com.example.demo.infraestructure.util.HttpHeader;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.MediaType;

import java.util.Optional;

import static com.example.demo.infraestructure.util.ExceptionHandler.handleException;

public class PostsHandler extends FunctionInvoker<PostsRequest, PostsResponse> {

    @FunctionName("posts")
    public HttpResponseMessage execute(
        @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<PostsRequest>> request, ExecutionContext context) {

        context.getLogger().info("Get All Posts");

        //Handle Request
        try {

            PostsResponse postsResponse = handleRequest(new PostsRequest(),context);

            //Response
            return request
                .createResponseBuilder(HttpStatus.OK)
                .body(postsResponse.getPosts())
                .header(HttpHeader.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        } catch (Exception ex) {
            return handleException(request, ex);
        }

    }
}
