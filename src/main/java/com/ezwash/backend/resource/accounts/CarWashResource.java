package com.ezwash.backend.resource.accounts;

import com.ezwash.backend.domain.model.geographic.Location;

import java.util.Date;

public class CarWashResource {
    private Long id;
    private String email;
    private String description;
    private Date birth_date;
    private String name;
    private String name_owner;
    private String phone_number;
    private String ruc;
    private Integer qualification;
    private Integer available;
    private Location location;
    private String image;

    public String getImage() {
        return image;
    }

    public CarWashResource setImage(String image) {
        this.image = image;
        return this;
    }

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

    public Long getId() {
        return id;
    }

    public CarWashResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CarWashResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public CarWashResource setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public CarWashResource setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getRuc() {
        return ruc;
    }

    public CarWashResource setRuc(String ruc) {
        this.ruc = ruc;
        return this;
    }

    public CarWashResource setQualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }

    public CarWashResource setAvailable(Integer available) {
        this.available = available;
        return this;
    }
}
