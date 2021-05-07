package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;

import javax.validation.constraints.NotNull;

public class SaveStaffResource extends SaveProfileResource {
    @NotNull
    private CarWash carWash;

    public SaveStaffResource setCarWash(CarWash carWash){
        this.carWash=carWash;
        return this;
    }

    public CarWash getCarWash(){return carWash;}

}
