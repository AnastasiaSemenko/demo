package com.example.demo.model.enums;

public enum Roles {
    ADMIN("Администратор"),
    TEACHER("Преподаватель"),
    STUDENT("Студент");

    private final String name;

    private Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
