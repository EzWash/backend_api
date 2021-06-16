package com.ezwash.backend.resource.business;

import com.ezwash.backend.domain.model.business.Contract;

import javax.validation.constraints.NotNull;

public class SaveReportResource {
    @NotNull
    private String description;

    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public SaveReportResource setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveReportResource setDescription(String description) {
        this.description = description;
        return this;
    }


}
