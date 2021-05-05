package com.ezwash.backend.domain.model.geographic;

import javax.persistence.*;

@Entity
@Table(name="provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //OneToMany distric
    //ManyToOne department
}
