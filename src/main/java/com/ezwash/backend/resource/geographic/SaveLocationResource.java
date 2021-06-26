package com.ezwash.backend.resource.geographic;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveLocationResource {
    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private Double lattitude;

    @NotNull
    private Double longitude;

    public String getAddress() {
        return address;
    }

    public SaveLocationResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public SaveLocationResource setLattitude(Double lattitude) {
        this.lattitude = lattitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public SaveLocationResource setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
