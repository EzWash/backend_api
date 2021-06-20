package com.ezwash.backend.resource.interactions;


import com.ezwash.backend.domain.model.geographic.Location;

public class VehicleResource {

    private String model;
    private String brand;
    private String registration_plate;
    private Location location_id;
    private Long customer_Id;

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

    public Location getLocation_id() {
        return location_id;
    }

    public VehicleResource setLocation_id(Location location_id) {
        this.location_id = location_id;
        return this;
    }

    public Long getCustomer_Id() {
        return customer_Id;
    }

    public VehicleResource setCustomer_Id(Long customer_Id) {
        this.customer_Id = customer_Id;
        return this;
    }
}
