package com.ezwash.backend.domain.model.accounts;

import javax.persistence.*;

@Entity
@Table(name="staff")
public class Staff extends Profile {

    //ManyToOne CarWash
    //OneToMany contract
    //OneToMany user Roles
    public Staff(Long id, String name, String lastName, String email, String phoneNumber, String userName, String password) {
        super(id, name, lastName, email, phoneNumber, userName, password);
    }
}
