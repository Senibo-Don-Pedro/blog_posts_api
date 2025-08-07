package com.senibo.blogApi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.senibo.blogApi.model.Category;
import com.senibo.blogApi.validations.NotBlankTagsSet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@JacksonXmlRootElement(localName = "postRequest")
@Schema(description = "Request payload for creating or updating a post")
public class PostRequest {

    @NotBlank(message = "Title is required")
    @JacksonXmlProperty(localName = "title")
    @Schema(description = "Post title", example = "Top 5 Spring Boot Features", required = true)
    String title;

    @NotBlank(message = "Content is required")
    @Size(min = 8, message = "Content must have at least 8 characters")
    @JacksonXmlProperty(localName = "content")
    @Schema(description = "Post content", example = "Spring Boot simplifies Java backend development...", required = true)
    String content;

    @NotNull(message = "Category is required") // Use this for enums!
    @JacksonXmlProperty(localName = "category")
    @Schema(description = "Category name", example = "Technology", required = true)
    Category category;

    @NotBlankTagsSet
    @JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
    @Schema(description = "Set of tags associated with the post", example = "[\"Java\", \"Spring\"]", required = true)
    Set<String> tags;

    // Getters and setters

}
