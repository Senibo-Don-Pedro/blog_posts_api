package com.senibo.blogApi.dto;

import com.senibo.blogApi.model.Category;
import jakarta.validation.constraints.*;

import java.util.List;

public record PostRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Content is required")
        @Size(min = 8,message = "Content must have at least 8 characters")
        String content,

        @NotNull(message = "Category is required") // Use this for enums!
        Category category,

        @NotEmpty(message = "At least one tag is required")
        List<String> tags
) {
}
