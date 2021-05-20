package com.ezwash.backend.resource.accounts;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SaveProfileResource {
    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String phone_number;

    @NotNull
    private String gender;

    public String getFirst_name() {
        return first_name;
    }

    public SaveProfileResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public SaveProfileResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveProfileResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public SaveProfileResource setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
