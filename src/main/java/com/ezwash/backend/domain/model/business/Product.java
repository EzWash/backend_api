package com.ezwash.backend.domain.model.business;

import javax.persistence.*;

@Entity
@Table (name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private String supplier;
    //OneToMany product_services
}
