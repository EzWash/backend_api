package com.ezwash.backend.resource.business;

public class CommentResource {
    private Long id;
    private String description;
    private Integer qualification;
    private String first_name;
    private String last_name;
    private Long customer_id;
    private Long carwash_id;

    public Long getCarwash_id() {
        return carwash_id;
    }

    public CommentResource setCarwash_id(Long carwash_id) {
        this.carwash_id = carwash_id;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public CommentResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public CommentResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public CommentResource setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CommentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CommentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getQualification() {
        return qualification;
    }

    public CommentResource setQualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }
}
