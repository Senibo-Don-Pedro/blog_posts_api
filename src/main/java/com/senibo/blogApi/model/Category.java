package com.senibo.blogApi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    TECHNOLOGY("Technology"),
    BUSINESS("Business"),
    SPORTS("Sports"),
    LIFESTYLE("Lifestyle"),
    HEALTH("Health"),
    TRAVEL("Travel"),
    FOOD("Food"),
    EDUCATION("Education"),
    ENTERTAINMENT("Entertainment"),
    SCIENCE("Science");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue // This makes Jackson use displayName in JSON
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.getDisplayName().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + displayName);
    }

    public static String getAllowedDisplayNames() {
        return java.util.Arrays.stream(Category.values())
                .map(Category::getDisplayName)
                .collect(java.util.stream.Collectors.joining(", "));
    }
}
