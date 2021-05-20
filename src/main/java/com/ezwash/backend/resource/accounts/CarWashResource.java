package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

public class CarWashResource {

    private String description;

    private String name;

    private String name_owner;

    private int qualification;

    private int available;

    private Location location;

    public String getDescription() {
        return description;
    }

    public CarWashResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarWashResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getName_owner() {
        return name_owner;
    }

    public CarWashResource setName_owner(String name_owner) {
        this.name_owner = name_owner;
        return this;
    }

    public int getQualification() {
        return qualification;
    }

    public CarWashResource setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public CarWashResource setAvailable(int available) {
        this.available = available;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public CarWashResource setLocation(Location location) {
        this.location = location;
        return this;
    }
}
