package com.ezwash.backend.domain.model.interactions;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String number;

    //Aprender a ponerlo con Date
    @NotNull
    @NotBlank
    private String expiration;

    @NotNull
    @NotBlank
    private String cvc;

    public Long getId() {
        return id;
    }

    public Card setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Card setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getExpiration() {
        return expiration;
    }

    public Card setExpiration(String expiration) {
        this.expiration = expiration;
        return this;
    }

    public String getCvc() {
        return cvc;
    }

    public Card setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }
}
