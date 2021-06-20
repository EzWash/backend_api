package com.ezwash.backend.resource.geographic;

public class LocationResource {
    private Long id;
    private String address;
    private Double lattitude;
    private Double longitude;

    public Long getId() {
        return id;
    }

    public LocationResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LocationResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public LocationResource setLattitude(Double lattitude) {
        this.lattitude = lattitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocationResource setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
