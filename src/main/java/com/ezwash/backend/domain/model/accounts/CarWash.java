package com.ezwash.backend.domain.model.accounts;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="carwashes")
public class CarWash {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //ManyToOne location
    //OneToMany staff
    @NotNull
    private String description;
    @NotNull
    private String name;
    @NotNull
    private String name_owner;



}
