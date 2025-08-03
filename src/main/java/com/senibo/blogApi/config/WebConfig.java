package com.senibo.blogApi.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2XmlHttpMessageConverter xmlConverter =
                new MappingJackson2XmlHttpMessageConverter(new XmlMapper());
        xmlConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_XML,
                MediaType.TEXT_XML,
                MediaType.valueOf("application/xml;charset=UTF-8"),
                MediaType.valueOf("text/xml;charset=UTF-8")
        ));
        converters.add(0, xmlConverter); // Highest priority
        System.out.println(">>> XML Message Converter Registered!");
    }
}
