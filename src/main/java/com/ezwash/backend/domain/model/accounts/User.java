package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table (name = "users")
public class User extends Profile {

    @NotNull
    @NotBlank
    private String password;

    //ManytoOne a location
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "carwash_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "carwash_id"))
    private List<CarWash> likedCarwashes;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    private List<Vehicle> vehicleList;

    @OneToMany(mappedBy = "user")
    private List<Contract> contractList;

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

    public List<CarWash> getLikedCarwashes() {
        return likedCarwashes;
    }

    public User setLikedCarwashes(List<CarWash> likedCarwashes) {
        this.likedCarwashes = likedCarwashes;
        return this;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public User setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        return this;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public User setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
        return this;
    }
    public List<Contract>getContractList(){return contractList;}

    public User setContractList(List<Contract>contractList){
        this.contractList=contractList;
        return this;
    }

    public boolean isCarWashAlready(CarWash carWash) {return this.getLikedCarwashes().contains(carWash);}

    public User addCarWashToLikedList(CarWash carWash){
        if(!this.isCarWashAlready(carWash))
            this.getLikedCarwashes().add(carWash);
        return this;
    }
    public User deleteCarWashFromLikedList(CarWash carWash) {
        if (this.isCarWashAlready(carWash)){
            this.getLikedCarwashes().remove(carWash);
            return this;
        }else return null;
    }
}
