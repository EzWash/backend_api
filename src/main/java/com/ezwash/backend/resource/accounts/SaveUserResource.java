package com.ezwash.backend.resource.accounts;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveUserResource extends SaveProfileResource {

    @NotNull
    @NotBlank
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

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
