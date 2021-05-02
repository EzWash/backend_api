package com.ezwash.backend.domain.model.accounts;

import javax.persistence.*;

@Entity
@Table(name="staffs")
public class Staff extends Profile {

    //ManyToOne CarWash

    public Staff(Long id, String name, String lastName, String email, String phoneNumber, String userName, String password) {
        super(id, name, lastName, email, phoneNumber, userName, password);
    }
}
