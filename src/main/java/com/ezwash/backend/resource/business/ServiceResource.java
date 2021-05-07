package com.ezwash.backend.resource.business;

public class ServiceResource {
    private Long id;
    private String name;
    private String description;
    private Integer is_promotion;
    private Double price;

    public Long getId() {
        return id;
    }

    public ServiceResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getIs_promotion() {
        return is_promotion;
    }

    public ServiceResource setIs_promotion(Integer is_promotion) {
        this.is_promotion = is_promotion;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceResource setPrice(Double price) {
        this.price = price;
        return this;
    }
}
