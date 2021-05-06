package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCarWashResource {
    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String name_owner;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "integer default 0")
    private int qualification;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "integer default 1")
    private int available;

    @NotNull
    private Long location;

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

    public int getQualification() {
        return qualification;
    }

    public SaveCarWashResource setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public SaveCarWashResource setAvailable(int available) {
        this.available = available;
        return this;
    }

    public Long getLocation() {
        return location;
    }

    public SaveCarWashResource setLocation(Long location) {
        this.location = location;
        return this;
    }
}
