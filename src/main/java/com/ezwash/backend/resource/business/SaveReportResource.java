package com.ezwash.backend.resource.business;

import javax.validation.constraints.NotNull;

public class SaveReportResource {
    @NotNull
    private String description;
    @NotNull
    private String contract_id;

    public String getDescription() {
        return description;
    }

    public SaveReportResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContract_id() {
        return contract_id;
    }

    public SaveReportResource setContract_id(String contract_id) {
        this.contract_id = contract_id;
        return this;
    }
}
