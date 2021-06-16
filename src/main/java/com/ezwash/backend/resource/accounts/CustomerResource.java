package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

public class CustomerResource extends ProfileResource {
    private String password;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public CustomerResource setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CustomerResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
