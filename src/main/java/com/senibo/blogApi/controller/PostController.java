package com.senibo.blogApi.controller;

import com.senibo.blogApi.dto.PostRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @PostMapping(consumes = {"application/xml", "application/json"},
            produces = {"application/xml", "application/json"}
    )
    public PostRequest createBlogPost(@RequestBody PostRequest postRequest) {
        return postRequest;
    }
}
