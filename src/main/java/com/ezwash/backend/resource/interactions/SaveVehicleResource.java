package com.ezwash.backend.resource.interactions;

import javax.validation.constraints.NotNull;

public class SaveVehicleResource {
    @NotNull
    private String model;
    @NotNull
    private String brand;
    @NotNull
    private String registration_plate;
    @NotNull
    private Long location_id;

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

    public Long getLocation_id() {
        return location_id;
    }

    public SaveVehicleResource setLocation_id(Long location_id) {
        this.location_id = location_id;
        return this;
    }


}
