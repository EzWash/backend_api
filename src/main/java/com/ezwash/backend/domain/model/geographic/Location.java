package com.ezwash.backend.domain.model.geographic;

import javax.persistence.*;

@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Double lattitude;
    private Double longitude;

    //ManyToOne district
    //OneToMany vehicle
    //OneToOne carwash
    //OneToMany user
}
