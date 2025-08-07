package com.senibo.blogApi.controller;

import com.senibo.blogApi.dto.ApiResponse;
import com.senibo.blogApi.dto.PostRequest;
import com.senibo.blogApi.dto.PostResponse;
import com.senibo.blogApi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Posts", description = "Manage blog posts: create, read, update, delete")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @Operation(summary = "Get all posts", description = "Retrieve a list of all blog posts. Supports optional search by title or content.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Posts retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class)))
    @GetMapping(produces = {"application/json", "application/xml"})
    public ApiResponse<List<PostResponse>> getAllPosts(
            @RequestParam(name = "searchTerm", required = false)
            @Parameter(description = "Optional search keyword") String searchTerm) {

        List<PostResponse> response = postService.getAllPosts(searchTerm);

        return ApiResponse.success(response, "All posts retrieved successfully");
    }

    @Operation(summary = "Get a post by ID", description = "Retrieve a single post using its UUID.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Post found", content = @Content(schema = @Schema(implementation = PostResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Post not found")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ApiResponse<PostResponse> getPostById(
            @PathVariable("id") @Parameter(description = "UUID of the post") String id) {
        PostResponse response = postService.getPostById(id);

        return ApiResponse.success(response, "Post retrieved successfully");
    }


    @Operation(summary = "Create a new post", description = "Create and save a new blog post with a title, content, category, and tags.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Post created successfully", content = @Content(schema = @Schema(implementation = PostResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation error")
    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ApiResponse<PostResponse>> createBlogPost(
            @Valid @RequestBody @Parameter(description = "Post details") PostRequest postRequest) {

        ApiResponse<PostResponse> response = ApiResponse.success(postService.createPost(postRequest),
                                                                 "Blog post created successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a post", description = "Update an existing blog post identified by UUID.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Post updated successfully", content = @Content(schema = @Schema(implementation = PostResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Post not found")
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ApiResponse<PostResponse> updateBlogPost(
            @PathVariable("id") @Parameter(description = "UUID of the post to update") String id,
            @Valid @RequestBody @Parameter(description = "Updated post data")
            PostRequest postRequest) {
        return ApiResponse.success(postService.updatePost(id, postRequest),
                                   "Blog post updated successfully");

    }

    @Operation(summary = "Delete a post", description = "Delete a blog post permanently using its UUID.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Post deleted successfully")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Post not found")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBlogPost(
            @PathVariable("id") @Parameter(description = "UUID" + " of the post to delete")
            String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
