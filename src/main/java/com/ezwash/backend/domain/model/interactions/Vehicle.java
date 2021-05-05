package com.ezwash.backend.domain.model.interactions;

import javax.persistence.*;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private String registration_plate;
    //ManyToOne user
    //ManyToOne location

}
