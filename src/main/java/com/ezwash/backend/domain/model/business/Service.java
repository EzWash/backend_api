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
    private Integer is_promotion;
    private Double price;
    //ManyToOne carwashes
    //OneToMany contracts

}
