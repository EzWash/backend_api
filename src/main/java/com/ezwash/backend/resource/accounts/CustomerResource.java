package com.ezwash.backend.resource.accounts;

import java.util.Date;

public class CustomerResource extends ProfileResource {
    private String password;
    private Date birth_date;
    private Long location_id;
    private Long cartId;

    public Long getCartId() {
        return cartId;
    }

    public CustomerResource setCartId(Long cartId) {
        this.cartId = cartId;
        return this;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public CustomerResource setLocation_id(Long location_id) {
        this.location_id = location_id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CustomerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public CustomerResource setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }
}
