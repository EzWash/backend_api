package com.ezwash.backend.resource.accounts;


import com.ezwash.backend.domain.model.accounts.CarWash;

public class StaffResource extends ProfileResource {
    private CarWash carWash;

    public CarWash getCarWash(){return carWash;}

    public StaffResource setCarWash(CarWash carWash){
        this.carWash=carWash;
        return this;
    }

}
