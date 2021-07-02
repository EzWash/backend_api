package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.business.Cart;
import com.ezwash.backend.domain.model.business.Service;

import java.util.List;

public interface CartService {
    List<Service> getServiceByCartId(Long cartId);
    Cart addServiceToCart(Long cartId, Long serviceId);
    Cart deleteServiceFromCart(Long cartId, Long serviceId);
}
