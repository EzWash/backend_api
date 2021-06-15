package com.ezwash.backend.resource.business;

public class ReportResource {

    private Long id;
    private String description;
    private String contract_id;

    public String getContract_id() {
        return contract_id;
    }

    public ReportResource setContract_id(String contract_id) {
        this.contract_id = contract_id;
        return this;
    }

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
