package com.senibo.blogApi.controller;

import com.senibo.blogApi.dto.PostRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {


    @PostMapping(
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public PostRequest createBlogPost(@RequestBody PostRequest postRequest) {
        return postRequest;
    }
}
