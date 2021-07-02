package com.ezwash.backend.resource.accounts;

import javax.validation.constraints.NotNull;

public class SaveCarWashQualificationResource {
    @NotNull
    public Long qualification;

    public Long getQualification() {
        return qualification;
    }

    public SaveCarWashQualificationResource setQualification(Long qualification) {
        this.qualification = qualification;
        return this;
    }
}
