package com.ezwash.backend.resource;

import javax.validation.constraints.NotNull;

public class SaveUserResource extends SaveProfileResource {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Long location;

    public Long getLocation() {
        return location;
    }

    public SaveUserResource setLocation(Long location) {
        this.location = location;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SaveUserResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
