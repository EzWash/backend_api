package com.ezwash.backend.domain.model.business;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //ManyToOne services
    //ManyToOne users
    private String state;
    private Date date;

    //ManyToOne staff
    //OneToOne report
}
