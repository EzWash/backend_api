package com.ezwash.backend.domain.model.interactions;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte image;

    //ManyToOne report

}
