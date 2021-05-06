package com.ezwash.backend.resource.interactions;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;

import javax.validation.constraints.NotNull;

public class SaveVehicleResource {
    @NotNull
    private String model;
    @NotNull
    private String brand;
    @NotNull
    private String registration_plate;
    @NotNull
    private Long location;
    @NotNull
    private Long user;

    public String getModel() {
        return model;
    }

    public SaveVehicleResource setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public SaveVehicleResource setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getRegistration_plate() {
        return registration_plate;
    }

    public SaveVehicleResource setRegistration_plate(String registration_plate) {
        this.registration_plate = registration_plate;
        return this;
    }

    public Long getLocation() {
        return location;
    }

    public SaveVehicleResource setLocation(Long location) {
        this.location = location;
        return this;
    }

    public Long getUser() {
        return user;
    }

    public SaveVehicleResource setUser(Long user) {
        this.user = user;
        return this;
    }
}
