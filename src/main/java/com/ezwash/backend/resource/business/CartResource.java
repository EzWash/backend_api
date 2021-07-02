package com.ezwash.backend.resource.business;

public class CartResource {
    private Long Id;
    private Long cartId;

    public Long getId() {
        return Id;
    }

    public CartResource setId(Long id) {
        Id = id;
        return this;
    }

    public Long getCartId() {
        return cartId;
    }

    public CartResource setCartId(Long cartId) {
        this.cartId = cartId;
        return this;
    }
}
