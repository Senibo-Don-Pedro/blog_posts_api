package com.senibo.blogApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API response wrapper")
public record ApiResponse<T>(
        @Schema(description = "Success status") boolean success,
        @Schema(description = "Response data") T data,
        @Schema(description = "Success message") String message,
        @Schema(description = "Error message") String error,
        @Schema(description = "List of validation or business errors") List<String> errors
) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message, null, null);
    }

    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(false, null, null, error, null);
    }

    public static <T> ApiResponse<T> error(String error, List<String> errors) {
        return new ApiResponse<>(false, null, null, error, errors);
    }
}
