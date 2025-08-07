package com.senibo.blogApi.service.serviceImpl;

import com.senibo.blogApi.dto.PostRequest;
import com.senibo.blogApi.dto.PostResponse;
import com.senibo.blogApi.exceptions.PostNotFoundException;
import com.senibo.blogApi.model.Post;
import com.senibo.blogApi.model.Tag;
import com.senibo.blogApi.repository.PostRepository;
import com.senibo.blogApi.repository.TagRepository;
import com.senibo.blogApi.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostServiceImpl(PostRepository postRepository,
                           TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<PostResponse> getAllPosts(String searchTerm) {

        List<Post> posts;

        if (searchTerm != null && !searchTerm.isBlank()) {
            log.info("Searching for posts with search term: {}", searchTerm);
            posts = postRepository.findBySearchTerm(searchTerm);
        } else {
            posts = postRepository.findAll();
            log.info("No search term provided, returning all posts");
        }

        return posts.stream().map(
                this::toPostResponse
        ).collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(String id) {
        Post singlePost = postRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new PostNotFoundException("Post with id: " + id + " not found")
        );

        return toPostResponse(singlePost);
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = new Post();

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCategory(postRequest.getCategory());

        // Convert string tag names to Tag entities
        Set<Tag> tags = getOrCreateTags(postRequest.getTags());
        post.setTags(tags);

        Post newPost = postRepository.save(post);

        log.info("New post has been saved successfully, {}", newPost);

        return toPostResponse(newPost);
    }

    @Override
    public PostResponse updatePost(String id, PostRequest postRequest) {
        Post singlePost = postRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new PostNotFoundException("Post with id: " + id + " not found")
        );

        singlePost.setTitle(postRequest.getTitle());
        singlePost.setContent(postRequest.getContent());
        singlePost.setCategory(postRequest.getCategory());
        
        // Fixed: Use the same tag handling logic as createPost
        Set<Tag> tags = getOrCreateTags(postRequest.getTags());
        singlePost.setTags(tags);

        Post post = postRepository.save(singlePost);

        return toPostResponse(post);
    }

    @Override
    public void deletePost(String id) {
        Post singlePost = postRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new PostNotFoundException("Post with id: " + id + " not found")
        );

        postRepository.delete(singlePost);
    }

    // Extract common tag handling logic into a separate method
    private Set<Tag> getOrCreateTags(Set<String> tagNames) {
        return tagNames.stream()
                      .map(tagName -> {
                          // Check if tag already exists in database
                          return tagRepository.findByName(tagName)
                                             .orElse(new Tag(tagName)); // Create new tag if it doesn't exist
                      }).collect(Collectors.toSet());
    }

    private PostResponse toPostResponse(Post singlePost) {
        return new PostResponse(
                singlePost.getId(),
                singlePost.getTitle(),
                singlePost.getContent(),
                singlePost.getCategory().getDisplayName(),
                singlePost.getTags().stream().map(
                        Tag::getName
                ).collect(Collectors.toSet()),
                singlePost.getCreatedAt().toString(),
                singlePost.getUpdatedAt().toString()
        );
    }
}
