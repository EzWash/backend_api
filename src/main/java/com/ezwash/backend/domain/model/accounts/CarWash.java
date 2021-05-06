package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.AuditModel;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.model.geographic.Location;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table (name="carwashes")
public class CarWash extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String name_owner;

    @Column(columnDefinition = "integer default 0")
    private Integer qualification;

    @Column(columnDefinition = "integer default 1")
    private Integer available;

    //OneToOne location
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    //OneToMany service
    @OneToMany(mappedBy = "carWash")
    private List<Service> serviceList;

    //OneToMany staff
    @OneToMany(mappedBy = "carWash")
    private List<Staff> staffList;

    //OneToMany userroles

    //OneToMany comments
    @OneToMany(mappedBy = "carWash")
    private List<Comment> commentList;

    //ManyToMany users_liked
    @ManyToMany(mappedBy = "likedCarwashes")
    private List<User> likes;

    public Long getId() {
        return id;
    }

    public CarWash setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarWash setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarWash setName(String name) {
        this.name = name;
        return this;
    }

    public String getName_owner() {
        return name_owner;
    }

    public CarWash setName_owner(String name_owner) {
        this.name_owner = name_owner;
        return this;
    }

    public int getQualification() {
        return qualification;
    }

    public CarWash setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public CarWash setAvailable(int available) {
        this.available = available;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public CarWash setLocation(Location location) {
        this.location = location;
        return this;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public CarWash setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public CarWash setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
        return this;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public CarWash setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        return this;
    }

    public List<User> getLikes() {
        return likes;
    }

    public CarWash setLikes(List<User> likes) {
        this.likes = likes;
        return this;
    }
}
