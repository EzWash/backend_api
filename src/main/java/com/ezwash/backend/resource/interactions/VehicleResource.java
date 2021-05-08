package com.ezwash.backend.resource.interactions;


import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;

public class VehicleResource {

    private String model;
    private String brand;
    private String registration_plate;
    private Location location;
    private Long user_Id;

    public String getModel() {
        return model;
    }

    public VehicleResource setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public VehicleResource setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getRegistration_plate() {
        return registration_plate;
    }

    public VehicleResource setRegistration_plate(String registration_plate) {
        this.registration_plate = registration_plate;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public VehicleResource setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public VehicleResource setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
        return this;
    }
}
