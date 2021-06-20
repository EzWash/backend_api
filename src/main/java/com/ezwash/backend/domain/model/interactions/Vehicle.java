package com.ezwash.backend.domain.model.interactions;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @NotBlank
    @Column(length = 7)
    private String registration_plate;

    //ManyToOne user
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    //ManyToOne location

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false, updatable = false)
    @JsonIgnore
    private Location location_id;

    public Long getId() {
        return id;
    }

    public Vehicle setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Vehicle setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Vehicle setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getRegistration_plate() {
        return registration_plate;
    }

    public Vehicle setRegistration_plate(String registration_plate) {
        this.registration_plate = registration_plate;
        return this;
    }

    public Customer getUser() {
        return customer;
    }

    public Vehicle setUser(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Location getLocation_id() {
        return location_id;
    }

    public Vehicle setLocation_id(Location location_id) {
        this.location_id = location_id;
        return this;
    }
}
