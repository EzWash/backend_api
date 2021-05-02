package com.ezwash.backend.domain.model.business;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    //ManyToOne CarWash
    //OneToOne location
}
