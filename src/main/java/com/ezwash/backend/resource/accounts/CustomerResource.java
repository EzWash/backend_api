package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

public class CustomerResource extends ProfileResource {
    private String password;
    private Long location_id;

    public Long getLocation_id() {
        return location_id;
    }

    public CustomerResource setLocation_id(Long location_id) {
        this.location_id = location_id;
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
