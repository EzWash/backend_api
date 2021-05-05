package com.ezwash.backend.domain.model.business;

import javax.persistence.*;

@Entity
@Table(name="reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //OneToOne contract
    private String description;

    //OneToMany images
}
