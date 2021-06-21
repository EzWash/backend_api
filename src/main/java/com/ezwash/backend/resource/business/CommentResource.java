package com.ezwash.backend.resource.business;

public class CommentResource {
    private Long id;
    private String description;
    private Integer qualification;


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
