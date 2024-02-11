package com.amir.finalproject.model;

public enum ERole {
    ROLE_USER("user"),
    ROLE_MANAGER("manager"),
    ROLE_ADMIN("admin");
    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
