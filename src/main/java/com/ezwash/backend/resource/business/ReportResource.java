package com.ezwash.backend.resource.business;

public class ReportResource {

    private Long id;
    private String description;



    public String getDescription() {
        return description;
    }

    public ReportResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ReportResource setId(Long id) {
        this.id = id;
        return this;
    }
}
