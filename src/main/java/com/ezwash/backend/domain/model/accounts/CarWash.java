package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.model.geographic.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table (name="carwashes")
public class CarWash {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //OneToOne location
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    //OneToMany service
    @OneToMany(mappedBy = "carwash")
    private List<Service> serviceList;

    //OneToMany staff
    @OneToMany(mappedBy = "carwash")
    private List<Staff> staffList;

    //OneToMany userroles

    //OneToMany comments
    @OneToMany(mappedBy = "carwash")
    private List<Comment> commentList;

    //ManyToMany users_liked
    @ManyToMany(mappedBy = "likedCarwashes")
    private List<User> likes;

    @NotNull
    private String description;
    @NotNull
    private String name;
    @NotNull
    private String name_owner;
}
