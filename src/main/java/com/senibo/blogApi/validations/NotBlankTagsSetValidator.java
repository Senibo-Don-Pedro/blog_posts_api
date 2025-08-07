package com.senibo.blogApi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public class NotBlankTagsSetValidator implements ConstraintValidator<NotBlankTagsSet, Collection<String>> {
    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;
        // All elements must be non-blank
        for (String s : value) {
            if (s == null || s.trim().isEmpty()) return false;
        }
        return true;
    }
}
