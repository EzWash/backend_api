package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.AuditModel;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table (name="carwashes")
public class CarWash extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    private Date birth_date;

    @NotNull
    @NotBlank
    @Column(length = 100)
    private String name;

    @NotNull
    @NotBlank
    private String name_owner;

    @NotNull
    @Column(length = 9)
    private String phone_number;

    @NotNull
    @Column(length = 11)
    private String ruc;

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

    @OneToMany(mappedBy = "carWash")
    private List<Contract> contractList;
    //OneToMany comments
    @OneToMany(mappedBy = "carWash")
    private List<Comment> commentList;

    //ManyToMany users_liked
    @ManyToMany(mappedBy = "likedCarwashes")
    private List<Customer> likes;

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

    public List<Customer> getLikes() {
        return likes;
    }

    public CarWash setLikes(List<Customer> likes) {
        this.likes = likes;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CarWash setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CarWash setPassword(String password) {
        this.password = password;
        return this;
    }

    public CarWash setQualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }

    public CarWash setAvailable(Integer available) {
        this.available = available;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public CarWash setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getRuc() {
        return ruc;
    }

    public CarWash setRuc(String ruc) {
        this.ruc = ruc;
        return this;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public CarWash setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }
}
