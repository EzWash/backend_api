package com.ezwash.backend.domain.model.interactions;

import com.ezwash.backend.domain.model.accounts.Customer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card_users")
public class CardUsers extends Card{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    public Customer getUser() {
        return customer;
    }

    public CardUsers setUser(Customer customer) {
        this.customer = customer;
        return this;
    }
}
