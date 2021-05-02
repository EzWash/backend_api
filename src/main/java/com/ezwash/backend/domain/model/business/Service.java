package com.ezwash.backend.domain.model.business;

import javax.persistence.*;

@Entity
@Table(name="services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    //ManyToOne reservations

    //ManyToMany carwash_services


}
