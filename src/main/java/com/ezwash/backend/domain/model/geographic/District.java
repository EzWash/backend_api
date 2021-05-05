package com.ezwash.backend.domain.model.geographic;

import javax.persistence.*;

@Entity
@Table(name="districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //ManyToOne province
    //OneToMany location

}
