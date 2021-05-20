package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Profile extends AuditModel {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String first_name;

    @NotNull
    @NotBlank
    private String last_name;

    @NotNull
    @Column(unique = true)
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Column(unique = true, length = 9)
    private String phone_number;

    @NotNull
    @NotBlank
    @Column(length = 1)
    private String gender;

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public Profile setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Profile setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Profile setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Profile setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
