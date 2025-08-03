package com.senibo.blogApi.dto;

import java.util.List;
import java.util.UUID;

public record PostResponse(
        UUID id,
        String title,
        String content,
        String category,
        List<String> tags,
        String createdAt,
        String updatedAt
) {
}
