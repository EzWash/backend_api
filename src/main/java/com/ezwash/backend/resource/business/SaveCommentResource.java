package com.ezwash.backend.resource.business;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCommentResource {
    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Integer qualification;

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
