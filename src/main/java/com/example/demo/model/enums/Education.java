package com.example.demo.model.enums;

public enum Education {
    ASSISTANT("Ассистент"),
    SENIOR_LECTURER("Старший преподаватель"),
    DOCENT("Доцент"),
    PROFESSOR("Профессор");

    private String name;

    private Education(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
