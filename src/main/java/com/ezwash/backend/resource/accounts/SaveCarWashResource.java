package com.ezwash.backend.resource.accounts;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCarWashResource {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    private String birth_date;

    @NotNull
    @NotBlank
    @Column(length = 100)
    private String name;

    @NotNull
    @NotBlank
    private String name_owner;

    @NotNull
    @Column(length = 9)
    private String phone_number;

    @NotNull
    @Column(length = 11)
    private String ruc;

    @NotNull
    private Long location_id;

    @NotNull
    private String image;

    public String getImage() {
        return image;
    }

    public SaveCarWashResource setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveCarWashResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public SaveCarWashResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getName_owner() {
        return name_owner;
    }

    public SaveCarWashResource setName_owner(String name_owner) {
        this.name_owner = name_owner;
        return this;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public SaveCarWashResource setBirth_date(String birth_date) {
        this.birth_date = birth_date;
        return this;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public SaveCarWashResource setLocation_id(Long location_id) {
        this.location_id = location_id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveCarWashResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveCarWashResource setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public SaveCarWashResource setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getRuc() {
        return ruc;
    }

    public SaveCarWashResource setRuc(String ruc) {
        this.ruc = ruc;
        return this;
    }
}
