package com.ezwash.backend.domain.model.business;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne carwashes
    //ManyToOne users
    private String description;
    private Integer qualification;
}
