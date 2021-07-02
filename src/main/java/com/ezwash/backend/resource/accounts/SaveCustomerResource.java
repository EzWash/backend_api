package com.ezwash.backend.resource.accounts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCustomerResource extends SaveProfileResource {

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String birth_date;

    @NotNull
    private Long location;

    public Long getLocation() {
        return location;
    }

    public SaveCustomerResource setLocation(Long location) {
        this.location = location;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveCustomerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public SaveCustomerResource setBirth_date(String birth_date) {
        this.birth_date = birth_date;
        return this;
    }
}
