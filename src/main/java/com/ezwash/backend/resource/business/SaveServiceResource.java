package com.ezwash.backend.resource.business;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveServiceResource {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer is_promotion;

    @NotNull
    private Double price;

    public String getName() {
        return name;
    }

    public SaveServiceResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveServiceResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getIs_promotion() {
        return is_promotion;
    }

    public SaveServiceResource setIs_promotion(Integer is_promotion) {
        this.is_promotion = is_promotion;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public SaveServiceResource setPrice(Double price) {
        this.price = price;
        return this;
    }
}
