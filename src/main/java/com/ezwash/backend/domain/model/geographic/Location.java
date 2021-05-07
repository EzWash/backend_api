package com.ezwash.backend.domain.model.geographic;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String address;
    @NotNull
    private Double lattitude;
    @NotNull
    private Double longitude;

    //ManyToOne district
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false, updatable = false)
    @JsonIgnore
    private District district;

    //OneToMany vehicle
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonIgnore
    private List<Vehicle> vehicle;

    //OneToOne carwash
    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private CarWash carwash;

    //OneToMany user
    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public Location setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Location setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public Location setLattitude(Double lattitude) {
        this.lattitude = lattitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Location setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
