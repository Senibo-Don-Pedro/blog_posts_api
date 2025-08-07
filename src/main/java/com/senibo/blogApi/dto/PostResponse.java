package com.senibo.blogApi.dto;

import com.senibo.blogApi.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Schema(description = "Response payload representing a blog post")
public record PostResponse(
        @Schema(description = "UUID of the post", example = "4c5e-abc-xyz") UUID id,
        @Schema(description = "Post title") String title,
        @Schema(description = "Post content") String content,
        @Schema(description = "Display name of the category") String category,
        @Schema(description = "Associated tags") Set<String> tags,
        @Schema(description = "Date of creation") String createdAt,
        @Schema(description = "Date of last update") String updatedAt
) {
}
