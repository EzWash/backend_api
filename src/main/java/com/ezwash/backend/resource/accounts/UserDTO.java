package com.ezwash.backend.resource.accounts;

public class UserDTO {
    private Long Id;
    private String email;
    private String name;

    public Long getId() {
        return Id;
    }

    public UserDTO setId(Long id) {
        Id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }
}
