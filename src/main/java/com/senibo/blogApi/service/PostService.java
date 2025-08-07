package com.senibo.blogApi.service;

import com.senibo.blogApi.dto.PostRequest;
import com.senibo.blogApi.dto.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPosts(String searchTerm);

    PostResponse getPostById(String id);

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(String id, PostRequest postRequest);

    void deletePost(String id);
}
