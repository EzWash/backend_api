package com.ezwash.backend.resource.business;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCommentResource {
    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Integer qualification;

    private Long customer_id;
    private String first_name;
    private String last_name;

    public String getFirst_name() {
        return first_name;
    }

    public SaveCommentResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public SaveCommentResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public SaveCommentResource setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public SaveCommentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getQualification() {
        return qualification;
    }

    public SaveCommentResource setQualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }
}
