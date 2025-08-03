package com.senibo.blogApi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.senibo.blogApi.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JacksonXmlRootElement(localName = "postRequest")
public class PostRequest {

    @NotBlank(message = "Title is required")
    @JacksonXmlProperty(localName = "title")
    String title;

    @NotBlank(message = "Content is required")
    @Size(min = 8, message = "Content must have at least 8 characters")
    @JacksonXmlProperty(localName = "content")
    String content;

    @NotNull(message = "Category is required") // Use this for enums!
    @JacksonXmlProperty(localName = "category")
    Category category;

    @NotEmpty(message = "At least one tag is required")
    @JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
    List<String> tags;

    // Getters and setters

}


//package com.senibo.blogApi.dto;
//
//import com.fasterxml.jackson.annotation.JsonRootName;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
//import com.senibo.blogApi.model.Category;
//import jakarta.validation.constraints.*;
//
//import java.util.List;
//
//@JsonRootName("postRequest")
//@JacksonXmlRootElement(localName = "postRequest")
//public record PostRequest(
//
//        @NotBlank(message = "Title is required")
//        @JacksonXmlProperty(localName = "title")
//        String title,
//
//        @NotBlank(message = "Content is required")
//        @Size(min = 8,message = "Content must have at least 8 characters")
//        @JacksonXmlProperty(localName = "content")
//        String content,
//
//        @NotNull(message = "Category is required") // Use this for enums!
//        @JacksonXmlProperty(localName = "category")
//        Category category,
//
//        @NotEmpty(message = "At least one tag is required")
//        @JacksonXmlElementWrapper(localName = "tags")
//        @JacksonXmlProperty(localName = "tag")
//        List<String> tags
//) {
//}
