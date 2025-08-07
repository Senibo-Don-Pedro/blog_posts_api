package com.senibo.blogApi.exceptions;

import com.senibo.blogApi.dto.ApiResponse;
import com.senibo.blogApi.model.Category;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage).toList();


        return ResponseEntity.badRequest().
                body(ApiResponse.error("You have the following validation errors", errors));


    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        String errorMessage = "Invalid category. Allowed values: " + Category.getAllowedDisplayNames();

        ApiResponse<Object> response = ApiResponse.error(errorMessage);

        return ResponseEntity.badRequest().body(response);
    }



    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        String supportedTypes = e.getSupportedMediaTypes()
                .stream()
                .map(MimeType::toString)
                .toList()
                .toString(); // e.g. [application/json, application/xml]

        String message = "Unsupported media type. Please use one of: " + supportedTypes;

        ApiResponse<Object> response = ApiResponse.error(message);

        return ResponseEntity.status(415).body(response); // 415 Unsupported Media Type
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage())) ;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        if (e.getMessage() != null && e.getMessage().startsWith("Invalid UUID string")) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid post ID format. Must be a valid UUID."));
        }

        // Fallback for other illegal arguments (optional)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ApiResponse.error("An unexpected error occurred: " + e.getMessage()));
    }



    // Add this helper method
    private String getRootCauseMessage(Throwable throwable) {
        Throwable root = throwable;
        while (root.getCause() != null) {
            root = root.getCause();
        }
        return root.getMessage();
    }

}
