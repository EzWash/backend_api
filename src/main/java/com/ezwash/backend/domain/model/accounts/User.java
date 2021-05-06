package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.geographic.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table (name = "users")
public class User extends Profile {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @Column(unique = true)
    @NotBlank
    private String password;

    //ManytoOne a location
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonIgnore
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "carwash_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "carwash_id"))
    private List<CarWash> likedCarwashes;

    //OneToMany cards

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public User setLocation(Location location) {
        this.location = location;
        return this;
    }
}
