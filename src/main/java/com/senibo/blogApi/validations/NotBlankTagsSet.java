package com.senibo.blogApi.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {NotBlankTagsSetValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankTagsSet {
    String message() default "At least one tag is required and must not contain blank values";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
