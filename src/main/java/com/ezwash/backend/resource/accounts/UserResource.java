package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

public class UserResource extends ProfileResource {
    private String username;
    private String password;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public UserResource setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
