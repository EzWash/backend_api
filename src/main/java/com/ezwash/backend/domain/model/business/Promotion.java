package com.ezwash.backend.domain.model.business;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double discount;
    private String description;

    //ManyToOne carwwash

}
