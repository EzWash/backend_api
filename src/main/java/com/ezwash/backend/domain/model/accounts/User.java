package com.ezwash.backend.domain.model.accounts;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User extends Profile {

    private String gender;

    //ManytoOne a location
    //OneToMany wallet

    public User(Long id, String name, String lastName, String email, String phoneNumber, String userName, String password, String gender) {
        super(id, name, lastName, email, phoneNumber, userName, password);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
